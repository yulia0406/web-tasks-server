package com.example.lr2yumaganova.service;

import com.example.lr2yumaganova.POJO.ProjectPojo;
import com.example.lr2yumaganova.POJO.TaskPojo;
import com.example.lr2yumaganova.model.Project;
import com.example.lr2yumaganova.model.Task;
import com.example.lr2yumaganova.repository.IProjectRepository;
import com.example.lr2yumaganova.repository.ITaskRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TaskService implements ITaskService{

    private final ITaskRepository taskRepository;
    private final IProjectRepository projectRepository;

    public TaskPojo createTaskForProject(Long projectId, TaskPojo taskPojo){
        System.out.println(taskPojo.getName());
        Task task = taskPojo.toEntity(taskPojo);
        Project project = projectRepository.findById(projectId).orElseThrow(()-> new EntityNotFoundException("Проект с указанным ID не найден"));
        task.setProject(project);
        return TaskPojo.fromEntity(taskRepository.save(task));
    }

    public TaskPojo getTaskByProjectIdAndTaskId(Long taskId, Long projectId){
        Project project = projectRepository.findById(projectId).orElseThrow(() -> new EntityNotFoundException("Проект с указанным ID не найден"));
        Task task1 = taskRepository.findByProjectAndId(project, taskId);
        if (task1 == null) {
            throw new EntityNotFoundException("Задача с указанным ID в рамках проекта не найдена");
        }
        TaskPojo task = TaskPojo.fromEntity(task1);

        // Преобразовываем найденную задачу в объект TaskPojo и возвращаем ее
        return task;
    }

    public List<TaskPojo> getAllTaskByProjectId(Long projectId){
        Project project = projectRepository.findById(projectId)
                .orElseThrow(() -> new EntityNotFoundException("Проект с указанным ID не найден"));

        List<TaskPojo> taskPojos = new ArrayList<>();
        for (Task task : project.getTasks()) {
            taskPojos.add(TaskPojo.fromEntity(task));
        }
        return taskPojos;
    }

    public TaskPojo updateTask(Long taskId, Long projectId, TaskPojo taskPojo)
    {
        Project project = projectRepository.findById(projectId)
                .orElseThrow(() -> new EntityNotFoundException("Проект с указанным ID не найден"));
        Task task = taskRepository.findByProjectAndId(project, taskId);
        if (task == null) {
            throw new EntityNotFoundException("Задача с указанным ID в рамках проекта не найдена");
        }
        Task taskUpdate = TaskPojo.toEntity(taskPojo);
        task.setDescription(taskUpdate.getDescription());
        task.setCompleted(taskUpdate.getCompleted());
        task.setName(taskUpdate.getName());
        task.setPlannedEndDate(taskUpdate.getPlannedEndDate());

        return TaskPojo.fromEntity(taskRepository.save(task));
    }

    public void deleteTask(Long taskId, Long projectId)
    {
        Project project = projectRepository.findById(projectId)
                .orElseThrow(() -> new EntityNotFoundException("Проект с указанным ID не найден"));
        Task task = taskRepository.findByProjectAndId(project, taskId);
        if (task == null) {
            throw new EntityNotFoundException("Задача с указанным ID в рамках проекта не найдена");
        }
        taskRepository.delete(task);
    }

    public void deleteCompletedTasksByProjectId(Long projectId){
        Project project = projectRepository.findById(projectId)
                .orElseThrow(() -> new EntityNotFoundException("Проект с указанным ID не найден"));
        List<Task> completedTasks = taskRepository.findByProjectIdAndCompletedIsTrue(projectId);
        taskRepository.deleteAll(completedTasks);
    }
}
