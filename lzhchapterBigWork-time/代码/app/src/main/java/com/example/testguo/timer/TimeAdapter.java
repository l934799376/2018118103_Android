package com.example.testguo.timer;

import java.util.ArrayList;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class TimeAdapter extends BaseAdapter{
    private ArrayList<String> datas;
    private LayoutInflater inflater;
    public TimeAdapter(Context context,ArrayList<String> list) {
        this.datas = list;
        inflater = LayoutInflater.from(context);
    }
    @Override
    public int getCount() {
        return datas==null?0:datas.size();
    }

    @Override
    public Object getItem(int position) {
        return datas == null?null:datas.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        if (convertView == null) {
            holder = new ViewHolder();
            convertView = inflater.inflate(R.layout.list_time, null);
            holder.countTv = (TextView) convertView.findViewById(R.id.time_count);
            holder.recordTv = (TextView) convertView.findViewById(R.id.time_record);
            convertView.setTag(holder);
        }else {
            holder = (ViewHolder) convertView.getTag();
        }
        holder.countTv.setText("计次"+(position+1));
        holder.recordTv.setText(datas.get(position));
        return convertView;
    }

    class ViewHolder{
        TextView countTv,recordTv;
    }

}