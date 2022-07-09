package com.example.blog.service.imp;


import com.example.blog.dto.Res.PostsInfoDto;
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
        Posts update = postsRepository.findById(id).orElseThrow(() -> {
            throw new PostsNotFoundException("Posts not found");
        });
        if (CheckPosts.check(posts)) {
            update.setPosts(posts.getPosts());
            update.setModifiedAt(LocalDateTime.now());
            update.setTitle(posts.getTitle());
            update.setModifiedAt(posts.getCreatedAt());
            return PostsInfoDto.fromEntity(postsRepository.save(update));
        } else {
            log.error("\n-- Update Posts False --" + "\nTime : "
                    + LocalDateTime.now() + "\nPostsId : " + id + "\nPosts : " + posts);
            throw new PostsError("update Posts false ,check again");
        }
    }

    @Override
    public void delete(Long id) {
        postsRepository.findById(id).orElseThrow(() -> {
            throw new PostsNotFoundException("posts not found");
        });
        postsRepository.deleteById(id);
    }

    public PostsInfoDto addComment() {
        return null;
    }
}
