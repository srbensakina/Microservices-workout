package com.example.workouttracker1.exercices.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@RequiredArgsConstructor
public class Exercise {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Long id;

    @Column
    private String name;

    @ManyToOne
    @JoinColumn(name = "MUSCLE_GROUP_ID")
    private MuscleGroup muscleGroup;

    @Column
    private Boolean isWithWeights;

    @Column
    private String link;

}