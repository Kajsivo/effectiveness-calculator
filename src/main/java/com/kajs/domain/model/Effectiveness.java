package com.kajs.domain.model;

public class Effectiveness {
    private String domain;
    private String amount;
    private String max;
    private String effectiveness;
    private String keeper;

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

    public String getKeeper() {
        return keeper;
    }

    public void setKeeper(String keeper) {
        this.keeper = keeper;
    }

    @Override
    public String toString() {
        return "Effectiveness{" +
                "domain='" + domain + '\'' +
                ", amount='" + amount + '\'' +
                ", max='" + max + '\'' +
                ", effectiveness='" + effectiveness + '\'' +
                ", keeper='" + keeper + '\'' +
                '}';
    }
}
