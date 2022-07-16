package com.example.demo.auth.user;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserService implements UserDetailsService {

  private final UserDao userDao;

  public UserService(UserDao userDao) {
    this.userDao = userDao;
  }

  @Override
  public UserDetails loadUserByUsername(String username)
    throws UsernameNotFoundException {
    System.out.println("Invoke ***UserService*** -> loadUserByUsername");

    return userDao
      .selectUserByUsername(username)
      .orElseThrow(
        () ->
          new UsernameNotFoundException(
            String.format("Username %s not found", username)
          )
      );
  }
}
