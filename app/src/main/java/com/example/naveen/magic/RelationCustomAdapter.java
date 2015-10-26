package com.example.naveen.magic;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;


public class RelationCustomAdapter extends ArrayAdapter<Relation_Item> {

    private LayoutInflater inflater;
    private List<Relation_Item> relationItemListLocal;
    private final String TAG="com.example.naveen.magic";

    public RelationCustomAdapter(Context context, List<Relation_Item> objects) {
        super(context, R.layout.relation_item, objects);
        inflater=LayoutInflater.from(getContext());
        relationItemListLocal = objects;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View view=inflater.inflate(R.layout.relation_item,parent,false);
        ImageView relationListImage = (ImageView)view.findViewById(R.id.relationListImage);
        TextView relationListName = (TextView)view.findViewById(R.id.relationListName);

        Relation_Item relation_item = relationItemListLocal.get(position);

        relationListImage.setImageURI(relation_item.getRelationImageUri());
        relationListName.setText(relation_item.getRelationName());

        return view;
    }
}
