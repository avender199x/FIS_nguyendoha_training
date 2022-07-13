package com.example.blog.controller;

import com.example.blog.dto.Req.PostsDtoReq;
import com.example.blog.dto.Res.PostsInfoDto;
import com.example.blog.service.PostsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;

@RestController
public class postsController {
    private PostsService postsService;

    @Autowired
    public postsController(PostsService postsService) {
        this.postsService = postsService;
    }

    @GetMapping("/{number}/{size}")
    public Page<PostsInfoDto> findAll(@PathVariable(name = "number") Integer n, @PathVariable(name = "size") Integer s) {
        return postsService.findAll(PageRequest.of(n, s));
    }

    @GetMapping("/{id}")
    public PostsInfoDto findById(@PathVariable(name = "id") Long id) {
        return postsService.findById(id);
    }

    @PutMapping("/")
    public PostsInfoDto save(@RequestBody PostsDtoReq posts) {
        return postsService.save(posts);
    }

    @PostMapping("/{id}")
    public PostsInfoDto update(@PathVariable(name = "id") Long id, PostsDtoReq posts) {
        return postsService.update(id, posts);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable(name = "id") Long id) {
        postsService.delete(id);
    }
}
