package com.example.naveen.magic;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Switch;

public class MagicMoney extends AppCompatActivity {
    SharedPreferences pref;
    SharedPreferences.Editor editor;
    Switch before_sleep_practice5;
    private final String TAG="com.example.naveen.magic";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_magic_money);
        Intent intent=getIntent();
        String dayname=intent.getStringExtra("Day Name");
        getSupportActionBar().setTitle(dayname);
        pref = getSharedPreferences("com.example.naveen.magic", MODE_PRIVATE);
        editor=pref.edit();
        before_sleep_practice5 = (Switch)findViewById(R.id.before_sleep_practice5);
        before_sleep_practice5.setChecked(pref.getBoolean("before sleep practice5", false));
    }
    public void beforesleeppractice(View view){
        int temp=pref.getInt("before_sleep_practice5",0);
        if(before_sleep_practice5.isChecked()){
            if(temp==0){
                int temp2 = pref.getInt("progress5", 0)+10;
                editor.putInt("progress5",temp2);
                editor.putInt("before_sleep_practice5",10);
                editor.commit();
            }

            editor.putBoolean("before sleep practice5",true);
            editor.commit();
        }else{
            editor.putBoolean("before sleep practice5",false);
            editor.commit();
        }
        Log.i(TAG, "Progress5 = " + pref.getInt("progress5", 0));

    }

    public void count_blessing(View view){
        Intent intent= new Intent(MagicMoney.this,Count_Blessings.class);
        intent.putExtra("Day", 5);
        startActivity(intent);
    }
}
