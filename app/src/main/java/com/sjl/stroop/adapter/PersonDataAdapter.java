package com.sjl.stroop.adapter;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.sjl.platform.util.AppUtil;
import com.sjl.platform.util.LogUtil;
import com.sjl.stroop.R;
import com.sjl.stroop.model.pojo.PersonData;
import com.sjl.stroop.ui.StroopResultActivity;
import com.sjl.stroop.ui.StroopTestActivity;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * PersonDataAdapter
 *
 * @author 林zero
 * @date 2018/4/18
 */

public class PersonDataAdapter extends RecyclerView.Adapter<PersonDataAdapter.ViewHolder> {
    private static final String TAG = "PersonDataAdapter";
    private Activity activity;
    private List<PersonData> list;

    public PersonDataAdapter(Activity activity, List<PersonData> list) {
        this.activity = activity;
        this.list = list;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_person_data, parent, false));
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        PersonData personData = list.get(position);
        holder.tvName.setText(personData.getName());
        holder.tvBirth.setText(personData.getBirth());
        holder.tvGender.setText(personData.getGender());
        holder.tvState.setText(personData.getStroopState() ? "查看详情 >>" : "继续测试 >>");
        holder.tvState.setTextColor(ContextCompat.getColor(activity, personData.getStroopState() ? R.color.black : R.color.red));
        holder.addOnItemClickListener(personData);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.tvName)
        TextView tvName;
        @BindView(R.id.tvBirth)
        TextView tvBirth;
        @BindView(R.id.tvGender)
        TextView tvGender;
        @BindView(R.id.tvState)
        TextView tvState;
        @BindView(R.id.tvEducation)
        TextView tvEducation;
        @BindView(R.id.tvJob)
        TextView tvJob;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        public void addOnItemClickListener(final PersonData personData) {
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Bundle bundle = new Bundle();
                    AppUtil.startActivity(activity, v, personData.getStroopState() ? StroopResultActivity.class : StroopTestActivity.class, bundle);
                }
            });
        }
    }
}
