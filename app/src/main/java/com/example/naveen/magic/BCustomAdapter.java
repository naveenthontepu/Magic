package com.example.naveen.magic;


import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

public class BCustomAdapter extends ArrayAdapter{

    private List<Blessings_Count> blessings_countLocal;
    private LayoutInflater inflater;
    private final String TAG="com.example.naveen.magic";



    public BCustomAdapter(Context context, List<Blessings_Count> blessings_count) {
        super(context,R.layout.blessing_item,blessings_count);
        inflater=LayoutInflater.from(getContext());
        blessings_countLocal = blessings_count;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View row=inflater.inflate(R.layout.blessing_item,parent,false);
        Log.i(TAG,"getView");
        TextView number_item=(TextView)row.findViewById(R.id.number_item);
        TextView item_text = (TextView)row.findViewById(R.id.item_text);
        Blessings_Count blessing_item = blessings_countLocal.get(position);
        number_item.setText(blessing_item.getItemnumber());
        Log.i(TAG, blessing_item.getItemnumber());

        item_text.setText(blessing_item.getBlessing());
        Log.i(TAG, blessing_item.getBlessing());


        return row;
    }
}
