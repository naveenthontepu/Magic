package com.example.naveen.magic;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Switch;

public class MagicIngredient extends AppCompatActivity {
    SharedPreferences pref;
    SharedPreferences.Editor editor;
    Switch before_sleep_practice8;
    private final String TAG="com.example.naveen.magic";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_magic_ingredient);
        Intent intent=getIntent();
        String dayname=intent.getStringExtra("Day Name");
        getSupportActionBar().setTitle(dayname);
        pref = getSharedPreferences("com.example.naveen.magic", MODE_PRIVATE);
        editor=pref.edit();
        before_sleep_practice8 = (Switch)findViewById(R.id.before_sleep_practice8);
        before_sleep_practice8.setChecked(pref.getBoolean("before sleep practice8", false));

    }
    public void beforesleeppractice(View view){
        int temp=pref.getInt("before_sleep_practice8",0);
        if(before_sleep_practice8.isChecked()){
            if(temp==0){
                int temp2 = pref.getInt("progress8", 0)+10;
                editor.putInt("progress8",temp2);
                editor.putInt("before_sleep_practice8",10);
                editor.commit();
            }

            editor.putBoolean("before sleep practice8",true);
            editor.commit();
        }else{
            editor.putBoolean("before sleep practice8",false);
            editor.commit();
        }
        Log.i(TAG, "Progress8 = " + pref.getInt("progress8", 0));

    }

    public void count_blessing(View view){
        Intent intent= new Intent(MagicIngredient.this,Count_Blessings.class);
        intent.putExtra("Day", 8);
        startActivity(intent);
    }
}
