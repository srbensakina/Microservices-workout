package com.example.workouttracker1.exercices.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MuscleGroupDTO implements Serializable {

    @Serial
    private static final long serialVersionUID = 9199771373158793130L;

    private Integer id;
    private String name;
}
