package com.kajs.domain.model;

public class DomainEffectiveness {
    private String domain;
    private String amount;
    private String max;
    private String effectiveness;
    private String fosterFather;

    public String getDomain() {
        return domain;
    }

    public void setDomain(String domain) {
        this.domain = domain;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getMax() {
        return max;
    }

    public void setMax(String max) {
        this.max = max;
    }

    public String getEffectiveness() {
        return effectiveness;
    }

    public void setEffectiveness(String effectiveness) {
        this.effectiveness = effectiveness;
    }

    public String getFosterFather() {
        return fosterFather;
    }

    public void setFosterFather(String fosterFather) {
        this.fosterFather = fosterFather;
    }

    @Override
    public String toString() {
        return "DomainEffectiveness{" +
                "domain='" + domain + '\'' +
                ", amount='" + amount + '\'' +
                ", max='" + max + '\'' +
                ", effectiveness='" + effectiveness + '\'' +
                ", fosterFather='" + fosterFather + '\'' +
                '}';
    }
}
