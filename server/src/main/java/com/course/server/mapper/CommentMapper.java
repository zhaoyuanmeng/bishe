package com.course.server.mapper;

import com.course.server.domain.Comment;

import java.util.List;

public interface CommentMapper {

    List<Comment> findCommentByCourseId(String courseId);

    Integer insert(Comment comment);

    List<Comment> findAll();

    Integer deleteById(String id);

    List<Comment> findCommentByName(String name);
}
