package com.ecng3020project.merchandiserstockcount;

public class BarcodeData {

    private String barcode_ID;
    private String item_ID;
    private String warehouse;
    private String expiry_Date;
    private String unit_Of_Measure;
    private String batch_No;

    public BarcodeData() {
    }

    public String getBarcode_ID() {
        return barcode_ID;
    }

    public void setBarcode_ID(String barcode_ID) {
        this.barcode_ID = barcode_ID;
    }

    public String getItem_ID() {
        return item_ID;
    }

    public void setItem_ID(String item_ID) {
        this.item_ID = item_ID;
    }

    public String getWarehouse() {
        return warehouse;
    }

    public void setWarehouse(String warehouse) {
        this.warehouse = warehouse;
    }

    public String getExpiry_Date() {
        return expiry_Date;
    }

    public void setExpiry_Date(String expiry_Date) {
        this.expiry_Date = expiry_Date;
    }

    public String getUnit_Of_Measure() {
        return unit_Of_Measure;
    }

    public void setUnit_Of_Measure(String unit_Of_Measure) {
        this.unit_Of_Measure = unit_Of_Measure;
    }

    public String getBatch_No() {
        return batch_No;
    }

    public void setBatch_No(String batch_No) {
        this.batch_No = batch_No;
    }

}
