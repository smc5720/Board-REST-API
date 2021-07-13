package com.mincheol.fullstack.controller;

import com.mincheol.fullstack.domain.BoardVO;
import com.mincheol.fullstack.domain.ResultVO;
import com.mincheol.fullstack.persistence.BoardMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.lang.Nullable;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api")
// @Autowired로 주입받는 경우 상호의존성 에러가 발생할 수 있으므로 생성자 주입을 받아야 한다.
// 따라서 boardMapper를 final로 만들고 생성자 주입은 @RequiredArgsConstructor로 한다.
public class BoardController {
    private final BoardMapper boardMapper;

    // board 테이블을 생성할 것이므로 post 매핑을 사용했다.
    // RestController 바로 아래에 '/api'가 매핑되어 있으므로 전체 매핑 URL은 '/api/board'이다.
    @PostMapping("/board")
    public ResultVO addBoard(@RequestBody BoardVO boardVO) {
        // 요청은 JSON으로 받고 결과는 ResultVO 타입의 JSON을 리턴한다.
        int result = boardMapper.insertBoard(boardVO);
        if (result > 0) {
            return new ResultVO(0, "success");
        } else {
            return new ResultVO(100, "fail");
        }
    }

    @GetMapping("/board/{id}")
    public BoardVO findOne(@PathVariable int id) {
        return boardMapper.findOneBoard(id);
    }

    @GetMapping("/boards")
    public List<BoardVO> findAllBoard(@RequestParam @Nullable Integer page_number, @RequestParam @Nullable Integer page_size) {
        Integer offset = null;
        if (page_number != null && page_size != null) {
            offset = (page_number - 1) * page_size;
        }
        return boardMapper.findBoard(offset, page_size);
    }

    @GetMapping("/board/count")
    public Integer countBoard() {
        return boardMapper.countBoard();
    }

    // 프로토콜 URL은 '/api/board'이고 메서드는 PUT, 입력과 출력은 JSON이다.
    // 수정 메소드로 POST를 사용해도 잘못된 것은 아니지만, 관례적으로 PUT을 사용한다.
    @PutMapping("/board")
    public ResultVO modifyBoard(@RequestBody BoardVO boardVO) {
        int result = boardMapper.updateBoard(boardVO);
        if ( result > 0) {
            return new ResultVO(0, "success");
        } else {
            return new ResultVO(100, "fail");
        }
    }

    // 호출 URL은 '/api/board'이고 메소드는 DELETE, 입력 파라메터는 Query Parameter로 받는다.
    // DELETE 메소드는 GET 메소드와 동일하게 request의 body가 존재하지 않는다.
    // 따라서 URL의 Query Parameter로 받아야 한다는 것에 유념하자.
    @DeleteMapping("/board")
    public ResultVO removeBoard(@RequestParam int id) {
        int result = boardMapper.deleteBoard(id);
        if (result > 0) {
            return new ResultVO(0, "success");
        } else {
            return new ResultVO(100, "fail");
        }
    }
}