package com.kajs.infrastructure.mongo.dao;

import com.kajs.infrastructure.mongo.entity.DomainEntity;

public interface MongoDomainDaoCustom {
    void saveDomain(DomainEntity domainEntity);
}
