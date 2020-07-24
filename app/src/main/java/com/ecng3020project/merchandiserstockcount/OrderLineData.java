package com.ecng3020project.merchandiserstockcount;

public class OrderLineData {

    private String order_ID;
    private String item_ID;
    private String no_Of_Cases;

    public OrderLineData() {
    }

    public String getOrder_ID() {
        return order_ID;
    }

    public void setOrder_ID(String order_ID) {
        this.order_ID = order_ID;
    }

    public String getItem_ID() {
        return item_ID;
    }

    public void setItem_ID(String item_ID) {
        this.item_ID = item_ID;
    }

    public String getNo_Of_Cases() {
        return no_Of_Cases;
    }

    public void setNo_Of_Cases(String no_Of_Cases) {
        this.no_Of_Cases = no_Of_Cases;
    }
}
