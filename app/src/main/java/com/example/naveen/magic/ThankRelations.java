package com.example.naveen.magic;

import android.app.DialogFragment;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class ThankRelations extends AppCompatActivity implements RelationDialog.RelationDialogListener{

    private final String TAG="com.example.naveen.magic";
    List<Relation_Item> relationItemList;
    Relation_Item relation_item;
    ArrayAdapter relationAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thank_relations);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        relationItemList = new ArrayList<>();


        ListView relationsList = (ListView)findViewById(R.id.relationsList);

        relationAdapter = new RelationCustomAdapter(this,relationItemList);
        relationsList.setAdapter(relationAdapter);
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                RelationDialog relationDialog = new RelationDialog();
                relationDialog.show(getFragmentManager(),"Relation Dialog");
            }
        });
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        relationsList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                Relation_Item relation_item_local = (Relation_Item)adapterView.getItemAtPosition(position);
                Uri imgUriLocal = relation_item_local.getRelationImageUri();
                String name = relation_item_local.getRelationName();
                int id=relation_item_local.get_id();
                Intent intent = new Intent(ThankRelations.this,RelationGratefulReason.class);
                intent.putExtra("relationImage",imgUriLocal);
                intent.putExtra("relationName",name);
                intent.putExtra("relationId",id);
                startActivity(intent);

            }
        });
    }

    @Override
    public void onDialogPositiveClick(DialogFragment dialog, Bundle extra) {
        Uri imgUri = (Uri)extra.getParcelable("relationImage");
        String name = extra.getString("relationName");
        Log.i(TAG,String.valueOf(extra.getParcelable("relationImage")));
        Log.i(TAG, "Name = " + extra.getString("relationName"));
        Log.i(TAG, "relationItemList = " + relationItemList.size());
        if(imgUri!=null && name!=""){
            relation_item = new Relation_Item(relationItemList.size()+1);
            relation_item.setRelationImageUri(imgUri);
            relation_item.setRelationName(name);
            relationItemList.add(relation_item);
            relationAdapter.notifyDataSetChanged();
        }

    }

    @Override
    public void onDialogNegativeClick(DialogFragment dialog) {

    }
}
