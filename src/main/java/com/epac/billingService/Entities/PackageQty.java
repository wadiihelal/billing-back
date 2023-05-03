package com.epac.billingService.Entities;


import org.springframework.data.annotation.Id;

public class PackageQty {
    @Id

    private String packageId;
    private int quantity;

    @Override
    public String toString() {
        return "PackageQty{" +
                "packageId='" + packageId + '\'' +
                ", quantity=" + quantity +
                '}';
    }

    public PackageQty(String packageId, int quantity) {
        this.packageId = packageId;
        this.quantity = quantity;
    }

    public String getPackageId() {
        return packageId;
    }

    public void setPackageId(String packageId) {
        this.packageId = packageId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public PackageQty() {
    }
}
