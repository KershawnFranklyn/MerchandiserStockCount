package com.ecng3020project.merchandiserstockcount;

import com.google.firebase.database.IgnoreExtraProperties;

@IgnoreExtraProperties
public class SavedData {

    public String route_No;
    public String customer_Account_No;
    public String customer_Name;
    public String item_Name;
    public String item_Brand;
    public String item_Pack_Size;
    public String flavor;
    public String number_Of_Cases;

    public SavedData(){

    }

    public SavedData(String route_Number, String customer_Account_Number,
                     String customer_Name, String item_Name, String item_Brand,
                     String item_Pack_Size, String flavor, String number_Of_Cases){

        this.route_No = route_Number;
        this.customer_Account_No = customer_Account_Number;
        this.customer_Name = customer_Name;
        this.item_Name = item_Name;
        this.item_Brand = item_Brand;
        this.item_Pack_Size = item_Pack_Size;
        this.flavor = flavor;
        this.number_Of_Cases = number_Of_Cases;
    }

}
