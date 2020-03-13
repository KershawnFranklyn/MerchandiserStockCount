package com.ecng3020project.merchandiserstockcount;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Camera;
import android.graphics.Matrix;
import android.graphics.Point;
import android.graphics.Rect;
import android.media.Image;
import android.os.Bundle;
import android.os.Environment;
import android.os.PersistableBundle;
import android.util.Log;
import android.util.Rational;
import android.util.Size;
import android.view.Surface;
import android.view.TextureView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.camera.core.CameraX;
import androidx.camera.core.ImageAnalysis;
import androidx.camera.core.ImageAnalysisConfig;
import androidx.camera.core.ImageCapture;
import androidx.camera.core.ImageCaptureConfig;
import androidx.camera.core.ImageProxy;
import androidx.camera.core.Preview;
import androidx.camera.core.PreviewConfig;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.lifecycle.LifecycleOwner;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.ml.vision.FirebaseVision;
import com.google.firebase.ml.vision.barcode.FirebaseVisionBarcode;
import com.google.firebase.ml.vision.barcode.FirebaseVisionBarcodeDetector;
import com.google.firebase.ml.vision.barcode.FirebaseVisionBarcodeDetectorOptions;
import com.google.firebase.ml.vision.common.FirebaseVisionImage;
import com.google.firebase.ml.vision.common.FirebaseVisionImageMetadata;

import java.io.File;
import java.util.List;

import static android.widget.Toast.LENGTH_SHORT;
import static androidx.constraintlayout.widget.Constraints.TAG;

public class CameraMainActivity extends AppCompatActivity {

    /***************************************************************************************
     *    Title:
     *    Author:
     *    Date:
     *    Code version: 1.0
     *    Availability: https://github.com/journaldev/journaldev/blob/master/Android/AndroidCameraX/app/src/main/java/com/journaldev/androidcamerax/MainActivity.java
     *    Code Adapted to fit this project
     ***************************************************************************************/

