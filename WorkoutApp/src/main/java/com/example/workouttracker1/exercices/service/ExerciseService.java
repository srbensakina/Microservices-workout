package com.example.workouttracker1.exercices.service;

import com.example.workouttracker1.exercices.entity.MuscleGroupEnum;
import com.example.workouttracker1.exercices.dto.ExerciseDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ExerciseService {

    ExerciseDTO saveExercise(ExerciseDTO exerciseDTO);
    List<ExerciseDTO> getAllExercises();
    void deleteExercise(ExerciseDTO exerciseDTO);
    List<ExerciseDTO> getRandomExercises(Integer numOfExercises, String muscleGroup, Boolean isWithWeights);
    List<ExerciseDTO> getExercisesForMuscleGroup(MuscleGroupEnum muscleGroup);
}
