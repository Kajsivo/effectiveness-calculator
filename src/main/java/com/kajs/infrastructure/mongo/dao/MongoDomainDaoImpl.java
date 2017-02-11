package com.kajs.infrastructure.mongo.dao;
import com.kajs.infrastructure.mongo.entity.DomainEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Component;

@Component
public class MongoDomainDaoImpl implements MongoDomainDaoCustom{

    private final MongoOperations mongo;

    @Autowired
    public MongoDomainDaoImpl(MongoOperations mongo) {
        this.mongo = mongo;
    }

    @Override
    public void saveDomain(DomainEntity domain) {
        Criteria criteria = Criteria.where("name").is(domain.getName());
        Query query = new Query(criteria);
        Update update = new Update().set("name", domain.getName());
        if(domain.getStartDate() != null && !domain.getStartDate().isEmpty()) {
            update.set("startDate", domain.getStartDate());
        }
        mongo.upsert(query, update, DomainEntity.class);
    }
}
