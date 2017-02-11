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
        EffectivenessEntity effectivenessEntity = new EffectivenessEntity();
        effectivenessEntity.setDomain("d1");
        effectivenessEntity.setAmount("250");
        effectivenessEntity.setDebtor(true);
        effectivenessEntity.setEffectiveness("41123");
        effectivenessEntity.setMax("123213");
        effectivenessEntity.setKeeper("asdasdasd");

        EffectivenessEntity effectivenessEntity2 = new EffectivenessEntity();
        effectivenessEntity2.setDomain("d12");
        effectivenessEntity2.setAmount("250123");
        effectivenessEntity2.setDebtor(false);
        effectivenessEntity2.setEffectiveness("12341123");
        effectivenessEntity2.setMax("1232131231231");
        effectivenessEntity2.setKeeper("asdasdasddasdasd");
        List<EffectivenessEntity> effectivenessEntities = new ArrayList<>();
        effectivenessEntities.add(effectivenessEntity);
        effectivenessEntities.add(effectivenessEntity2);
        return effectivenessEntities;
        //return mongo.find(query, List<EffectivenessEntity.class);
    }
}
