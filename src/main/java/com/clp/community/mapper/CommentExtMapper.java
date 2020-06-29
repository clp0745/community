package com.clp.community.mapper;

import com.clp.community.model.Comment;
import com.clp.community.model.CommentExample;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface CommentExtMapper {
    int incCommentCount(Comment comment);
}