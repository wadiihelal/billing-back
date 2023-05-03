package com.epac.billingService.Entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.HashSet;

@JsonInclude(JsonInclude.Include.NON_DEFAULT)
@JsonIgnoreProperties(ignoreUnknown = true)
public class InvoiceLine {

    private long idOrder;
    private String PartId;
    private int pnlPrintingNumber;
    private String PoNumber;
    private int productionPage;
    private String shipAttn;
    private String billAttn;
    private String clientPartNum;
    private float unitPrice;
    private float qty;
    private String type;
    private String name;
    private Float value;
    private float transportCost;
    private String isbn10;
    private String isbn13;
    private String title;
    private HashSet<Long> psIds;
    private String surcharge;
    private float total ;
    private boolean diffLine;

  public int getPnlPrintingNumber() {
    return pnlPrintingNumber;
  }

  public void setPnlPrintingNumber(int pnlPrintingNumber) {
    this.pnlPrintingNumber = pnlPrintingNumber;
  }

    public String getPartId() {
        return PartId;
    }

    public void setPartId(String partId) {
        PartId = partId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Float getValue() {
        return value;
    }

    public void setValue(Float value) {
        this.value = value;
    }

    public boolean isDiffLine() {
        return diffLine;
    }

    public int getProductionPage() {
        return productionPage;
    }

    public void setProductionPage(int productionPage) {
        this.productionPage = productionPage;
    }

    public void setDiffLine(boolean diffLine) {
        this.diffLine = diffLine;
    }

    public float getTotal() {
        return total;
    }

    public void setTotal(float total) {
        this.total = total;
    }

    public InvoiceLine() { }
    public long getIdOrder() {
        return idOrder;
    }
    public void setIdOrder(long idOrder) {
        this.idOrder = idOrder;
    }
    public String getPoNumber() {
        return PoNumber;
    }
    public void setPoNumber(String poNumber) {
        this.PoNumber = poNumber;
    }

    public HashSet<Long> getPsIds() {
        return psIds;
    }
    public void setPsIds(HashSet<Long> psIds) {
        this.psIds = psIds;
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

    public String getClientPartNum() {
        return clientPartNum;
    }
    public void setClientPartNum(String clientPartNum) {
        this.clientPartNum = clientPartNum;
    }
    public float getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(float unitPrice) {
        this.unitPrice = unitPrice;
    }

    public float getQty() {
        return qty;
    }

    public void setQty(float qty) {
        this.qty = qty;
    }

    public String getIsbn() {
        return isbn10;
    }

    public void setIsbn(String isbn) {
        this.isbn10 = isbn;
    }
    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }

    public String getSurcharge() {
        return surcharge;
    }

    public String getIsbn13() {
        return isbn13;
    }

    public void setIsbn13(String isbn13) {
        this.isbn13 = isbn13;
    }

    public String getIsbn10() {
        return isbn10;
    }

    public void setIsbn10(String isbn10) {
        this.isbn10 = isbn10;
    }

    public void setSurcharge(String surcharge) {
        this.surcharge = surcharge;
    }

    @Override
    public String toString() {
        return "InvoiceLine{" +
                "idOrder=" + idOrder +
                ", partId=" + PartId +
                ", PoNumber='" + PoNumber + '\'' +
                ", shipAttn='" + shipAttn + '\'' +
                ", billAttn='" + billAttn + '\'' +

                ", clientPartNum='" + clientPartNum + '\'' +
                ", unitPrice=" + unitPrice +
                ", qty=" + qty +
                ", isbn10='" + isbn10 + '\'' +
                ", isbn13='" + isbn13 + '\'' +
                ", title='" + title + '\'' +
                ", psIds=" + psIds +
                ", surcharge='" + surcharge + '\'' +
                ", total=" + total +
                '}';
    }
}
