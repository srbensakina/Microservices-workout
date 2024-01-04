package com.example.workouttracker1.authentification.controller;


import com.example.workouttracker1.authentification.entity.User;
import com.example.workouttracker1.authentification.repository.UserRepository;
import com.example.workouttracker1.dto.LoginDto;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class AuthController {

    private final UserRepository userRepository;


@GetMapping("/{receiverId}")
@ResponseStatus(HttpStatus.OK)
public boolean IsUser(@PathVariable Integer receiverId  ){
    return userRepository.existsById(receiverId);
}

    @PostMapping("signUp")
    public User save(@RequestBody User user) {
        return userRepository.save(user);
    }

    @PostMapping("login")
    public User save(@RequestBody LoginDto loginDto) {
      //  System.out.println("i am user" + userRepository.findByEmailAndPassword(loginDto.getEmail(), loginDto.getPassword()));
        return userRepository.findByEmail(loginDto.getEmail());
    }




    @GetMapping(value = "/secure")
    public String getSecure() {
        return "Secure endpoint available";
    }

}
