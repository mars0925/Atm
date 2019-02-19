package com.mars.atm;


import android.database.Cursor;
import android.provider.ContactsContract;
import android.support.annotation.NonNull;

import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import static android.Manifest.permission.READ_CONTACTS;
import static android.content.pm.PackageManager.PERMISSION_GRANTED;

public class ContactActivity extends AppCompatActivity {

    private static final int REQUEST_CODE_CONTACT = 1;
    private static final String TAG = ContactActivity.class.getSimpleName();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact);

        int permission = ActivityCompat.checkSelfPermission(this, READ_CONTACTS);
        if (permission == PERMISSION_GRANTED){
            readContact();
        }else {
            ActivityCompat.requestPermissions(this,new String[]{READ_CONTACTS},REQUEST_CODE_CONTACT);
        }

    }

    /**
        * 讀取聯絡人
        */
    private void readContact() {
        Cursor cursor = getContentResolver().query(ContactsContract.Contacts.CONTENT_URI,
                null, null, null, null);

        while (cursor.moveToNext()){
            String name = cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME));
            Log.e(TAG, "readContact: " + name );

            int id = cursor.getInt(cursor.getColumnIndex(ContactsContract.Contacts._ID));

            int hasphone = cursor.getInt(cursor.getColumnIndex(ContactsContract.Contacts.HAS_PHONE_NUMBER));

            Cursor phoneCursor = getContentResolver().query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI,null, ContactsContract.CommonDataKinds.Phone.CONTACT_ID + "=?",
                                        new String[]{String.valueOf(id)},null);

            while (phoneCursor.moveToNext()){
                String number = phoneCursor.getString(phoneCursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DATA));
                Log.e(TAG, "readContact: \t" + number);
            }


        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == REQUEST_CODE_CONTACT){
            if (grantResults[0] == PERMISSION_GRANTED){
                readContact();
            }
        }
    }
}
