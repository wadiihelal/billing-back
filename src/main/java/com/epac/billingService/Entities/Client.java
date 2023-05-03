package com.epac.billingService.Entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Client {
    @Id
    private String clientId;
    private String accountManager ;
    private String billingLocation;
    private String billingCountry;
    private String billingAdress;
    private String siren;
    private String accountName;
    private String shippingLocation;
    private String shippingCountry;
    private String shippingAdress;
    private String email ;
    private String phone;
    private String name ;
    private String tva;
    private boolean useISBN10INVOICE;
    private boolean useISBN13INVOICE;
    private boolean useClientPartNumberINVOICE;
    private boolean useClientPOINVOICE;
    private  boolean usePrintNumberINVOICE;   }
