package com.example.demo.controller;

import static com.example.demo.util.Constants.*;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Tag(name = "Test-permission")
@RequestMapping(TEST_ENDPOINT)
class TestController {

  @GetMapping("access_by_all")
  public String accessByAll() {
    return "Anyone can access this!";
  }

  @GetMapping(READ)
  public String read() {
    return "I can do READ operations, using http GET request!";
  }

  @PostMapping(WRITE)
  public String write() {
    return "I can do WRITE operations, using http POST request!";
  }

  @GetMapping(MANAGER_ONLY)
  public String managerOnly() {
    return "You are a manager, you can access this endpoint!";
  }
}
