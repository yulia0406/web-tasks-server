package com.example.lr2yumaganova.service;

import com.example.lr2yumaganova.POJO.ProjectPojo;
import com.example.lr2yumaganova.model.Project;

import java.util.Date;
import java.util.List;
import java.util.Map;


public interface IProjectService {
    ProjectPojo getProjectById(long projectId);
    ProjectPojo create(ProjectPojo project);
    ProjectPojo update(ProjectPojo projectPojo);
    void deleteProjectById(long projectId);
    Map<Long, Long> getTasksCountInProjects();
    List<ProjectPojo> getProjects(String searchText);
  /*  Project create(Project project);
    void update(Project project);
    Project getProjectById(int projectId);
    int deleteProjectById(int projectId);
    List<Project> getAllProjects();
    List<Project> getFiltrationListProjects(Date date_start, Date date_end);*/
}
