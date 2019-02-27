package com.mars.atm;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.mars.atm.dao.TranstTask;

public class LoginActivity extends AppCompatActivity {

    private static final int REQUEST_CODE_CAMERA = 5;

    private EditText e_id;
    private EditText e_password;
    private CheckBox cb_save_id;
    private Button b_camera;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        findViews();

        saveID();

        b_camera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getpermission();
            }
        });


        new TranstTask(getApplicationContext()).execute("http://tw.yahoo.com");

    }



    private void getpermission() {
        int permission = ContextCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.CAMERA);

        /*危險權限*/
        if (permission == PackageManager.PERMISSION_GRANTED){
            takePhote();
        }else {
            ActivityCompat.requestPermissions(LoginActivity.this,new String[]{Manifest.permission.CAMERA}, REQUEST_CODE_CAMERA);
        }
    }

    private void saveID() {
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

    private void findViews() {
        e_id = findViewById(R.id.e_id);
        e_password = findViewById(R.id.e_password);
        cb_save_id = findViewById(R.id.cb_save_id);
        b_camera = findViewById(R.id.camera);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == REQUEST_CODE_CAMERA){
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED){
                takePhote();
            }
        }
    }

    private void takePhote() {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivity(intent);
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

    /**
     * open map
     */
    public void map(View view){
        Intent intent = new Intent(this, MapsActivity.class);
        startActivity(intent);
    }
}
