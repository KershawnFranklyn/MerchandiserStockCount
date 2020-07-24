package com.ecng3020project.merchandiserstockcount;

public class ItemData {

    private String item_ID;
    private String item_Brand;
    private String item_Pack_Size;
    private String flavor;
    private String item_Name;

    public ItemData() {
    }

    public String getItem_Pack_Size() {
        return item_Pack_Size;
    }

    public void setItem_Pack_Size(String item_Pack_Size) {
        this.item_Pack_Size = item_Pack_Size;
    }

    public String getItem_ID() {
        return item_ID;
    }

    public void setItem_ID(String item_ID) {
        this.item_ID = item_ID;
    }

    public String getItem_Brand() {
        return item_Brand;
    }

    public void setItem_Brand(String item_Brand) {
        this.item_Brand = item_Brand;
    }

    public String getFlavor() {
        return flavor;
    }

    public void setFlavor(String flavor) {
        this.flavor = flavor;
    }

    public String getItem_Name() {
        return item_Name;
    }

    public void setItem_Name(String item_Name) {
        this.item_Name = item_Name;
    }
}
