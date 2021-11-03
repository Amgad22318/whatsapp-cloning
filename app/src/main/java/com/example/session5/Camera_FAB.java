package com.example.session5;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.otaliastudios.cameraview.CameraView;

public class Camera_FAB extends AppCompatActivity {
    static CameraView cameraView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fab_camera);
        cameraView = findViewById(R.id.FAB_camera);
        cameraView.setLifecycleOwner(this);



    }

    @Override
    protected void onPause() {
        super.onPause();
        cameraView.destroy();
    }
}
