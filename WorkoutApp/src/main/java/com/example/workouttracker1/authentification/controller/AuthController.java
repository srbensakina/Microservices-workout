package com.example.workouttracker1.authentification.controller;


import com.example.workouttracker1.authentification.entity.User;
import com.example.workouttracker1.authentification.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class AuthController {

    private final UserRepository userRepository;


   /* private final AuthenticationManager authenticationManager;
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

*/



@GetMapping("/{receiverId}")
@ResponseStatus(HttpStatus.OK)
public boolean IsUser(@PathVariable Integer receiverId  ){
    return userRepository.existsById(receiverId);
}

    @PostMapping
    public User save(@RequestBody User user) {
        return userRepository.save(user);
    }



    @GetMapping(value = "/secure")
    public String getSecure() {
        return "Secure endpoint available";
    }

}
