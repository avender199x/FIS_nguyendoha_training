package com.example.blog.service;

import com.example.blog.exception.Error;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface MethodBasic<DtoReq, DtoRes, ID> {
    public Page<DtoRes> findAll(Pageable pageable);

    public DtoRes findById(Long id);

    public DtoRes save(DtoReq dtoReq) throws Error;

    public DtoRes update(Long id, DtoReq dtoReq);

    public void delete(Long id);
}
