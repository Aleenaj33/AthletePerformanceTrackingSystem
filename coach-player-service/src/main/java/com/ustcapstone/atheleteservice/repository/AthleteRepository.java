package com.ustcapstone.atheleteservice.repository;




import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.ustcapstone.atheleteservice.model.Player;

@Repository
public interface AthleteRepository extends MongoRepository<Player, Integer> {
    // Custom query methods can be defined here if needed
}

