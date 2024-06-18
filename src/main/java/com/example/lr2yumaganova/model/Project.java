package com.example.lr2yumaganova.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.sql.Date;
import java.util.List;

@Entity
@Table(name = "project", schema = "public")
@Getter
@Setter
public class Project {
    @Id
    @Column(name = "id_project")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(name = "name_project")
    private String projectName;
    @Column(name = "description_project")
    private String projectDescription;
    @Column(name = "start_date")
    private Date startDate;
    @Column(name = "end_date")
    private Date endDate;

    @OneToMany(mappedBy="project", cascade = CascadeType.ALL)
    private List<Task> tasks;
}
