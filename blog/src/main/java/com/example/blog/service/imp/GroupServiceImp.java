package com.example.blog.service.imp;

import com.example.blog.dto.Req.GroupDtoReq;
import com.example.blog.dto.Res.GroupDto;
import com.example.blog.entity.Group;
import com.example.blog.exception.GroupError;
import com.example.blog.exception.GroupNotFoundException;
import com.example.blog.repository.GroupRepository;
import com.example.blog.repository.PostsRepository;
import com.example.blog.repository.UserRepository;
import com.example.blog.service.GroupService;
import com.example.blog.until.CheckGroup;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@Slf4j
public class GroupServiceImp implements GroupService {
    private GroupRepository groupRepository;
    private UserRepository userRepository;
    private PostsRepository postsRepository;

    @Autowired
    public GroupServiceImp(GroupRepository groupRepository, UserRepository userRepository,
                           PostsRepository postsRepository) {
        this.groupRepository = groupRepository;
        this.userRepository = userRepository;
        this.postsRepository = postsRepository;
    }


    @Override
    public Page<GroupDto> findAll(Pageable pageable) {
        return groupRepository.findAll(pageable).map(GroupDto::fromEntity);
    }

    @Override
    public GroupDto findById(Long id) {
        return groupRepository.findById(id).map(GroupDto::fromEntity).orElseThrow(() -> {
            throw new GroupNotFoundException("group not found");
        });
    }

    @Override
    public GroupDto save(GroupDtoReq groupDtoReq) {
        if (CheckGroup.check(groupDtoReq)) {
            Group group = Group.builder()
                    .createdAt(LocalDateTime.now())
                    .modifiedAt(groupDtoReq.getModifiedAt())
                    .groupName(groupDtoReq.getGroupName())
                    .build();
            return GroupDto.fromEntity(groupRepository.save(group));
        } else {
            throw new GroupError("Save Group false , check again");
        }
    }

    @Override
    public GroupDto update(Long id, GroupDtoReq groupDtoReq) {
        return null;
    }

    @Override
    public void delete(Long id) {

    }
}