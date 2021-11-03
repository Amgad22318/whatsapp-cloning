package com.example.session5;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.session5.Data.Country_and_codes;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.concurrent.TimeUnit;

public class Sign extends AppCompatActivity {
    public static String current_user_id;
    public static String current_phoneNuumber;
    Button sign_btn;
    Spinner country_spinner;
    EditText editText_phoneNum, editText_countryKey;
    DatabaseReference reference;
    FirebaseAuth firebaseAuth;

    androidx.appcompat.widget.Toolbar toolbar_sign;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sign);

        Util.getDatabase();
        firebaseAuth = FirebaseAuth.getInstance();
        reference = FirebaseDatabase.getInstance().getReference();

        country_spinner = findViewById(R.id.country_spinner);
        country_spinner.setAdapter(new ArrayAdapter<>(this, R.layout.support_simple_spinner_dropdown_item, Country_and_codes.country));
        country_spinner.setSelection(61);

        sign_btn = findViewById(R.id.sign_btn);
        toolbar_sign = findViewById(R.id.sign_toolbar);
        editText_phoneNum = findViewById(R.id.user_phoneNumber);
        editText_countryKey = findViewById(R.id.user_phoneNumber_countryKey);
        setSupportActionBar(toolbar_sign);

        country_spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                String country_position = Country_and_codes.code[country_spinner.getSelectedItemPosition() + 2].replaceAll(" ", "");
                editText_countryKey.setText(country_position.trim());

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });


        sign_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String code = Country_and_codes.code[country_spinner.getSelectedItemPosition() + 2];
                code.replaceAll("0","");
                String phone_num = editText_phoneNum.getText().toString();
                String phoneWithCode = (code + phone_num).replaceAll(" ", "");
                Toast.makeText(Sign.this, "" + phoneWithCode, Toast.LENGTH_SHORT).show();
                Verification(phoneWithCode);
            }
        });


    }

    @Override
    protected void onStart() {
        super.onStart();

        if (firebaseAuth.getCurrentUser() != null) {
            current_user_id = firebaseAuth.getCurrentUser().getUid();
            current_phoneNuumber = firebaseAuth.getCurrentUser().getPhoneNumber();
            reference.child("users").child(firebaseAuth.getCurrentUser().getUid()).child("phoneNum").setValue(firebaseAuth.getCurrentUser().getPhoneNumber());
            Chat_FAB chat_fab = new Chat_FAB();
            chat_fab.getContactList(Sign.this);
            chat_fab.update_contacts();

            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
            finish();

        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.sign_menu, menu);
        return true;
    }

    private void Verification(final String phoneNum) {

        PhoneAuthProvider.getInstance().verifyPhoneNumber(phoneNum, 60, TimeUnit.SECONDS, this, new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
            @Override
            public void onVerificationCompleted(PhoneAuthCredential phoneAuthCredential) {

                firebaseAuth.signInWithCredential(phoneAuthCredential).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {

                            String UID = task.getResult().getUser().getUid();
                            reference.child("users").child(UID).child("phoneNum").setValue(firebaseAuth.getCurrentUser().getPhoneNumber());
                            Chat_FAB chat_fab = new Chat_FAB();
                            chat_fab.getContactList(Sign.this);
                            chat_fab.update_contacts();
                            Intent intent = new Intent(Sign.this, MainActivity.class);
                            startActivity(intent);
                            finish();

                        }
                    }
                });


            }

            @Override
            public void onVerificationFailed(FirebaseException e) {
                Toast.makeText(Sign.this, "Wrong Number or Code", Toast.LENGTH_SHORT).show();

            }
        });


    }


}
