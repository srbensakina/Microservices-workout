package com.example.authservice.controller;


import com.example.authservice.entity.AuthRequest;
import com.example.authservice.entity.AuthResponse;
import com.example.authservice.entity.LoginRequest;
import com.example.authservice.entity.UserVO;
import com.example.authservice.service.AuthService;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;
import io.github.resilience4j.timelimiter.annotation.TimeLimiter;
import lombok.RequiredArgsConstructor;
import org.apache.tomcat.websocket.AuthenticationException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.CompletableFuture;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/auth")
public class AuthController {

    private final AuthService authService;

    @PostMapping(value = "/register")
    @CircuitBreaker(name = "authentication" , fallbackMethod = "fallbackMethod")
    @TimeLimiter(name = "authentication")
    @Retry(name = "authentication" )
    public CompletableFuture<ResponseEntity<?>>register(@RequestBody AuthRequest authRequest) {
        return CompletableFuture.supplyAsync(() ->   ResponseEntity.ok(authService.register(authRequest))
      );
    }

    @PostMapping(value = "/login")
    @TimeLimiter(name = "authentication")
    @CircuitBreaker(name = "authentication" , fallbackMethod = "fallbackMethod")
    public CompletableFuture<ResponseEntity<?>> login(@RequestBody LoginRequest loginRequest) throws AuthenticationException {
        return CompletableFuture.supplyAsync(() -> {
            try {
                return ResponseEntity.ok(authService.login(loginRequest));
            } catch (AuthenticationException e) {
                throw new RuntimeException(e);
            }
        });
    }
    public ResponseEntity<?> fallbackMethod(Throwable throwable){
        return ResponseEntity.ok( "Something went wrong, please try to authenticate again after some time!");
    }
}