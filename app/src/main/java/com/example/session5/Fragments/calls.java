package com.example.session5.Fragments;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import androidx.fragment.app.Fragment;

import com.example.session5.Adapters.Custom_Adapter_Calls;
import com.example.session5.Models_data.Model_data_calls;
import com.example.session5.R;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class calls extends Fragment {
    ArrayList<Model_data_calls> Calls_Data = new ArrayList<>();
    ListView listViewCalls;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_calls, container, false);

        if (Calls_Data.size() == 0) {


            Model_data_calls model_data_calls1 = new Model_data_calls();
            Model_data_calls model_data_calls2 = new Model_data_calls();
            Model_data_calls model_data_calls3 = new Model_data_calls();
            Model_data_calls model_data_calls4 = new Model_data_calls();
            Model_data_calls model_data_calls5 = new Model_data_calls();
            Model_data_calls model_data_calls6 = new Model_data_calls();
            Model_data_calls model_data_calls7 = new Model_data_calls();
            Model_data_calls model_data_calls8 = new Model_data_calls();
            Model_data_calls model_data_calls9 = new Model_data_calls();
            Model_data_calls model_data_calls10 = new Model_data_calls();




            model_data_calls1.setCalls_CallerPhoto(R.drawable.amir);
            model_data_calls1.setCalls_Call_direction(R.drawable.ic_call_made);
            model_data_calls1.setCalls_CallOrVideo(R.drawable.ic_call);
            model_data_calls1.setCalls_CallerName("Amir Mohamed");
            model_data_calls1.setCalls_CallTime("Today, 05:22 pm");

            model_data_calls2.setCalls_CallerPhoto(R.drawable.ahmedramdan);
            model_data_calls2.setCalls_Call_direction(R.drawable.ic_call_received);
            model_data_calls2.setCalls_CallOrVideo(R.drawable.ic_videocall_green);
            model_data_calls2.setCalls_CallerName("Ahmed Ramadan");
            model_data_calls2.setCalls_CallTime("21 August, 01:26 am");

            model_data_calls3.setCalls_CallerPhoto(R.drawable.engkamal);
            model_data_calls3.setCalls_Call_direction(R.drawable.ic_call_received);
            model_data_calls3.setCalls_CallOrVideo(R.drawable.ic_call);
            model_data_calls3.setCalls_CallerName("ENG Kamal");
            model_data_calls3.setCalls_CallTime("17 August, 12:08 pm");

            model_data_calls4.setCalls_CallerPhoto(R.drawable.ahmedbasem);
            model_data_calls4.setCalls_Call_direction(R.drawable.ic_call_made);
            model_data_calls4.setCalls_CallOrVideo(R.drawable.ic_videocall_green);
            model_data_calls4.setCalls_CallerName("Ahmed Basem");
            model_data_calls4.setCalls_CallTime("(2) 15 August, 12:57 pm");

            model_data_calls5.setCalls_CallerPhoto(R.drawable.ahmedbehairy);
            model_data_calls5.setCalls_Call_direction(R.drawable.ic_call_received);
            model_data_calls5.setCalls_CallOrVideo(R.drawable.ic_videocall_green);
            model_data_calls5.setCalls_CallerName("Ahemd M.Behairy");
            model_data_calls5.setCalls_CallTime("12 August, 12:57 pm");

            model_data_calls6.setCalls_CallerPhoto(R.drawable.ahmedibrahim);
            model_data_calls6.setCalls_Call_direction(R.drawable.ic_call_received);
            model_data_calls6.setCalls_CallOrVideo(R.drawable.ic_call);
            model_data_calls6.setCalls_CallerName("Ahemed Ibrahim");
            model_data_calls6.setCalls_CallTime("(6)10 August, 1:56 pm");

            model_data_calls7.setCalls_CallerPhoto(R.drawable.ahmedshapan);
            model_data_calls7.setCalls_Call_direction(R.drawable.ic_call_received);
            model_data_calls7.setCalls_CallOrVideo(R.drawable.ic_call);
            model_data_calls7.setCalls_CallerName("Ahemd Shapan");
            model_data_calls7.setCalls_CallTime("(3) 5 August, 01:22 pm");

            model_data_calls8.setCalls_CallerPhoto(R.drawable.mahmod);
            model_data_calls8.setCalls_Call_direction(R.drawable.ic_call_made);
            model_data_calls8.setCalls_CallOrVideo(R.drawable.ic_videocall_green);
            model_data_calls8.setCalls_CallerName("Mahmod");
            model_data_calls8.setCalls_CallTime("3 August, 12:29 pm");

            model_data_calls9.setCalls_CallerPhoto(R.drawable.mohamedsaber);
            model_data_calls9.setCalls_Call_direction(R.drawable.ic_call_received);
            model_data_calls9.setCalls_CallOrVideo(R.drawable.ic_call);
            model_data_calls9.setCalls_CallerName("Ahemd M.Behairy");
            model_data_calls9.setCalls_CallTime("(6) 1 August, 12:03 am");




            Calls_Data.add(model_data_calls1);
            Calls_Data.add(model_data_calls2);
            Calls_Data.add(model_data_calls3);
            Calls_Data.add(model_data_calls4);
            Calls_Data.add(model_data_calls5);
            Calls_Data.add(model_data_calls6);
            Calls_Data.add(model_data_calls7);
            Calls_Data.add(model_data_calls8);
            Calls_Data.add(model_data_calls9);
            Calls_Data.add(model_data_calls5);
            Calls_Data.add(model_data_calls8);
            Calls_Data.add(model_data_calls3);
            Calls_Data.add(model_data_calls5);

        }



        listViewCalls = view.findViewById(R.id.calls_listview);
        Custom_Adapter_Calls adapter_calls = new Custom_Adapter_Calls(getContext(), R.layout.calls_layout_model, Calls_Data);
        listViewCalls.setAdapter(adapter_calls);

        return view;
    }

}
