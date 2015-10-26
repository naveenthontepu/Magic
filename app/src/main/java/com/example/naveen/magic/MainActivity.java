package com.example.naveen.magic;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    List<Day_Tasks> dayTasksList =new ArrayList<Day_Tasks>();
    private final String TAG="com.example.naveen.magic";

    String[] day_names; /*{"Count you blessing", "The Magic Rock", "Magical Relationships",
            "Magical Health", "Magic Money", "Works Like Magic", "The Magical Way Out of Negativity",
            "The Magic Ingredient", "The Money Magnet", "Magic Dust Everyone"}*/;
    int[] task_numbers={2,3,5,4,5,4,4,3,4,4};
    Intent intent;
    SharedPreferences pref;
    SharedPreferences.Editor editor;
    int[] progress;
    ArrayAdapter DaysAdapter;
    ListView DaysList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.i(TAG, "onCreateCalled");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        progress=new int[task_numbers.length];
        day_names=getResources().getStringArray(R.array.Day_Names);
        pref = getSharedPreferences("com.example.naveen.magic", MODE_PRIVATE);
        editor =pref.edit();

        initialize();
        DaysList.setOnItemClickListener(
                new AdapterView.OnItemClickListener(){
                    @Override
                    public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                        Log.i(TAG, "i = " + String.valueOf(position));
                        switch (position){
                            case 0:
                                Log.i(TAG,"Switch case 0");
                                intent = new Intent(MainActivity.this,CountYourBlessings.class);
                                intent.putExtra("Day Name", day_names[position]);
                                startActivity(intent);
                                break;
                            case 1:
                                Log.i(TAG,"Switch case 1");
                                intent = new Intent(MainActivity.this, TheMagicRock.class);
                                intent.putExtra("Day Name", day_names[position]);
                                startActivity(intent);
                                break;
                            case 2:
                                Log.i(TAG,"Switch case 2");
                                intent = new Intent(MainActivity.this, MagicalRelationships.class);
                                intent.putExtra("Day Name", day_names[position]);
                                startActivity(intent);
                                break;
                            case 3:
                                intent = new Intent(MainActivity.this, MagicalHealth.class);
                                intent.putExtra("Day Name", day_names[position]);
                                startActivity(intent);
                                break;
                            case 4:
                                intent = new Intent(MainActivity.this, MagicMoney.class);
                                intent.putExtra("Day Name", day_names[position]);
                                startActivity(intent);
                                break;
                            case 5:
                                intent = new Intent(MainActivity.this, WorksLikeMagic.class);
                                intent.putExtra("Day Name", day_names[position]);
                                startActivity(intent);
                                break;
                            case 6:
                                intent = new Intent(MainActivity.this, MagicalWayOutofNegativity.class);
                                intent.putExtra("Day Name", day_names[position]);
                                startActivity(intent);
                                break;
                            case 7:
                                intent = new Intent(MainActivity.this, MagicIngredient.class);
                                intent.putExtra("Day Name", day_names[position]);
                                startActivity(intent);
                                break;
                            case 8:
                                intent = new Intent(MainActivity.this, MoneyMagnet.class);
                                intent.putExtra("Day Name", day_names[position]);
                                startActivity(intent);
                                break;
                            case 9:
                                intent = new Intent(MainActivity.this, MagicDustEveryone.class);
                                intent.putExtra("Day Name", day_names[position]);
                                startActivity(intent);
                                break;

                        }
                    }
                }
        );


    }
    public void initialize(){
        for (int i=0;i<task_numbers.length;i++){
            int temp=i+1;
            progress[i]=pref.getInt("progress"+String.valueOf(temp),0);
        }

        for(int i=1;i<=task_numbers.length;i++){
            Log.i(TAG, "for loop i = " + String.valueOf(i));
            Day_Tasks day_tasks = new Day_Tasks();
            day_tasks.setDay_number("Day : " + String.valueOf(i));
            day_tasks.setTask_number("Tasks : " + String.valueOf(task_numbers[i - 1]));
            day_tasks.setDay_name(day_names[i - 1]);
            Log.i(TAG,"Progress = "+pref.getInt("progress"+String.valueOf(i),0));
            day_tasks.setProgress(pref.getInt("progress"+String.valueOf(i),0));
            dayTasksList.add(day_tasks);
            Log.i(TAG,"Adding Done");
        }
        Log.i(TAG, "Came out of for loop");
        DaysAdapter = new CustomAdapter(this,dayTasksList);
        Log.i(TAG, "Adapter Created");
        DaysList = (ListView)findViewById(R.id.DaysList);
        DaysList.setAdapter(DaysAdapter);
        Log.i(TAG, "Adapter Setting done");

    }



/*
    public int tasksnumber(int daynumber){
        switch (daynumber){
            case 1:
                return 1;
            case 2:
                return 2;
            case 3:
                return 4;
            default:
                return 1;
        }
    }
*/

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onRestart() {
        Log.i(TAG, "onRestart");
        super.onRestart();
    }

    @Override
    protected void onResume() {
        Log.i(TAG, "onResume");
        super.onResume();
    }

    @Override
    protected void onPause() {
        Log.i(TAG,"onPause");
        super.onPause();
    }

    @Override
    protected void onStop() {
        Log.i(TAG,"onStop");
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        Log.i(TAG,"onDestroy");
        super.onDestroy();
    }
}
