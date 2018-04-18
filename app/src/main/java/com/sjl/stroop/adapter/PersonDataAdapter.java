package com.sjl.stroop.adapter;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.sjl.platform.util.AppUtil;
import com.sjl.stroop.R;
import com.sjl.stroop.model.pojo.PersonData;
import com.sjl.stroop.ui.TestActivity;

import java.util.List;

/**
 * PersonDataAdapter
 *
 * @author 林zero
 * @date 2018/4/18
 */

public class PersonDataAdapter extends RecyclerView.Adapter<PersonDataAdapter.ViewHolder> {
    private Activity activity;
    private List<PersonData> list;

    public PersonDataAdapter(List<PersonData> list) {
        this.list = list;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_person_data, parent, false));
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle = new Bundle();
                AppUtil.startActivity(activity, v, TestActivity.class, bundle);
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        public ViewHolder(View itemView) {
            super(itemView);
        }
    }
}
