package com.epac.billingService.Entities;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
@Document

public class Invoice {
    @Id
    private String idInvoice;
    private Date dueDate;
    private Date invoiceDate;
    private String accountName;
    private String billingAddress;
    private String accountManager;
    private boolean exported;
    private String billAttn;
    private String billingLocation;
    private String billingCountry;
    private String shipAttn;
    private String shippingAddress;
    private String surcharge;
    private float total;
    private String status;
     private String shippingLocation;
    private String shippingCountry;
    private int pnlPrintingNumber;
    private ArrayList<InvoiceLine> invoiceLines;
    private String siren;
    private boolean annulated ;
    private ArrayList <Fees> totalCost ;
    private String shipAccountName;
    private  String billAccountName;

    public ArrayList<Fees> getTotalCost() {
        return totalCost;
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

  public void setTotalCost(ArrayList<Fees> totalCost) {
        this.totalCost = totalCost;
    }

    public boolean isAnnulated() {
        return annulated;
    }

    public void setAnnulated(boolean annulated) {
        this.annulated = annulated;
    }

    public Invoice(Date dueDate, String accountName, String billingAddress) {
        this.dueDate = dueDate;
        this.accountName = accountName;
        this.billingAddress = billingAddress;
    }

    public Invoice() {
    }

    public String getSiren() {
        return siren;
    }

    public void setSiren(String siren) {
        this.siren = siren;
    }

    public String getIdInvoice() {
        return idInvoice;
    }
    public void setIdInvoice(String idInvoice) {
        this.idInvoice = idInvoice;
    }

    public Date getInvoiceDate() {
        return invoiceDate;
    }

    public void setInvoiceDate(Date invoiceDate) {
        this.invoiceDate = invoiceDate;
    }

    public Date getDueDate() {
        return dueDate;
    }

    public void setDueDate(Date dueDate) {
        this.dueDate = dueDate;
    }

    public String getAccountName() {
        return accountName;
    }

    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }

    public ArrayList<InvoiceLine> getInvoiceLines() {
        return invoiceLines;
    }

    public void setInvoiceLines(ArrayList<InvoiceLine> invoiceLines) {
        this.invoiceLines = invoiceLines;
    }

    public String getBillingAddress() {
        return billingAddress;
    }

    public void setBillingAddress(String billingAddress) {
        this.billingAddress = billingAddress;
    }

    public String getAccountManager() {
        return accountManager;
    }

    public void setAccountManager(String accountManager) {
        this.accountManager = accountManager;
    }

    public boolean isExported() {
        return exported;
    }

    public void setExported(boolean exported) {
        this.exported = exported;
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

    public String getShipAttn() {
        return shipAttn;
    }

    public void setShipAttn(String shipAttn) {
        this.shipAttn = shipAttn;
    }

    public String getShippingAddress() {
        return shippingAddress;
    }

    public void setShippingAddress(String shippingAddress) {
        this.shippingAddress = shippingAddress;
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

    public int getPnlPrintingNumber() {
        return pnlPrintingNumber;
    }

    public void setPnlPrintingNumber(int pnlPrintingNumber) {
        this.pnlPrintingNumber = pnlPrintingNumber;
    }

    public String getSurcharge() {
        return surcharge;
    }

    public void setSurcharge(String surcharge) {
        this.surcharge = surcharge;
    }

    public float getTotal() {
        return total;
    }

    public void setTotal(float total) {
        this.total = total;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Invoice{" +
                "idInvoice=" + idInvoice +
                ", dueDate=" + dueDate +
                ", invoiceDate=" + invoiceDate +
                ", accountName='" + accountName + '\'' +
                ", billingAddress='" + billingAddress + '\'' +
                ", accountManager='" + accountManager + '\'' +
                ", exported=" + exported +
                ", billAttn='" + billAttn + '\'' +
                ", billingLocation='" + billingLocation + '\'' +
                ", billingCountry='" + billingCountry + '\'' +
                ", shipAttn='" + shipAttn + '\'' +
                ", shippingAddress='" + shippingAddress + '\'' +
                ", surcharge='" + surcharge + '\'' +
                ", total=" + total +
                ", status='" + status + '\'' +
                ", shippingLocation='" + shippingLocation + '\'' +
                ", shippingCountry='" + shippingCountry + '\'' +
                ", pnlPrintingNumber=" + pnlPrintingNumber +
                ", invoiceLines=" + invoiceLines +
                '}';
    }
}
