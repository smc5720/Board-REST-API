package com.mincheol.fullstack.persistence;

import com.mincheol.fullstack.domain.CommentVO;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
// 게시판 하단에 붙일 댓글 API를 만든다.
// 댓글 상세보기, 댓글 목록 보기, 댓글 생성, 댓글 수정, 댓글 삭제 이렇게 5개의 API를 만든다.
public interface CommentMapper {
    // insert 후에 자동으로 생성된 id를 돌려받고 싶을 때
    // @Options(useGeneratedKeys = true, keyProperty = “id”)를 사용하면 commentVO 객체의 id에 해당 값이 들어간다.
    @Options(useGeneratedKeys = true, keyProperty = "id")
    @Insert({"<script>",
            "INSERT INTO comment(content, board_id)",
            "VALUES(#{content}, #{board_id})",
            "</script>"})
    int insertComment(CommentVO commentVO);

    @Select({"<script>",
            "SELECT * from comment",
            "where id = #{id}",
            "</script>"})
    CommentVO findOneComment(int id);

    @Select({"<script>",
            "SELECT * from comment",
            "order by id desc",
            "</script>"})
    List<CommentVO> findComment(int board_id);

    @Update({"<script>",
            "UPDATE comment",
            "set content = #{content}",
            "WHERE id = #{id}",
            "</script>"})
    int updateComment(CommentVO commentVO);

    @Delete({"<script>",
            "DELETE FROM comment",
            "WHERE id = #{id}",
            "</script>"})
    int deleteComment(int id);
}