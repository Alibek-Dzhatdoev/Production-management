package com.novopacksv.production.service;

import com.novopacksv.production.exception.ResourceNotFoundException;
import com.novopacksv.production.model.BaseEntity;

import java.util.List;

public interface BaseEntityService<T extends BaseEntity> {

    T findById(Long id) throws ResourceNotFoundException;

    List<T> findAll();

    T save(T t);

    T update(T t) throws ResourceNotFoundException;

    void delete(Long id) throws ResourceNotFoundException;

}
