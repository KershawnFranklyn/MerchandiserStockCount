package com.ecng3020project.merchandiserstockcount;

public class OrderData {

    private String order_ID;
    private String order_Date;
    private String customer_Account_No;

    public OrderData() {
    }

    public String getOrder_ID() {
        return order_ID;
    }

    public void setOrder_ID(String order_ID) {
        this.order_ID = order_ID;
    }

    public String getOrder_Date() {
        return order_Date;
    }

    public void setOrder_Date(String order_Date) {
        this.order_Date = order_Date;
    }

    public String getCustomer_Account_No() {
        return customer_Account_No;
    }

    public void setCustomer_Account_No(String customer_Account_No) {
        this.customer_Account_No = customer_Account_No;
    }
}
