package com.akinmail.blockchain.immune.repository;

import com.akinmail.blockchain.immune.model.Session;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface SessionRepository extends MongoRepository<Session, String> {
}
