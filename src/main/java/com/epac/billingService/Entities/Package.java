package com.epac.billingService.Entities;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.data.annotation.Id;


@Data
@AllArgsConstructor
//@JsonIdentityInfo(generator= ObjectIdGenerators.IntSequenceGenerator.class)
@JsonIgnoreProperties(ignoreUnknown = true)
public class Package {
    @Id
    private  String packageId;
    private int count ;
    private int  partialPackage;

    private int depth;
    
    private int height;
    
    private int width;
    
    private int maxPallet;
    
    private int depthQty;
    
    private int heightQty;
    
    private int widthQty;
    
    private int quantity;
    
    private String description;
    
    private String bookOrientation;
    
    private double waste;
    
    private String type;
    private String ship;
     private String destination;
    private String shipAccountName;
     private String billAccountName;
     private String clientPartNum;
    public Package() {
    }


    public String getClientPartNum() {
        return clientPartNum;
    }

    public void setClientPartNum(String clientPartNum) {
        this.clientPartNum = clientPartNum;
    }

    public String getPackageId() {
        return packageId;
    }

    public void setPackageId(String packageId) {
        this.packageId = packageId;
    }


    public int getPartialPackage() {
        return partialPackage;
    }

    public void setPartialPackage(int partialPackage) {
        this.partialPackage = partialPackage;
    }


    public int getDepth() {
        return depth;
    }

    public void setDepth(int depth) {
        this.depth = depth;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getMaxPallet() {
        return maxPallet;
    }

    public void setMaxPallet(int maxPallet) {
        this.maxPallet = maxPallet;
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

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getBookOrientation() {
        return bookOrientation;
    }

    public void setBookOrientation(String bookOrientation) {
        this.bookOrientation = bookOrientation;
    }

    public double getWaste() {
        return waste;
    }

    public void setWaste(double waste) {
        this.waste = waste;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getShip() {
        return ship;
    }

    public void setShip(String ship) {
        this.ship = ship;
    }
}
