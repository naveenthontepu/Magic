package com.example.naveen.magic;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.ViewSwitcher;

import java.util.ArrayList;
import java.util.List;

public class Count_Blessings extends AppCompatActivity {

    private final String TAG="com.example.naveen.magic";
    ArrayAdapter blessingAdapter;

    String[] b_num;
    Blessings_Count blessings_count;
    List<Blessings_Count> bless_list_complete = new ArrayList<Blessings_Count>();
    List<Blessings_Count> day_list;
    int count1,count2;
    Intent intent;
    AdapterView<?> adapterView1;
    int position1;
    ViewSwitcher viewSwitcher;
    TextView item_text;
    EditText item_edittext;
    Blessings_Count local_blessing_edittext;
    ListView blessing_List;
    MyDBHandler dbHandler;
    SharedPreferences pref;
    SharedPreferences.Editor editor;
    int daynumber;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.i(TAG, "onCreate");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_count__blessings);
        intent = getIntent();
        daynumber = intent.getIntExtra("Day",1);
        dbHandler = new MyDBHandler(this,null,null,1);
        Log.i(TAG, "Day number = " + String.valueOf(daynumber));
        getSupportActionBar().setTitle("Day " + daynumber);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        pref = getSharedPreferences("com.example.naveen.magic",MODE_PRIVATE);
        editor = pref.edit();

        Log.i(TAG, "Day " + String.valueOf(daynumber) + ": Count Your Blessing");

        day_list=new ArrayList<Blessings_Count>();
//        count1= bless_list_complete.size();
        Log.i(TAG, "Count 1 = "+String.valueOf(count1));


        count2=formList(daynumber);

        blessingAdapter = new BCustomAdapter(this,day_list);
        blessing_List = (ListView)findViewById(R.id.listviewblessing);
        blessing_List.setAdapter(blessingAdapter);
        blessing_List.setItemsCanFocus(true);
        Log.i(TAG, "Adapter setting done");
        getSupportActionBar().show();


        blessing_List.setOnItemClickListener(
                new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(final AdapterView<?> adapterView, View view, final int position, long l) {
                        Log.i(TAG, "onItemClick " + String.valueOf(position));
                        adapterView1 = adapterView;
                        //final ArrayAdapter blessingAdapter1= (ArrayAdapter) adapterView.getAdapter();
                        position1 = position;
                        local_blessing_edittext = (Blessings_Count) adapterView.getItemAtPosition(position);
                        viewSwitcher = (ViewSwitcher) view.findViewById(R.id.item_viewswitch);
                        item_text = (TextView) view.findViewById(R.id.item_text);
                        item_edittext = (EditText) view.findViewById(R.id.item_edittext);
                        Log.i(TAG, "Position = " + String.valueOf(position));
                        viewSwitcher.showNext();
                        item_edittext.setText(item_text.getText().toString());
                        getSupportActionBar().show();
                        item_edittext.requestFocus();

                        item_edittext.setOnEditorActionListener(new TextView.OnEditorActionListener() {
                            @Override
                            public boolean onEditorAction(TextView textView, int keyNote, KeyEvent keyEvent) {
                                if (keyNote == EditorInfo.IME_ACTION_DONE) {
                                    Log.i(TAG, "setOnEditorActionListener");

                                    if (!item_edittext.getText().toString().startsWith("Add Blessing") &&
                                            item_edittext.getText().toString() != "" &&
                                            item_edittext.getText().toString() != null) {
                                        Log.i(TAG, "if condition in setOnEditorActionListener");
                                        viewSwitcher.showNext();
                                        item_text.setText(item_edittext.getText().toString());

                                        Log.i(TAG, local_blessing_edittext.get_id());

                                        int in_complete_list = dbHandler.checkindatabase(local_blessing_edittext.get_id());
                                        Log.i(TAG, "return value of checkindatabase = " + String.valueOf(in_complete_list));
                                        if (in_complete_list == 1) {

                                            dbHandler.updateBlessing(local_blessing_edittext.get_id(), item_edittext.getText().toString());
//                                            bless_list_complete.get(in_complete_list).setBlessing(item_edittext.getText().toString());
                                        } else {
                                            Log.i(TAG, "Else codition started");
                                            local_blessing_edittext.setBlessing(item_edittext.getText().toString());
                                            local_blessing_edittext.setItemnumber(String.valueOf(position1 + 1) + ".");
                                            Log.i(TAG, "setting done");
                                            Log.i(TAG, local_blessing_edittext.get_id());
                                            dbHandler.addBlessing(local_blessing_edittext);
//                                            bless_list_complete.add(local_blessing_edittext);
                                            Log.i(TAG, "Adding done");
                                            getprogress(position1+1);

//                                            Log.i(TAG,String.valueOf( bless_list_complete.size()));
                                        }
                                        if (position1 + 1 == day_list.size()) {
                                            Log.i(TAG, "if condition in setOnEditorActionListener");
                                            Blessings_Count blessings_count_in = new Blessings_Count(
                                                    String.valueOf(daynumber) + "_" + String.valueOf(position1 + 2));
                                            blessings_count_in.setItemnumber("+ ");
                                            blessings_count_in.setBlessing("Add Blessing");
                                            day_list.add(blessings_count_in);
                                            Log.i(TAG, "daylist item added");
                                            Log.i(TAG, String.valueOf(day_list.size()));
                                            blessingAdapter.notifyDataSetChanged();
                                            Log.i(TAG, "Adapter changed");
                                        }

                                    }
                                    Log.i(TAG, "Return true");
                                    return true;

                                }

                                return false;
                            }
                        });

                    }
                });
        blessing_List.setAdapter(blessingAdapter);
    }
    public void getprogress(int num){
        int temp=pref.getInt("progress"+daynumber,0);
        Log.i(TAG,"Progress = "+temp);
        if(daynumber==1){
            if(num<=10){
                temp+=8;
                editor.putInt("progress1",temp);
                editor.commit();
            }
        }else {
            if(num<=10){
                temp+=4;
                editor.putInt("progress"+daynumber,temp);
                editor.commit();
            }
        }
        Log.i(TAG,"Progress = "+temp);
    }

    /*public int checkindatabase(Blessings_Count local_blessing){
        Log.i(TAG,"checkinlist");
        for (int i=0;i<bless_list_complete.size();i++){
            Log.i(TAG,"for loop");
            if (bless_list_complete.get(i).get_id()==local_blessing.get_id()){
                Log.i(TAG,"Position = "+String.valueOf(i));
                return i;
            }
        }
        Log.i(TAG,"checkinlist: -1");
        return -1;
    }
*/
    public int formList(int daynumber){
        Log.i(TAG, "formList");

        day_list = dbHandler.getBlessingList(daynumber);

        /*for (int i=0;i<bless_list_complete.size();i++){
            Log.i(TAG, "formList for loop");
            if (bless_list_complete.get(i).get_id().startsWith(String.valueOf(daynumber))){
                day_list.add(bless_list_complete.get(i));
            }
        }
*/
        Log.i(TAG, "formList if condition");
        Blessings_Count blessings_count = new Blessings_Count(String.valueOf(daynumber) + "_"+String.valueOf(day_list.size()+1));
        blessings_count.setBlessing("Add Blessing");
        blessings_count.setItemnumber(/*String.valueOf(day_list.size()+1)+*/"+ ");
        day_list.add(blessings_count);


        return day_list.size();

    }


}
