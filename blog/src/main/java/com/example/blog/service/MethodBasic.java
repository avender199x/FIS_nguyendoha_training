package com.example.blog.service;

import com.example.blog.dto.UserDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface MethodBasic<T, DTO, ID> {
    public Page<DTO> findAll(Pageable pageable);

    public DTO findById(Long id);

    public DTO save(T t);

    public DTO update(Long id, T t);

    public void delete(Long id);
}
