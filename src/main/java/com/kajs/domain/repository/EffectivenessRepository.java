package com.kajs.domain.repository;

import com.kajs.domain.model.Effectiveness;

import java.util.List;

public interface EffectivenessRepository {
    Effectiveness save(Effectiveness effectiveness);
    List<Effectiveness> getByMonthAndYear(String month, String year);
    void saveDomainAndInvoiceAmount(String domain, double invoiceAmount);
    void saveDomainKeeperAndDebtor(String domain, String keeper, String debtor);
    void saveDomainAndStartDate(String domain, String startDate);
    void saveDomainAndBarricade(String domain, String barricade);
}
