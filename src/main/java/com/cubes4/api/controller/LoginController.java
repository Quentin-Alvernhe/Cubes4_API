package com.cubes4.api.controller;

import com.cubes4.api.model.User;
import com.cubes4.api.security.JwtUtils;
import com.cubes4.api.security.MyUserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin()
@RestController
public class LoginController {
    @Autowired
    private AuthenticationProvider authenticationProvider;

    @Autowired
    private JwtUtils jwtUtils;

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody User user) {
        try {
            MyUserDetails userDetails = (MyUserDetails) authenticationProvider.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            user.getLogin(),
                            user.getPassword()))
                    .getPrincipal();
            return new ResponseEntity<>(jwtUtils.generateJwt(userDetails), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Les identifiants sont erronés", HttpStatus.FORBIDDEN);
        }
    }
}
