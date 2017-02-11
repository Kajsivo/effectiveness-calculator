package com.kajs.infrastructure.mongo.repository;

import com.kajs.domain.model.Effectiveness;
import com.kajs.domain.repository.EffectivenessRepository;
import com.kajs.infrastructure.mongo.dao.MongoEffectivenessDao;
import com.kajs.infrastructure.mongo.entity.EffectivenessEntity;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class MongoEffectivenessRepository implements EffectivenessRepository {

    private final MongoEffectivenessDao mongoEffectivenessDao;
    private final ModelMapper modelMapper;

    @Autowired
    public MongoEffectivenessRepository(MongoEffectivenessDao mongoEffectivenessDao, ModelMapper modelMapper) {
        this.mongoEffectivenessDao = mongoEffectivenessDao;
        this.modelMapper = modelMapper;
    }

    @Override
    public Effectiveness save(Effectiveness effectiveness) {
        EffectivenessEntity entity = mongoEffectivenessDao.save(modelMapper.map(effectiveness, EffectivenessEntity.class));
        return modelMapper.map(entity, Effectiveness.class);
    }

    @Override
    public List<Effectiveness> getByMonthAndYear(String month, String year) {
        List<EffectivenessEntity> effectivenessEntities = mongoEffectivenessDao.getByMonthAndYear(month, year);
        return effectivenessEntities
                .stream()
                .map(effectivenessEntity -> modelMapper.map(effectivenessEntity, Effectiveness.class))
                .collect(Collectors.toList());
    }

    @Override
    public void saveDomainAndInvoiceAmount(String domain, double invoiceAmount) {
        //TODO save domain and invoice amount in mongo
    }

    @Override
    public void saveDomainKeeperAndDebtor(String domain, String keeper, String debtor) {
        //TODO save domain, keeper and debtor in mongo
    }

    @Override
    public void saveDomainAndStartDate(String domain, String startDate) {
        //TODO save domain and start date in mongo
    }

    @Override
    public void saveDomainAndBarricade(String domain, String barricade) {
        //TODO save domain and barricade in mongo
    }
}
