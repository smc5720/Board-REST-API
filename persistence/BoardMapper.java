package com.mincheol.fullstack.persistence;

import com.mincheol.fullstack.domain.BoardVO;
import org.apache.ibatis.annotations.*;

import java.util.List;


// Control 레이어에서 request를 받은 뒤, DB를 조회해야 하므로 DB 처리를 담당할 persistence 레이어를 생성한다.
// persistence 패키지를 생성하고 BoardMapper 인터페이스를 생성한다.
@Mapper
// @Mapper를 사용하면 스프링이 구동 시 해당 인터페이스가 인스턴스로 스프링에 등록된다.
// 문법적으로 인터페이스가 인스턴스를 생성하는게 맞지 않지만, 내부적으로 익명 클래스 인스턴스로 생성된다고 이해하면 되겠다.
public interface BoardMapper {
    // xml 구문없이 자바에서 MyBatis 구문을 사용하기 위해서는 <script> 태그를 사용하면 된다.
    @Insert({"<script>",
            "INSERT INTO board(title, content)",
            "VALUES(#{title}, #{content})",
            "</script>"})
    // insertBoard 메서드는 입력을 객체로 받고 출력은 int로 리턴한다.
    // 입력이 성공하면 1, 실패하면 0이 리턴되기 때문이다.
    int insertBoard(BoardVO boardVO);

    // page_number와 page_size 두 개의 파라미터 모두 null이 아니면 offset을 계산해서 페이징을 수행한다.
    // 둘 중 하나라도 값이 null이라면 전체 목록을 리턴한다.
    // Mapper 쿼리문에는 if 구문에서 offset과 page_size 모두 null이 아닐 경우에만 LIMIT 구문을 실행한다.
    @Select({"<script>",
            "SELECT * from board",
            "order by id desc",
            "<if test='offset != null and page_size != null'>",
            "LIMIT #{offset}, #{page_size}",
            "</if>",
            "</script>"})
    List<BoardVO> findBoard(Integer offset, Integer page_size);

    // id를 입력받아 하나의 board를 가져오는 API이다.
    @Select({"<script>",
            "SELECT * from board",
            "where id = #{id}",
            "</script>"})
    BoardVO findOneBoard(int id);

    // 게시판 갯수를 리턴하는 API이다.
    @Select({"<script>",
            "SELECT count(*) from board",
            "</script>"})
    Integer countBoard();

    // board를 수정하는 API를 만든다.
    // update 되는 property의 유무를 판단하기 위해 if 구문으로 null 여부를 체크한다.
    // update 구문을 만들때 'set key1 = value1, key2 = value2,' 이런 식으로 만들면 맨 뒤 콤마는 없어져야 한다.
    // 따라서 trim 구문을 사용하여 prefix를 set으로 설정하여 맨 앞에 set을 위치시키고, suffixOverrids=","를 사용하여 맨 뒤 쉼표를 제거한다.
    @Update({"<script>",
            "UPDATE board",
            "<trim prefix='set' suffixOverrides=','>",
            "<if test='title != null'>title = #{title},</if>",
            "<if test='content != null'>content = #{content},</if>",
            "</trim>",
            "WHERE id = #{id}",
            "</script>"})
    int updateBoard(BoardVO boardVO);

    // 삭제 HTTP 메소드는 DELETE를 사용한다.
    // DELETE도 GET과 마찬가지로 request 시에 body가 없다.
    // 따라서 Query Parameter나 URI Parameter 방식으로 데이터를 전달해야 한다.
    @Delete({"<script>",
            "DELETE FROM board",
            "WHERE id = #{id}",
            "</script>"})
    int deleteBoard(int id);
}