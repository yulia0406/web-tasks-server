package com.example.lr2yumaganova.POJO;

import com.example.lr2yumaganova.model.Project;
import com.example.lr2yumaganova.model.Task;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.FetchType;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.Setter;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class ProjectPojo {
    private long id;
    //@JsonProperty("name_project")
    private String projectName;
    //@JsonProperty("description_project")
    private String projectDescription;
   // @JsonProperty("start_date")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date startDate;
   // @JsonProperty("end_date")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date endDate;



    public static ProjectPojo fromEntity(Project project)
    {
        ProjectPojo projectPojo = new ProjectPojo();
        projectPojo.setId(project.getId());
        projectPojo.setProjectName(project.getProjectName());
        projectPojo.setProjectDescription(project.getProjectDescription());
        projectPojo.setEndDate(project.getEndDate());
        projectPojo.setStartDate(project.getStartDate());

        return projectPojo;
    }



    public static Project toEntity(ProjectPojo projectPojo) {
        Project project = new Project();
        project.setId(projectPojo.getId());
        project.setProjectName(projectPojo.getProjectName());
        project.setProjectDescription(projectPojo.getProjectDescription());
        project.setEndDate(projectPojo.getEndDate());
        project.setStartDate(projectPojo.getStartDate());


        return project;
    }
}
