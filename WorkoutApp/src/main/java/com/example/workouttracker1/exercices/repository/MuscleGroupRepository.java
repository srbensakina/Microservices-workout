package com.example.workouttracker1.exercices.repository;

import com.example.workouttracker1.exercices.entity.MuscleGroup;
import com.example.workouttracker1.exercices.entity.MuscleGroupEnum;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface MuscleGroupRepository extends CrudRepository<MuscleGroup, Integer> {

    //Optional<MuscleGroup> findAllByName(String name);

    Optional<MuscleGroup> findAllByName(MuscleGroupEnum name);

}
