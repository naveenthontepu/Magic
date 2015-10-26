package com.example.naveen.magic;

import android.content.Intent;
import android.graphics.Typeface;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class RelationGratefulReason extends AppCompatActivity {

    List<TextView> gratefulReasonList;
    LinearLayout gratefulThingsList;
    int horizontalPadding, verticalPadding,textColor ;
    float textSize;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_relation_grateful_reason);
        Intent intent = getIntent();
        Bundle extras = intent.getExtras();
        Uri imageUri = (Uri)extras.get("relationImage");
        String name = extras.getString("relationName");
        ImageView relationLargeImage =(ImageView)findViewById(R.id.relationLargeImage);
        relationLargeImage.setImageURI(imageUri);
        getSupportActionBar().setTitle(name);
        horizontalPadding = (int)getResources().getDimension(R.dimen.activity_horizontal_margin);
        verticalPadding = (int)getResources().getDimension(R.dimen.activity_vertical_margin);
        textColor=getResources().getColor(R.color.TextColor);
        textSize = getResources().getDimension(R.dimen.activity_Text_Size);
        gratefulReasonList = new ArrayList<TextView>();
        gratefulThingsList = (LinearLayout)findViewById(R.id.gratefulThingsList);
        final EditText gratefulReason =(EditText)findViewById(R.id.gratefulReason);
        gratefulReason.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int keyNote, KeyEvent keyEvent) {
                if (keyNote == EditorInfo.IME_ACTION_DONE){
                    String reason = gratefulReason.getText().toString();
                    TextView gratefulReasonText = new TextView(RelationGratefulReason.this);
                    gratefulReasonText.setText(reason);
                    gratefulReasonText.setTypeface(Typeface.create("sans-serif-light",Typeface.NORMAL));
                    gratefulReasonText.setTextColor(textColor);
                    gratefulReasonText.setTextSize(textSize);
                    gratefulReasonText.setPadding(horizontalPadding, verticalPadding, horizontalPadding, verticalPadding);
                    gratefulThingsList.addView(gratefulReasonText);
                    gratefulReasonList.add(gratefulReasonText);
                    gratefulReason.setText("");
                    return true;

                }
                return false;
            }
        });




    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_relation_grateful_reason,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if(id== R.id.action_settings){
            return true;
        }
        if(id==R.id.action_edit){
            Toast.makeText(RelationGratefulReason.this,"Edit Selected",Toast.LENGTH_LONG).show();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
