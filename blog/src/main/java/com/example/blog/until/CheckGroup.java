package com.example.blog.until;

import com.example.blog.dto.Req.GroupDtoReq;
import com.example.blog.exception.GroupNameException;

public final class CheckGroup {
    public static Boolean check(GroupDtoReq groupDtoReq) {
        Boolean checkGroupName = groupDtoReq.getGroupName().length() > 5 && groupDtoReq.getGroupName().length() < 60;
        if (!checkGroupName) {
            throw new GroupNameException("group name > 5 characters and group name < 60 characters");
        }
        return true;
    }
}
