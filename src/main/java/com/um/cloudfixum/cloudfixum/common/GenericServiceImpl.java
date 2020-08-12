package com.um.cloudfixum.cloudfixum.common;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.io.Serializable;
import java.util.List;

public abstract class GenericServiceImpl<T extends Identificable & Serializable> implements GenericService<T> {
    @Override
    public ResponseEntity<T> create(T entity) {
        return null;
    }

    @Override
    public ResponseEntity<T> getById(Long id) {
        return null;
    }

    @Override
    public ResponseEntity<List<T>> getAll() {
        return null;
    }

    @Override
    public ResponseEntity<T> update(T entity) {
        return null;
    }

    @Override
    public ResponseEntity<HttpStatus> delete(Long id) {
        return null;
    }

    public abstract JpaRepository<T, Long> getRepository();
}
