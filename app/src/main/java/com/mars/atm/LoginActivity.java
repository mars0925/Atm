package com.mars.atm;

import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class LoginActivity extends AppCompatActivity {

    private EditText e_id;
    private EditText e_password;
    private CheckBox cb_save_id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        e_id = findViewById(R.id.e_id);
        e_password = findViewById(R.id.e_password);
        cb_save_id = findViewById(R.id.cb_save_id);

        /*是否記住帳號*/
        cb_save_id.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                //儲存是否記得帳號的狀態
                getSharedPreferences("ACOUNT",MODE_PRIVATE).edit().putBoolean("IS_SAVE_ID",isChecked).apply();
            }
        });

        cb_save_id.setChecked(getSharedPreferences("ACOUNT",MODE_PRIVATE).getBoolean("IS_SAVE_ID",false));

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

                            boolean isSave = getSharedPreferences("ACOUNT",MODE_PRIVATE).getBoolean("IS_SAVE_ID",false);

                            if (isSave){
                                getSharedPreferences("ACOUNT",MODE_PRIVATE).edit()
                                        .putString("ID",id)
                                        .commit();
                            }
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
