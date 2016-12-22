package com.kajs.infrastructure.mongo.dao;

import com.kajs.infrastructure.mongo.entity.KeeperEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MongoKeeperDao extends MongoRepository<KeeperEntity, String>, MongoKeeperDaoCustom {
}
