package com.capstone.while1.beaconandroidstudio;

import android.annotation.TargetApi;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat.OnRequestPermissionsResultCallback;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;

public class SplashActivity extends AppCompatActivity implements OnRequestPermissionsResultCallback {
    /**
     * The code used when requesting permissions
     */
    private static final int PERMISSIONS_REQUEST = 1234;
    private boolean mPermissionDenied = false;

    @SuppressWarnings("rawtypes")
    private Class getNextActivityClass() {
        return MainActivity.class;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        /*
          On a post-Android 6.0 devices, check if the required permissions have
          been granted.
         */
        if (Build.VERSION.SDK_INT >= 23 &&
                ContextCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION)
                        != PackageManager.PERMISSION_GRANTED) {
            PermissionUtils.requestPermission(this, PERMISSIONS_REQUEST, android.Manifest.permission.ACCESS_FINE_LOCATION, true);
        } else {
            startNextActivity();
        }
    }

    @TargetApi(23)
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String permissions[], @NonNull int[] grantResults) {
        if (requestCode == PERMISSIONS_REQUEST) {
            if (PermissionUtils.isPermissionGranted(permissions, grantResults,
                    android.Manifest.permission.ACCESS_FINE_LOCATION)) {

                // Enable the map layer if the permission has been granted.
                startNextActivity();
            } else {
                mPermissionDenied = true;
            }
        }
    }

    private void startNextActivity() {
        startActivity(new Intent(SplashActivity.this, getNextActivityClass()));
        finish();
    }

    @Override
    protected void onResumeFragments() {
        super.onResumeFragments();
        if (mPermissionDenied) {
            showMissingPermissionError();
            mPermissionDenied = false;
        }
    }

    /**
     * Displays a dialog with error message explaining that the location permission is missing.
     */
    private void showMissingPermissionError() {
        PermissionUtils.PermissionDeniedDialog
                .newInstance(true).show(getSupportFragmentManager(), "dialog");
    }
}