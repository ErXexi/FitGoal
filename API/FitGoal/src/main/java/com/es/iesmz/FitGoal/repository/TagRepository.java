package com.es.iesmz.FitGoal.repository;

import com.es.iesmz.FitGoal.domain.Tag;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TagRepository extends CrudRepository<Tag, Long> {
}