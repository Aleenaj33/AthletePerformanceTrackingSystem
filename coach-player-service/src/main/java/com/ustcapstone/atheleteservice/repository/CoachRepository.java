package com.ustcapstone.atheleteservice.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.ustcapstone.atheleteservice.model.Coach;

public interface CoachRepository extends MongoRepository<Coach, Integer> {
   
}