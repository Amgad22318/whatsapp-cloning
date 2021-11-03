package com.example.session5.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.session5.Models_data.Model_data_chat_FAB;
import com.example.session5.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

public class Cutom_Adapter_Chat_FAB extends ArrayAdapter {

    public Cutom_Adapter_Chat_FAB(Context context, int resource, ArrayList<Model_data_chat_FAB> model_data_chat_fabs) {
        super(context, resource, model_data_chat_fabs);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = LayoutInflater.from(getContext()).inflate(R.layout.chat_fab_item_model, parent, false);
        Model_data_chat_FAB model_data_chat_fab=(Model_data_chat_FAB)getItem(position);


        CircleImageView receiverImage;
        TextView receiverNmae, receiver_status;


        receiverImage = view.findViewById(R.id.chat_fab_item_Image);
        receiverNmae = view.findViewById(R.id.chat_fab_item_UserName);
        receiver_status = view.findViewById(R.id.chat_fab_item_Status);

        String Url = model_data_chat_fab.getUser_photo();
        Picasso.get().load(Url).placeholder(R.drawable.defualt_avatar).error(R.drawable.defualt_avatar).into(receiverImage);
        receiverNmae.setText(model_data_chat_fab.getUser_name());
        receiver_status.setText(model_data_chat_fab.getReceiver_about());


        return view;
    }
}
