package com.rogue.search.service;

import com.rogue.search.collections.User;
import com.rogue.search.dto.request.CreateUserRequest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface IUserService {
  String createUser(CreateUserRequest createUserRequest);

  Page<User> getAllUsers(String filters, Pageable pageable);
}
