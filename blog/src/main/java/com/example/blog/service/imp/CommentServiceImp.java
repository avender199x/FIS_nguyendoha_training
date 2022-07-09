package com.example.blog.service.imp;

import com.example.blog.dto.Req.CommentDtoReq;
import com.example.blog.dto.Res.PostsInfoDto;
import com.example.blog.entity.Comment;
import com.example.blog.entity.Posts;
import com.example.blog.entity.User;
import com.example.blog.exception.CommentError;
import com.example.blog.exception.CommentNotFound;
import com.example.blog.exception.PostsError;
import com.example.blog.repository.CommentRepository;
import com.example.blog.repository.PostsRepository;
import com.example.blog.repository.UserRepository;
import com.example.blog.service.CommentService;
import com.example.blog.until.CheckComment;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
@Slf4j
public class CommentServiceImp implements CommentService {
    private UserRepository userRepository;
    private PostsRepository postsRepository;
    private CommentRepository commentRepository;

    @Autowired
    public CommentServiceImp(UserRepository userRepository, PostsRepository postsRepository
            , CommentRepository commentRepository) {
        this.userRepository = userRepository;
        this.postsRepository = postsRepository;
        this.commentRepository = commentRepository;
    }
    @Transactional
    @Override
    public PostsInfoDto save(CommentDtoReq commentDtoReq) {
        User user = userRepository.findById(commentDtoReq.getUser()).orElseThrow(() -> {
            throw new CommentError("user not found");
        });
        Posts posts = postsRepository.findById(commentDtoReq.getPosts()).orElseThrow(() -> {
            throw new CommentError("posts not found");
        });
        if (CheckComment.check(commentDtoReq)) {
            Comment save = Comment.builder()
                    .posts(posts)
                    .comment(commentDtoReq.getComment())
                    .user(user)
                    .createdAt(LocalDateTime.now())
                    .modifiedAt(commentDtoReq.getModifiedAt())
                    .build();
            commentRepository.save(save);
            return PostsInfoDto.fromEntity(posts);
        } else {
            log.error("\n-- save Comment false --" + "\nTime : " + LocalDateTime.now() + "\nComment : " + commentDtoReq);
            throw new PostsError("save comment false , check again");
        }
    }
    @Transactional
    @Override
    public PostsInfoDto update(Long id, CommentDtoReq commentDtoReq) {
        Comment update = commentRepository.findById(id).orElseThrow(() -> {
            throw new CommentNotFound("comment not found");
        });
        User user = userRepository.findById(commentDtoReq.getUser()).orElseThrow(() -> {
            throw new CommentError("user not found");
        });
        Posts posts = postsRepository.findById(commentDtoReq.getPosts()).orElseThrow(() -> {
            throw new CommentError("posts not found");
        });
        if (CheckComment.check(commentDtoReq)) {
            update.setComment(commentDtoReq.getComment());
            update.setModifiedAt(LocalDateTime.now());
            update.setUser(user);
            update.setPosts(posts);
            update.setCreatedAt(commentDtoReq.getCreatedAt());
            commentRepository.save(update);
            return PostsInfoDto.fromEntity(posts);
        } else {
            throw new CommentError("update comment false , check again");
        }
    }

    @Transactional
    @Override
    public void delete(Long id) {
        commentRepository.findById(id).orElseThrow(() -> {
            throw new CommentNotFound("comment not found");
        });
        commentRepository.deleteById(id);
    }
}
