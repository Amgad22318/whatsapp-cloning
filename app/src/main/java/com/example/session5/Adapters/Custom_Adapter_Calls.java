package com.example.session5.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.session5.Models_data.Model_data_calls;
import com.example.session5.R;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

public class Custom_Adapter_Calls extends ArrayAdapter {

    ArrayList<Model_data_calls> model_data_calls=new ArrayList<>();

    public Custom_Adapter_Calls(Context context, int resource, ArrayList<Model_data_calls> model_data_calls) {
        super(context, resource, model_data_calls);
        this.model_data_calls=model_data_calls;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = LayoutInflater.from(getContext()).inflate(R.layout.calls_layout_model,parent,false);

        CircleImageView circleImageViewCalls;
        ImageView imageView_CallDirection;
        ImageButton imageButton_CallOrVideo;
        TextView textViewCaller_Name,textViewCall_time;

        circleImageViewCalls=view.findViewById(R.id.circcleImageViewCalls);
        imageView_CallDirection=view.findViewById(R.id.imageviewCalls_call_direction);
        imageButton_CallOrVideo=view.findViewById(R.id.imageButtonCalls_VideoOrCall);
        textViewCaller_Name=view.findViewById(R.id.TextViewCalls_name);
        textViewCall_time=view.findViewById(R.id.TextViewCalls_Time);

        circleImageViewCalls.setImageResource(model_data_calls.get(position).getCalls_CallerPhoto());
        imageView_CallDirection.setImageResource(model_data_calls.get(position).getCalls_Call_direction());
        imageButton_CallOrVideo.setImageResource(model_data_calls.get(position).getCalls_CallOrVideo());
        textViewCaller_Name.setText(model_data_calls.get(position).getCalls_CallerName());
        textViewCall_time.setText(model_data_calls.get(position).getCalls_CallTime());



      return  view;
    }
}
