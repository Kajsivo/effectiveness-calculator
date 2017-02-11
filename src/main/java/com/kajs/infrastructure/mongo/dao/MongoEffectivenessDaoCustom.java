package com.kajs.infrastructure.mongo.dao;

import com.kajs.infrastructure.mongo.entity.EffectivenessEntity;

import java.util.List;

public interface MongoEffectivenessDaoCustom {

    List<EffectivenessEntity> getByMonthAndYear(String month, String year);
}
