package com.example.identityservice.repository;


import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.example.identityservice.entity_.UserCredential;

import java.util.Optional;

@Repository
public interface UserCredentialRepository extends MongoRepository<UserCredential,String> {
    Optional<UserCredential> findByName(String username);
}
