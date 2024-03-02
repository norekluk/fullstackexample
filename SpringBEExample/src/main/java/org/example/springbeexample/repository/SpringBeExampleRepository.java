package org.example.springbeexample.repository;

import org.example.springbeexample.data.ExchangeRate;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface SpringBeExampleRepository extends MongoRepository<ExchangeRate, String> {}


