package com.example.naveen.magic;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Switch;

public class MagicalRelationships extends AppCompatActivity {
    SharedPreferences pref;
    SharedPreferences.Editor editor;
    Switch before_sleep_practice2;
    private final String TAG="com.example.naveen.magic";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_magical_relationships);
        Intent intent=getIntent();
        String dayname=intent.getStringExtra("Day Name");
//        getSupportActionBar().setTitle(dayname);
        pref = getSharedPreferences("com.example.naveen.magic", MODE_PRIVATE);
        editor=pref.edit();
        before_sleep_practice2 = (Switch)findViewById(R.id.before_sleep_practice2);
        before_sleep_practice2.setChecked(pref.getBoolean("before sleep practice2",false));

    }
    public void beforesleeppractice(View view){
        int temp=pref.getInt("before_sleep_practice2",0);
        if(before_sleep_practice2.isChecked()){
            if(temp==0){
                int temp2 = pref.getInt("progress3", 0)+10;
                editor.putInt("progress3",temp2);
                editor.putInt("before_sleep_practice2",10);
                editor.commit();
            }

            editor.putBoolean("before sleep practice2",true);
            editor.commit();
        }else{
            editor.putBoolean("before sleep practice2",false);
            editor.commit();
        }
        Log.i(TAG, "Progress3 = " + pref.getInt("progress3", 0));

    }

    public void count_blessing(View view){
        Intent intent= new Intent(MagicalRelationships.this,Count_Blessings.class);
        intent.putExtra("Day", 3);
        startActivity(intent);
    }
    public void relationsThanks(View view){
        Intent intent = new Intent(MagicalRelationships.this,ThankRelations.class);
        startActivity(intent);
    }

}
