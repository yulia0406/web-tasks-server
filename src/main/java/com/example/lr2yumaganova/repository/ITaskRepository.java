package com.example.lr2yumaganova.repository;

import com.example.lr2yumaganova.POJO.ProjectPojo;
import com.example.lr2yumaganova.POJO.TaskPojo;
import com.example.lr2yumaganova.model.Project;
import com.example.lr2yumaganova.model.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ITaskRepository extends JpaRepository<Task, Long> {
    Task findByProjectAndId(Project project, Long id);
    List<Task> findByProjectIdAndCompletedIsTrue(Long project);

}
