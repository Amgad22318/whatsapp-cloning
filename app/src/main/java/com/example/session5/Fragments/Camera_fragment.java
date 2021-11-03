package com.example.session5.Fragments;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import com.example.session5.R;
import com.google.android.cameraview.CameraView;


public class Camera_fragment extends Fragment  {

    public static CameraView cameraView;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_camera, container, false);
        cameraView = view.findViewById(R.id.fragment_camera);






        return view;
    }






}
