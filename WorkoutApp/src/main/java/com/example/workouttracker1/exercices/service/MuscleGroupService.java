package com.example.workouttracker1.exercices.service;

import com.example.workouttracker1.exercices.dto.MuscleGroupDTO;

import java.util.List;

public interface MuscleGroupService {

    MuscleGroupDTO saveMuscleGroup(MuscleGroupDTO muscleGroupDTO);
    List<MuscleGroupDTO> getAllMuscleGroups();
    void deleteMuscleGroup(MuscleGroupDTO muscleGroupDTO);
}