package com.exampl.api;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class ApiController {

  ModelMapper mapper = new ModelMapper();

  @Autowired
  private UserRepository repository;

  @GetMapping("/hello-world")
  public String Hello() {
    return "hello world!";
  }

  @PostMapping("/hello-world/post")
  public RequestResponseDTO HelloPost(@RequestBody RequestDTO request) {
    RequestDTO requestDTO = new RequestDTO();
    requestDTO.setName(request.getName());
    requestDTO.setAge(request.getAge());
    requestDTO.setAddress(request.getAddress());

    RequestResponseDTO requestResponseDTO = mapper.map(RequestDTO.class, RequestResponseDTO.class);

    return requestResponseDTO;
  }

  @GetMapping("/users")
  public List<User> getUsers() {
    return repository.findAll();
  }

  @GetMapping("/users/login")
  public User getUserByLogin(String login) {
    return repository.findByLogin(login);
  }

  @PostMapping("/users/insert")
  public void insertUser(@RequestBody User user) {
    repository.save(user);
  }
}
