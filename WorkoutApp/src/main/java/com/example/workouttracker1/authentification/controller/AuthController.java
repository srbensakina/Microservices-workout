package com.example.workouttracker1.authentification.controller;


import com.example.workouttracker1.authentification.dto.LoginDto;
import com.example.workouttracker1.authentification.dto.SignUpDto;
import com.example.workouttracker1.authentification.entity.Role;
import com.example.workouttracker1.authentification.entity.User;
import com.example.workouttracker1.authentification.jwt.JwtUtil;
import com.example.workouttracker1.authentification.repository.RoleRepository;
import com.example.workouttracker1.authentification.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthenticationManager authenticationManager;
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    private final JwtUtil jwtUtil;



    @PostMapping("/login")
    public ResponseEntity<String> authenticateUser(@RequestBody LoginDto loginDto) {
        Authentication authentication = authenticationManager
                .authenticate(new UsernamePasswordAuthenticationToken(loginDto.getUsername(), loginDto.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String email = authentication.getName();
        User user = User.builder().email(email).build();
        String token = jwtUtil.createToken(user);

      //  String token = jwtUtil.createToken(user);

        return new ResponseEntity<>("User login successfully!..." + token, HttpStatus.OK);
    }


    @PostMapping("/signup")
    public ResponseEntity<?> registerUser(@RequestBody SignUpDto signUpDto){
        // checking for username exists in a database
        if(userRepository.existsByUserName(signUpDto.getUsername())){
            return new ResponseEntity<>("Username is already exist!", HttpStatus.BAD_REQUEST);
        }
        // checking for email exists in a database
        if(userRepository.existsByEmail(signUpDto.getEmail())){
            return new ResponseEntity<>("Email is already exist!", HttpStatus.BAD_REQUEST);
        }
        String encodedPassword = passwordEncoder.encode(signUpDto.getPassword());

        Role roles = roleRepository.findByName("ROLE_ADMIN").get();
        User user = User.builder().name(signUpDto.getName()).userName(signUpDto.getUsername()).email(signUpDto.getEmail()).password(encodedPassword).roles(Collections.singleton(roles)).build();

        userRepository.save(user);

        return new ResponseEntity<>("User is registered successfully!", HttpStatus.OK);
    }

}
