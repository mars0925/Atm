package com.mars.atm;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;

import com.mars.atm.adapter.GridViewAdapter;
import com.mars.atm.model.Function;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements GridViewAdapter.MyListener{
    private static final int REQUEST_LOGIN = 100;
    private static final String TAG = MainActivity.class.getSimpleName();
    private boolean login;
    private RecyclerView r_list;
    private ArrayList<Function> functionList;
    private String[] functions;

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (requestCode == REQUEST_LOGIN){
            if (resultCode != RESULT_OK){
                finish();
            }
        }

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (!login){
            Intent intent  = new Intent(MainActivity.this,LoginActivity.class);
            startActivityForResult(intent,REQUEST_LOGIN);
        }

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        getfunction();

        //recycler
        r_list = findViewById(R.id.r_list);
        r_list.setHasFixedSize(true);
        r_list.setLayoutManager(new GridLayoutManager(this,3));
        GridViewAdapter adapter = new GridViewAdapter(functionList);
        adapter.setCustomClickListener(this);
        r_list.setAdapter(adapter);

    }

    private void getfunction() {
        functions = getResources().getStringArray(R.array.function);

        functionList = new ArrayList<>();
        functionList.add(new Function(functions[0],R.drawable.func_transaction));
        functionList.add(new Function(functions[1],R.drawable.func_balance));
        functionList.add(new Function(functions[2],R.drawable.func_finance));
        functionList.add(new Function(functions[3],R.drawable.func_contacts));
        functionList.add(new Function(functions[4],R.drawable.func_exit));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void clickResult(Function result) {
        switch (result.getImageId()){
            case R.drawable.func_finance:
                Intent financeIntent = new Intent(MainActivity.this, FinanceActivity.class);
                startActivity(financeIntent);
                break;
            case R.drawable.func_balance:
                break;
            case R.drawable.func_contacts:
                Intent intent = new Intent(MainActivity.this,ContactActivity.class);
                startActivity(intent);
                break;
            case R.drawable.func_transaction:
                Intent traIntent = new Intent(MainActivity.this, TrasnsActivity.class);
                startActivity(traIntent);
                break;
            case R.drawable.func_exit:
                break;
        }

        Log.e(TAG, "clickResult: " + result.getName() );
    }
}
