package com.mars.atm.adapter;

import android.database.Cursor;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.mars.atm.R;

public class ExpenseAdapter extends  RecyclerView.Adapter<ExpenseAdapter.expenseHolder>{
    Cursor cursor;

    public ExpenseAdapter(Cursor cursor) {
        this.cursor = cursor;
    }

    @NonNull
    @Override
    public expenseHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.recyclerview_item_expense,viewGroup,false);
        return new expenseHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull expenseHolder expenseHolder, int i) {
        /*取查詢DB的資料*/
        cursor.moveToPosition(i);
        String date = cursor.getString(cursor.getColumnIndex("cdate"));
        String description = cursor.getString(cursor.getColumnIndex("info"));
        int amount = cursor.getInt(cursor.getColumnIndex("amount"));

        expenseHolder.t_date.setText(date);
        expenseHolder.t_desc.setText(description);
        expenseHolder.t_amount.setText(String.valueOf(amount));


    }

    @Override
    public int getItemCount() {
        return cursor.getCount();
    }

    class expenseHolder extends RecyclerView.ViewHolder{
        TextView t_date;
        TextView t_desc;
        TextView t_amount;

        public expenseHolder(@NonNull View itemView) {
            super(itemView);
            t_date = itemView.findViewById(R.id.t_date);
            t_desc = itemView.findViewById(R.id.t_dec);
            t_amount = itemView.findViewById(R.id.t_aount);
        }
    }
}
