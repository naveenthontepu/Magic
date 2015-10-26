package com.example.naveen.magic;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Switch;

public class MagicalHealth extends AppCompatActivity {
    SharedPreferences pref;
    SharedPreferences.Editor editor;
    Switch before_sleep_practice4;
    private final String TAG="com.example.naveen.magic";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_magical_health);
        Intent intent=getIntent();
        String dayname=intent.getStringExtra("Day Name");
        getSupportActionBar().setTitle(dayname);
        pref = getSharedPreferences("com.example.naveen.magic", MODE_PRIVATE);
        editor=pref.edit();
        before_sleep_practice4 = (Switch)findViewById(R.id.before_sleep_practice4);
        before_sleep_practice4.setChecked(pref.getBoolean("before sleep practice4", false));
    }
    public void beforesleeppractice(View view){
        int temp=pref.getInt("before_sleep_practice4",0);
        if(before_sleep_practice4.isChecked()){
            if(temp==0){
                int temp2 = pref.getInt("progress4", 0)+10;
                editor.putInt("progress4",temp2);
                editor.putInt("before_sleep_practice4",10);
                editor.commit();
            }

            editor.putBoolean("before sleep practice4",true);
            editor.commit();
        }else{
            editor.putBoolean("before sleep practice4",false);
            editor.commit();
        }
        Log.i(TAG, "Progress4 = " + pref.getInt("progress4", 0));

    }

    public void count_blessing(View view){
        Intent intent= new Intent(MagicalHealth.this,Count_Blessings.class);
        intent.putExtra("Day", 4);
        startActivity(intent);
    }
}
