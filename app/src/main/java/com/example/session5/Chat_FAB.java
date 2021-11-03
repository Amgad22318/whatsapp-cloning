package com.example.session5;

import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.example.session5.Adapters.Cutom_Adapter_Chat_FAB;
import com.example.session5.Models_data.Model_data_chat_FAB;
import com.example.session5.Models_data.Models_data_contacts;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;


public class Chat_FAB extends AppCompatActivity {
    private Boolean singel_chat_check = true;
    private DatabaseReference reference = FirebaseDatabase.getInstance().getReference();
    private FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
    ArrayList<Models_data_contacts> modelsdatacontactsArrayList = new ArrayList<>();
    Toolbar toolbar;
    ArrayList<Model_data_chat_FAB> model_data_chat_fabArrayList = new ArrayList<>();
    ListView listView_chat_fab;
    TextView textView_contacts_size;
    Cutom_Adapter_Chat_FAB adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.chat__fab);

        toolbar = findViewById(R.id.chat_fab_toolbar);

        textView_contacts_size = findViewById(R.id.chat_fab_contactsSize);


        listView_chat_fab = findViewById(R.id.chat_fab_listView);
        adapter = new Cutom_Adapter_Chat_FAB(Chat_FAB.this, R.layout.chat_fab_item_model, model_data_chat_fabArrayList);
        listView_chat_fab.setAdapter(adapter);


        setSupportActionBar(toolbar);

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Chat_FAB.this, MainActivity.class);
                startActivity(intent);


            }
        });


        reference.keepSynced(true);
        reference.child("users").child(firebaseAuth.getCurrentUser().getUid()).child("chat_list").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot chats : dataSnapshot.getChildren()) {

                    get_chat_list(chats);

                }

                Collections.sort(model_data_chat_fabArrayList, new Comparator<Model_data_chat_FAB>() {
                    @Override
                    public int compare(Model_data_chat_FAB model_data_chat_fab, Model_data_chat_FAB t1) {
                        return model_data_chat_fab.getUser_name().compareTo(t1.getUser_name());
                    }
                });

                adapter.notifyDataSetChanged();
                textView_contacts_size.setText((String.valueOf(model_data_chat_fabArrayList.size())) + " contacts");

            }


            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        final Intent intent = new Intent(this, Person_Chat.class);
        listView_chat_fab.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                Log.i("value", "" + model_data_chat_fabArrayList.get(position).getChat_key());
                intent.putExtra("chat_key", model_data_chat_fabArrayList.get(position).getChat_key());
                intent.putExtra("receiver_id", model_data_chat_fabArrayList.get(position).getReceiver_id());
                intent.putExtra("user_name", model_data_chat_fabArrayList.get(position).getUser_name());
                intent.putExtra("user_photo", model_data_chat_fabArrayList.get(position).getUser_photo());
                intent.putExtra("receiver_phoneNo", model_data_chat_fabArrayList.get(position).getReceiver_phoneNo());
                startActivity(intent);
            }
        });

    }


    public void getContactList(Context context) {

        ContentResolver cr = context.getContentResolver();
        Cursor cur = cr.query(ContactsContract.Contacts.CONTENT_URI,
                null, null, null, null);

        if ((cur != null ? cur.getCount() : 0) > 0) {
            while (cur != null && cur.moveToNext()) {
                String id = cur.getString(
                        cur.getColumnIndex(ContactsContract.Contacts._ID));
                String name = cur.getString(cur.getColumnIndex(
                        ContactsContract.Contacts.DISPLAY_NAME));

                if (cur.getInt(cur.getColumnIndex(
                        ContactsContract.Contacts.HAS_PHONE_NUMBER)) > 0) {
                    Cursor pCur = cr.query(
                            ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
                            null,
                            ContactsContract.CommonDataKinds.Phone.CONTACT_ID + " = ?",
                            new String[]{id}, null);
                    String phoneNo = null;
                    while (pCur.moveToNext()) {


                        if (pCur.getString(pCur.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER))
                                .replaceAll(" ", "").replaceAll("-", "").equals(phoneNo)) {
                            continue;
                        }
                        name.replaceAll(" ", "");

                        phoneNo = pCur.getString(pCur.getColumnIndex(
                                ContactsContract.CommonDataKinds.Phone.NUMBER)).replaceAll(" ", "").replaceAll("-", "");
                        if (!phoneNo.contains("+")) {
                            phoneNo = "+2" + phoneNo;
                        }

                        Models_data_contacts modelsdatacontacts = new Models_data_contacts();
                        modelsdatacontacts.setContact_name(name);
                        modelsdatacontacts.setContact_phoneNum(phoneNo);


                        modelsdatacontactsArrayList.add(modelsdatacontacts);


                    }
                    pCur.close();
                }
            }
        }
        if (cur != null) {
            cur.close();
        }


        for (int i = 0; i < modelsdatacontactsArrayList.size(); i++) {
            for (int j = i + 1; j < modelsdatacontactsArrayList.size(); j++) {
                if (modelsdatacontactsArrayList.get(i).getContact_phoneNum().equals(modelsdatacontactsArrayList.get(j).getContact_phoneNum())) {
                    modelsdatacontactsArrayList.remove(j);
                    j--;
                }
            }
        }

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.chat_fab, menu);
        return true;
    }


    public void update_contacts() {
        reference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {


                for (int i = 0; i < modelsdatacontactsArrayList.size(); i++) {

                    String contact_phone_num = modelsdatacontactsArrayList.get(i).getContact_phoneNum();
                    String contact_name = modelsdatacontactsArrayList.get(i).getContact_name();


                    for (DataSnapshot snapshot_search_key : dataSnapshot.child("users").getChildren()) {

                        if (snapshot_search_key.child("phoneNum").getValue(String.class).equals(contact_phone_num)) {

                            Model_data_chat_FAB model_data_chat_fab = new Model_data_chat_FAB();

                            if (snapshot_search_key.child("about").exists()) {
                                model_data_chat_fab.setReceiver_about(snapshot_search_key.child("about").getValue(String.class));
                            } else if (!snapshot_search_key.child("about").exists()) {
                                model_data_chat_fab.setReceiver_about("Hey there! I am using WhatsApp");
                            }

                            if(snapshot_search_key.child("profile_photo")!=null){
                                model_data_chat_fab.setUser_photo(snapshot_search_key.child("profile_photo").getValue(String.class));
                            }


                            model_data_chat_fab.setReceiver_phoneNo(contact_phone_num);
                            model_data_chat_fab.setSender_phoneNo(firebaseAuth.getCurrentUser().getPhoneNumber());
                            model_data_chat_fab.setReceiver_id(snapshot_search_key.getKey());
                            model_data_chat_fab.setUser_name(contact_name);
                            model_data_chat_fabArrayList.add(model_data_chat_fab);

                            Boolean single_chat_update_check = true;
                            for (DataSnapshot snapshot_chat_key : dataSnapshot.child("users").child(firebaseAuth.getCurrentUser().getUid()).child("chat_list").getChildren()) {


                                if (snapshot_chat_key.child("receiver_phoneNo").getValue(String.class).equals(contact_phone_num)) {

                                    reference.child("users").child(firebaseAuth.getCurrentUser().getUid()).child("chat_list").child(snapshot_chat_key.getKey()).child("user_photo").setValue(model_data_chat_fab.getUser_photo());
                                    reference.child("users").child(firebaseAuth.getCurrentUser().getUid()).child("chat_list").child(snapshot_chat_key.getKey()).child("chat_key").setValue(snapshot_chat_key.getKey());
                                    reference.child("users").child(firebaseAuth.getCurrentUser().getUid()).child("chat_list").child(snapshot_chat_key.getKey()).child("receiver_about").setValue(model_data_chat_fab.getReceiver_about());
                                    reference.child("users").child(firebaseAuth.getCurrentUser().getUid()).child("chat_list").child(snapshot_chat_key.getKey()).child("receiver_id").setValue(model_data_chat_fab.getReceiver_id());
                                    reference.child("users").child(firebaseAuth.getCurrentUser().getUid()).child("chat_list").child(snapshot_chat_key.getKey()).child("receiver_phoneNo").setValue(model_data_chat_fab.getReceiver_phoneNo());
                                    reference.child("users").child(firebaseAuth.getCurrentUser().getUid()).child("chat_list").child(snapshot_chat_key.getKey()).child("sender_phoneNo").setValue(model_data_chat_fab.getSender_phoneNo());
                                    reference.child("users").child(firebaseAuth.getCurrentUser().getUid()).child("chat_list").child(snapshot_chat_key.getKey()).child("user_name").setValue(model_data_chat_fab.getUser_name());
                                    single_chat_update_check = false;
                                    break;
                                }

                            }
                            if (single_chat_update_check) {

                                String random_chat_id = reference.child("users").child(firebaseAuth.getCurrentUser().getUid()).child("chat_list").push().getKey();

                                reference.child("users").child(firebaseAuth.getCurrentUser().getUid()).child("chat_list").child(random_chat_id).child("user_photo").setValue(model_data_chat_fab.getUser_photo());
                                reference.child("users").child(firebaseAuth.getCurrentUser().getUid()).child("chat_list").child(random_chat_id).child("chat_key").setValue(random_chat_id);
                                reference.child("users").child(firebaseAuth.getCurrentUser().getUid()).child("chat_list").child(random_chat_id).child("receiver_about").setValue(model_data_chat_fab.getReceiver_about());
                                reference.child("users").child(firebaseAuth.getCurrentUser().getUid()).child("chat_list").child(random_chat_id).child("receiver_id").setValue(model_data_chat_fab.getReceiver_id());
                                reference.child("users").child(firebaseAuth.getCurrentUser().getUid()).child("chat_list").child(random_chat_id).child("receiver_phoneNo").setValue(model_data_chat_fab.getReceiver_phoneNo());
                                reference.child("users").child(firebaseAuth.getCurrentUser().getUid()).child("chat_list").child(random_chat_id).child("sender_phoneNo").setValue(model_data_chat_fab.getSender_phoneNo());
                                reference.child("users").child(firebaseAuth.getCurrentUser().getUid()).child("chat_list").child(random_chat_id).child("user_name").setValue(model_data_chat_fab.getUser_name());
                            }


                            singel_chat_check = false;
                            break;

                        } else {
                            singel_chat_check = true;
                        }

                    }
                    if (singel_chat_check) {

                        Model_data_chat_FAB model_data_chat_fab = new Model_data_chat_FAB();

                        model_data_chat_fab.setReceiver_about("Hey there! I am using WhatsApp");
                        model_data_chat_fab.setReceiver_phoneNo(contact_phone_num);
                        model_data_chat_fab.setSender_phoneNo(firebaseAuth.getCurrentUser().getPhoneNumber());
                        model_data_chat_fab.setReceiver_id("null");
                        model_data_chat_fab.setUser_name(contact_name);
                        model_data_chat_fabArrayList.add(model_data_chat_fab);



                        Boolean single_chat_update_check = true;
                        for (DataSnapshot snapshot_chat_key : dataSnapshot.child("users").child(firebaseAuth.getCurrentUser().getUid()).child("chat_list").getChildren()) {

                            if (snapshot_chat_key.child("receiver_phoneNo").getValue(String.class).equals(contact_phone_num)) {


                                reference.child("users").child(firebaseAuth.getCurrentUser().getUid()).child("chat_list").child(snapshot_chat_key.getKey()).child("chat_key").setValue(snapshot_chat_key.getKey());
                                reference.child("users").child(firebaseAuth.getCurrentUser().getUid()).child("chat_list").child(snapshot_chat_key.getKey()).child("receiver_about").setValue(model_data_chat_fab.getReceiver_about());
                                reference.child("users").child(firebaseAuth.getCurrentUser().getUid()).child("chat_list").child(snapshot_chat_key.getKey()).child("receiver_id").setValue(model_data_chat_fab.getReceiver_id());
                                reference.child("users").child(firebaseAuth.getCurrentUser().getUid()).child("chat_list").child(snapshot_chat_key.getKey()).child("receiver_phoneNo").setValue(model_data_chat_fab.getReceiver_phoneNo());
                                reference.child("users").child(firebaseAuth.getCurrentUser().getUid()).child("chat_list").child(snapshot_chat_key.getKey()).child("sender_phoneNo").setValue(model_data_chat_fab.getSender_phoneNo());
                                reference.child("users").child(firebaseAuth.getCurrentUser().getUid()).child("chat_list").child(snapshot_chat_key.getKey()).child("user_name").setValue(model_data_chat_fab.getUser_name());
                                single_chat_update_check = false;
                                break;
                            }

                        }
                        if (single_chat_update_check) {

                            String random_chat_id = reference.child("users").child(firebaseAuth.getCurrentUser().getUid()).child("chat_list").push().getKey();


                            reference.child("users").child(firebaseAuth.getCurrentUser().getUid()).child("chat_list").child(random_chat_id).child("chat_key").setValue(random_chat_id);
                            reference.child("users").child(firebaseAuth.getCurrentUser().getUid()).child("chat_list").child(random_chat_id).child("receiver_about").setValue(model_data_chat_fab.getReceiver_about());
                            reference.child("users").child(firebaseAuth.getCurrentUser().getUid()).child("chat_list").child(random_chat_id).child("receiver_id").setValue(model_data_chat_fab.getReceiver_id());
                            reference.child("users").child(firebaseAuth.getCurrentUser().getUid()).child("chat_list").child(random_chat_id).child("receiver_phoneNo").setValue(model_data_chat_fab.getReceiver_phoneNo());
                            reference.child("users").child(firebaseAuth.getCurrentUser().getUid()).child("chat_list").child(random_chat_id).child("sender_phoneNo").setValue(model_data_chat_fab.getSender_phoneNo());
                            reference.child("users").child(firebaseAuth.getCurrentUser().getUid()).child("chat_list").child(random_chat_id).child("user_name").setValue(model_data_chat_fab.getUser_name());
                        }


                    }


                }


            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }


    private void get_chat_list(DataSnapshot snapshot) {

        Model_data_chat_FAB model_data_chat_fab = new Model_data_chat_FAB();

        model_data_chat_fab = snapshot.getValue(Model_data_chat_FAB.class);

        model_data_chat_fabArrayList.add(model_data_chat_fab);


    }
}
