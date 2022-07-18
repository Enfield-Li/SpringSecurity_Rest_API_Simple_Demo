package com.example.demo.auth.user;

import java.util.Collection;
import java.util.Set;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

@Data
public class User implements UserDetails {

  private final String username;
  private final String password;
  private final Set<? extends GrantedAuthority> grantedAuthorities;

  public User(
    String username,
    String password,
    Set<? extends GrantedAuthority> grantedAuthorities
  ) {
    this.username = username;
    this.password = password;
    this.grantedAuthorities = grantedAuthorities;
  }

  @Override
  public Collection<? extends GrantedAuthority> getAuthorities() {
    return grantedAuthorities;
  }

  @Override
  public String getPassword() {
    return password;
  }

  @Override
  public String getUsername() {
    return username;
  }

  @Override
  public boolean isAccountNonExpired() {
    return true;
  }

  @Override
  public boolean isAccountNonLocked() {
    return true;
  }

  @Override
  public boolean isCredentialsNonExpired() {
    return true;
  }

  @Override
  public boolean isEnabled() {
    return true;
  }
}
