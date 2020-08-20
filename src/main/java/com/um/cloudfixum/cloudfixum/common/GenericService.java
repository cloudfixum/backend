package com.um.cloudfixum.cloudfixum.common;

import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.io.Serializable;
import java.util.List;

public interface GenericService<T extends Serializable> {

    ResponseEntity<T> create(T entity);

    ResponseEntity<T> getById(Long id);

    ResponseEntity<List<T>> getAll();

    ResponseEntity<T> update(T entity);

    ResponseEntity<HttpStatus> delete(Long id);

    ResponseEntity<List<T>> findServiceByPage(int page);
}
