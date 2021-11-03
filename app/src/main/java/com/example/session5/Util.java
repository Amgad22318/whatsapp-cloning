package com.example.session5;

import com.google.firebase.database.FirebaseDatabase;

public class Util {


    private static FirebaseDatabase mData;

    public static FirebaseDatabase getDatabase() {
        if (mData == null) {

            mData = FirebaseDatabase.getInstance();
            mData.setPersistenceEnabled(true);
        }
        return mData;
    }
}
