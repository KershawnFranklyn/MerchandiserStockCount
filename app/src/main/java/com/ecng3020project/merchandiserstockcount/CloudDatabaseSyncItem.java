package com.ecng3020project.merchandiserstockcount;

public class CloudDatabaseSyncItem {

    private int mId;
    private String mroute_No;
    private String mcustomer_Account_No;
    private String mcustomer_Name;
    private String mitem_Name;
    private String mitem_Brand;
    private String mitem_Pack_Size;
    private String mflavor;
    private String mnumber_Of_Cases;

    public CloudDatabaseSyncItem(){

    }

    public int getId(){
        return mId;
    }

    public final void setId(int Id){
        mId = Id;
    }

    public String getRoute_No(){
        return mroute_No;
    }

    public final void setRoute_No(String route_no){
        mroute_No = route_no;
    }

    public String getcustomer_Account_Name(){
        return mcustomer_Account_No;
    }

    public final void setcustomer_Account_Number(String customer_Account_No){
        mcustomer_Account_No = customer_Account_No;
    }

    public String getcustomer_Number(){
        return mcustomer_Name;
    }

    public final void setcustomer_Name(String customer_Name){
        mcustomer_Name = customer_Name;
    }

    public String getitem_Name(){
        return mitem_Name;
    }

    public final void setitem_Name(String item_Name){
        mitem_Name = item_Name;
    }

    public String getitem_Brand(){
        return mitem_Brand;
    }

    public final void setitem_Brand(String item_Brand){
        mitem_Brand = item_Brand;
    }

    public String getitem_Pack_Size(){
        return mitem_Pack_Size;
    }

    public final void setitem_Pack_Size(String item_Pack_Size){
        mitem_Pack_Size = item_Pack_Size;
    }

    public String getflavor(){
        return mflavor;
    }

    public final void setflavor(String flavor){
        mflavor = flavor;
    }

    public String getnumber_Of_Cases(){
        return mnumber_Of_Cases;
    }

    public final void setnumber_Of_Cases(String number_Of_Cases){
        mnumber_Of_Cases = number_Of_Cases;
    }

    public CloudDatabaseSyncItem(int id, String rout_No, String customer_Account_No,
                                     String customer_Name, String item_Name, String item_Brand,
                                     String item_Pack_Size, String flavor, String number_Of_Cases){

        this.setId(id);
        this.setcustomer_Account_Number(customer_Account_No);
        this.setcustomer_Name(customer_Name);
        this.setitem_Name(item_Name);
        this.setitem_Brand(item_Brand);
        this.setitem_Pack_Size(item_Pack_Size);
        this.setflavor(flavor);
        this.setnumber_Of_Cases(number_Of_Cases);

    }

}
