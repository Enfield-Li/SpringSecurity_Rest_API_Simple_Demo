package com.example.demo.auth.filters;

import static com.example.demo.util.Constants.*;

import com.example.demo.auth.user.User;
import com.example.demo.dto.LoginUserDto;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;

public class LoginFilter extends AbstractAuthenticationProcessingFilter {

  public LoginFilter(String url, AuthenticationManager authManager) {
    super(url);
    setAuthenticationManager(authManager);
  }

  @Override
  public Authentication attemptAuthentication(
    HttpServletRequest req,
    HttpServletResponse res
  )
    throws AuthenticationException, IOException {
    System.out.println("Invoke ***LoginFilter*** -> attemptAuthentication");

    LoginUserDto loginUserDto = new ObjectMapper()
    .readValue(req.getInputStream(), LoginUserDto.class);

    return getAuthenticationManager()
      .authenticate(
        new UsernamePasswordAuthenticationToken(
          loginUserDto.getUsername(),
          loginUserDto.getPassword()
        )
      );
  }

  @Override
  protected void successfulAuthentication(
    HttpServletRequest req,
    HttpServletResponse res,
    FilterChain chain,
    Authentication auth
  )
    throws IOException, ServletException {
    System.out.println("Invoke ***LoginFilter*** -> successfulAuthentication");

    User user = (User) auth.getPrincipal();

    req.getSession().setAttribute(UserSessionKey, user);

    res.getOutputStream().print("You are logged in as " + user.getUsername());
  }

  @Override
  protected void unsuccessfulAuthentication(
    HttpServletRequest request,
    HttpServletResponse response,
    AuthenticationException failed
  )
    throws IOException, ServletException {
    System.out.println( "Invoke ***LoginFilter*** -> unsuccessfulAuthentication");

    response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
    response.setContentType("text/plain");
    response.getOutputStream().print(failed.getMessage());
  }
}
