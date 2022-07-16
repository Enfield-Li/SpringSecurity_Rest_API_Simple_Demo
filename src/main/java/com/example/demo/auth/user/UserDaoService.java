package com.example.demo.auth.user;

import static com.example.demo.auth.user.UserRole.INTERN;
import static com.example.demo.auth.user.UserRole.MANAGER;

import com.google.common.collect.Lists;
import java.util.List;
import java.util.Optional;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserDaoService implements UserDao {

  private final PasswordEncoder passwordEncoder;

  public UserDaoService(PasswordEncoder passwordEncoder) {
    this.passwordEncoder = passwordEncoder;
  }

  @Override
  public Optional<User> selectUserByUsername(String username) {
    return getAllUsers()
      .stream()
      .filter(applicationUser -> username.equals(applicationUser.getUsername()))
      .findFirst();
  }

  private List<User> getAllUsers() {
    List<User> applicationUsers = Lists.newArrayList(
      new User(
        "intern",
        passwordEncoder.encode("intern"),
        INTERN.getGrantedAuthorities()
      ),
      new User(
        "manager",
        passwordEncoder.encode("manager"),
        MANAGER.getGrantedAuthorities()
      )
    );

    return applicationUsers;
  }
}
