package com.example.blog.service.imp;


import com.example.blog.dto.PostsInfoDto;
import com.example.blog.entity.Posts;
import com.example.blog.exception.PostsError;
import com.example.blog.exception.PostsNotFoundException;
import com.example.blog.repository.PostsRepository;
import com.example.blog.service.PostsService;
import com.example.blog.until.CheckPosts;
import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@Slf4j
public class PostsServiceImp implements PostsService {
    private PostsRepository postsRepository;

    @Autowired
    public PostsServiceImp(PostsRepository postsRepository) {
        this.postsRepository = postsRepository;
    }

    @Override
    public Page<PostsInfoDto> findAll(Pageable pageable) {
        return postsRepository.findAll(pageable).map(PostsInfoDto::fromEntity);
    }

    @Override
    public PostsInfoDto findById(Long id) {
        return postsRepository.findById(id).map(PostsInfoDto::fromEntity).orElseThrow(() -> {
            throw new PostsNotFoundException("Posts not found");
        });
    }

    @Override
    public PostsInfoDto save(Posts posts) {
        if (CheckPosts.check(posts)) {
            return PostsInfoDto.fromEntity(postsRepository.save(posts));
        } else {
            log.error("\n-- Save Posts False --" + "\nTime : " + LocalDateTime.now() + "\nPosts : " + posts);
            throw new PostsError("Save Posts False , check again");
        }
    }

    @Override
    public PostsInfoDto update(Long id, Posts posts) {
        return null;
    }

    @Override
    public void delete(Long id) {

    }
}
