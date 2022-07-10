package com.example.blog.service.imp;


import com.example.blog.dto.Req.CommentDtoReq;
import com.example.blog.dto.Req.PostsDtoReq;
import com.example.blog.dto.Res.PostsDto;
import com.example.blog.dto.Res.PostsInfoDto;
import com.example.blog.entity.Comment;
import com.example.blog.entity.Group;
import com.example.blog.entity.Posts;
import com.example.blog.entity.User;
import com.example.blog.exception.*;
import com.example.blog.repository.CommentRepository;
import com.example.blog.repository.GroupRepository;
import com.example.blog.repository.PostsRepository;
import com.example.blog.repository.UserRepository;
import com.example.blog.service.PostsService;
import com.example.blog.until.CheckComment;
import com.example.blog.until.CheckPosts;
import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
@Slf4j
public class PostsServiceImp implements PostsService {
    private PostsRepository postsRepository;
    private UserRepository userRepository;
    private GroupRepository groupRepository;

    @Autowired
    public PostsServiceImp(PostsRepository postsRepository, UserRepository userRepository, GroupRepository groupRepository) {
        this.postsRepository = postsRepository;
        this.userRepository = userRepository;
        this.groupRepository = groupRepository;
    }

    @Transactional(readOnly = true)
    @Override
    public Page<PostsInfoDto> findAll(Pageable pageable) {
        return postsRepository.findAll(pageable).map(PostsInfoDto::fromEntity);
    }

    @Transactional(readOnly = true)
    @Override
    public PostsInfoDto findById(Long id) {
        return postsRepository.findById(id).map(PostsInfoDto::fromEntity).orElseThrow(() -> {
            throw new PostsNotFoundException("Posts not found");
        });
    }

    @Transactional
    @Override
    public PostsInfoDto save(PostsDtoReq posts) {
        User user = userRepository.findById(posts.getUser()).orElseThrow(() -> {
            throw new PostsError("user not found");
        });
        Group group = groupRepository.findById(posts.getGroup()).orElseThrow(() -> {
            throw new PostsError("group not found");
        });
        if (CheckPosts.check(posts)) {
            Posts save = Posts.builder()
                    .posts(posts.getPosts())
                    .createdAt(LocalDateTime.now())
                    .modifiedAt(posts.getModifiedAt())
                    .user(user)
                    .group(group)
                    .title(posts.getTitle())
                    .build();
            return PostsInfoDto.fromEntity(postsRepository.save(save));
        } else {
            log.error("\n-- Save Posts False --" + "\nTime : " + LocalDateTime.now() + "\nPosts : " + posts);
            throw new PostsError("Save Posts False , check again");
        }
    }

    @Transactional
    @Override
    public PostsInfoDto update(Long id, PostsDtoReq posts) {
        Posts update = postsRepository.findById(id).orElseThrow(() -> {
            throw new PostsNotFoundException("Posts not found");
        });
        User user = userRepository.findById(posts.getUser()).orElseThrow(() -> {
            throw new PostsError("user not found");
        });
        Group group = groupRepository.findById(posts.getGroup()).orElseThrow(() -> {
            throw new PostsError("group not found");
        });
        if (CheckPosts.check(posts)) {
            update.setPosts(posts.getPosts());
            update.setModifiedAt(LocalDateTime.now());
            update.setTitle(posts.getTitle());
            update.setUser(user);
            update.setGroup(group);
            update.setModifiedAt(posts.getCreatedAt());
            return PostsInfoDto.fromEntity(postsRepository.save(update));
        } else {
            log.error("\n-- Update Posts False --" + "\nTime : "
                    + LocalDateTime.now() + "\nPostsId : " + id + "\nPosts : " + posts);
            throw new PostsError("update Posts false ,check again");
        }
    }

    @Transactional
    @Override
    public void delete(Long id) {
        postsRepository.findById(id).orElseThrow(() -> {
            throw new PostsNotFoundException("posts not found");
        });
        postsRepository.deleteById(id);
    }


}
