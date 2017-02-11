package com.kajs.infrastructure.mongo.dao;

import com.kajs.infrastructure.mongo.entity.EffectivenessEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class MongoEffectivenessDaoImpl implements MongoEffectivenessDaoCustom {

    private final MongoOperations mongo;

    @Autowired
    public MongoEffectivenessDaoImpl(MongoOperations mongo) {
        this.mongo = mongo;
    }

    @Override
    public List<EffectivenessEntity> getByMonthAndYear(String month, String year) {
        Criteria criteria = Criteria.where("MONTH").is(month);
        Query query = new Query(criteria);
        //return mongo.find(query, List<EffectivenessEntity.class);
        return new ArrayList<>();
    }
}
