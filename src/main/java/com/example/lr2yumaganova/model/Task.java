package com.example.lr2yumaganova.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.NaturalId;

import java.sql.Date;

@Entity
@Table(name="task", schema = "public")
@Getter
@Setter
public class Task {
    @Id
    @Column(name = "id_task")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "name_task")
    private String name;
    @Column(name = "description_task")
    private String description;
    @Column(name = "planned_end_date")
    @Temporal(value = TemporalType.DATE)
    private Date plannedEndDate;
    @Column(name = "completed")
    private Boolean completed;

    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(name = "id_project", foreignKey = @ForeignKey(ConstraintMode.NO_CONSTRAINT))
    private Project project;

}
