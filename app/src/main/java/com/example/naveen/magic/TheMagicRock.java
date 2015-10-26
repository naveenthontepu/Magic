package com.example.naveen.magic;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Switch;

public class TheMagicRock extends AppCompatActivity {


    private final String TAG="com.example.naveen.magic";
    SharedPreferences pref ;
    SharedPreferences.Editor editor;
    MyDBHandler dbHandler;
    Switch find_magic_rock,before_sleep_practice1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_the_magic_rock);
        Intent intent=getIntent();
        String dayname=intent.getStringExtra("Day Name");
        getSupportActionBar().setTitle(dayname);
        pref = getSharedPreferences("com.example.naveen.magic", MODE_PRIVATE);
        editor=pref.edit();
        find_magic_rock =(Switch)findViewById(R.id.find_magic_rock);
        before_sleep_practice1=(Switch)findViewById(R.id.before_sleep_practice1);

        find_magic_rock.setChecked(pref.getBoolean("find magic rock",false));
        before_sleep_practice1.setChecked(pref.getBoolean("before sleep practice1",false));

        dbHandler=new MyDBHandler(this,null,null,1);
    }
    public void count_blessing(View view){
        Intent intent= new Intent(TheMagicRock.this,Count_Blessings.class);
        intent.putExtra("Day", 2);
        startActivity(intent);
    }
    public void findmagicrock(View view){
        int temp=pref.getInt("find_magic_rock",0);
        if(find_magic_rock.isChecked()){
            if(temp==0){
                int temp2 = pref.getInt("progress2", 0)+30;
                editor.putInt("progress2",temp2);
                editor.putInt("find_magic_rock",10);
                editor.commit();
            }

            editor.putBoolean("find magic rock",true);
            editor.commit();
        }else{
            editor.putBoolean("find magic rock",false);
            editor.commit();
        }
        Log.i(TAG,"Progress2 = "+pref.getInt("progress2",0));

    }
    public void beforesleeppractice(View view){
        int temp=pref.getInt("before_sleep_practice1",0);
        if(before_sleep_practice1.isChecked()){
            if(temp==0){
                int temp2 = pref.getInt("progress2", 0)+30;
                editor.putInt("progress2",temp2);
                editor.putInt("before_sleep_practice1",10);
                editor.commit();
            }

            editor.putBoolean("before sleep practice1",true);
            editor.commit();
        }else{
            editor.putBoolean("before sleep practice1",false);
            editor.commit();
        }
        Log.i(TAG,"Progress2 = "+pref.getInt("progress2",0));

    }


}
