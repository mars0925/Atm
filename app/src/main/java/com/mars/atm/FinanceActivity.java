package com.mars.atm;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.mars.atm.adapter.ExpenseAdapter;
import com.mars.atm.dao.ExpenseHelp;

public class FinanceActivity extends AppCompatActivity {

    private RecyclerView r_expense;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_finance);
        Toolbar toolbar = findViewById(R.id.toolbar);
        FloatingActionButton fab = findViewById(R.id.fab);


        ExpenseHelp expenseHelp = new ExpenseHelp(this);
        Cursor cursor = expenseHelp.getReadableDatabase().query("expense",null,null,null,null,null,null);



        r_expense = findViewById(R.id.r_expense);
        r_expense.setLayoutManager(new LinearLayoutManager(this));
        r_expense.setHasFixedSize(true);
        r_expense.setAdapter(new ExpenseAdapter(cursor));
        setSupportActionBar(toolbar);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(FinanceActivity.this,AddActivity.class);
                startActivity(intent);
            }
        });
    }

}
