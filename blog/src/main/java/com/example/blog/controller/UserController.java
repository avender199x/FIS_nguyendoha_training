package com.example.blog.controller;

import com.example.blog.dto.Res.UserDto;
import com.example.blog.entity.User;
import com.example.blog.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/{PageNumber}/{PageSize}")
    public Page<UserDto> findAll(@PathVariable(name = "PageNumber") Integer n, @PathVariable("PageSize") Integer s) {
        return userService.findAll(PageRequest.of(n, s));
    }

    @GetMapping("/{id}")
    public UserDto findById(@PathVariable(name = "id") Long id) {
        return userService.findById(id);
    }

    @PutMapping("/")
    public UserDto save(@RequestBody User user) {
        return userService.save(user);
    }

    @PostMapping("/{id}")
    public UserDto update(@PathVariable(name = "id") Long id, @RequestBody User user) {
        return userService.update(id, user);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable(name = "id") Long id) {
        userService.delete(id);
    }

}
