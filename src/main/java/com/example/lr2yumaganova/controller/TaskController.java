package com.example.lr2yumaganova.controller;

import com.example.lr2yumaganova.POJO.TaskPojo;
import com.example.lr2yumaganova.service.ITaskService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tasks")
public class TaskController {
    @Autowired
    private ITaskService iTaskService;
    @PostMapping("/createTask/{projectId}")
    public ResponseEntity<TaskPojo> createTask(@PathVariable("projectId") Long id, @RequestBody TaskPojo taskPojo)
    {
        try {
            TaskPojo result = iTaskService.createTaskForProject(id, taskPojo);
            return ResponseEntity.status(HttpStatus.CREATED).body(result);
        }catch (EntityNotFoundException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    @GetMapping("/getAllTask/{projectId}")
    public ResponseEntity<List<TaskPojo>> getAllTaskByProject(@PathVariable("projectId") Long id)
    {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(iTaskService.getAllTaskByProjectId(id));
        }catch (EntityNotFoundException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    @GetMapping("/getTask/projects/{projectId}/tasks/{taskId}")
    public ResponseEntity<TaskPojo> getTask(@PathVariable("projectId") Long idProject, @PathVariable("taskId") Long idTask)
    {
        try{
            return ResponseEntity.status(HttpStatus.OK).body(iTaskService.getTaskByProjectIdAndTaskId(idTask, idProject));
        }catch (EntityNotFoundException e)
        {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    @PutMapping("/updateTask/projects/{projectId}/tasks/{taskId}")
    public ResponseEntity<TaskPojo> updateTask(@PathVariable("projectId") Long idProject, @PathVariable("taskId") Long idTask, @RequestBody TaskPojo taskPojo)
    {
        try{
            return ResponseEntity.status(HttpStatus.OK).body(iTaskService.updateTask(idTask, idProject, taskPojo));
        }catch (EntityNotFoundException e)
        {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    @DeleteMapping("/deleteTask/projects/{projectId}/tasks/{taskId}")
    public ResponseEntity deleteTask(@PathVariable("projectId") Long idProject, @PathVariable("taskId") Long idTask)
    {
        try{
            iTaskService.deleteTask(idTask, idProject);
            return ResponseEntity.status(HttpStatus.OK).body(null);
        }catch (EntityNotFoundException e)
        {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    @DeleteMapping("/deleteCompletedTasksByProjectId/{projectId}")
    public ResponseEntity deleteCompletedTasksByProjectId(@PathVariable("projectId") Long idProject)
    {
        try{
            iTaskService.deleteCompletedTasksByProjectId(idProject);
            return ResponseEntity.status(HttpStatus.OK).body(null);
        }catch (EntityNotFoundException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }
}
