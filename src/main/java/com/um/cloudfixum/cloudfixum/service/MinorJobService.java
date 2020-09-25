package com.um.cloudfixum.cloudfixum.service;

import com.um.cloudfixum.cloudfixum.common.GenericServiceImpl;
import com.um.cloudfixum.cloudfixum.model.Category;
import com.um.cloudfixum.cloudfixum.model.MinorJob;
import com.um.cloudfixum.cloudfixum.model.ProviderUser;
import com.um.cloudfixum.cloudfixum.repository.MinorJobRepository;
import com.um.cloudfixum.cloudfixum.repository.ProviderUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.MethodArgumentNotValidException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class MinorJobService extends GenericServiceImpl<MinorJob> {

    private final MinorJobRepository minorJobRepository;
    private final ProviderUserRepository providerUserRepository;

    public MinorJobService(MinorJobRepository minorJobRepository, ProviderUserRepository providerUserRepository) {
        this.minorJobRepository = minorJobRepository;
        this.providerUserRepository = providerUserRepository;
    }

    @Override
    public ResponseEntity<MinorJob> create(MinorJob job) {

        Optional<ProviderUser> serviceProvider = providerUserRepository.findById(job.getServiceProvider().getId());
        if (!serviceProvider.isPresent()) return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

        for (MinorJob minorJob : serviceProvider.get().getServiceList()) {
            if (minorJob.equals(job)) {
                return new ResponseEntity<>(HttpStatus.CONFLICT);
            }
        }

        job.setServiceProvider(serviceProvider.get());
        return super.create(job);
    }

    public ResponseEntity<List<MinorJob>> filterByTitleOrDescription(String query_title, String query_description) {
        if (minorJobRepository.findByTitleContainingOrDescriptionContaining(query_title, query_description).size() == 0)
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        return new ResponseEntity<>(minorJobRepository.findByTitleContainingOrDescriptionContaining(query_title, query_description), HttpStatus.OK);
    }

    public ResponseEntity<List<MinorJob>> filterBySubCategory(String query) { //2°
        List<MinorJob> minorJobList = minorJobRepository.findAll().stream().filter(e -> e.getCategory().getName().equalsIgnoreCase(query)).collect(Collectors.toList());
        if (minorJobList.size() == 0) return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        return new ResponseEntity<>(minorJobList, HttpStatus.OK);
    }

    public ResponseEntity<List<MinorJob>> filterBySuperCategory(String query) {
        List<MinorJob> minorJobList = minorJobRepository.findAll().stream().filter(e -> e.getCategory().getSuperCategory().equalsIgnoreCase(query)).collect(Collectors.toList());
        if (minorJobList.size() == 0) return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        return new ResponseEntity<>(minorJobList, HttpStatus.OK);
    }

    public ResponseEntity<List<Category>> filterBySuperCategoryAux(String query) { //1° //Forma cuando tenemos 3 vistas. Devuelve lista de subcategorias de una supercategoria.
        List<Category> categoryList = new ArrayList<>();
        for (Category i : Category.values()) {
            if (i.getSuperCategory().equalsIgnoreCase(query)) {
                categoryList.add(i);
            }
        }
        if (categoryList.isEmpty()) return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        return new ResponseEntity<>(categoryList, HttpStatus.OK);
    }

    //estos 3 filtros deben retornar joblist sin responseEntity
    public ResponseEntity<List<MinorJob>> applyFilter(String query_title, String query_subcategory, String query_supercategory) {
        boolean title_or_description_exists = query_title != null;
        boolean subcategory_exists = query_subcategory != null;
        boolean supercategory_exists = query_supercategory != null;
        if (title_or_description_exists){
            
        }

    }
    //buscar logica para verificar el booleano que este en true, llamar al filtro que corresponda

    @Override
    public JpaRepository<MinorJob, Long> getRepository() {
        return minorJobRepository;
    }
}
