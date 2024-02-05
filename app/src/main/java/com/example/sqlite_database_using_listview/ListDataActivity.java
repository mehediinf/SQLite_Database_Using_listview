package com.example.sqlite_database_using_listview;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.media.browse.MediaBrowser;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

import listView.OnItemClickListener;

public class ListDataActivity extends AppCompatActivity {

    private ListView listView;

    private DatabaseHelper databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_data);


        listView = findViewById(R.id.listView_Id);
        databaseHelper = new DatabaseHelper(this);

        loadData();

    }

    public void loadData(){

        ArrayList<String> listData = new ArrayList<>();
        Cursor cursor = databaseHelper.showAllData();

        if (cursor.getCount()==0){

            Toast.makeText(getApplicationContext(),"No Data is available in database",Toast.LENGTH_SHORT).show();
        }
        else {
            while (cursor.moveToNext()){
                listData.add(cursor.getString(0)+" \t "+cursor.getString(1));
            }
        }
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,R.layout.list_item,R.id.textView_Id,listData);
        listView.setAdapter(adapter);




    }

}