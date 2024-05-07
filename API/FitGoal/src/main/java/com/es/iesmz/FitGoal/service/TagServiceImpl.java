package com.es.iesmz.FitGoal.service;

import com.es.iesmz.FitGoal.domain.Tag;
import com.es.iesmz.FitGoal.repository.TagRepository;
import com.es.iesmz.FitGoal.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Set;

@Service
public class TagServiceImpl implements TagService {
    @Autowired
    private TagRepository tagRepository;

    @Override
    public Set<Tag> findAll() {
        return tagRepository.findAll();
    }

    @Override
    public Optional<Tag> findByName(String name) {
        return tagRepository.findByName(name);
    }

    @Override
    public Optional<Tag> findById(Long id) {
        return tagRepository.findById(id);
    }

    @Override
    public Set<Tag> findByExercice(Long id) {
        return  tagRepository.findByExercice(id);
    }

    @Override
    public Tag addTag(Tag tag) {
        return tagRepository.save(tag);
    }

    @Override
    public Tag modifyTag(Long id, Tag newTag) {
        Tag tag = tagRepository.findById(id).orElseThrow();
        newTag.setId(id);
        return tagRepository.save(newTag);
    }

    @Override
    public void deleteTag(Long id) {
        Tag tag = tagRepository.findById(id).orElseThrow();
        tagRepository.deleteById(id);
    }
}