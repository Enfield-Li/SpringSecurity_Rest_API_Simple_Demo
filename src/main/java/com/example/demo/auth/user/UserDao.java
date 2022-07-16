package com.example.demo.auth.user;

import java.util.Optional;

public interface UserDao {
  Optional<User> selectApplicationUserByUsername(String username);
}
