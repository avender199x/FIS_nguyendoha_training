package com.example.blog.until;

import com.example.blog.dto.Req.CommentDtoReq;

public final class CheckComment {
    public static Boolean check(CommentDtoReq commentDtoReq) {
        Boolean checkComment = commentDtoReq.getComment().length() > 5 && commentDtoReq.getComment().length() < 100;
        return checkComment;
    }
}
