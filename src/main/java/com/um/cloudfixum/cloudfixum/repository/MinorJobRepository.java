package com.um.cloudfixum.cloudfixum.repository;
import com.um.cloudfixum.cloudfixum.model.MinorJob;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MinorJobRepository extends JpaRepository <MinorJob, Long> {
    List<MinorJob> findByTitleContainingOrDescriptionContaining(String title_query, String description_query);
    //List<MinorJob> findByCategoryName(String name);
    //List<MinorJob> findByCategorySuperCategory(String super_category);
}
