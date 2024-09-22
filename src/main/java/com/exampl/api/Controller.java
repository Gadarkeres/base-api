package com.exampl.api;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController("api")
public class Controller {


  @GetMapping("hello-world")
  public String Hello() {
    return "hello world";
  }


  @GetMapping("exception")
  public void exception() {
    throw new RuntimeException();
  }

}
