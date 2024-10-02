package com.exampl.api;

import org.modelmapper.ModelMapper;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ApiController {

  ModelMapper mapper = new ModelMapper();

  @GetMapping("hello-world")
  public String Hello() {
    return "hello world!";
  }

  @PostMapping("hello-world/post")
  public RequestResponseDTO HelloPost(@RequestBody RequestDTO request) {
    RequestDTO requestDTO = new RequestDTO();
    requestDTO.setName(request.getName());
    requestDTO.setAge(request.getAge());
    requestDTO.setAddress(request.getAddress());

    RequestResponseDTO requestResponseDTO = mapper.map(RequestDTO.class, RequestResponseDTO.class);

    return requestResponseDTO;
  }
}
