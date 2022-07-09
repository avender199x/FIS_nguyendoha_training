package com.example.blog.service.imp;

import com.example.blog.dto.PostsInfoDto;
import com.example.blog.dto.UserDto;
import com.example.blog.entity.Comment;
import com.example.blog.entity.Posts;
import com.example.blog.entity.User;
import com.example.blog.exception.UserError;
import com.example.blog.exception.UserNotFoundException;
import com.example.blog.repository.UserRepository;
import com.example.blog.service.UserService;
import com.example.blog.until.CheckUser;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Slf4j
@Service
public class UserServiceImp implements UserService {
    private UserRepository userRepository;

    @Autowired
    public UserServiceImp(UserRepository repository) {
        this.userRepository = repository;
    }

    @Override
    public Page<UserDto> findAll(Pageable pageable) {
        return userRepository.findAll(pageable).map(UserDto::fromEntity);
    }

    @Override
    public UserDto findById(Long id) {
        return userRepository.findById(id).map(UserDto::fromEntity).orElseThrow(
                () -> {
                    throw new UserNotFoundException("User not found");
                }
        );
    }

    @Override
    public UserDto save(User user) {
        if (CheckUser.check(user) &&
                userRepository.findByPhone(user.getPhone()).isEmpty()) {
            return UserDto.fromEntity(userRepository.save(user));
        } else {
            log.error("\n -- Save User false -- " + "\nTime : " + LocalDateTime.now() + "\n User : " + user);
            throw new UserError("Save User false , check again");
        }
    }

    @Override
    public UserDto update(Long id, User user) {
        Optional<User> update = userRepository.findById(id);
        if (update.isPresent() && CheckUser.check(user)) {
            update.get().setName(user.getName());
            update.get().setModifiedAt(LocalDateTime.now());
            update.get().setPassword(user.getPassword());
            update.get().setPhone(user.getPhone());
            update.get().setStatus(update.get().getStatus());
            return UserDto.fromEntity(userRepository.save(update.get()));
        } else {
            log.error("\n -- update User false -- " + "\nTime : "
                    + LocalDateTime.now() + "\nUserId : " + id + "\nUser : " + user);
            throw new UserError("Update User false , check again");
        }
    }

    @Override
    public void delete(Long id) {
        userRepository.deleteById(id);
    }

}
