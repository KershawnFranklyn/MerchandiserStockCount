package com.ecng3020project.merchandiserstockcount;

import android.content.Context;

import com.microsoft.windowsazure.mobileservices.MobileServiceClient;

import java.net.MalformedURLException;

/***************************************************************************************
 *    Title: How to use the Azure Mobile Apps SDK for Android
 *    Authors: Rick Saling, Ela Malani, Glenn Gailey, Ivan Matkov, Nick Schonning
 *              Cephas Lin, Shilpa Sharma, Roland Chernov, Beth Harvey, Matthew Henderson
 *              Ajay Kumar, Mimi Xu, Pavel Yakovlev, Jiayue Hu, James Dunn
 *              Craig Casey, Tyson Nevil, Serhat Erfidan, Sandeep Singh, Pat Altimore
 *              Craig Dunn, Chong Onn Keat, Cory Fowler, PRmerger, Mike Urnun
 *    Date: 06/25/2019
 *    Availability: https://docs.microsoft.com/en-us/azure/app-service-mobile/app-service-mobile-android-how-to-use-client-library
 *    Code Adapted to fit this project
 ***************************************************************************************/

public class AzureServiceAdapter {
    private String mMobileBackendUrl = "https://merchandiser-stock-count.azurewebsites.net/";
    private Context mContext;
    private MobileServiceClient mClient;
    private static AzureServiceAdapter mInstance = null;

    private AzureServiceAdapter(Context context) throws MalformedURLException {
        mContext = context;
        mClient = new MobileServiceClient(mMobileBackendUrl, mContext);
    }

    public static void Initialize(Context context) throws MalformedURLException {
        if (mInstance == null) {
            mInstance = new AzureServiceAdapter(context);
        } else {
            throw new IllegalStateException("AzureServiceAdapter is already initialized");
        }
    }

    public static AzureServiceAdapter getInstance() {
        if (mInstance == null) {
            throw new IllegalStateException("AzureServiceAdapter is not initialized");
        }
        return mInstance;
    }

    public MobileServiceClient getClient() {
        return mClient;
    }

    // Place any public methods that operate on mClient here.
}
