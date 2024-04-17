package com.es.iesmz.FitGoal.service;

import com.es.iesmz.FitGoal.domain.Tag;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;
import java.util.Set;

public interface TagService {

    Set<Tag> findAll();
    Optional<Tag> findByName(String name);
    Optional<Tag> findById(Long id);
    Set<Tag> findByExercice(int id);
    Tag addTag(Tag tag);
    Tag modifyTag(Long id, Tag newTag);
    void deleteTag(Long id);


}