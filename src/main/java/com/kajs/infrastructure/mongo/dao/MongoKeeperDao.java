package com.kajs.infrastructure.mongo.dao;

import com.kajs.infrastructure.mongo.entity.KeeperEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

@Component
public interface MongoKeeperDao extends MongoRepository<KeeperEntity, String>, MongoKeeperDaoCustom {
}
