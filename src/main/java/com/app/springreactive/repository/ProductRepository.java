package com.app.springreactive.repository;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

import com.app.springreactive.model.Product;
public interface ProductRepository extends ReactiveMongoRepository<Product, String> {
}