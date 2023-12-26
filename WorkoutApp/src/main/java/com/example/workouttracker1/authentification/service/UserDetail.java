package com.example.workouttracker1.authentification.service;

import com.example.workouttracker1.authentification.entity.User;
import com.example.workouttracker1.authentification.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserDetail implements UserDetailsService {

   private final UserRepository userRepo;
    @Override
    public UserDetails loadUserByUsername(String username )throws UsernameNotFoundException {
        User user = userRepo.findByUserNameOrEmail(username, username);
        if(user==null){
            throw new UsernameNotFoundException("User not exists by Username");
        }

        Set<GrantedAuthority> authorities = user.getRoles().stream()
                .map((role) -> new SimpleGrantedAuthority(role.getName()))
                .collect(Collectors.toSet());
        return new org.springframework.security.core.userdetails.User(username,user.getPassword(),authorities);
    }
}