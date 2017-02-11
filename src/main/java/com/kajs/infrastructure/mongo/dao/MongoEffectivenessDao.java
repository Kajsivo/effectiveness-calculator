package com.kajs.infrastructure.mongo.dao;

import com.kajs.infrastructure.mongo.entity.EffectivenessEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Component;

@Component
public interface MongoEffectivenessDao extends MongoRepository<EffectivenessEntity, String>, MongoEffectivenessDaoCustom {
}