    private int REQUEST_CODE_PERMISSIONS = 101;
    private final String[] REQUIRED_PERMISSIONS = new String[]{"android.permission.CAMERA", "android.permission.WRITE_EXTERNAL_STORAGE"};
    TextureView scanning_TextureView;
    String routeNumber;
    String customerName;
    String customerAccount;
    String rawValue;
    int check;
    DatabaseOpenHelper databaseH;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.camera_screen_ver_1_0);

        scanning_TextureView = findViewById(R.id.view_finder);

        Intent intent = getIntent();
        routeNumber = intent.getStringExtra("routeNumberIntent");
        customerName = intent.getStringExtra("customerNameIntent");
        customerAccount = intent.getStringExtra("customerAccountIntent");
        check = 1;

        if(allPermissionsGranted()){
            startCamera(); //start camera if permission has been granted by user
        } else{
            ActivityCompat.requestPermissions(this, REQUIRED_PERMISSIONS, REQUEST_CODE_PERMISSIONS);
        }

    }

    private void startCamera() {
        CameraX.unbindAll();
        //For scanning screen
        Rational scanning_AspectRatio = new Rational(4,3 );
        Size scanning_ScreenSize = new Size(scanning_TextureView.getWidth(), scanning_TextureView.getHeight()); //size of the screen

        //Preview for scanning
        PreviewConfig scanning_PreviewConfig = new PreviewConfig.Builder().setTargetAspectRatio(scanning_AspectRatio).setTargetResolution(scanning_ScreenSize).build();
        Preview preview = new Preview(scanning_PreviewConfig);

        preview.setOnPreviewOutputUpdateListener(
                new Preview.OnPreviewOutputUpdateListener() {
                    @Override
                    public void onUpdated(Preview.PreviewOutput output) {
                        //to update the surface texture we  have to destroy it first then re-add it
                            ViewGroup parent = (ViewGroup) scanning_TextureView.getParent();
                            parent.removeView(scanning_TextureView);
                            parent.addView(scanning_TextureView, 0);

                            scanning_TextureView.setSurfaceTexture(output.getSurfaceTexture());
                            updateTransform();
                        }
                }
        );

        /***************************************************************************************
         *    Title: Analyze images
         *    Author:
         *    Date:
         *    Code version: 1.0
         *    Availability: https://developer.android.com/training/camerax/analyze
         *    Code Adapted to fit this project
         ***************************************************************************************/

        ImageAnalysisConfig config =
                new ImageAnalysisConfig.Builder()
                        .setTargetResolution(new Size(640, 480  ))
                        .setImageReaderMode(ImageAnalysis.ImageReaderMode.ACQUIRE_LATEST_IMAGE)
                        .build();

        ImageAnalysis imageAnalysis = new ImageAnalysis(config);

        imageAnalysis.setAnalyzer(
                new ImageAnalysis.Analyzer(){
                    @Override
                    public void analyze(ImageProxy imageProxy, int rotationDegrees) {

                        /***************************************************************************************
                         *    Title: Scan Barcodes with ML Kit on Android
                         *    Author:
                         *    Date:
                         *    Code version: 1.0
                         *    Availability: https://firebase.google.com/docs/ml-kit/android/read-barcodes
                         *    Code Adapted to fit this project
                         ***************************************************************************************/

                        if (imageProxy == null || imageProxy.getImage() == null) {
                            return;
                        }
                        FirebaseVisionBarcodeDetectorOptions options =
                                new FirebaseVisionBarcodeDetectorOptions.Builder()
                                        .setBarcodeFormats(
                                                FirebaseVisionBarcode.FORMAT_QR_CODE,
                                                FirebaseVisionBarcode.FORMAT_UPC_A)
                                        .build();

                        FirebaseVisionBarcodeDetector detector = FirebaseVision.getInstance()
                                .getVisionBarcodeDetector(options);

                        Image mediaImage = imageProxy.getImage();
                        int rotation = degreesToFirebaseRotation(rotationDegrees);
                        FirebaseVisionImage image = FirebaseVisionImage.fromMediaImage(mediaImage, rotation);

                        Task<List<FirebaseVisionBarcode>> result = detector.detectInImage(image)
                                .addOnSuccessListener(new OnSuccessListener<List<FirebaseVisionBarcode>>() {
                                    @Override
                                    public void onSuccess(List<FirebaseVisionBarcode> barcodes) {
                                        // Task completed successfully
                                        // ...
                                        Log.d("Barcode Scanner", "onSuccess: Barcodescanner Successful", null);

                                        for (FirebaseVisionBarcode barcode: barcodes) {

                                            Rect bounds = barcode.getBoundingBox();
                                            Point[] corners = barcode.getCornerPoints();

                                            rawValue = barcode.getRawValue();
                                            int barcodetype = barcode.getFormat();
                                            int valueType = barcode.getValueType();

                                            Log.i("RAW VALUE", rawValue, null);
                                            Log.i("VALUE TYPE", "Value type: "+ valueType, null);
                                            Log.i("FORMAT TYPE", "Barcode Format type: "+ barcodetype, null);
                                            // See API reference for complete list of supported types
                                            CameraX.unbindAll(); // Stops camera function

                                            if (valueType == 5)
                                            {
                                                nextActivity();
                                            }
                                        }
                                    }
                                })
                                .addOnFailureListener(new OnFailureListener() {
                                    @Override
                                    public void onFailure(@NonNull Exception e) {
                                        // Task failed with an exception
                                        Log.e("BarcodeScannerError", "Error in scanning", null);
                                        Toast toast;
                                        toast = Toast.makeText(getBaseContext(), "Error scanning", LENGTH_SHORT);
                                    }
                                });
                    }
                }
        );

        //bind to lifecycle:
        CameraX.bindToLifecycle((LifecycleOwner)this, preview, imageAnalysis);
    }

    private void updateTransform() {
        Matrix mx = new Matrix();
        float h = scanning_TextureView.getMeasuredHeight();
        float w = scanning_TextureView.getMeasuredWidth();

        float cX = w / 2f;
        float cY = h / 2f;

        int rotationDgr;
        int rotation = (int)scanning_TextureView.getRotation();

        switch (rotation){
            case Surface.ROTATION_0:
                rotationDgr = 0;
                break;
            case Surface.ROTATION_90:
                rotationDgr = 90;
                break;
            case Surface.ROTATION_180:
                rotationDgr = 180;
                break;
            case Surface.ROTATION_270:
                rotationDgr = 270;
                break;
            default:
                return;

        }
        mx.postRotate((float)rotationDgr, cX, cY);
        scanning_TextureView.setTransform(mx);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {

        if(requestCode == REQUEST_CODE_PERMISSIONS){
            if(allPermissionsGranted()){
                startCamera();
            } else{
                Toast.makeText(this, "Permissions not granted by the user.", LENGTH_SHORT).show();
                finish();
            }
        }
    }

    private boolean allPermissionsGranted() {
        for(String permission : REQUIRED_PERMISSIONS){
            if(ContextCompat.checkSelfPermission(this, permission) != PackageManager.PERMISSION_GRANTED){
                return false;
            }
        }
        return true;
    }

    public int degreesToFirebaseRotation(int degrees) {
        switch (degrees) {
            case 0:
                return FirebaseVisionImageMetadata.ROTATION_0;
            case 90:
                return FirebaseVisionImageMetadata.ROTATION_90;
            case 180:
                return FirebaseVisionImageMetadata.ROTATION_180;
            case 270:
                return FirebaseVisionImageMetadata.ROTATION_270;
            default:
                throw new IllegalArgumentException(
                        "Rotation must be 0, 90, 180, or 270.");
        }
    }

    public void nextActivity (){

        if(check == 1){
            check++;

            databaseH = new DatabaseOpenHelper(CameraMainActivity.this);
            List<com.ecng3020project.merchandiserstockcount.MyObject> ItemIDQuery = databaseH.BarcodeIDtoItemID(rawValue);
            int itemIDRowCount = ItemIDQuery.size();

            String[] itemIDStringArray = new String[itemIDRowCount];
            int x = 0;

            for (com.ecng3020project.merchandiserstockcount.MyObject record : ItemIDQuery) {

                itemIDStringArray[x] = record.objectName;
                x++;
            }

            List<com.ecng3020project.merchandiserstockcount.MyObject> ItemNameQuery = databaseH.ItemNameScannedQuery(itemIDStringArray[0]);
            int itemNameRowCount = ItemNameQuery.size();

            String[] itemNameStringArray = new String[itemNameRowCount];
            x = 0;

            for (com.ecng3020project.merchandiserstockcount.MyObject record : ItemNameQuery) {

                itemNameStringArray[x] = record.objectName;
                x++;
            }

            List<com.ecng3020project.merchandiserstockcount.MyObject> ItemBrandQuery = databaseH.ItemBrandScannedQuery(itemIDStringArray[0]);
            int itemBrandRowCount = ItemNameQuery.size();
            String[] itemBrandStringArray = new String[itemBrandRowCount];
            x = 0;

            for (com.ecng3020project.merchandiserstockcount.MyObject record : ItemBrandQuery) {

                itemBrandStringArray[x] = record.objectName;
                x++;
            }

            List<com.ecng3020project.merchandiserstockcount.MyObject> ItemPackSizeQuery = databaseH.ItemPackSizeScannedQuery(itemIDStringArray[0]);
            int itemPackSizeRowCount = ItemNameQuery.size();
            String[] itemPackSizeStringArray = new String[itemPackSizeRowCount];
            x = 0;

            for (com.ecng3020project.merchandiserstockcount.MyObject record : ItemPackSizeQuery) {

                itemPackSizeStringArray[x] = record.objectName;
                x++;
            }

            List<com.ecng3020project.merchandiserstockcount.MyObject> ItemFlavorQuery = databaseH.ItemFlavorScannedQuery(itemIDStringArray[0]);
            int itemFlavorRowCount = ItemNameQuery.size();
            String[] itemFlavorStringArray = new String[itemFlavorRowCount];
            x = 0;

            for (com.ecng3020project.merchandiserstockcount.MyObject record : ItemFlavorQuery) {

                itemFlavorStringArray[x] = record.objectName;
                x++;
            }

            Intent intent = new Intent(CameraMainActivity.this, MainActivity.class);

            intent.putExtra("RouteNumberIntent", routeNumber);
            intent.putExtra("CustomerNameIntent", customerName);
            intent.putExtra("CustomerAccountIntent", customerAccount);
            intent.putExtra("ItemNameIntent", itemNameStringArray[0]);
            intent.putExtra("ItemBrandIntent", itemBrandStringArray[0]);
            intent.putExtra("ItemPackSizeIntent", itemPackSizeStringArray[0]);
            intent.putExtra("ItemFlavorIntent", itemFlavorStringArray[0]);

            startActivity(intent);
        }
    }


}
