package com.example.authservice.service;

import com.example.authservice.entity.AuthRequest;
import com.example.authservice.entity.AuthResponse;
import com.example.authservice.entity.UserVO;
import io.jsonwebtoken.lang.Assert;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

/*@Service
@RequiredArgsConstructor
public class AuthService {


    private final RestTemplate restTemplate;
    private final JwtUtil jwt;


    public AuthResponse register(AuthRequest authRequest) {
        //do validation if user already exists
        authRequest.setPassword(BCrypt.hashpw(authRequest.getPassword(), BCrypt.gensalt()));

        UserVO userVO = restTemplate.postForObject("http://workout-service/users", authRequest, UserVO.class);
        Assert.notNull(userVO, "Failed to register user. Please try again later");

        String accessToken = jwt.generate(userVO, "ACCESS");
        String refreshToken = jwt.generate(userVO, "REFRESH");

        return new AuthResponse(accessToken, refreshToken);

    }
}*/

@Service
@RequiredArgsConstructor
public class AuthService {

    private final WebClient.Builder webClientBuilder;
    private final JwtUtil jwt;

    public AuthResponse register(AuthRequest authRequest) {
        // do validation if the user already exists
        authRequest.setPassword(BCrypt.hashpw(authRequest.getPassword(), BCrypt.gensalt()));

        UserVO userVO = webClientBuilder.build()
                .post()
                .uri("http://workout-service/users")
                .bodyValue(authRequest)
                .retrieve()
                .bodyToMono(UserVO.class)
                .block();  // blocking for simplicity; use subscribe() in a reactive application

        Assert.notNull(userVO, "Failed to register user. Please try again later");

        String accessToken = jwt.generate(userVO, "ACCESS");
        String refreshToken = jwt.generate(userVO, "REFRESH");

        return new AuthResponse(accessToken, refreshToken);
    }
}
