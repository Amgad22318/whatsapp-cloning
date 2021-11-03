package com.example.session5.Adapters;

import android.content.Context;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.cardview.widget.CardView;

import com.example.session5.Models_data.Model_data_message;
import com.example.session5.R;
import com.example.session5.Sign;
import com.google.firebase.auth.FirebaseAuth;

import java.util.ArrayList;

public class Custom_Adapter_singlemessage extends ArrayAdapter {

    LinearLayout linearLayout;
    private FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();


    private ArrayList<Model_data_message> model_data_messageArrayList = new ArrayList<>();
    String chat_key;
    String current_user_number;

    public Custom_Adapter_singlemessage(Context context, int resource, ArrayList<Model_data_message> model_data_messageArrayList, String chat_key,String current_user_number) {
        super(context, resource, model_data_messageArrayList);
        this.model_data_messageArrayList = model_data_messageArrayList;
        this.chat_key = chat_key;
        this.current_user_number=current_user_number;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = LayoutInflater.from(getContext()).inflate(R.layout.messages, parent, false);

        Log.i("test", ""+model_data_messageArrayList.get(position).getMessage_time());
        Log.i("test", ""+model_data_messageArrayList.get(position).getSender_phoneNo());
        Log.i("test", ""+model_data_messageArrayList.get(position).getMessage_text());
        TextView textView_textmMssage, textView_time;
        linearLayout = view.findViewById(R.id.LinearLayout_message);
        textView_textmMssage = view.findViewById(R.id.message_textView);
        textView_time = view.findViewById(R.id.time_textView);
        final CardView cardView = view.findViewById(R.id.message_cardView);


        if (model_data_messageArrayList.get(position).getSender_phoneNo().equals(firebaseAuth.getCurrentUser().getPhoneNumber())
        ) {
            linearLayout.setGravity(Gravity.END);
            textView_textmMssage.setText(model_data_messageArrayList.get(position).getMessage_text());
            textView_time.setText(model_data_messageArrayList.get(position).getMessage_time());

        } if (!model_data_messageArrayList.get(position).getSender_phoneNo().equals(firebaseAuth.getCurrentUser().getPhoneNumber())) {
            linearLayout.setGravity(Gravity.START);
            textView_time.setCompoundDrawablesWithIntrinsicBounds(0,0,0,0);
            cardView.setBackgroundTintList(getContext().getResources().getColorStateList(R.color.white));
            textView_textmMssage.setText(model_data_messageArrayList.get(position).getMessage_text());
            textView_time.setText(model_data_messageArrayList.get(position).getMessage_time());

        }
        return view;
    }
}
