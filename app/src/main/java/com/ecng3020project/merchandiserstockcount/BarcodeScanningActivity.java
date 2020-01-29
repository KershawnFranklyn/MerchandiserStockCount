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
import android.media.Image;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.camera.core.ImageAnalysis;
import androidx.camera.core.ImageProxy;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.google.firebase.ml.vision.FirebaseVision;
import com.google.firebase.ml.vision.barcode.FirebaseVisionBarcode;
import com.google.firebase.ml.vision.barcode.FirebaseVisionBarcodeDetector;
import com.google.firebase.ml.vision.barcode.FirebaseVisionBarcodeDetectorOptions;
import com.google.firebase.ml.vision.common.FirebaseVisionImage;
import com.google.firebase.ml.vision.common.FirebaseVisionImageMetadata;

import java.util.List;

class BarcodeScanningActivity implements ImageAnalysis.Analyzer {


    @Override
    public void analyze(ImageProxy image, int rotationDegrees) {

    }
}