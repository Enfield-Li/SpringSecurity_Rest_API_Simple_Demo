package com.example.demo.auth.user;

import static com.example.demo.auth.user.UserPermission.*;

import com.google.common.collect.Sets;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

public enum UserRole {
  INTERN(Sets.newHashSet(READ_PERMISSION)),
  MANAGER(Sets.newHashSet(READ_PERMISSION, WRITE_PERMISSION));

  private final Set<UserPermission> permissions;

  UserRole(Set<UserPermission> permissions) {
    this.permissions = permissions;
  }

  public Set<UserPermission> getPermissions() {
    return permissions;
  }

  public List<GrantedAuthority> getGrantedAuthorities() {
    List<GrantedAuthority> grantedAuthorities = getPermissions()
      .stream()
      .map(
        permission ->
          (GrantedAuthority) new SimpleGrantedAuthority(
            permission.getPermission()
          )
      )
      .collect(Collectors.toList());

    grantedAuthorities.add(new SimpleGrantedAuthority("ROLE_" + this.name()));
    return grantedAuthorities;
  }
}
