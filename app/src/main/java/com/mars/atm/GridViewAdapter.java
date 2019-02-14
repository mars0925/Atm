package com.mars.atm;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class GridViewAdapter extends RecyclerView.Adapter<GridViewAdapter.GridViewHolder>{
    private ArrayList<Function> functionList;

    public GridViewAdapter(ArrayList<Function> functionList) {
        this.functionList = functionList;
    }

    @NonNull
    @Override
    public GridViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.icon_item,viewGroup, false);

        return new GridViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull GridViewHolder gridViewHolder, int i) {
        gridViewHolder.i_icon.setImageResource(functionList.get(i).getImageId());
        gridViewHolder.t_name.setText(functionList.get(i).getName());

        gridViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }

    @Override
    public int getItemCount() {
        return functionList.size();
    }

    class GridViewHolder extends RecyclerView.ViewHolder{
        ImageView i_icon;
        TextView t_name;
        GridViewHolder(@NonNull View itemView) {
            super(itemView);
            i_icon = itemView.findViewById(R.id.i_icon);
            t_name = itemView.findViewById(R.id.t_name);
        }
    }
}
