package com.example.naveen.magic;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Switch;
import android.widget.ToggleButton;

public class CountYourBlessings extends AppCompatActivity {

    private final String TAG="com.example.naveen.magic";
//    LinearLayout done_not_buttons;
    SharedPreferences pref ;
    SharedPreferences.Editor editor;
    int progress1;
    MyDBHandler dbHandler;
    Switch toggle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.i(TAG, "onCreate");
        super.onCreate(savedInstanceState);
        Intent intent = getIntent();
        String dayname=intent.getStringExtra("Day Name");
        setContentView(R.layout.activity_count_your_blessings);
        getSupportActionBar().setTitle(dayname);
        pref = getSharedPreferences("com.example.naveen.magic", MODE_PRIVATE);
        editor=pref.edit();
//        done_not_buttons = (LinearLayout)findViewById(R.id.done_not_buttons);
        toggle =(Switch)findViewById(R.id.switch1);
        toggle.setChecked(pref.getBoolean("tomorrow practice done",false));
        dbHandler=new MyDBHandler(this,null,null,1);
        progress1 = pref.getInt("progress1",0);
        Log.i(TAG, "progress1 = " + progress1);
//        getprogress();
    }
    /*public void getprogress(){
        int count = dbHandler.countblessing(1);
        Log.i(TAG,"Count = "+count);
        if (count>=10){
            progress1+=80;
            Log.i(TAG,"progress1 = "+progress1);
            editor.putInt("progress1",progress1);
            editor.commit();
        }else {
            progress1+=count*8;
            Log.i(TAG,"progress1 = "+progress1);
            editor.putInt("progress1",progress1);
            editor.commit();
        }

    }
*/
    public void CountBlessingActivity(View view){
        Intent intent = new Intent(this,Count_Blessings.class);
        intent.putExtra("Day", 1);
        startActivity(intent);

    }/*
    public void done_notdone(View view){
        if(done_not_buttons.getVisibility()== View.GONE)
            done_not_buttons.setVisibility(View.VISIBLE);
        else
            done_not_buttons.setVisibility(View.GONE);
    }*/
    public void donenotdone(View view){
        int temp=pref.getInt("donenotdone1",0);
        if(toggle.isChecked()){
            if(temp==0){
                int temp2=pref.getInt("progress1",0)+20;
                editor.putInt("progress1",temp2);
                editor.putInt("donenotdone1",10);
                editor.commit();
            }

            editor.putBoolean("tomorrow practice done",true);
            editor.commit();
        }else{
            editor.putBoolean("tomorrow practice done",false);
            editor.commit();
        }
        Log.i(TAG, "progress1 = " +pref.getInt("progress1",0) );


    }

    @Override
    protected void onRestart() {
        super.onRestart();
    }

    @Override
    protected void onResume() {
        super.onResume();
//        getprogress();
    }
}
