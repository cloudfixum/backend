package com.um.cloudfixum.cloudfixum.common;

import org.springframework.data.domain.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.io.Serializable;
import java.util.Arrays;
import java.util.LinkedList;
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

        String linkPrevious = first ? "null" : request.getRequestURL() + "?page=" + (page - 1) + "&size=" + size;
        String linkNext = last ? "null" : request.getRequestURL() + "?page=" + (page + 1) + "&size=" + size;

        responseHeaders.add("CurrentPage", String.valueOf(page));
        responseHeaders.add("Size", String.valueOf(size));
        responseHeaders.add("TotalRecords", String.valueOf(getRepository().findAll(PageRequest.of(page, size)).getTotalElements()));
        responseHeaders.add("TotalPages", String.valueOf(getRepository().findAll(PageRequest.of(page, size)).getTotalPages()));
        responseHeaders.add("Prev",  linkPrevious);
        responseHeaders.add("Next",  linkNext);

        List<T> responseBody = getRepository().findAll(PageRequest.of(page, size, Sort.by("id").descending())).get().collect(Collectors.toList());

        return new ResponseEntity<>(responseBody, responseHeaders, HttpStatus.OK);

    }
    @Override
    public ResponseEntity<List<T>> findByPageFiltered(int page, int size, HttpServletRequest request, List<T> filteredList) {
        HttpHeaders responseHeaders = new HttpHeaders();

        Pageable pageable = PageRequest.of(page, size, Sort.by("id").descending());

        int start_index = (int) pageable.getOffset();
        int end_index = (int) (((pageable.getOffset() + pageable.getPageSize()) > filteredList.size()) ? filteredList.size() : (pageable.getOffset() + pageable.getPageSize()));

        List<T> filtered_sublist = new LinkedList<>();

        try {
            filtered_sublist = filteredList.subList(start_index,end_index);
        }catch (IllegalArgumentException ignored){}

        Page<T> paged_list = new PageImpl<>(filtered_sublist, pageable, filteredList.size());

        boolean first = page == 0;
        boolean last = (page + 1) == paged_list.getTotalPages();

        String[] query_split = request.getQueryString().split("(?:pag|siz)e=[0-99]|&");
        String final_split = "";
        for (String i:query_split) {
            if (!i.equals(" ") && !i.equals("")){
                final_split += "&" + i;
            }
        }

        String linkPrevious = first ? "null" : request.getRequestURL() + "?page=" + (page - 1) + "&size=" + size + final_split;
        String linkNext = last ? "null" : request.getRequestURL() + "?page=" + (page + 1) + "&size=" + size + final_split;


        responseHeaders.add("CurrentPage", String.valueOf(page));
        responseHeaders.add("Size", String.valueOf(size));
        responseHeaders.add("TotalRecords", String.valueOf(paged_list.getTotalElements()));
        responseHeaders.add("TotalPages", String.valueOf(paged_list.getTotalPages()));
        responseHeaders.add("Prev",  linkPrevious);
        responseHeaders.add("Next",  linkNext);

        List<T> responseBody = paged_list.get().collect(Collectors.toList());
        return new ResponseEntity<>(responseBody, responseHeaders, HttpStatus.OK);

    }

    public abstract JpaRepository<T, Long> getRepository();
}
