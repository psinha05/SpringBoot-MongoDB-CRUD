package com.example.demo.repository;

/*
 * UserRepository used for performed the CRUD
 * operations in MongoDB database 
 * 
 * 
 */

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import com.example.demo.User;

@Repository
public interface UserRepository extends MongoRepository<User, String>{

	Optional<User> findById(String id);

	
}
