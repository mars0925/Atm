package com.mars.atm;


import android.database.Cursor;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.google.firebase.database.FirebaseDatabase;
import com.mars.atm.adapter.ContactAdapter;
import com.mars.atm.model.ContactData;

import java.util.ArrayList;

import static android.Manifest.permission.READ_CONTACTS;
import static android.content.pm.PackageManager.PERMISSION_GRANTED;

public class ContactActivity extends AppCompatActivity {

    private static final int REQUEST_CODE_CONTACT = 1;
    private static final String TAG = ContactActivity.class.getSimpleName();
    private RecyclerView r_contact;
    private ArrayList<ContactData> contactList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact);

        /*危險權限*/
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

        contactList = new ArrayList<>();

        while (cursor.moveToNext()){
            String name = cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME));
            Log.e(TAG, "readContact: " + name );

            int id = cursor.getInt(cursor.getColumnIndex(ContactsContract.Contacts._ID));

            ContactData contactData = new ContactData(name);

            int hasphone = cursor.getInt(cursor.getColumnIndex(ContactsContract.Contacts.HAS_PHONE_NUMBER));

            if (hasphone == 1){
                Cursor phoneCursor = getContentResolver().query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI,null, ContactsContract.CommonDataKinds.Phone.CONTACT_ID + "=?",
                        new String[]{String.valueOf(id)},null);

                while (phoneCursor.moveToNext()){
                    String number = phoneCursor.getString(phoneCursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DATA));
                    Log.e(TAG, "readContact: \t" + number);

                    contactData.getPhones().add(number);
                }
            }
            contactList.add(contactData);
        }

        r_contact = findViewById(R.id.r_contact);
        r_contact.setHasFixedSize(true);
        r_contact.setLayoutManager(new LinearLayoutManager(this));
        r_contact.setAdapter(new ContactAdapter(contactList));
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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.upload, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.uplaod_item:
               String id = getSharedPreferences("ACOUNT",MODE_PRIVATE).getString("ID",null);
                /*放到firebase上*/
                if (id != null) {
                    FirebaseDatabase.getInstance().getReference("users").child(id).child("contact").setValue(contactList);
                }

                Log.e(TAG, "onOptionsItemSelected");
                break;
        }

        return super.onOptionsItemSelected(item);
    }
}
