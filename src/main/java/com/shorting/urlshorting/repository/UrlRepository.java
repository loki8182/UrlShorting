package com.shorting.urlshorting.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.shorting.urlshorting.module.UrlShorting;

@Repository
public interface UrlRepository extends MongoRepository<UrlShorting, String> {

	

}
