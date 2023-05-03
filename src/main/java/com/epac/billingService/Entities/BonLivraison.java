package com.epac.billingService.Entities;


import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;

import java.util.*;
public class BonLivraison {
    @Id
    private  long bonLivraisonlId;
    private Date creationDate;
    private String trackingNumber;
    private  String status;
    private Date  validationDate;
    @Transient
    private  int quantity;
    private String shippingAdress;
    private List<PackingSlips> packingSlips;
    private String career;
    @Transient
    private ArrayList<Long> psIds;
    @Transient
    private ArrayList<String> po;
    @Transient
    private String client;
    @Transient
    private int nbPS;
    @Transient
    private double weight;



    public BonLivraison() {
    }



    public long getBonLivraisonlId() {
        return bonLivraisonlId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public void setBonLivraisonlId(long bonLivraisonlId) {
        this.bonLivraisonlId = bonLivraisonlId;
    }

    public void setBonLivraisonlId(Long bonLivraisonlId) {
        this.bonLivraisonlId = bonLivraisonlId;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public String getTrackingNumber() {
        return trackingNumber;
    }

    public void setTrackingNumber(String trackingNumber) {
        this.trackingNumber = trackingNumber;
    }

    public Date getValidationDate() {
        return validationDate;
    }

    public void setValidationDate(Date validationDate) {
        this.validationDate = validationDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<PackingSlips> getPackingSlips() {
        return packingSlips;
    }

    public void setPackingSlips(List<PackingSlips> packingSlips) {
        this.packingSlips = packingSlips;
    }

    public String getShippingAdress() {
        return shippingAdress;
    }

    public void setShippingAdress(String shippingAdress) {
        this.shippingAdress = shippingAdress;
    }


    public String getCareer() {
        return career;
    }

    public void setCareer(String career) {
        this.career = career;
    }

    public ArrayList<Long> getPsIds() {
        return psIds;
    }

    public void setPsIds(ArrayList<Long> psIds) {
        this.psIds = psIds;
    }

    public ArrayList<String> getPo() {
        return po;
    }

    public void setPo(ArrayList<String> po) {
        this.po = po;
    }

    public String getClient() {
        return client;
    }

    public void setClient(String client) {
        this.client = client;
    }

    public int getNbPS() {
        return nbPS;
    }

    public void setNbPS(int nbPS) {
        this.nbPS = nbPS;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public static Comparator<BonLivraison> sortBonLivraison=new Comparator<BonLivraison>() {
        @Override
        public int compare(BonLivraison p1, BonLivraison p2)
        {
            return (int) (p1.getBonLivraisonlId()-p2.getBonLivraisonlId());
        }
    };


}
