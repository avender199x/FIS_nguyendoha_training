package com.example.blog.until;

import com.example.blog.dto.Req.CommentDtoReq;
import com.example.blog.exception.CommentCharactersException;

public final class CheckComment {
    public static Boolean check(CommentDtoReq commentDtoReq) {
        Boolean checkComment = commentDtoReq.getComment().length() > 5 && commentDtoReq.getComment().length() < 100;
        if (!checkComment) {
            throw new CommentCharactersException("comment > 5 characters and comment < 100 characters");
        }
        return true;
    }
}
