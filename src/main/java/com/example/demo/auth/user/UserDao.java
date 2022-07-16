package com.example.demo.auth.user;

import java.util.Optional;

public interface UserDao {
  Optional<User> selectUserByUsername(String username);
}
