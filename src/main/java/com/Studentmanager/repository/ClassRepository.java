package com.Studentmanager.repository;

import com.Studentmanager.Model.ClassEntity;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ClassRepository extends JpaRepository<ClassEntity, Long> {
    List<ClassEntity> findByClassname(String classname);

}
