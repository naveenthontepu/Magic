package com.example.naveen.magic;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Naveen on 16-10-2015.
 */
public class MyDBHandler extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION=1;
    private static final String DATABASE_NAME = "Blessings.db";
    public static final String TABLE_BLESSING = "BLESSINGS";
    public static final String COLUMN_ID = "_id";
    public static final String COLUMN_BLESSING_ID="blessing_id";
    public static final String COLUMN_ITEMNUMBER = "itemnumber";
    public static final String COLUMN_BLESSING = "blessing";
    private static final String TAG = "com.example.naveen.magic";

    public MyDBHandler(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, DATABASE_NAME, factory, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String query="CREATE TABLE "+TABLE_BLESSING+"( "+
                COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_BLESSING_ID+" TEXT, "+
                COLUMN_ITEMNUMBER+" TEXT, "+
                COLUMN_BLESSING+" TEXT );";
        db.execSQL(query);


    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXITS "+TABLE_BLESSING );
        onCreate(db);

    }
    public void addBlessing(Blessings_Count blessing){
        Log.i(TAG, "ADDITION Started");
        ContentValues values = new ContentValues();
        values.put(COLUMN_BLESSING_ID,blessing.get_id());
        values.put(COLUMN_BLESSING,blessing.getBlessing());
        values.put(COLUMN_ITEMNUMBER, blessing.getItemnumber());
        SQLiteDatabase db = getReadableDatabase();
        db.insert(TABLE_BLESSING,null,values);
        db.close();
        Log.i(TAG, "ADDITION HAPPENED");
    }
    public List<Blessings_Count> getBlessingList(int number){
        Log.i(TAG,"getBlessingList");

        SQLiteDatabase db =getReadableDatabase();
        List<Blessings_Count> blessings = new ArrayList<Blessings_Count>();
        String query = "SELECT * FROM "+ TABLE_BLESSING+" WHERE 1";
        Blessings_Count blessing=null;
        Cursor c=db.rawQuery(query, null);
        c.moveToFirst();
        Log.i(TAG, "getBlessingList");
        while (!c.isAfterLast()){
            Log.i(TAG, "While loop of getBlessingList");
            String id= c.getString(c.getColumnIndex(COLUMN_BLESSING_ID));
            if(id.startsWith(String.valueOf(number))){
                Log.i(TAG, "if condition of While loop of getBlessingList");
                blessing = new Blessings_Count(c.getString(c.getColumnIndex(COLUMN_BLESSING_ID)));
                blessing.setItemnumber(c.getString(c.getColumnIndex(COLUMN_ITEMNUMBER)));
                blessing.setBlessing(c.getString(c.getColumnIndex(COLUMN_BLESSING)));
                blessings.add(blessing);
            }
            c.moveToNext();
        }
        Log.i(TAG, "getBlessingList While loop completed");
        db.close();
        return blessings;
    }
    public void updateBlessing(String blessing_id,String blessing_text){
        Log.i(TAG,"updateBlessing");
        SQLiteDatabase db = getReadableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_BLESSING, blessing_text);
        db.update(TABLE_BLESSING, values, COLUMN_BLESSING_ID + " =?", new String[]{blessing_id});

    }
    public int checkindatabase(String blessing_id){
        Log.i(TAG,"checkindatabase");
        SQLiteDatabase db = getReadableDatabase();
        String query = "SELECT * FROM "+TABLE_BLESSING+" WHERE "+COLUMN_BLESSING_ID+" =?";
        Cursor c =db.rawQuery(query,new String[]{blessing_id});
        if(c.getCount()<=0){
            Log.i(TAG,"if condition of checkindatabase");
            c.close();
            return 0;
        }
        c.close();
        return 1;
    }
    public int countblessing(int number){
        int count = 0;
        SQLiteDatabase db = getReadableDatabase();
        String query = "SELECT * FROM "+ TABLE_BLESSING+" WHERE 1";
        Cursor c=db.rawQuery(query, null);
        c.moveToFirst();
        Log.i(TAG, "countblessing");
        while (!c.isAfterLast()){
            String id= c.getString(c.getColumnIndex(COLUMN_BLESSING_ID));
            if(id.startsWith(String.valueOf(number))){
                Log.i(TAG,"If condition");
                count+=1;
            }
            c.moveToNext();
        }
        Log.i(TAG, "getBlessingList While loop completed");
        db.close();
        return count;

    }
}
