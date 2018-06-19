package com.khtn.healthcare.view.caseRecord.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.khtn.healthcare.R;
import com.khtn.healthcare.view.caseRecord.OnClickedOption;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class OptionAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private List<String> listOption;
    private Context context;
    private OnClickedOption onClickedOption;

    public OptionAdapter(List<String> listOption, OnClickedOption onClickedOption) {
        this.listOption = listOption;
        this.onClickedOption = onClickedOption;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.tvOption)
        TextView tvOption;
        @BindView(R.id.card_view)
        CardView card_view;

        public MyViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }

        public void bind(String option) {
            tvOption.setText(option);
            card_view.setPreventCornerOverlap(false);
            itemView.setOnClickListener(v -> OptionAdapter.this.onClickedOption.onClickedOption(option));
        }
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        RecyclerView.ViewHolder viewHolder = null;
        View view;
        view = inflater.inflate(R.layout.cardview_item, parent, false);
        viewHolder = new MyViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        String option = listOption.get(position);
        ((MyViewHolder) holder).bind(option);
    }


    @Override
    public int getItemCount() {
        return listOption == null ? 0 : listOption.size();
    }
}
