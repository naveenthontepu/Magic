package com.example.naveen.magic;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Switch;

public class MagicalWayOutofNegativity extends AppCompatActivity {
    SharedPreferences pref;
    SharedPreferences.Editor editor;
    Switch before_sleep_practice7;
    private final String TAG="com.example.naveen.magic";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_magical_way_outof_negativity);
        Intent intent=getIntent();
        String dayname=intent.getStringExtra("Day Name");
        getSupportActionBar().setTitle(dayname);
        pref = getSharedPreferences("com.example.naveen.magic", MODE_PRIVATE);
        editor=pref.edit();
        before_sleep_practice7 = (Switch)findViewById(R.id.before_sleep_practice7);
        before_sleep_practice7.setChecked(pref.getBoolean("before sleep practice7", false));
    }
    public void beforesleeppractice(View view){
        int temp=pref.getInt("before_sleep_practice7",0);
        if(before_sleep_practice7.isChecked()){
            if(temp==0){
                int temp2 = pref.getInt("progress7", 0)+10;
                editor.putInt("progress7",temp2);
                editor.putInt("before_sleep_practice7",10);
                editor.commit();
            }

            editor.putBoolean("before sleep practice7",true);
            editor.commit();
        }else{
            editor.putBoolean("before sleep practice7",false);
            editor.commit();
        }
        Log.i(TAG, "Progress7 = " + pref.getInt("progress7", 0));

    }

    public void count_blessing(View view){
        Intent intent= new Intent(MagicalWayOutofNegativity.this,Count_Blessings.class);
        intent.putExtra("Day", 7);
        startActivity(intent);
    }
}
