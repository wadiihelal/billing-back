package com.epac.billingService.Entities;

public class Fees {
    private String type;
    private String value;

    public String getType() {
        return type;
    }

    public Fees() {
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
