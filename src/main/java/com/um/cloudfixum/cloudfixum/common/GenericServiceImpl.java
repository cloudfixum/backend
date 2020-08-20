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
    public ResponseEntity<List<T>> findServiceByPage(int page,HttpServletRequest request) {
        HttpHeaders responseHeaders = new HttpHeaders();
        int previus = page - 1;
        int next = page + 1;
        if (page > 0 && (getRepository().findAll(PageRequest.of(page, 2)).getTotalPages() - 1) > page) {
            responseHeaders.add("prev", getPathURL(request,6) + previus);
            responseHeaders.add("next", getPathURL(request,6) + next);
        } else if (page == 0 && (getRepository().findAll(PageRequest.of(page, 2)).getTotalPages() - 1) > page) {
            previus = 0;
            responseHeaders.add("prev", getPathURL(request,6) + previus);
            responseHeaders.add("next", getPathURL(request,6) + next);
        } else {
            next = page;
            responseHeaders.add("prev", getPathURL(request,6) + previus);
            responseHeaders.add("next", getPathURL(request,6) + next);
        }
        responseHeaders.add("numberOfElements", String.valueOf(getRepository().findAll(PageRequest.of(page,2)).getTotalElements()));
        ResponseEntity<List<T>> responseEntity = new ResponseEntity<>(getRepository().findAll(PageRequest.of(page, 2)).get().collect(Collectors.toList()), responseHeaders, HttpStatus.OK);
        return responseEntity;
    }
    public String getPathURL(HttpServletRequest request,int bar_number){
        String url = String.valueOf(request.getRequestURL());
        String final_url = "";
        int counter = 0;
        for (int i = 0; i<url.length();i++){
            final_url = final_url+""+String.valueOf(url.charAt(i));
            if (url.charAt(i) == '/'){
                counter += 1;
            }
            if (counter == bar_number){
                break;
            }
        }
        return final_url;
    }
    public abstract JpaRepository<T, Long> getRepository();
}
