package com.example.lr2yumaganova.service;

import com.example.lr2yumaganova.POJO.TaskPojo;

import java.util.List;

public interface ITaskService {

    List<TaskPojo> getAllTaskByProjectId(Long projectId);
    TaskPojo getTaskByProjectIdAndTaskId(Long taskId, Long projectId);
    TaskPojo createTaskForProject(Long projectId, TaskPojo task);
    TaskPojo updateTask(Long taskId, Long projectId, TaskPojo taskPojo);
    void deleteTask(Long taskId, Long projectId);
    void deleteCompletedTasksByProjectId(Long projectId);
}
