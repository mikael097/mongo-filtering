package com.rogue.search.controller;

import com.rogue.search.collections.User;
import com.rogue.search.dto.request.CreateUserRequest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1")
public interface IUserController {
  @PostMapping("/create")
  ResponseEntity<String> createUser(@RequestBody CreateUserRequest createUserRequest);

  @GetMapping("/users")
  ResponseEntity<Page<User>> getAllUsers(@RequestParam(name = "filters", defaultValue = "") String filters, Pageable pageable);
}
