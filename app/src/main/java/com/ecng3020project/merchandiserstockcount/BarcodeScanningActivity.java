package com.ecng3020project.merchandiserstockcount;

/***************************************************************************************
 *    Title:
 *    Author:
 *    Date:
 *    Code version: 1.0
 *    Availability: https://firebase.google.com/docs/ml-kit/android/read-barcodes
 *    Code Adapted to fit this project
 ***************************************************************************************/

import android.graphics.Point;
import android.graphics.Rect;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.google.firebase.ml.vision.FirebaseVision;
import com.google.firebase.ml.vision.barcode.FirebaseVisionBarcode;
import com.google.firebase.ml.vision.barcode.FirebaseVisionBarcodeDetector;
import com.google.firebase.ml.vision.barcode.FirebaseVisionBarcodeDetectorOptions;
import com.google.firebase.ml.vision.common.FirebaseVisionImage;

import java.util.List;

public class BarcodeScanningActivity extends AppCompatActivity {

    @Override

    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

    }


//Changing formats
    private void scanBarcodes(FirebaseVisionImage image) {

        // [START set_detector_options]

        FirebaseVisionBarcodeDetectorOptions options =

                new FirebaseVisionBarcodeDetectorOptions.Builder()

                        .setBarcodeFormats(

                                FirebaseVisionBarcode.FORMAT_ALL_FORMATS)

                        .build();

        // [END set_detector_options]



        // [START get_detector]

        FirebaseVisionBarcodeDetector detector = FirebaseVision.getInstance()

                .getVisionBarcodeDetector();

        // Or, to specify the formats to recognize:

        // FirebaseVisionBarcodeDetector detector = FirebaseVision.getInstance()

        //        .getVisionBarcodeDetector(options);

        // [END get_detector]



        // [START run_detector]

        Task<List<FirebaseVisionBarcode>> result = detector.detectInImage(image)

                .addOnSuccessListener(new OnSuccessListener<List<FirebaseVisionBarcode>>() {

                    @Override

                    public void onSuccess(List<FirebaseVisionBarcode> barcodes) {

                        // Task completed successfully

                        // [START_EXCLUDE]

                        // [START get_barcodes]

                        for (FirebaseVisionBarcode barcode: barcodes) {

                            Rect bounds = barcode.getBoundingBox();

                            Point[] corners = barcode.getCornerPoints();



                            String rawValue = barcode.getRawValue();



                            int valueType = barcode.getValueType();

                            // See API reference for complete list of supported types

                            switch (valueType) {

                                case FirebaseVisionBarcode.TYPE_WIFI:

                                    String ssid = barcode.getWifi().getSsid();

                                    String password = barcode.getWifi().getPassword();

                                    int type = barcode.getWifi().getEncryptionType();

                                    break;

                                case FirebaseVisionBarcode.TYPE_URL:

                                    String title = barcode.getUrl().getTitle();

                                    String url = barcode.getUrl().getUrl();

                                    break;

                            }

                        }

                        // [END get_barcodes]

                        // [END_EXCLUDE]

                    }

                })

                .addOnFailureListener(new OnFailureListener() {

                    @Override

                    public void onFailure(@NonNull Exception e) {

                        // Task failed with an exception

                        // ...

                    }

                });

        // [END run_detector]

    }



}
