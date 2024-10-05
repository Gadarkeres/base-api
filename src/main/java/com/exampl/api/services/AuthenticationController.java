package com.exampl.api.services;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.exampl.api.infra.TokenService;
import com.exampl.api.user.AuthenticationDTO;
import com.exampl.api.user.LoginResponseDTO;
import com.exampl.api.user.RegisterDTO;
import com.exampl.api.user.User;
import com.exampl.api.user.UserRepository;

@RestController
@RequestMapping("/auth")
public class AuthenticationController {

  @Autowired
  private AuthenticationManager authManager;
  @Autowired
  private UserRepository repository;
  @Autowired
  private TokenService tokenService;
  @PostMapping("/login")
  public ResponseEntity login(@RequestBody @Valid AuthenticationDTO data ) { 
   var usernamePassword = new UsernamePasswordAuthenticationToken(data.login(), data.password());
   var auth = this.authManager.authenticate(usernamePassword);

   var token = tokenService.generateToken((User) auth.getPrincipal());


   return ResponseEntity.ok(new LoginResponseDTO(token));
  }

  @PostMapping("/register")
  public ResponseEntity register(@RequestBody @Valid RegisterDTO data ) { 
   if(this.repository.findByLogin(data.login()) != null) {
     return ResponseEntity.badRequest().build();
   }

   String encrytedPassword = new BCryptPasswordEncoder().encode(data.password());
   User newUser = new User(data.login(), encrytedPassword, data.role());
   this.repository.save(newUser);

   return ResponseEntity.ok().build();
  }

}
