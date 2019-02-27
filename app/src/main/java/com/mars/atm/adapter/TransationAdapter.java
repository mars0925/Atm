package com.mars.atm.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.transition.Transition;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.mars.atm.R;
import com.mars.atm.model.Transaction;

import java.util.ArrayList;

public class TransationAdapter extends RecyclerView.Adapter<TransationAdapter.TransViewHolder>{
    private ArrayList<Transaction> transitionList;

    public TransationAdapter(ArrayList<Transaction> transitionList) {
        this.transitionList = transitionList;
    }

    @NonNull
    @Override
    public TransViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.recyclerview_transation, viewGroup,false);

        return new TransViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TransViewHolder transViewHolder, int i) {
            transViewHolder.t_acount.setText(transitionList.get(i).getAcount());
            transViewHolder.t_date.setText(transitionList.get(i).getDate());
            transViewHolder.t_amount.setText(String.valueOf(transitionList.get(i).getAmount()));
            transViewHolder.t_type.setText(String.valueOf(transitionList.get(i).getType()));
    }

    @Override
    public int getItemCount() {
        return transitionList.size();
    }

    public class TransViewHolder extends RecyclerView.ViewHolder {
        TextView t_acount;
        TextView t_date;
        TextView t_amount;
        TextView t_type;

        public TransViewHolder(@NonNull View itemView) {
            super(itemView);
            t_acount = itemView.findViewById(R.id.t_acount);
            t_date = itemView.findViewById(R.id.t_date);
            t_amount = itemView.findViewById(R.id.t_amount);
            t_type = itemView.findViewById(R.id.t_type);
        }
    }
}
