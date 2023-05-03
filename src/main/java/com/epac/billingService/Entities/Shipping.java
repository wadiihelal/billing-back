package com.epac.billingService.Entities;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.Data;
import org.springframework.data.annotation.Id;

import java.util.Collection;
import java.util.Date;
import java.util.Objects;
public class Shipping {
    @Id
    private Long productionPartId;
    private int qtePackaged;
    private long  orderQty;
    private long  idOrder;
    private String destination;
    private String orderNum;
    private String barcode;
    private String Title;
    private String publisher;
    private String label;
    private String accountName;
    private String note;
    private String clientPoNumber;
    private Date dateComplete;
    private float weight;
    private String isbn10;
    private String clientPartNum;
    private String coilType;
    private String shipAttn;
    private String billAttn;
    private String billingLocation;
    private String billingCountry;
    private String billingAddress;
    private String shippingLocation;
    private String shippingCountry;
    private String partid;
    private int pnlPrintingNumber;
    private  double unitPrice;
    private String bindingType;
    private int    qtyRequested;
    private String shipAccountName;
    private String billAccountName;
    private Collection<PackingSlips> packingSlips;
    private Collection<Package> packages;



    public Shipping() {
    }

    public Shipping(String destination, String orderNum, String barcode, String title, String publisher, String label, String accountName, String note) {
        this.destination = destination;
        this.orderNum = orderNum;
        this.barcode = barcode;
        Title = title;
        this.publisher = publisher;
        this.label = label;
        this.accountName = accountName;
        this.note = note;
    }

    public Shipping(Long productionPartId, int qtepackaged) {
        this.productionPartId = productionPartId;
        this.qtePackaged = qtepackaged;
    }

    public String getAccountName() {
        return accountName;
    }

    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public String getBarcode() {
        return barcode;
    }

    public void setBarcode(String barcode) {
        this.barcode = barcode;
    }

    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public String getOrderNum() {
        return orderNum;
    }

    public void setOrderNum(String orderNum) {
        this.orderNum = orderNum;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public Long getProductionPartId() {
        return productionPartId;
    }

    public void setProductionPartId(Long productionPartId) {
        this.productionPartId = productionPartId;
    }

    public int getQtePackaged() {
        return qtePackaged;
    }

    public void setQtePackaged(int qtePackaged) {
        this.qtePackaged = qtePackaged;
    }

    public Collection<PackingSlips> getPackingSlips() {
        return packingSlips;
    }

    public void setPackingSlips(Collection<PackingSlips> packingSlips) {
        this.packingSlips = packingSlips;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public Date getDateComplete() {
        return dateComplete;
    }

    public void setDateComplete(Date dateComplete) {
        this.dateComplete = dateComplete;
    }

    public Collection<Package> getPackages() {
        return packages;
    }

    public void setPackages(Collection<Package> packages) {
        this.packages = packages;
    }

    public float getWeight() {
        return weight;
    }

    public void setWeight(float weight) {
        this.weight = weight;
    }

    public long getOrderQty() {
        return orderQty;
    }

    public String getClientPoNumber() {
        return clientPoNumber;
    }

    public void setClientPoNumber(String clientPoNumber) {
        this.clientPoNumber = clientPoNumber;
    }

    public void setOrderQty(long orderQty) {
        this.orderQty = orderQty;
    }


    public long getIdOrder() {
        return idOrder;
    }

    public void setIdOrder(long idOrder) {
        this.idOrder = idOrder;
    }

    public String getIsbn10() {
        return isbn10;
    }

    public void setIsbn10(String isbn10) {
        this.isbn10 = isbn10;
    }

    public String getClientPartNum() {
        return clientPartNum;
    }

    public void setClientPartNum(String clientPartNum) {
        this.clientPartNum = clientPartNum;
    }

    public String getCoilType() {
        return coilType;
    }

    public void setCoilType(String coilType) {
        this.coilType = coilType;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Shipping shipping = (Shipping) o;
        return Objects.equals(productionPartId, shipping.productionPartId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(productionPartId);
    }

    public String getShipAttn() {
        return shipAttn;
    }

    public void setShipAttn(String shipAttn) {
        this.shipAttn = shipAttn;
    }

    public String getBillAttn() {
        return billAttn;
    }

    public void setBillAttn(String billAttn) {
        this.billAttn = billAttn;
    }

    public String getBillingLocation() {
        return billingLocation;
    }

    public void setBillingLocation(String billingLocation) {
        this.billingLocation = billingLocation;
    }

    public String getBillingCountry() {
        return billingCountry;
    }

    public void setBillingCountry(String billingCountry) {
        this.billingCountry = billingCountry;
    }

    public String getBillingAddress() {
        return billingAddress;
    }

    public void setBillingAddress(String billingAddress) {
        this.billingAddress = billingAddress;
    }

    public String getPartid() {
        return partid;
    }

    public void setPartid(String partid) {
        this.partid = partid;
    }

    public int getPnlPrintingNumber() {
        return pnlPrintingNumber;
    }

    public void setPnlPrintingNumber(int pnlPrintingNumber) {
        this.pnlPrintingNumber = pnlPrintingNumber;
    }

    public double getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(double unitPrice) {
        this.unitPrice = unitPrice;
    }

    public String getShippingLocation() {
        return shippingLocation;
    }

    public void setShippingLocation(String shippingLocation) {
        this.shippingLocation = shippingLocation;
    }

    public String getShippingCountry() {
        return shippingCountry;
    }

    public void setShippingCountry(String shippingCountry) {
        this.shippingCountry = shippingCountry;
    }

    public String getBindingType() {
        return bindingType;
    }

    public void setBindingType(String bindingType) {
        this.bindingType = bindingType;
    }

    public int getQtyRequested() {
        return qtyRequested;
    }

    public void setQtyRequested(int qtyRequested) {
        this.qtyRequested = qtyRequested;
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

