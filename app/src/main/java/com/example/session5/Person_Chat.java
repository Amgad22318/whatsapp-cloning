package com.example.session5;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.session5.Adapters.Custom_Adapter_singlemessage;
import com.example.session5.Models_data.Model_data_chat;
import com.example.session5.Models_data.Model_data_message;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.squareup.picasso.Picasso;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

import de.hdodenhof.circleimageview.CircleImageView;

public class Person_Chat extends AppCompatActivity {
    int size=0;
    CircleImageView circleImageView_chatingPerson_photo;
    TextView textView_userName;
    DatabaseReference reference = FirebaseDatabase.getInstance().getReference();
    ArrayList<Model_data_message> messageArrayList = new ArrayList<>();
    androidx.appcompat.widget.Toolbar toolbar_chat;
    EditText editText_msg;
    ListView chatListView;
    FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
    StorageReference storageReference = FirebaseStorage.getInstance().getReference();
    FloatingActionButton text_or_mic_FAB;
    String chat_key;
    Custom_Adapter_singlemessage adapter;
    public static Model_data_chat model_data_chat_static = new Model_data_chat();
    boolean load_chat = true;
    String time_message_breaker;

    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.chat_activity_main);
        chat_key = getIntent().getStringExtra("chat_key");

        circleImageView_chatingPerson_photo = findViewById(R.id.Chating_withPesrson_photo);
        textView_userName = findViewById(R.id.Chating_withPerson_name);

        update_chat_name_photo();

        toolbar_chat = findViewById(R.id.chat_toolbar);
        setSupportActionBar(toolbar_chat);
        toolbar_chat.setNavigationIcon(R.drawable.ic_arrow_back);
        toolbar_chat.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), MainActivity.class);
                startActivity(intent);
            }
        });

        editText_msg = findViewById(R.id.edittext_msg_place);
        text_or_mic_FAB = findViewById(R.id.mic);
        editText_msg.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {


            }

            @Override
            public void onTextChanged(CharSequence charSequence, int start, int before, int count) {

                if (start == 0 && count == 0) {
                    text_or_mic_FAB.setImageResource(R.drawable.ic_mic_black_24dp);

                } else {
                    text_or_mic_FAB.setImageResource(R.drawable.ic_send_black_24dp);
                    send_message();

                }


            }

            @Override
            public void afterTextChanged(Editable editable) {


            }
        });


        chatListView = findViewById(R.id.message_listview);
        adapter = new Custom_Adapter_singlemessage(Person_Chat.this, R.layout.messages, messageArrayList, chat_key, firebaseAuth.getCurrentUser().getPhoneNumber());
        chatListView.setAdapter(adapter);

        if (load_chat) {
            reference.child("users").child(firebaseAuth.getCurrentUser().getUid()).child("chat_list")
                    .child(chat_key).child("messages").orderByKey().limitToLast(100).addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    for (DataSnapshot message : dataSnapshot.getChildren())
                    {size++;}
                    int counter = 0;
                    for (DataSnapshot message : dataSnapshot.getChildren()) {
                        counter++;
                        if (counter == size) {
                            break;
                        }
                        Model_data_message model_data_message = new Model_data_message();
                        model_data_message = message.getValue(Model_data_message.class);
                        messageArrayList.add(model_data_message);
                        adapter.notifyDataSetChanged();
                        chatListView.setSelection(messageArrayList.size());
                        Log.i("msg", " " + model_data_message.getMessage_text());
                    }

                    load_chat = false;
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            });
        }


        reference.child("users").child(firebaseAuth.getCurrentUser().getUid()).child("chat_list")
                .child(chat_key).child("messages").orderByKey().limitToLast(1).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    Model_data_message model_data_message = new Model_data_message();


                    model_data_message.setSender_phoneNo(snapshot.child("sender_phoneNo").getValue(String.class));
                    model_data_message.setMessage_text(snapshot.child("message_text").getValue(String.class));
                    model_data_message.setMessage_time(snapshot.child("message_time").getValue(String.class));

                    Log.i("msg1", " " + model_data_message.getMessage_text());
                    if (time_message_breaker != model_data_message.getMessage_time() &&
                            model_data_message.getMessage_text() != null
                            && model_data_message.getSender_phoneNo() != null
                            && model_data_message.getMessage_time() != null) {

                        Log.i("phonenum", "onClick: " + model_data_message.getSender_phoneNo());
                        messageArrayList.add(model_data_message);
                        adapter.notifyDataSetChanged();
                        chatListView.setSelection(messageArrayList.size());
                    }
                    time_message_breaker = model_data_message.getMessage_time();
                }
            }


            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.chat_menu, menu);
        return true;
    }


    private void send_message() {
        text_or_mic_FAB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!editText_msg.getText().toString().isEmpty()) {

                    long time = System.currentTimeMillis();
                    String time_string = getDate(time, "h:mm a");


                    final String receiver_id = getIntent().getStringExtra("receiver_id");
                    String receiver_phoneNo = getIntent().getStringExtra("receiver_phoneNo");


                    final Model_data_message model_data_message = new Model_data_message();

                    model_data_message.setMessage_text(editText_msg.getText().toString());
                    model_data_message.setMessage_time(time_string);
                    model_data_message.setSender_phoneNo(firebaseAuth.getCurrentUser().getPhoneNumber());


                    //setting my message
                    String random_messageId = reference.child("users").child(firebaseAuth.getCurrentUser().getUid()).child("chat_list").child(chat_key).child("messages").push().getKey();

                    reference.child("users").child(firebaseAuth.getCurrentUser().getUid()).child("chat_list").child(chat_key).child("messages").child(random_messageId).setValue(model_data_message);

                    //setting last message
                    reference.child("users").child(firebaseAuth.getCurrentUser().getUid()).child("chat_list").child(chat_key).child("last_message").child("message_text").setValue(model_data_message.getMessage_text());
                    reference.child("users").child(firebaseAuth.getCurrentUser().getUid()).child("chat_list").child(chat_key).child("last_message").child("message_time").setValue(model_data_message.getMessage_time());
                    reference.child("users").child(firebaseAuth.getCurrentUser().getUid()).child("chat_list").child(chat_key).child("last_message").child("message_sender_phoneNo").setValue(model_data_message.getSender_phoneNo());


                    reference.child("users").child(receiver_id).child("chat_list").addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                            for (DataSnapshot reciver_single_chat : dataSnapshot.getChildren()) {
                                if (reciver_single_chat.child("receiver_phoneNo").getValue(String.class).equals(firebaseAuth.getCurrentUser().getPhoneNumber())) {


                                    //setting other person message
                                    String random_messageId2 = reference.child("users").child(receiver_id).child("chat_list").child(reciver_single_chat.getKey()).child("messages").push().getKey();
                                    reference.child("users").child(receiver_id).child("chat_list").child(reciver_single_chat.getKey()).child("messages").child(random_messageId2).setValue(model_data_message);

                                    //setting last message
                                    reference.child("users").child(receiver_id).child("chat_list").child(reciver_single_chat.getKey()).child("last_message").child("message_text").setValue(model_data_message.getMessage_text());
                                    reference.child("users").child(receiver_id).child("chat_list").child(reciver_single_chat.getKey()).child("last_message").child("message_time").setValue(model_data_message.getMessage_time());
                                    reference.child("users").child(receiver_id).child("chat_list").child(reciver_single_chat.getKey()).child("last_message").child("message_sender_phoneNo").setValue(model_data_message.getSender_phoneNo());

                                }


                            }


                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError) {

                        }
                    });

                    editText_msg.getText().clear();
                }


            }
        });


    }


    private void update_chat_name_photo() {
        String Url = getIntent().getStringExtra("user_photo");
        String userName = getIntent().getStringExtra("user_name");

        Picasso.get().load(Url).placeholder(R.drawable.defualt_avatar).error(R.drawable.defualt_avatar).into(circleImageView_chatingPerson_photo);
        textView_userName.setText(userName);

    }

    public static String getDate(long milliSeconds, String dateFormat) {

        SimpleDateFormat formatter = new SimpleDateFormat(dateFormat);

        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(milliSeconds);
        return formatter.format(calendar.getTime());
    }
}
