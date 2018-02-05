package com.innoplexus.rohitassignment_04_02_2018.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.innoplexus.rohitassignment_04_02_2018.R;
import com.innoplexus.rohitassignment_04_02_2018.model.Example;

import java.util.ArrayList;

/**
 * Created by Rohit K. Pawar on 04-Feb-18.
 */

public class ContactAdapter extends RecyclerView.Adapter<ContactAdapter.ItemRowHolder> {
    private ArrayList<Example> exampleList;
    private Context mContext;

    public ContactAdapter(Context mContext, ArrayList<Example> exampleList) {
        this.exampleList = exampleList;
        this.mContext = mContext;
    }

    @Override
    public ItemRowHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_contacts, viewGroup, false);
        return new ContactAdapter.ItemRowHolder(v);
    }

    @Override
    public void onBindViewHolder(ItemRowHolder holder, int position) {
        final Example exampleInfo = (Example) exampleList.get(position);
        holder.etName.setText(exampleInfo.getName());
        holder.etUserame.setText(exampleInfo.getUsername());
        holder.etEmail.setText(exampleInfo.getEmail());
        holder.etStreet.setText(exampleInfo.getAddress().getStreet());
        holder.etSuite.setText(exampleInfo.getAddress().getSuite());
        holder.etCity.setText(exampleInfo.getAddress().getCity());
        holder.etZipcode.setText(exampleInfo.getAddress().getZipcode());
        holder.etLat.setText(exampleInfo.getAddress().getGeo().getLat());
        holder.etLng.setText(exampleInfo.getAddress().getGeo().getLng());
        holder.etPhone.setText(exampleInfo.getPhone());
        holder.etWebsite.setText(exampleInfo.getWebsite());
        holder.etComName.setText(exampleInfo.getCompany().getName());
        holder.etBs.setText(exampleInfo.getCompany().getBs());
        holder.etCatPur.setText(exampleInfo.getCompany().getCatchPhrase());
    }

    @Override
    public int getItemCount() {
        return exampleList.size();
    }

    class ItemRowHolder extends RecyclerView.ViewHolder {
        private EditText etName, etUserame, etEmail, etStreet, etSuite, etCity, etZipcode,
                etLat, etLng, etPhone, etWebsite, etComName, etBs, etCatPur;

        ItemRowHolder(View view) {
            super(view);
            etName = (EditText) view.findViewById(R.id.etName);
            etUserame = (EditText) view.findViewById(R.id.etUserame);
            etEmail = (EditText) view.findViewById(R.id.etEmail);
            etStreet = (EditText) view.findViewById(R.id.etStreet);
            etSuite = (EditText) view.findViewById(R.id.etSuite);
            etCity = (EditText) view.findViewById(R.id.etCity);
            etZipcode = (EditText) view.findViewById(R.id.etZipcode);
            etLat = (EditText) view.findViewById(R.id.etLat);
            etLng = (EditText) view.findViewById(R.id.etLng);
            etPhone = (EditText) view.findViewById(R.id.etPhone);
            etWebsite = (EditText) view.findViewById(R.id.etWebsite);
            etComName = (EditText) view.findViewById(R.id.etComName);
            etBs = (EditText) view.findViewById(R.id.etBs);
            etCatPur = (EditText) view.findViewById(R.id.etCatPur);

        }
    }
}
