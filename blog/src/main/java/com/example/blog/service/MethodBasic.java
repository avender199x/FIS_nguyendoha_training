package com.example.blog.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface MethodBasic<DtoReq, DtoRes, ID> {
    public Page<DtoRes> findAll(Pageable pageable);

    public DtoRes findById(Long id);

    public DtoRes save(DtoReq t);

    public DtoRes update(Long id, DtoReq t);

    public void delete(Long id);
}
