package com.example.blog.service.imp;

import com.example.blog.dto.Req.GroupDtoReq;
import com.example.blog.dto.Res.GroupDto;
import com.example.blog.entity.Group;
import com.example.blog.entity.User;
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
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class GroupServiceImp implements GroupService {
    private final GroupRepository groupRepository;
    private final UserRepository userRepository;

    @Autowired
    public GroupServiceImp(GroupRepository groupRepository, UserRepository userRepository) {
        this.groupRepository = groupRepository;
        this.userRepository = userRepository;
    }

    @Transactional(readOnly = true)
    @Override
    public Page<GroupDto> findAll(Pageable pageable) {
        return groupRepository.findAll(pageable).map(GroupDto::fromEntity);
    }

    @Transactional(readOnly = true)
    @Override
    public GroupDto findById(Long id) {
        return groupRepository.findById(id).map(GroupDto::fromEntity).orElseThrow(() -> {
            throw new GroupNotFoundException("group not found");
        });
    }

    @Transactional
    @Override
    public GroupDto save(GroupDtoReq groupDtoReq) throws Error {
        CheckGroup.check(groupDtoReq);
        Group group = Group.builder()
                .createdAt(LocalDateTime.now())
                .modifiedAt(groupDtoReq.getModifiedAt())
                .groupName(groupDtoReq.getGroupName())
                .build();
        return GroupDto.fromEntity(groupRepository.save(group));
    }

    @Transactional
    @Override
    public GroupDto update(Long id, GroupDtoReq groupDtoReq) {
        Group update = groupRepository.findById(id).orElseThrow(() -> {
            throw new GroupNotFoundException("group not found");
        });
        CheckGroup.check(groupDtoReq);
        update.setGroupName(groupDtoReq.getGroupName());
        update.setCreatedAt(groupDtoReq.getCreatedAt());
        update.setModifiedAt(LocalDateTime.now());
        return GroupDto.fromEntity(groupRepository.save(update));
    }

    @Transactional
    @Override
    public void delete(Long id) {
        groupRepository.findById(id).orElseThrow(() -> {
            throw new GroupNotFoundException("group not found");
        });
        groupRepository.deleteById(id);
    }

    @Transactional
    public GroupDto addUser(Long groupId, GroupDtoReq groupDtoReq) {
        Group group = groupRepository.findById(groupId).orElseThrow(() -> {
            throw new GroupNotFoundException("group not found");
        });
        List<User> users = new ArrayList<>();
        groupDtoReq.getUsers().forEach(aLong -> {
            User user = userRepository.findById(aLong).orElseThrow(() -> {
                throw new GroupError("user not found");
            });
            users.add(user);
        });
        group.setUsers(users);
        return GroupDto.fromEntity(groupRepository.save(group));
    }

    @Transactional
    public GroupDto removeUserInGroup(Long groupId, GroupDtoReq groupDtoReq) {
        Group group = groupRepository.findById(groupId).orElseThrow(() -> {
            throw new GroupNotFoundException("group not found");
        });
        List<Group> groups = new ArrayList<>();
        groups.add(group);
        group.setUsers(userRepository.findByGroups(groups));
        groupDtoReq.getUsers().forEach(aLong -> {
            User user = userRepository.findById(aLong).orElseThrow(() -> {
                throw new GroupError("user not found");
            });
            group.getUsers().remove(user);
        });
        return GroupDto.fromEntity(groupRepository.save(group));
    }
}
