package com.example.sqlite_database_using_listview;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import androidx.annotation.Nullable;

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "student.db";
    private static final String TABLE_NAME = "student_details";
    private static final String ID = "Id";
    private static final String NAME = "Name";
    private static final int VERSION_NUMBER = 2;
    private Context context;
    private static final String CREATE_TABLE = "CREATE TABLE "+TABLE_NAME+"("+ID+" INTEGER PRIMARY KEY AUTOINCREMENT,"+NAME+" VARCHAR(50));";

    public DatabaseHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, VERSION_NUMBER);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        try
        {
            Toast.makeText(context,"onCreate is called ",Toast.LENGTH_SHORT).show();
            db.execSQL(CREATE_TABLE);

        }
        catch (Exception e)
        {
            Toast.makeText(context,"Exception : "+e,Toast.LENGTH_SHORT).show();
        }




    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        try {
            Toast.makeText(context,"onUpgrade is called ",Toast.LENGTH_SHORT).show();
            db.execSQL("DROP TABLE IF EXISTS " +TABLE_NAME);
            onCreate(db);

        }catch (Exception e){

            Toast.makeText(context,"Exception : "+e,Toast.LENGTH_SHORT).show();
        }

    }

    public long saveData(String id,String name){

        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(ID,id);
        contentValues.put(NAME,name);


        long rowNumber = sqLiteDatabase.insert(TABLE_NAME,null,contentValues);

        return rowNumber;

    }

    public Cursor showAllData()
    {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM "+TABLE_NAME,null);
        return cursor;
    }

    public Boolean updateData(String id,String name)
    {
        SQLiteDatabase sqLiteDatabase =this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(ID,id);
        contentValues.put(NAME,name);

        sqLiteDatabase.update(TABLE_NAME,contentValues,ID+" = ?",new String[]{id});

        return true;
    }

    public int deleteData(String id)
    {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        int value = sqLiteDatabase.delete(TABLE_NAME,ID+" = ?",new String[]{id});
        return value;
    }

}
