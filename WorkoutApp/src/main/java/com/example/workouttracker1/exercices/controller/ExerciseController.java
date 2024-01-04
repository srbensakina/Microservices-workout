package com.example.workouttracker1.exercices.controller;


import com.example.workouttracker1.exercices.dto.ExerciseDTO;
import com.example.workouttracker1.exercices.service.ExerciseService;
import com.example.workouttracker1.exercices.entity.MuscleGroupEnum;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/exercises")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
//@CrossOrigin
public class ExerciseController {

    private final ExerciseService exerciseService;

    @GetMapping
    public ResponseEntity<List<ExerciseDTO>> getAllExercises() {
        return new ResponseEntity<>(exerciseService.getAllExercises(), HttpStatus.OK);
    }

    @GetMapping("/random/{numOfExercises}/{muscleGroup}")
    public ResponseEntity<List<ExerciseDTO>> getRandomExercises(
            @PathVariable("numOfExercises") Integer numOfExercises,
            @PathVariable("muscleGroup") String muscleGroup,
            @RequestParam(defaultValue = "false") Boolean isWithWeights) {
        return new ResponseEntity<>(exerciseService.getRandomExercises(numOfExercises, muscleGroup, isWithWeights),
                HttpStatus.OK);
    }

    @GetMapping("/{muscleGroup}")
    public ResponseEntity<List<ExerciseDTO>> getExercisesForMuscleGroup(
            @PathVariable("muscleGroup") String muscleGroup) {
        return new ResponseEntity<>(exerciseService.getExercisesForMuscleGroup(MuscleGroupEnum.valueOf(muscleGroup.toUpperCase())), HttpStatus.OK);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<ExerciseDTO> saveExercise(@RequestBody ExerciseDTO exerciseDTO) {
        return new ResponseEntity<>(exerciseService.saveExercise(exerciseDTO), HttpStatus.CREATED);
    }
}
