package com.akinmail.blockchain.immune.repository;

import com.akinmail.blockchain.immune.model.Hospital;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface HospitalRepository extends MongoRepository<Hospital, String> {
}
