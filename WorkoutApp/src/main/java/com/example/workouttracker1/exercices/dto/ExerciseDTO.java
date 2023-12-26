package com.example.workouttracker1.exercices.dto;

import com.example.workouttracker1.exercices.entity.MuscleGroupEnum;
import lombok.*;

import java.io.Serial;
import java.io.Serializable;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ExerciseDTO implements Serializable {

    @Serial
    private static final long serialVersionUID = -2546371005065411686L;

    private Long id;
    private String name;
    private MuscleGroupEnum muscleGroupName;
    private Boolean isWithWeights;
    private String link;
    private Integer number;
}