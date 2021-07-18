package com.example.uitoux;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class StudentsMark extends AppCompatActivity
{
    TextView n0,m1,m2,m3,t1;
    EditText n1,n2,m11,m12,m21,m22,m31,m32,t2,t3;
    Button savebutton;
    String Name, PhoneNumber, Subject ;
    Boolean CheckEditTextEmpty ;
    String SQLiteQuery;
    SQLiteDatabase SQLITEDATABASE;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_students_mark);

        n0 = findViewById(R.id.name0);

        n1 = findViewById(R.id.name1);

        n2 = findViewById(R.id.name2);

        m1 = findViewById(R.id.mark_1);

        m11 = findViewById(R.id.mark_1_1);

        m12 = findViewById(R.id.mark1_2);

        m2 = findViewById(R.id.mark2);

        m21 = findViewById(R.id.mark2_1);

        m22 = findViewById(R.id.mark2_2);

        m3 = findViewById(R.id.mark3);

        m31 = findViewById(R.id.mark3_1);

        m32 = findViewById(R.id.mak3_2);

        t1 = findViewById(R.id.total);

        t2 = findViewById(R.id.total_1);

        t3 = findViewById(R.id.total_2);


        savebutton = findViewById(R.id.button_save);

        savebutton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                DBCreate();

                SubmitData2SQLiteDB();
            }
        });
    }


    public void DBCreate(){

        SQLITEDATABASE = openOrCreateDatabase("DemoDataBase", Context.MODE_PRIVATE, null);

        SQLITEDATABASE.execSQL("CREATE TABLE IF NOT EXISTS demoTable(id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, name VARCHAR, phone_number VARCHAR, subject VARCHAR);");
    }
    public void SubmitData2SQLiteDB(){
        Name = n1.getText().toString();
        PhoneNumber = m11.getText().toString();
        Subject = t2.getText().toString();
        CheckEditTextIsEmptyOrNot( Name,PhoneNumber, Subject);
        if(CheckEditTextEmpty == true)
        {
            SQLiteQuery = "INSERT INTO demoTable (name,phone_number,subject) VALUES('"+Name+"', '"+PhoneNumber+"', '"+Subject+"');";
            SQLITEDATABASE.execSQL(SQLiteQuery);
            Toast.makeText(StudentsMark.this,"Data Submit Successfully", Toast.LENGTH_LONG).show();
            ClearEditTextAfterDoneTask();
        }
        else {

            Toast.makeText(StudentsMark.this,"Please Fill All the Fields", Toast.LENGTH_LONG).show();
        }
    }

    public void CheckEditTextIsEmptyOrNot(String Name,String PhoneNumber, String subject ){

        if(TextUtils.isEmpty(Name) || TextUtils.isEmpty(PhoneNumber) || TextUtils.isEmpty(Subject)){

            CheckEditTextEmpty = false ;

        }
        else {
            CheckEditTextEmpty = true ;
        }
    }

    public void ClearEditTextAfterDoneTask(){

        n1.getText().clear();

        n2.getText().clear();

        m11.getText().clear();

        m12.getText().clear();

        m21.getText().clear();

        m22.getText().clear();
        m31.getText().clear();
        m32.getText().clear();
        t3.getText().clear();
        t2.getText().clear();

    }

}
