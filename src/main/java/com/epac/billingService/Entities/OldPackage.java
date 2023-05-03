package com.epac.billingService.Entities;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.io.Serializable;

@JsonIgnoreProperties(ignoreUnknown = true)
public class OldPackage extends Object {
    private String packageId;
    private int count;
    private int quantity;
    private String type;
    private float weight;
    private String destination;
    private String label;
    private int depthQty;
    private int heightQty;
    private int widthQty;
    private int delivredQty;
    private int packagedQty;
    private int maxPallet;
    private String shippingLocation;
    private String billingLocation;
    private String billingAdress;
    private String shipAccountName;
    private String billAccountName;

    public OldPackage() {
    }

    @Override
    public String toString() {
        return "OldPackage{" +
                "packageId='" + packageId + '\'' +
                ", count=" + count +
                ", quantity=" + quantity +
                ", type='" + type + '\'' +
                ", weight=" + weight +
                ", destination='" + destination + '\'' +
                ", label='" + label + '\'' +
                ", depthQty=" + depthQty +
                ", heightQty=" + heightQty +
                ", widthQty=" + widthQty +
                ", delivredQty=" + delivredQty +
                ", packagedQty=" + packagedQty +
                '}';
    }

    public String getPackageId() {
        return packageId;
    }

    public void setPackageId(String packageId) {
        this.packageId = packageId;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public float getWeight() {
        return weight;
    }

    public void setWeight(float weight) {
        this.weight = weight;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public int getDepthQty() {
        return depthQty;
    }

    public void setDepthQty(int depthQty) {
        this.depthQty = depthQty;
    }

    public int getHeightQty() {
        return heightQty;
    }

    public void setHeightQty(int heightQty) {
        this.heightQty = heightQty;
    }

    public int getWidthQty() {
        return widthQty;
    }

    public void setWidthQty(int widthQty) {
        this.widthQty = widthQty;
    }

    public int getDelivredQty() {
        return delivredQty;
    }

    public void setDelivredQty(int delivredQty) {
        this.delivredQty = delivredQty;
    }

    public int getPackagedQty() {
        return packagedQty;
    }

    public void setPackagedQty(int packagedQty) {
        this.packagedQty = packagedQty;
    }

    public int getMaxPallet() {
        return maxPallet;
    }

    public void setMaxPallet(int maxPallet) {
        this.maxPallet = maxPallet;
    }

    public String getShippingLocation() {
        return shippingLocation;
    }

    public void setShippingLocation(String shippingLocation) {
        this.shippingLocation = shippingLocation;
    }

    public String getBillingLocation() {
        return billingLocation;
    }

    public void setBillingLocation(String billingLocation) {
        this.billingLocation = billingLocation;
    }

    public String getBillingAdress() {
        return billingAdress;
    }

    public void setBillingAdress(String billingAdress) {
        this.billingAdress = billingAdress;
    }

    public String getShipAccountName() {
        return shipAccountName;
    }

    public void setShipAccountName(String shipAccountName) {
        this.shipAccountName = shipAccountName;
    }

    public String getBillAccountName() {
        return billAccountName;
    }

    public void setBillAccountName(String billAccountName) {
        this.billAccountName = billAccountName;
    }
}
