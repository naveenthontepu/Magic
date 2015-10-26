package com.example.naveen.magic;


import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.List;

public class CustomAdapter extends ArrayAdapter<Day_Tasks>{


    private LayoutInflater inflater;
    private List<Day_Tasks> day_tasksLocal;
    private final String TAG="com.example.naveen.magic";


    public CustomAdapter(Context context, List<Day_Tasks> day_tasks) {
        super(context,R.layout.days_item_layout, day_tasks);
        Log.i(TAG, "Custom Adaptor");
        inflater=LayoutInflater.from(getContext());
        day_tasksLocal =day_tasks;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Log.i(TAG, "getView");
        View row = inflater.inflate(R.layout.days_item_layout,parent,false);
        Log.i(TAG, "inflating done");
        TextView day_number = (TextView)row.findViewById(R.id.day_number);
        TextView tasks_number = (TextView)row.findViewById(R.id.tasks_number);
        ProgressBar percent_progress = (ProgressBar)row.findViewById(R.id.percent_progress);
        TextView day_name=(TextView)row.findViewById(R.id.day_name);
        Log.i(TAG, "find view by id done");

        Day_Tasks day_tasks = day_tasksLocal.get(position);
        Log.i(TAG, "Progress = "+String.valueOf(day_tasks.getProgress()));

        day_number.setText(day_tasks.getDay_number());
        tasks_number.setText(day_tasks.getTask_number());
        percent_progress.setProgress(day_tasks.getProgress());
        day_name.setText(day_tasks.getDay_name());

        return row;
    }
}
