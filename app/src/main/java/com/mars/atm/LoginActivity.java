package com.mars.atm;

import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class LoginActivity extends AppCompatActivity {

    private EditText e_id;
    private EditText e_password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        e_id = findViewById(R.id.e_id);
        e_password = findViewById(R.id.e_password);

        //如果getSharedPreferences有資料的話
        String id = getSharedPreferences("ACOUNT",MODE_PRIVATE).getString("ID","");
        e_id.setText(id);
    }

    public void login(View view){

        final String id = e_id.getText().toString();
        final String password = e_password.getText().toString();
        /*取得firebease的資料*/
        FirebaseDatabase.getInstance().getReference("users").child(id).child("password")
                .addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        String pw = (String) dataSnapshot.getValue();
                        if (password.equals(pw)){
                            //save id
                            getSharedPreferences("ACOUNT",MODE_PRIVATE).edit()
                                                .putString("ID",id)
                                                .commit();

                            setResult(RESULT_OK);
                            finish();
                        }
                        else {
                            new AlertDialog.Builder(LoginActivity.this)
                                            .setTitle("Atm")
                                            .setMessage("登入失敗")
                                            .setPositiveButton("OK",null)
                                            .show();
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });
    }

    public void quit(View view){

    }
}
