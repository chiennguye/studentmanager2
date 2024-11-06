package com.Studentmanager.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.Studentmanager.Model.ClassEntity;
import com.Studentmanager.repository.ClassRepository;

@Service
public class ClassService {
    @Autowired
    private ClassRepository classRepository;

    public void addClass(ClassEntity classes) {
        classRepository.save(classes);

    }

    public ClassEntity getClassById(Long id) {
        Optional<ClassEntity> classOptional = classRepository.findById(id);
        return classOptional.orElseThrow(() -> new RuntimeException("Class not found"));
    }

}
