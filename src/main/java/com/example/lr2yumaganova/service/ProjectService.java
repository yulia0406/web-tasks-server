package com.example.lr2yumaganova.service;

import com.example.lr2yumaganova.POJO.ProjectPojo;
import com.example.lr2yumaganova.POJO.TaskPojo;
import com.example.lr2yumaganova.model.Project;
import com.example.lr2yumaganova.model.Task;
import com.example.lr2yumaganova.repository.IProjectRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service

public class ProjectService implements IProjectService {
    @Autowired
    private final IProjectRepository projectRepository;
    public ProjectService(IProjectRepository projectRepository) {
        this.projectRepository = projectRepository;
    }

    public ProjectPojo getProjectById(long projectId)
    {
        Project project = projectRepository.findById(projectId)
                .orElseThrow(() -> new EntityNotFoundException("Проект с указанным ID не найден"));
        return ProjectPojo.fromEntity(project);
    }

    public ProjectPojo create(ProjectPojo project)
    {
        Project project1 = ProjectPojo.toEntity(project);
        return ProjectPojo.fromEntity(projectRepository.save(project1));
    }

    public ProjectPojo update(ProjectPojo projectPojo)
    {
        System.out.println(projectPojo.getProjectName());
        Project project = projectRepository.findById(projectPojo.getId())
                .orElseThrow(() -> new EntityNotFoundException("Проект с указанным ID не найден"));
        Project projectUpdate = ProjectPojo.toEntity(projectPojo);
        project.setProjectDescription(projectUpdate.getProjectDescription());
        project.setProjectName(projectUpdate.getProjectName());
        project.setEndDate(projectUpdate.getEndDate());
        project.setStartDate(projectUpdate.getStartDate());
        return ProjectPojo.fromEntity(projectRepository.save(project));

    }

    public void deleteProjectById(long projectId)
    {
        Project project = projectRepository.findById(projectId)
                .orElseThrow(() -> new EntityNotFoundException("Проект с указанным ID не найден"));
        projectRepository.delete(project);
    }

    public Map<Long, Long> getTasksCountInProjects() {
        List<Project> projects = projectRepository.findAll();
        Map<Long, Long> tasksCountMap = new HashMap<>();
        for (Project project : projects) {
            long openTasksCount = project.getTasks().stream().filter(task -> !task.getCompleted()).count();
            tasksCountMap.put(project.getId(), openTasksCount);
        }
        return tasksCountMap;
    }

    public List<ProjectPojo> getProjects(String searchText) {
        if (searchText != null && !searchText.isEmpty()) {
            String searchQuery = searchText.toLowerCase();
            List<ProjectPojo> projectPojos = new ArrayList<>();
            List<Project> project1 = projectRepository.findByProjectDescriptionContainingIgnoreCaseOrProjectNameContainingIgnoreCase(searchQuery, searchQuery);
            for (Project project : project1) {
                projectPojos.add(ProjectPojo.fromEntity(project));
            }
            return projectPojos;
        } else {
            List<ProjectPojo> projectPojos = new ArrayList<>();
            List<Project> project1 = projectRepository.findAll();
            for (Project project : project1) {
                projectPojos.add(ProjectPojo.fromEntity(project));
            }
            return projectPojos;

        }
    }
}
