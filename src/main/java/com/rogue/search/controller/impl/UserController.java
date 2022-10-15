package com.rogue.search.controller.impl;

import com.rogue.search.collections.User;
import com.rogue.search.controller.IUserController;
import com.rogue.search.dto.request.CreateUserRequest;
import com.rogue.search.service.IUserService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@AllArgsConstructor
@Component
public class UserController implements IUserController {
  @Autowired
  private final IUserService userService;

  @Override
  public ResponseEntity<String> createUser(CreateUserRequest createUserRequest) {
    return ResponseEntity.ok(userService.createUser(createUserRequest));
  }

  @Override
  public ResponseEntity<Page<User>> getAllUsers(String filters, Pageable pageable) {
    return ResponseEntity.ok(userService.getAllUsers(filters, pageable));
  }
}
