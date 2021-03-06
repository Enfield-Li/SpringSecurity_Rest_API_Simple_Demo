package com.example.demo.auth.user;

import static com.example.demo.auth.user.UserPermission.*;

import java.util.Set;
import java.util.stream.Collectors;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

public enum UserRole {
  INTERN(Set.of(READ_PERMISSION)),
  MANAGER(Set.of(READ_PERMISSION, WRITE_PERMISSION));

  private final Set<UserPermission> permissions;

  UserRole(Set<UserPermission> permissions) {
    this.permissions = permissions;
  }

  public Set<UserPermission> getPermissions() {
    return permissions;
  }

  public Set<GrantedAuthority> getGrantedAuthorities() {
    Set<GrantedAuthority> grantedAuthorities = getPermissions()
      .stream()
      .map(
        permission ->
          (GrantedAuthority) new SimpleGrantedAuthority(
            permission.getPermission()
          )
      )
      .collect(Collectors.toSet());

    grantedAuthorities.add(new SimpleGrantedAuthority("ROLE_" + this.name()));
    return grantedAuthorities;
  }
}
