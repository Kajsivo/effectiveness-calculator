package com.kajs.infrastructure.mongo.repository;

import com.kajs.domain.model.Domain;
import com.kajs.domain.repository.DomainRepository;
import com.kajs.infrastructure.mongo.dao.MongoDomainDao;
import com.kajs.infrastructure.mongo.entity.DomainEntity;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MongoDomainRepository implements DomainRepository {

    private final MongoDomainDao mongoDomainDao;
    private final ModelMapper modelMapper;

    @Autowired
    public MongoDomainRepository(MongoDomainDao mongoDomainDao, ModelMapper modelMapper) {
        this.mongoDomainDao = mongoDomainDao;
        this.modelMapper = modelMapper;
    }

    @Override
    public void save(Domain domain) {
        mongoDomainDao.saveDomain(modelMapper.map(domain, DomainEntity.class));
    }
}
