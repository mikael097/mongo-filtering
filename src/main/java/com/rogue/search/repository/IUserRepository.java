package com.rogue.search.repository;

import com.rogue.search.collections.User;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface IUserRepository extends MongoRepository<User, String> {
}
