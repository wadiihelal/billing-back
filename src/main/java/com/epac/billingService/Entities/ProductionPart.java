package com.epac.billingService.Entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
@Data
@NoArgsConstructor
@AllArgsConstructor

public class ProductionPart  {

    private long productionPartId;
    private String barcode;
    private String title;
    private String partid;
    private String publisher;
    private String publisherId;
    private long idJob;
    private String status ;
    private int    qtyDelivered;
    private int    qtyMin;
    private int    qtyMax;
    private float weight;
    private int producedBinder;
    private int    qtyRequested;
    private String packageType;
    private long parentId;
    private String isbn10;
    private String clientPartNum;
    private String coilType;
    private Pnl pnl;
    private  double unitPrice;
    private String bindingType;
    private String productionNote;
    private int productionPage;
    private Collection<Package> packages;  }

