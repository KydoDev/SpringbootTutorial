package com.skia.lab.models;

public enum ERole {
  ROLE_VIEWER,
  ROLE_USER,
  ROLE_MODERATOR,
  ROLE_ADMIN,
  ROLE_GOD;


  private static ERole[] list = ERole.values();

  public static ERole getRole(int i) {
      return list[i];
  }

  public static int listGetLastIndex() {
      return list.length - 1;
  }
}
