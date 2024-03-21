package com.es.iesmz.FitGoal.service;

import com.es.iesmz.FitGoal.domain.Tag;

import java.util.Set;

public interface TagService {
    Set<Tag> findAll();
    Set<Tag> findByName(String name);
}