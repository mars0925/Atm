package com.mars.atm.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.mars.atm.model.ContactData;

import java.util.ArrayList;

public class ContactAdapter extends RecyclerView.Adapter<ContactAdapter.ContactHolder> {
    private ArrayList<ContactData> dataList;
    public ContactAdapter(ArrayList<ContactData> dataList) {
        this.dataList = dataList;
    }

    @NonNull
    @Override
    public ContactHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(android.R.layout.simple_expandable_list_item_2, viewGroup, false);
        return new ContactHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ContactHolder contactHolder, int i) {
        ContactData contactData = dataList.get(i);
        StringBuilder stringBuffer = new StringBuilder();
        contactHolder.nametext.setText(contactData.getName());

        for (String phone : contactData.getPhones()) {
                stringBuffer.append(phone);
                stringBuffer.append("  ");
        }
        contactHolder.phonetext.setText(stringBuffer.toString());
    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }

    class ContactHolder extends RecyclerView.ViewHolder {
        TextView nametext;
        TextView phonetext;
        ContactHolder(@NonNull View itemView) {
            super(itemView);
            nametext = itemView.findViewById(android.R.id.text1);
            phonetext = itemView.findViewById(android.R.id.text2);
        }
    }
}
