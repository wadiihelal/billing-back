package com.epac.billingService.Entities;

import java.util.Comparator;
import java.util.Objects;
public class PsLine {
    private Long orderId;
    private String barcode;
    private String title;
    private int boxQty;
    private String packageType;
    private int cQ;
    private float cW;
    private int lineTotalQty;
    private String clientpo;
    private String clientPartNum;
    private String isbn10;
    private  double unitPrice;
    private int pnlPrintingNumber;
    private float weight;
    private String billingLocation;
    private String billingCountry;
    private String billingAddress;
    private String shippingLocation;
    private String shippingCountry;
    private String destination;
    private String shipAttn;
    private String billAttn;
    private long epac;
    private Long psId;
    private int ctn;
    private int ctnQ;
    private int qty;
    private int partial;
    private String buyer;
    public PsLine(Long orderId, String barcode, String title, int boxQty, String packageType) {
        this.orderId = orderId;
        this.barcode = barcode;
        this.title = title;
        this.boxQty = boxQty;
        this.packageType = packageType;
    }

    public PsLine() {
    }

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public String getBarcode() {
        return barcode;
    }

    public void setBarcode(String barcode) {
        this.barcode = barcode;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getBoxQty() {
        return boxQty;
    }

    public void setBoxQty(int boxQty) {
        this.boxQty = boxQty;
    }

    public String getPackageType() {
        return packageType;
    }

    public void setPackageType(String packageType) {
        this.packageType = packageType;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PsLine psLine = (PsLine) o;
        return Objects.equals(orderId, psLine.orderId) &&
                Objects.equals(barcode, psLine.barcode);
    }

    @Override
    public int hashCode() {
        return Objects.hash(orderId, barcode);
    }

    public int getcQ() {
        return cQ;
    }

    public void setcQ(int cQ) {
        this.cQ = cQ;
    }

    public float getcW() {
        return cW;
    }

    public void setcW(float cW) {
        this.cW = cW;
    }

    public int getLineTotalQty() {
        return lineTotalQty;
    }

    public void setLineTotalQty(int lineTotalQty) {
        this.lineTotalQty = lineTotalQty;
    }

    public String getClientpo() {
        return clientpo;
    }

    public void setClientpo(String clientpo) {
        this.clientpo = clientpo;
    }

    public String getClientPartNum() {
        return clientPartNum;
    }

    public void setClientPartNum(String clientPartNum) {
        this.clientPartNum = clientPartNum;
    }

    public String getIsbn10() {
        return isbn10;
    }

    public void setIsbn10(String isbn10) {
        this.isbn10 = isbn10;
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

    public float getWeight() {
        return weight;
    }

    public void setWeight(float weight) {
        this.weight = weight;
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

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
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

    public Long getPsId() {
        return psId;
    }

    public void setPsId(Long psId) {
        this.psId = psId;
    }

    public long getEpac() {
        return epac;
    }

    public void setEpac(long epac) {
        this.epac = epac;
    }

    public int getCtn() {
        return ctn;
    }

    public void setCtn(int ctn) {
        this.ctn = ctn;
    }

    public int getQty() {
        return qty;
    }

    public void setQty(int qty) {
        this.qty = qty;
    }

    public int getPartial() {
        return partial;
    }

    public void setPartial(int partial) {
        this.partial = partial;
    }

    public int getCtnQ() {
        return ctnQ;
    }

    public void setCtnQ(int ctnQ) {
        this.ctnQ = ctnQ;
    }

    public String getBuyer() {
        return buyer;
    }

    public void setBuyer(String buyer) {
        this.buyer = buyer;
    }

    public static Comparator<PsLine> sortPsLine=new Comparator<PsLine>() {
        @Override
        public int compare(PsLine p1, PsLine p2)
        {
            return (int) (p1.getPsId()-p2.getPsId());
        }
    };
}
