package com.es.iesmz.FitGoal.service;

import com.es.iesmz.FitGoal.domain.Tag;

import java.util.Optional;
import java.util.Set;

public interface TagService {
    Set<Tag> findAll();
    Optional<Tag> findByName(String name);
}