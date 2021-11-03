package com.example.session5.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.session5.Models_data.Model_data_chat;
import com.example.session5.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

public class Custom_Adapter_Chat extends ArrayAdapter {
    ArrayList<Model_data_chat> model_data_chats = new ArrayList<>();

    public Custom_Adapter_Chat(Context context, int resource, ArrayList<Model_data_chat> model_data_chats) {
        super(context, resource, model_data_chats);
        this.model_data_chats = model_data_chats;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = LayoutInflater.from(getContext()).inflate(R.layout.chat_model, parent, false);
        CircleImageView circleImageViewChat;
        TextView textViewChat_UserName, textViewChat_Msg, textViewChat_time;
        ImageView imageView_seenIndicator;

        circleImageViewChat = view.findViewById(R.id.circcleImageViewChat);
        textViewChat_UserName = view.findViewById(R.id.TextViewChat_name);
        textViewChat_Msg = view.findViewById(R.id.TextViewChat_MSG);
        textViewChat_time = view.findViewById(R.id.TextViewChat_Time);


        String URL = model_data_chats.get(position).getUser_photo();
        Picasso.get().load(URL).placeholder(R.drawable.defualt_avatar).error(R.drawable.defualt_avatar).into(circleImageViewChat);
        textViewChat_UserName.setText(model_data_chats.get(position).getUser_name());
        textViewChat_Msg.setText(model_data_chats.get(position).getUser_msg());
        textViewChat_time.setText(model_data_chats.get(position).getUser_msg_time());



        return view;

    }
}
