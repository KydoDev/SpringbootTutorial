package com.skia.lab.controllers;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


// There are 4 APIs:
// – /api/test/all for public access
// – /api/test/user for users has ROLE_USER or ROLE_MODERATOR or ROLE_ADMIN or ROLE_GOD
// – /api/test/mod for users has ROLE_MODERATOR
// – /api/test/admin for users has ROLE_ADMIN
@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/test")
public class TestController {
  @GetMapping("/all")
  public String allAccess() {
    return "Public Content.";
  }

  @GetMapping("/user")
  @PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN') or hasRole('GOD')")
  public String userAccess() {
    return "User Content.";
  }

  @GetMapping("/mod")
  @PreAuthorize("hasRole('MODERATOR')")
  public String moderatorAccess() {
    return "Moderator Board.";
  }

  @GetMapping("/admin")
  @PreAuthorize("hasRole('ADMIN')or hasRole('GOD')")
  public String adminAccess() {
    return "Admin Board.";
  }
}
