package com.example.workouttracker1.exercices.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
@Entity
@Table
public class MuscleGroup {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Integer id;

    @Enumerated(EnumType.STRING)
    @Column
    private MuscleGroupEnum name ;
}
