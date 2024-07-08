package com.example.CoderHack.repository;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.example.CoderHack.entity.Contestent;

public interface CoderHackRepository extends MongoRepository<Contestent, String> {

    Optional<Contestent> findByUserId(String id);
    
}
