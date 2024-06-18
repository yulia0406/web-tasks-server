package com.example.lr2yumaganova.controller;

import com.example.lr2yumaganova.POJO.ProjectPojo;
import com.example.lr2yumaganova.model.Project;
import com.example.lr2yumaganova.service.IProjectService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/projects")
public class ProjectController {
    @Autowired
    private IProjectService iProjectService;

    @Secured("ROLE_ADMIN")
    @PostMapping("/create")
    public ResponseEntity<ProjectPojo> create(@RequestBody ProjectPojo projectPojo){
            ProjectPojo projectPojo1 = iProjectService.create(projectPojo);
            return ResponseEntity.status(HttpStatus.CREATED).body(projectPojo1);

    }

    @Secured("ROLE_ADMIN")
    @PostMapping("/update")
    public ResponseEntity<ProjectPojo> update(@RequestBody ProjectPojo projectPojo)
    {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(iProjectService.update(projectPojo));
        }catch (EntityNotFoundException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @GetMapping("/get/{projectId}")
    public ResponseEntity<ProjectPojo> get(@PathVariable("projectId") long id)
    {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(iProjectService.getProjectById(id));
        }catch (EntityNotFoundException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @Secured("ROLE_ADMIN")
    @DeleteMapping("/delete/{projectId}")
    public ResponseEntity delete(@PathVariable("projectId") long id)
    {
        try{
            iProjectService.deleteProjectById(id);
            return ResponseEntity.status(HttpStatus.OK).build();
        }catch (EntityNotFoundException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @GetMapping("/getCountTasksUnclosed")
    public ResponseEntity<Map<Long, Long>> getTasksCountInProjects()
    {
        return ResponseEntity.status(HttpStatus.OK).body(iProjectService.getTasksCountInProjects());
    }

    @GetMapping("/getProjects")
    public ResponseEntity<List<ProjectPojo>> getProjects(@RequestParam("search") String search){
        return ResponseEntity.status(HttpStatus.OK).body(iProjectService.getProjects(search));
    }

    /*@PostMapping("/create")
    public ResponseEntity<Project> create(@RequestBody Project project){

        Project newProject = iProjectService.create(project);
        return ResponseEntity.status(HttpStatus.CREATED).body(newProject);
    }
    @PutMapping("/update/{projectId}")
    public ResponseEntity update(@PathVariable("projectId") int id, @RequestBody Project project)
    {
        Project project1 = iProjectService.getProjectById(id);
        if(project1 == null)
        {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        project1.setProjectName(project.getProjectName());
        project1.setProjectDescription(project.getProjectDescription());
        project1.setStartDate(project.getStartDate());
        project1.setEndDate(project.getEndDate());
        iProjectService.update(project1);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @DeleteMapping("/delete/{projectId}")
    public ResponseEntity delete(@PathVariable("projectId") int id)
    {
        int result = iProjectService.deleteProjectById(id);
        if(result == 1)
        {
            return  ResponseEntity.noContent().build();
        }
        else{
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/get/{projectId}")
    public ResponseEntity<Project> get(@PathVariable("projectId") int id)
    {
        Project project = iProjectService.getProjectById(id);
        if(project == null)
        {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.status(HttpStatus.OK).body(project);
    }

    @GetMapping("/getall")
    public ResponseEntity<List<Project>> getAll()
    {
        List<Project> projectList = iProjectService.getAllProjects();
        if(projectList == null)
        {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.status(HttpStatus.OK).body(projectList);
    }

    @GetMapping("/getFilter")
    public ResponseEntity<List<Project>> getProjectsFilter(@RequestParam("start_date") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date startDate,
                                                           @RequestParam("end_date") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date endDate) {
        List<Project> projects = iProjectService.getFiltrationListProjects(startDate, endDate);
        if (projects.isEmpty())
        {
            System.out.println("LLLLLLLLLLLLL");
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(projects, HttpStatus.OK);
    }*/

}
