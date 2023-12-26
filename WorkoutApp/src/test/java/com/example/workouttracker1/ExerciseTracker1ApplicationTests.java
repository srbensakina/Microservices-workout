package com.example.workouttracker1;

import com.example.workouttracker1.exercices.dto.ExerciseDTO;
import com.example.workouttracker1.exercices.entity.MuscleGroupEnum;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import org.testcontainers.shaded.com.fasterxml.jackson.core.JsonProcessingException;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest

@Testcontainers
@AutoConfigureMockMvc
class ExerciseTracker1ApplicationTests {

    //TODO : TEST THIS AS SOON AS THE NETWORK GETS BETTER
    @Container
  static PostgreSQLContainer<?> postgresSQLContainer = new PostgreSQLContainer<>("postgres:15.5-bullseye");


    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private Jackson2ObjectMapperBuilder mapperBuilder;


    @DynamicPropertySource
    static void setProperties(DynamicPropertyRegistry dynamicPropertyRegistry){
        dynamicPropertyRegistry.add("spring.datasource.url", postgresSQLContainer::getJdbcUrl);
    }

    @Test
    void shouldSaveExercise() throws Exception {

        ObjectMapper objectMapper = mapperBuilder.build();
        ExerciseDTO exerciseDTO = getExerciseDto();
        mockMvc.perform(MockMvcRequestBuilders.put("/api/exercises")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(exerciseDTO)))
                .andExpect(status().isCreated());



    }

    private ExerciseDTO getExerciseDto() {
      return ExerciseDTO.builder().isWithWeights(true).name("ran").number(2).link("https://hello").muscleGroupName(MuscleGroupEnum.CARDIO).build();
    }


}
