package com.khtn.healthcare.view.caseRecord.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.khtn.healthcare.R;

import java.util.List;

public class HistoryAdapter extends BaseAdapter {
    LayoutInflater inflater;
    private List<String> historyList;
    Context contextActivity;

    public HistoryAdapter(Context context, List<String> data) {
        inflater = LayoutInflater.from(context);
        historyList = data;
        contextActivity = context;
    }

    @Override
    public int getCount() {
        return (historyList == null) ? 0 : historyList.size();
    }

    @Override
    public String getItem(int position) {
        return historyList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        View v = convertView;
        if (v == null) {
            v = inflater.inflate(R.layout.history_row, parent, false);
            holder = new ViewHolder(v);
            v.setTag(holder);
        } else {
            holder = (ViewHolder) v.getTag();
        }
        holder.mDate.setText("12-03-1291");
        holder.mHistory.setText("Khó tiêu");
        return v;
    }

    class ViewHolder {
        TextView mDate = null;
        TextView mHistory = null;

        ViewHolder(View convertView) {
            mDate = convertView.findViewById(R.id.sNo);
            mHistory = convertView.findViewById(R.id.product);
        }
    }
}
