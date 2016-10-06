package com.codependent.reactive;

import java.util.concurrent.CompletableFuture;
import java.util.stream.Stream;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

public interface PersonRepository extends MongoRepository<Person, String>{
	
	CompletableFuture<Person> findById(String id);
	
	@Query("{}")
	Stream<Person> all();
	
}
