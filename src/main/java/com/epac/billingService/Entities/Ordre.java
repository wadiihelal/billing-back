package com.epac.billingService.Entities;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.*;
@Document(collection = "ordre")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Ordre {
    @Id
    private Long idOrder;
    private String clientPoNumber;
    private Date dateReceipt;
    private Date proofDate;
    private Date dateDelivered;
    private int quantity;
    private  String priorityLevel;
    private String clientId;
    private Date completeDate;
    private String status;
    private String   productionNote;
    private String requesterName;
    private String  owner;
    private Date dateExpacted;
    private String shipAccountName;
    protected String billAccountName;
    private String orderNum;
    private List<PackingSlips> packingSlips;
    private String Title;
    private long bonLivraisonlId;
    private String destination;
    private boolean proformaCreated;
    private String shipAttn;
    private String billAttn;
    private String surcharge;
    private Date invoiceDelay;
    private ArrayList<ProductionPart> productionParts;
    private String email ;
    private String phone;
    private ArrayList <Fees> totalCost ;
    private String billingLocation;
    private String billingCountry;
    private String billingAdress;
    private String shippingLocation;
    private String shippingCountry;
    private String shippingAdress;
    private boolean useISBN10INVOICE;
    private boolean useISBN13INVOICE;
    private boolean useClientPartNumberINVOICE;
    private boolean useClientPOINVOICE;
    private  boolean usePrintNumberINVOICE;
    public boolean ordreInvoiced;
    public boolean Newordre;

    public Date getDateExpacted() {
        return dateExpacted;
    }

    public void setDateExpacted(Date dateExpacted) {
        this.dateExpacted = dateExpacted;
    }

    @Override
    public String toString() {
        return "Ordre{" +
                "idOrder=" + idOrder +
                ", clientPoNumber='" + clientPoNumber + '\'' +
                ", dateReceipt=" + dateReceipt +
                ", proofDate=" + proofDate +
                ", dateDelivered=" + dateDelivered +
                ", quantity=" + quantity +
                ", priorityLevel='" + priorityLevel + '\'' +
                ", clientId='" + clientId + '\'' +
                ", completeDate=" + completeDate +
                ", status='" + status + '\'' +
                ", productionNote='" + productionNote + '\'' +
                ", requesterName='" + requesterName + '\'' +
                ", owner='" + owner + '\'' +
                ", dateExpacted=" + dateExpacted +
                ", shipAccountName='" + shipAccountName + '\'' +
                ", billAccountName='" + billAccountName + '\'' +
                ", orderNum='" + orderNum + '\'' +
                ", packingSlips=" + packingSlips +
                ", Title='" + Title + '\'' +
                ", bonLivraisonlId=" + bonLivraisonlId +
                ", destination='" + destination + '\'' +
                ", proformaCreated=" + proformaCreated +
                ", shipAttn='" + shipAttn + '\'' +
                ", billAttn='" + billAttn + '\'' +
                ", surcharge='" + surcharge + '\'' +
                ", invoiceDelay=" + invoiceDelay +
                ", productionParts=" + productionParts +
                ", email='" + email + '\'' +
                ", phone='" + phone + '\'' +
                ", totalCost=" + totalCost +
                ", billingLocation='" + billingLocation + '\'' +
                ", billingCountry='" + billingCountry + '\'' +
                ", billingAdress='" + billingAdress + '\'' +
                ", shippingLocation='" + shippingLocation + '\'' +
                ", shippingCountry='" + shippingCountry + '\'' +
                ", shippingAdress='" + shippingAdress + '\'' +
                ", useISBN10INVOICE=" + useISBN10INVOICE +
                ", useISBN13INVOICE=" + useISBN13INVOICE +
                ", useClientPartNumberINVOICE=" + useClientPartNumberINVOICE +
                ", useClientPOINVOICE=" + useClientPOINVOICE +
                ", usePrintNumberINVOICE=" + usePrintNumberINVOICE +
                ", OrdreInvoiced=" + ordreInvoiced +
                ", Newordre=" + Newordre +
                '}';
    }
}
