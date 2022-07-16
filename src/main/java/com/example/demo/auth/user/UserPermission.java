package com.example.demo.auth.user;

public enum UserPermission {
  READ_PERMISSION("permission:read"),
  WRITE_PERMISSION("permission:write");

  private final String permission;

  UserPermission(String permission) {
    this.permission = permission;
  }

  public String getPermission() {
    return permission;
  }
}
