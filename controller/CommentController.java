package com.mincheol.fullstack.controller;

import com.mincheol.fullstack.domain.CommentVO;
import com.mincheol.fullstack.domain.ResultVO;
import com.mincheol.fullstack.persistence.CommentMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api")
public class CommentController {
    private final CommentMapper commentMapper;

    @PostMapping("/comment")
    public CommentVO addComment(@RequestBody CommentVO commentVO) {
        commentMapper.insertComment(commentVO);
        return commentVO;
    }

    @GetMapping("/comment")
    public CommentVO findOneComment(@RequestParam Integer id) {
        return commentMapper.findOneComment(id);
    }

    @GetMapping("/comments")
    public List<CommentVO> findAllComment(@RequestParam Integer board_id) {
        List<CommentVO> comments = commentMapper.findComment(board_id);

        return comments;
    }

    @PutMapping("/comment")
    public ResultVO modifyComment(@RequestBody CommentVO commentVO) {
        int result = commentMapper.updateComment(commentVO);
        if ( result > 0) {
            return new ResultVO(0, "success");
        } else {
            return new ResultVO(100, "fail");
        }
    }

    @DeleteMapping("/comment")
    public ResultVO removeComment(@RequestParam int id) {
        int result = commentMapper.deleteComment(id);
        if (result > 0) {
            return new ResultVO(0, "success");
        } else {
            return new ResultVO(100, "fail");
        }
    }
}