package com.example.authservice.service;

import com.example.authservice.entity.AuthRequest;
import com.example.authservice.entity.AuthResponse;
import com.example.authservice.entity.LoginRequest;
import com.example.authservice.entity.UserVO;
import io.jsonwebtoken.lang.Assert;
import lombok.RequiredArgsConstructor;
import org.apache.tomcat.websocket.AuthenticationException;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

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
                .uri("http://workout-service/users/signUp")
                .bodyValue(authRequest)
                .retrieve()
                .bodyToMono(UserVO.class)
                .block();

        Assert.notNull(userVO, "Failed to register user. Please try again later");

        String accessToken = jwt.generate(userVO, "ACCESS");
        String refreshToken = jwt.generate(userVO, "REFRESH");

        return new AuthResponse(accessToken, refreshToken);
    }

    public AuthResponse login(LoginRequest loginRequest) throws AuthenticationException {

        UserVO userVO = webClientBuilder.build()
                .post()
                .uri("http://workout-service/users/login")
                .bodyValue(loginRequest)
                .retrieve()
                .bodyToMono(UserVO.class)
                .block();
        assert userVO != null;
        System.out.println("i am login request " + loginRequest.getPassword() + "i am the one from the db" + userVO.getPassword());
        if (BCrypt.checkpw(loginRequest.getPassword(), userVO.getPassword())) {
            String accessToken = jwt.generate(userVO, "ACCESS");
            String refreshToken = jwt.generate(userVO, "REFRESH");

            return new AuthResponse(accessToken, refreshToken);
        } else {
            throw new AuthenticationException("Invalid username or password");
        }
    }
}


