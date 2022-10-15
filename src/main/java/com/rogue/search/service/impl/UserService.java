package com.rogue.search.service.impl;

import com.rogue.search.collections.User;
import com.rogue.search.config.MongoFilter;
import com.rogue.search.dto.request.CreateUserRequest;
import com.rogue.search.repository.IUserRepository;
import com.rogue.search.service.IUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.support.PageableExecutionUtils;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService implements IUserService {
  private final IUserRepository userRepository;
  private final MongoTemplate mongoTemplate;

  @Override
  public Page<User> getAllUsers(String filters, Pageable pageable) {
    String collectionName = "user";
    final Query query = MongoFilter.createSearchCriteria(filters);
    long count = mongoTemplate.count(query, collectionName);
    var data = getData(pageable, collectionName, query);
    return PageableExecutionUtils.getPage(data, pageable, () -> count);
  }

  private List<User> getData(Pageable pageable, String collectionName, Query query) {
    query.with(pageable);
    return mongoTemplate.find(query, User.class, collectionName);
  }

  @Override
  public String createUser(CreateUserRequest createUserRequest) {
    final User user = User.builder()
            .firstName(createUserRequest.getFirstName())
            .lastName(createUserRequest.getLastName())
            .email(createUserRequest.getEmail())
            .phNo(createUserRequest.getPhNo())
            .address(createUserRequest.getAddress())
            .marks(createUserRequest.getMarks())
            .properties(createUserRequest.getProperties())
            .build();
    userRepository.save(user);
    return user.toString();
  }
}
