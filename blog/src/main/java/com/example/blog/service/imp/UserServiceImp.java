package com.example.blog.service.imp;

import com.example.blog.dto.Res.UserDto;
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
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Optional;

@Slf4j
@Service
public class UserServiceImp implements UserService {
    private final UserRepository userRepository;

    @Autowired
    public UserServiceImp(UserRepository repository) {
        this.userRepository = repository;
    }

    @Transactional(readOnly = true)
    @Override
    public Page<UserDto> findAll(Pageable pageable) {
        return userRepository.findAll(pageable).map(UserDto::fromEntity);
    }

    @Transactional(readOnly = true)
    @Override
    public UserDto findById(Long id) {
        return userRepository.findById(id).map(UserDto::fromEntity).orElseThrow(
                () -> {
                    throw new UserNotFoundException("User not found");
                }
        );
    }

    @Transactional
    @Override
    public UserDto save(User user) {
        CheckUser.check(user);
        if (userRepository.findByPhone(user.getPhone()).isEmpty()) {
            return UserDto.fromEntity(userRepository.save(user));
        } else {
            throw new UserError("Save User false , User already exists");
        }
    }

    @Transactional
    @Override
    public UserDto update(Long id, User user) {
        User update = userRepository.findById(id).orElseThrow(() -> {
            throw new UserNotFoundException("user not found");
        });
        CheckUser.check(user);
        update.setName(user.getName());
        update.setModifiedAt(LocalDateTime.now());
        update.setPassword(user.getPassword());
        update.setPhone(user.getPhone());
        update.setStatus(user.getStatus());
        return UserDto.fromEntity(userRepository.save(update));
    }

    @Transactional
    @Override
    public void delete(Long id) {
        userRepository.findById(id).orElseThrow(() -> {
            throw new UserNotFoundException("user not found");
        });
        userRepository.deleteById(id);
    }

}
