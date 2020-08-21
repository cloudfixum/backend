package com.um.cloudfixum.cloudfixum.common;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.io.Serializable;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public abstract class GenericServiceImpl<T extends Identificable & Serializable> implements GenericService<T> {

    @Override
    public ResponseEntity<T> create(T entity) {
        if (entity.getId() != null && getRepository().findById(entity.getId()).isPresent()) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        getRepository().save(entity);
        return new ResponseEntity<>(entity, HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<T> getById(Long id) {
        if (!getRepository().findById(id).isPresent())
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(getRepository().findById(id).get(), HttpStatus.OK);
    }


    // We probably will modify this method's behavior to paginate entities.
    @Override
    public ResponseEntity<List<T>> getAll() {
        List<T> responseList = getRepository().findAll()
                .stream()
                .sorted((e1, e2) -> e2.getId().compareTo(e1.getId()))       // List sorted desc. by entity's ID
                .collect(Collectors.toList());

        return new ResponseEntity<>(responseList, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<T> update(T entity) {
        if (!getRepository().findById(entity.getId()).isPresent())
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        getRepository().save(entity);
        return new ResponseEntity<>(entity, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<HttpStatus> delete(Long id) {
        if (!getRepository().findById(id).isPresent()) return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        getRepository().deleteById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @Override
    public ResponseEntity<List<T>> findByPage(int page, int size, HttpServletRequest request) {
        HttpHeaders responseHeaders = new HttpHeaders();

        boolean first = page == 0;
        boolean last = (page + 1) == getRepository().findAll(PageRequest.of(page, size)).getTotalPages();

        int previous = first ? 0 : page - 1;
        int next = last? page : page + 1;

        responseHeaders.add("currentPage", String.valueOf(page));
        responseHeaders.add("size", String.valueOf(size));
        responseHeaders.add("totalRecords", String.valueOf(getRepository().findAll(PageRequest.of(page, size)).getTotalElements()));
        responseHeaders.add("totalPages", String.valueOf(getRepository().findAll(PageRequest.of(page, size)).getTotalPages()));
        responseHeaders.add("prev",  request.getRequestURL() + "?page=" + previous + "&size=" + size);
        responseHeaders.add("next",  request.getRequestURL() + "?page=" + next + "&size=" + size);


        return new ResponseEntity<>(getRepository().findAll(PageRequest.of(page, size)).get().collect(Collectors.toList()), responseHeaders, HttpStatus.OK);

    }

    public abstract JpaRepository<T, Long> getRepository();
}
