package com.example.session5.Fragments;


import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.session5.Adapters.Custom_Adapter_Chat;
import com.example.session5.Models_data.Model_data_chat;
import com.example.session5.Person_Chat;
import com.example.session5.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;


public class chat extends Fragment {

    private ArrayList<Model_data_chat> chat_data = new ArrayList<>();
    private ListView listViewChat;
    private DatabaseReference reference = FirebaseDatabase.getInstance().getReference();
    private FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
    Custom_Adapter_Chat adapter_chat;
    String URL;


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
reference.keepSynced(true);
        View view = inflater.inflate(R.layout.fragment_chat, container, false);
        listViewChat = view.findViewById(R.id.chats_listview);
        adapter_chat = new Custom_Adapter_Chat(getContext(), R.layout.chat_model, chat_data);
        listViewChat.setAdapter(adapter_chat);
        chat_list();


        final Intent intent_chat = new Intent(getContext(), Person_Chat.class);
        listViewChat.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {

                intent_chat.putExtra("chat_key", chat_data.get(position).getChat_key());
                intent_chat.putExtra("receiver_id", chat_data.get(position).getReceiver_id());
                intent_chat.putExtra("user_name", chat_data.get(position).getUser_name());
                intent_chat.putExtra("user_photo", chat_data.get(position).getUser_photo());
                intent_chat.putExtra("receiver_phoneNo", chat_data.get(position).getReceiver_phoneNo());

                startActivity(intent_chat);


            }
        });


        return view;

    }


    private void chat_list() {

        reference.child("users").child(firebaseAuth.getCurrentUser().getUid()).child("chat_list").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {

                    chat_data.clear();
                    for (final DataSnapshot snapshot_single_chat : dataSnapshot.getChildren()) {

                        if (snapshot_single_chat.child("messages").exists()) {
                            final Model_data_chat model_data_chat = new Model_data_chat();

                            String time_string = snapshot_single_chat.child("last_message").child("message_time").getValue(String.class);


                            model_data_chat.setChat_key(snapshot_single_chat.getKey());
                            model_data_chat.setReceiver_id(snapshot_single_chat.child("receiver_id").getValue(String.class));
                            model_data_chat.setReceiver_phoneNo(snapshot_single_chat.child("receiver_phoneNo").getValue(String.class));
                            model_data_chat.setUser_msg(snapshot_single_chat.child("last_message").child("message_text").getValue(String.class));

                            URL = snapshot_single_chat.child("user_photo").getValue(String.class);
                            model_data_chat.setUser_photo(URL);


                            Log.i("URL", "onDataChange: " + URL);

                            model_data_chat.setUser_msg_time(time_string);
                            model_data_chat.setUser_name(snapshot_single_chat.child("user_name").getValue(String.class));
                            model_data_chat.setSender_phoneNo(firebaseAuth.getCurrentUser().getPhoneNumber());
                            chat_data.add(model_data_chat);

                        }
                    }
                }

                adapter_chat.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


    }

    public static String getDate(long milliSeconds, String dateFormat) {
        // Create a DateFormatter object for displaying date in specified format.
        SimpleDateFormat formatter = new SimpleDateFormat(dateFormat);

        // Create a calendar object that will convert the date and time value in milliseconds to date.
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(milliSeconds);
        return formatter.format(calendar.getTime());
    }

    private String pass_url(String url) {
        return URL = url;


    }
}




