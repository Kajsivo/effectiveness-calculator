package com.kajs.domain.repository;

import com.kajs.domain.model.DomainEffectiveness;

public interface DomainRepository {
    DomainEffectiveness save(DomainEffectiveness domainEffectiveness);
}
