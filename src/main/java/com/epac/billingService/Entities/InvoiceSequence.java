package com.epac.billingService.Entities;

import org.springframework.data.annotation.Id;

public class InvoiceSequence {
    @Id
    int index;
    String name;
    public int getIndex() {
        return index;
    }
    public void setIndex(int index) {
        this.index = index;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
