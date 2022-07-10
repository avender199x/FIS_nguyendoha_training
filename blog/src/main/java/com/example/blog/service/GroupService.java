package com.example.blog.service;

import com.example.blog.dto.Req.GroupDtoReq;
import com.example.blog.dto.Res.GroupDto;

public interface GroupService extends MethodBasic<GroupDtoReq, GroupDto, Long> {
    public GroupDto addUser(Long groupId, GroupDtoReq groupDtoReq);

    public GroupDto removeUserInGroup(Long groupId, GroupDtoReq groupDtoReq);
}
