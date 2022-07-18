package com.example.demo.controller;

import static com.example.demo.util.Constants.*;

import com.example.demo.auth.user.User;
import com.example.demo.dto.LoginUserDto;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Tag(name = "Auth")
@RequestMapping(AUTH_ENDPOINT)
class AuthController {

  @PostMapping(LOGOUT) // For exposing endpoint to swagger UI
  public void logout() {}

  @PostMapping(LOGIN) // For exposing endpoint to swagger UI
  public void login(@RequestBody LoginUserDto dto) {}

  @GetMapping(GET_AUTH_INFO)
  public String getAuthInfo() {
    User user = (User) SecurityContextHolder
      .getContext()
      .getAuthentication()
      .getPrincipal();

    return "Auth info from securityContext: " + user.toString();
  }
}
