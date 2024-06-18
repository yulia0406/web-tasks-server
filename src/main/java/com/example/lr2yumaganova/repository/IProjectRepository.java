package com.example.lr2yumaganova.repository;

import com.example.lr2yumaganova.model.Project;
import com.example.lr2yumaganova.model.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface IProjectRepository extends JpaRepository<Project, Long> {
    List<Project> findByProjectDescriptionContainingIgnoreCaseOrProjectNameContainingIgnoreCase(String searchQuery1, String searchQuery);
}
