package com.example.praful.barcodescanner;

import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.google.zxing.Result;

import de.mrapp.android.dialog.MaterialDialog;
import me.dm7.barcodescanner.zxing.ZXingScannerView;

public class MainActivity extends AppCompatActivity implements ZXingScannerView.ResultHandler {

    private Button buttonScan;
    private ZXingScannerView zXingScannerView;
    private Dialog alertDialog;
    private AlertDialog.Builder builder;
    private MaterialDialog.Builder dialogBuilder;
    private AlertDialog.Builder alertDialogBuilder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //buttonScan = (Button)findViewById(R.id.btn_scan);

    }

    public void scan(View view){
        zXingScannerView = new ZXingScannerView(getApplicationContext());
        setContentView(zXingScannerView);
        zXingScannerView.setResultHandler(this); // Register ourselves as a handler for scan results.
        zXingScannerView.startCamera();          // Start camera on resume
        }

    @Override
    public void onPause() {
        super.onPause();
        zXingScannerView.stopCamera();           // Stop camera on pause
    }

    @Override
    public void handleResult(Result result) {
        // Do something with the result here
        Log.e("MainActivity", result.getText()); // Prints scan results
        Log.e("MainActivity", result.getBarcodeFormat().toString()); // Prints the scan format (qrcode, pdf417 etc.)

        //Toast.makeText(this, result.getText(), Toast.LENGTH_LONG).show();
        // If you would like to resume scanning, call this method below:
        zXingScannerView.resumeCameraPreview(this);

        alertDialogBuilder = new AlertDialog.Builder(this);
        alertDialogBuilder.setMessage(result.getText());
        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();
    }

}
