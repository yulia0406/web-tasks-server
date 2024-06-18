package com.example.lr2yumaganova.POJO;

import com.example.lr2yumaganova.model.Project;
import com.example.lr2yumaganova.model.Task;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.sql.Date;

@Getter
@Setter
public class TaskPojo {
    private long id;
    @JsonProperty("name_task")
    private String name;
    @JsonProperty("description_task")
    private String description;
    @JsonProperty("planned_end_date")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date plannedEndDate;
    @JsonProperty("completed")
    private Boolean completed;

    public static TaskPojo fromEntity(Task task)
    {
        TaskPojo taskPojo = new TaskPojo();
        taskPojo.setId(task.getId());
        taskPojo.setName(task.getName());
        taskPojo.setDescription(task.getDescription());
        taskPojo.setPlannedEndDate(task.getPlannedEndDate());
        taskPojo.setCompleted(task.getCompleted());
        return taskPojo;
    }

    public static Task toEntity(TaskPojo taskPojo)
    {
        Task task = new Task();
        task.setId(taskPojo.getId());
        task.setName(taskPojo.getName());
        task.setDescription(taskPojo.getDescription());
        task.setPlannedEndDate(taskPojo.getPlannedEndDate());
        task.setCompleted(taskPojo.getCompleted());
        return task;
    }
}
