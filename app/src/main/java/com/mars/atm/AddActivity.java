package com.mars.atm;

import android.content.ContentValues;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.mars.atm.dao.ExpenseHelp;

public class AddActivity extends AppCompatActivity {

    private EditText e_date;
    private EditText e_desc;
    private EditText e_aount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);
        e_date = findViewById(R.id.e_date);
        e_desc = findViewById(R.id.e_description);
        e_aount = findViewById(R.id.e_amount);
    }


    /**新增*/
    public void add(View view){
        String date = e_date.getText().toString();
        String des = e_desc.getText().toString();
        int aount = Integer.parseInt(e_aount.getText().toString());

        ExpenseHelp expenseHelp = new ExpenseHelp(this);

        ContentValues contentValues = new ContentValues();
        contentValues.put("cdate",date);
        contentValues.put("info", des);
        contentValues.put("amount",aount);

        long id = expenseHelp.getWritableDatabase().insert("expense",null,contentValues);

        if (id > -1){
            Toast.makeText(this,"新增成功",Toast.LENGTH_LONG).show();
        }else {
            Toast.makeText(this,"新增成功",Toast.LENGTH_LONG).show();
        }

    }
}
