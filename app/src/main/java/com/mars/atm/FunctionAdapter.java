package com.mars.atm;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class FunctionAdapter extends RecyclerView.Adapter<FunctionAdapter.FuctionViewHoder> {
    private final String[] functions;
    private Context context;
    public FunctionAdapter(Context context) {
        this.context = context;
        functions = context.getResources().getStringArray(R.array.function);
    }

    @NonNull
    @Override
    public FuctionViewHoder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(
                    android.R.layout.simple_expandable_list_item_1,viewGroup,false);

        return new FuctionViewHoder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FuctionViewHoder fuctionViewHoder, int i) {
        fuctionViewHoder.name.setText(functions[i]);
    }

    @Override
    public int getItemCount() {
        return functions.length;
    }

    public class FuctionViewHoder extends RecyclerView.ViewHolder {
        TextView name;
        public FuctionViewHoder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(android.R.id.text1);
        }
    }
}
