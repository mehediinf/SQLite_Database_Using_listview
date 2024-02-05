package com.example.sqlite_database_using_listview;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private DatabaseHelper databaseHelper;
    private EditText idEditText,nameEditText;
    private Button saveButton,showButton,updateButton,deleteButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        databaseHelper =new DatabaseHelper(this);
        SQLiteDatabase sqLiteDatabase = databaseHelper.getWritableDatabase();

        saveButton = findViewById(R.id.saveButton_Id);
        showButton = findViewById(R.id.showButton_Id);
        updateButton = findViewById(R.id.updateButton_Id);
        deleteButton = findViewById(R.id.deleteButton_Id);
        idEditText = findViewById(R.id.idEditText_Id);
        nameEditText = findViewById(R.id.nameEditText_Id);

        saveButton.setOnClickListener(this);
        showButton.setOnClickListener(this);
        updateButton.setOnClickListener(this);
        deleteButton.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {

        String id =idEditText.getText().toString();
        String name =nameEditText.getText().toString();

        if (v.getId()==R.id.saveButton_Id)
        {
            if (id.equals("") && name.equals("")){
                Toast.makeText(getApplicationContext(),"Please enter all the data",Toast.LENGTH_SHORT).show();

            }else {

                long rowNumber = databaseHelper.saveData(id,name);
                if (rowNumber > -1){
                    Toast.makeText(getApplicationContext(),"Data is inserted successfully",Toast.LENGTH_SHORT).show();
                    idEditText.setText("");
                    nameEditText.setText("");

                }else {
                    Toast.makeText(getApplicationContext(),"Data is not inserted successfully",Toast.LENGTH_SHORT).show();
                }


            }
        }
        else if (v.getId()==R.id.showButton_Id)
        {
            Intent intent = new Intent(MainActivity.this, ListDataActivity.class);
            startActivity(intent);
        }
        else if (v.getId()==R.id.updateButton_Id)
        {
            Boolean isUpdate = databaseHelper.updateData(id,name);
            if (isUpdate==true){
                Toast.makeText(getApplicationContext(),"Data is successfully Updated",Toast.LENGTH_SHORT).show();
            }
            else {
                Toast.makeText(getApplicationContext(),"Data is not Updated",Toast.LENGTH_SHORT).show();
            }
        }
        else if (v.getId()==R.id.deleteButton_Id)
        {

            int value =  databaseHelper.deleteData(id);

            if (value>0)
            {
                Toast.makeText(getApplicationContext(),"Data is Deleted",Toast.LENGTH_SHORT).show();
            }
            else
            {
                Toast.makeText(getApplicationContext(),"Data is not Deleted",Toast.LENGTH_SHORT).show();
            }

        }


    }
}