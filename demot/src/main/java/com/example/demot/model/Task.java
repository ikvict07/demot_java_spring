package com.example.demot.model;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;


@Data

@Entity
@Table(name= "task")
public class Task {

    @Id

    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Long id;

    private LocalDate date;

    private String description;

    private boolean done;

}
