package com.example.blog.service.imp;

import com.example.blog.dto.UserDto;
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
    private UserRepository repository;

    @Autowired
    public UserServiceImp(UserRepository repository) {
        this.repository = repository;
    }

    @Override
    public Page<UserDto> findAll(Pageable pageable) {
        return repository.findAll(pageable).map(UserDto::fromEntity);
    }

    @Override
    public UserDto findById(Long id) {
        return repository.findById(id).map(UserDto::fromEntity).orElseThrow(
                () -> {
                    throw new UserNotFoundException("User not found");
                }
        );
    }

    @Override
    public UserDto save(User user) {
        if (CheckUser.check(user)) {
            return UserDto.fromEntity(repository.save(user));
        } else {
            log.error("\n -- Save false -- " + "\nTime : " + LocalDateTime.now() + "\n User : " + user);
            throw new UserError("Error save");
        }
    }

    @Override
    public UserDto update(Long id, User user) {
        Optional<User> update = repository.findById(id);
        if (update.isPresent() && CheckUser.check(user)) {
            update.get().setName(user.getName());
            update.get().setModifiedAt(LocalDateTime.now());
            update.get().setPassword(user.getPassword());
            update.get().setPhone(user.getPhone());
            update.get().setStatus(update.get().getStatus());
            return UserDto.fromEntity(repository.save(update.get()));
        } else {
            log.error("\n -- update false -- " + "\nTime : "
                    + LocalDateTime.now() + "\nUserId : " + id + "\nUser : " + user);
            throw new UserError("Error update");
        }
    }

    @Override
    public void delete(Long id) {
        repository.deleteById(id);
    }
}
