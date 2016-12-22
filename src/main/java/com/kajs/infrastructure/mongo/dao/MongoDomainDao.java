package com.kajs.infrastructure.mongo.dao;

import com.kajs.infrastructure.mongo.entity.DomainEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MongoDomainDao extends MongoRepository<DomainEntity, String>, MongoDomainDaoCustom{
}
