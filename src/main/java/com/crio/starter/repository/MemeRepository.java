package com.crio.starter.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

import com.crio.starter.model.MemeEntity;

@Repository
public interface MemeRepository extends MongoRepository<MemeEntity, String> {

    MemeEntity  findBymemeid(String memeid);

}