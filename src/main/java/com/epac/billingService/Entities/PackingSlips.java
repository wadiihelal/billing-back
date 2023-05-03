package com.epac.billingService.Entities;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.*;
@Document

    @JsonIgnoreProperties(ignoreUnknown = true)
    public class PackingSlips {
        @Id
        private Long psId;
        private int quantity;//number of book in pallet
        private  String statut;
        private String type;
        private int packageQty;//nomber of package
        private int  partialPackage;
        private Date creationDate;
        private boolean shiptoday;
        private Collection<Shipping> shippings;
        private Collection<Package> packages;
        private BonLivraison bonLivraison;
        @Transient
        private ArrayList<OldPackage> oldPackages;
        @Transient
        private HashSet<Long> orderIds;
        @Transient
        private HashSet<String> orderNums;
        @Transient
        private HashSet<String> barcodes;
        @Transient
        private  long bonLivraisonlId;
        @Transient
        private String clientId;
        @Transient
        private String destination;
        @Transient
        private ArrayList<PsLine> psLines;
        private int bookPerCTN;
        private float realWeight;
        @Transient
        private String shipAccountName;
        @Transient
        private String billAccountName;
        public PackingSlips() {
        }
        public boolean isShiptoday() {
            return shiptoday;
        }

        public void setShiptoday(boolean shiptoday) {
            this.shiptoday = shiptoday;
        }

        public ArrayList<OldPackage> getOldPackages() {
            return oldPackages;
        }

        public void setOldPackages(ArrayList<OldPackage> oldPackages) {
            this.oldPackages = oldPackages;
        }

        public int getPackageQty() {
            return packageQty;
        }

        public void setPackageQty(int packageQty) {
            this.packageQty = packageQty;
        }

        public Long getPsId() {
            return psId;
        }

        public void setPsId(Long psId) {
            this.psId = psId;
        }

        public int getQuantity() {
            return quantity;
        }

        public void setQuantity(int quantity) {
            this.quantity = quantity;
        }

        public String getStatut() {
            return statut;
        }

        public void setStatut(String statut) {
            this.statut = statut;
        }

        public int getPartialPackage() {
            return partialPackage;
        }

        public void setPartialPackage(int partialPackage) {
            this.partialPackage = partialPackage;
        }

        public Date getCreationDate() {
            return creationDate;
        }

        public void setCreationDate(Date creationDate) {
            this.creationDate = creationDate;
        }

        public Collection<Shipping> getShippings() {
            return shippings;
        }

        public void setShippings(Collection<Shipping> shippings) {
            this.shippings = shippings;
        }

        public Collection<Package> getPackages() {
            return packages;
        }

        public void setPackages(Collection<Package> packages) {
            this.packages = packages;
        }

        public BonLivraison getBonLivraison() {
            return bonLivraison;
        }

        public void setBonLivraison(BonLivraison bonLivraison) {
            this.bonLivraison = bonLivraison;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            PackingSlips that = (PackingSlips) o;
            return Objects.equals(psId, that.psId);
        }

        public HashSet<Long> getOrderIds() {
            return orderIds;
        }

        public void setOrderIds(HashSet<Long> orderIds) {
            this.orderIds = orderIds;
        }

        public HashSet<String> getBarcodes() {
            return barcodes;
        }

        public void setBarcodes(HashSet<String> barcodes) {
            this.barcodes = barcodes;
        }

        public long getBonLivraisonlId() {
            return bonLivraisonlId;
        }

        public void setBonLivraisonlId(long bonLivraisonlId) {
            this.bonLivraisonlId = bonLivraisonlId;
        }

        public String getClientId() {
            return clientId;
        }

        public void setClientId(String clientId) {
            this.clientId = clientId;
        }

        public String getDestination() {
            return destination;
        }

        public void setDestination(String destination) {
            this.destination = destination;
        }

        public ArrayList<PsLine> getPsLines() {
            return psLines;
        }

        public void setPsLines(ArrayList<PsLine> psLines) {
            this.psLines = psLines;
        }

        public int getBookPerCTN() {
            return bookPerCTN;
        }

        public void setBookPerCTN(int bookPerCTN) {
            this.bookPerCTN = bookPerCTN;
        }

        public float getRealWeight() {
            return realWeight;
        }

        public void setRealWeight(float realWeight) {
            this.realWeight = realWeight;
        }

        public HashSet<String> getOrderNums() {
            return orderNums;
        }

        public void setOrderNums(HashSet<String> orderNums) {
            this.orderNums = orderNums;
        }

        public static Comparator<PackingSlips> sortOrdre=new Comparator<PackingSlips>() {
            @Override
            public int compare(PackingSlips o1, PackingSlips o2) {

                return (int) (o2.getPsId()-o1.getPsId());
            }
        };
        @Override
        public int hashCode() {
            return Objects.hash(psId);
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
