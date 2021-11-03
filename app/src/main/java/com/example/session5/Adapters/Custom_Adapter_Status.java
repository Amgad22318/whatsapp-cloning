package com.example.session5.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.session5.Models_data.Model_data_status;
import com.example.session5.R;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

public class Custom_Adapter_Status extends ArrayAdapter {
    ArrayList<Model_data_status> model_data_status =new ArrayList<>();

    public Custom_Adapter_Status(Context context, int resource, ArrayList<Model_data_status> model_data_status) {
        super(context, resource, model_data_status);
        this.model_data_status=model_data_status;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = LayoutInflater.from(getContext()).inflate(R.layout.status_layout_model,parent,false);

        CircleImageView circleImageViewStatus;
        TextView textViewStatus_Username,textViewStatus_Time;

        circleImageViewStatus=view.findViewById(R.id.circcleImageViewStatus);
        textViewStatus_Username=view.findViewById(R.id.TextViewStatus_name);
        textViewStatus_Time=view.findViewById(R.id.TextViewStatus_Time);

        circleImageViewStatus.setImageResource(model_data_status.get(position).getStatus_photo());
        textViewStatus_Username.setText(model_data_status.get(position).getStatus_name());
        textViewStatus_Time.setText(model_data_status.get(position).getStatus_time());

        return view;
    }
}
