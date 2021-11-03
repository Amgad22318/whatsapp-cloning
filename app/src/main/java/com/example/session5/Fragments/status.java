package com.example.session5.Fragments;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import androidx.fragment.app.Fragment;

import com.example.session5.Adapters.Custom_Adapter_Status;
import com.example.session5.Models_data.Model_data_status;
import com.example.session5.R;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class status extends Fragment {
    ArrayList<Model_data_status> status_data = new ArrayList<>();
    ListView listViewStatus;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_status, container, false);
        if (status_data.size() == 0) {
            Model_data_status model_data_status1 = new Model_data_status();
            Model_data_status model_data_status2 = new Model_data_status();
            Model_data_status model_data_status3 = new Model_data_status();
            Model_data_status model_data_status4 = new Model_data_status();
            Model_data_status model_data_status5 = new Model_data_status();
            Model_data_status model_data_status6 = new Model_data_status();
            Model_data_status model_data_status7 = new Model_data_status();
            Model_data_status model_data_status8 = new Model_data_status();
            Model_data_status model_data_status9 = new Model_data_status();





            model_data_status1.setStatus_photo(R.drawable.amir);
            model_data_status1.setStatus_name("Amir Mohamed");
            model_data_status1.setStatus_time("5 minutes ago");

            model_data_status2.setStatus_photo(R.drawable.ahmedramdan);
            model_data_status2.setStatus_name("Ahmed Ramadan");
            model_data_status2.setStatus_time("29 minutes ago");

            model_data_status3.setStatus_photo(R.drawable.ahmedbasem);
            model_data_status3.setStatus_name("Ahmed Basem");
            model_data_status3.setStatus_time("Today, 9:23 pm");

            model_data_status4.setStatus_photo(R.drawable.engkamal);
            model_data_status4.setStatus_name("Eng Kamal");
            model_data_status4.setStatus_time("Today, 9:05 pm");

            model_data_status5.setStatus_photo(R.drawable.ahmedbehairy);
            model_data_status5.setStatus_name("Ahmed M.Behairy");
            model_data_status5.setStatus_time("Today, 7:13 pm");

            model_data_status6.setStatus_photo(R.drawable.mahmod);
            model_data_status6.setStatus_name("Mahmod");
            model_data_status6.setStatus_time("Today, 3:23 pm");

            model_data_status7.setStatus_photo(R.drawable.ahmedibrahim);
            model_data_status7.setStatus_name("Ahmed Ibrahim");
            model_data_status7.setStatus_time("Today, 2:28 pm");

            model_data_status8.setStatus_photo(R.drawable.ahmedshapan);
            model_data_status8.setStatus_name("Ahemd Shapan");
            model_data_status8.setStatus_time("Today, 5:23 am");

            model_data_status9.setStatus_photo(R.drawable.nourmedhat);
            model_data_status9.setStatus_name("Nour Medhat");
            model_data_status9.setStatus_time("Today, 2:52 am");



            status_data.add(model_data_status1);
            status_data.add(model_data_status2);
            status_data.add(model_data_status3);
            status_data.add(model_data_status4);
            status_data.add(model_data_status5);
            status_data.add(model_data_status6);
            status_data.add(model_data_status7);
            status_data.add(model_data_status8);
            status_data.add(model_data_status9);
            status_data.add(model_data_status4);
            status_data.add(model_data_status1);
            status_data.add(model_data_status6);



        }


        listViewStatus = view.findViewById(R.id.status_listview);
        Custom_Adapter_Status adapter_status = new Custom_Adapter_Status(getContext(), R.layout.status_layout_model, status_data);
        listViewStatus.setAdapter(adapter_status);

     View header = inflater.inflate(R.layout.status_header,null);
       listViewStatus.addHeaderView(header);

        return view;
    }

}
