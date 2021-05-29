package com.example.crudusingsqlite;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    EditText ID, NAME, AGE,SALARY,DEPARTMENT;
    Button insert, update, delete, view;
    DBhelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ID = findViewById(R.id.id);
        NAME = findViewById(R.id.name);
        AGE = findViewById(R.id.age);
        SALARY = findViewById(R.id.salary);
        DEPARTMENT = findViewById(R.id.department);
        insert = findViewById(R.id.Insert);
        update = findViewById(R.id.Update);
        delete = findViewById(R.id.Delete);
        view = findViewById(R.id.View);

        db = new DBhelper(getApplicationContext());
        SQLiteDatabase dh = db.getReadableDatabase();

        insert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int id = Integer.parseInt(ID.getText().toString());
                String name = NAME.getText().toString();
                int age = Integer.parseInt(AGE.getText().toString());
                int salary = Integer.parseInt(SALARY.getText().toString());
                String department = DEPARTMENT.getText().toString();

                Boolean checkinsertdata = db.insertdata(id,name,age,salary,department);
                if(checkinsertdata==true)
                    Toast.makeText(MainActivity.this, "Data Inserted Successfully", Toast.LENGTH_SHORT).show();
                else
                    Toast.makeText(MainActivity.this, "Data Not Inserted", Toast.LENGTH_SHORT).show();
            }
        });

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Cursor res = db.getdata();
                if (res.getCount() == 0) {
                    Toast.makeText(MainActivity.this, "No Entry Exists", Toast.LENGTH_SHORT).show();
                    return;
                }
                StringBuffer buffer = new StringBuffer();
                while (res.moveToNext()) {
                    buffer.append("ID :" + res.getString(0) + "\n");
                    buffer.append("NAME :" + res.getString(1) + "\n");
                    buffer.append("AGE :" + res.getString(2) + "\n\n");
                    buffer.append("SALARY :" + res.getString(3) + "\n");
                    buffer.append("DEPARTMENT :" + res.getString(4) + "\n\n");
                }

                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                builder.setCancelable(true);
                builder.setTitle("Employer Data");
                builder.setMessage(buffer.toString());
                builder.show();
            }
        });

        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = NAME.getText().toString();
                Boolean checkudeletedata = db.deletedata(name);
                if(checkudeletedata==true)
                    Toast.makeText(MainActivity.this, "Data Deleted Successfully", Toast.LENGTH_SHORT).show();
                else
                    Toast.makeText(MainActivity.this, "Data Not Deleted", Toast.LENGTH_SHORT).show();
            }        });

        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int id = Integer.parseInt(ID.getText().toString());
                String name = NAME.getText().toString();
                int age = Integer.parseInt(AGE.getText().toString());
                int salary = Integer.parseInt(SALARY.getText().toString());
                String department = DEPARTMENT.getText().toString();
                Boolean checkupdatedata = db.updatedata(id,name, age, salary,department);
                if(checkupdatedata==true)
                    Toast.makeText(MainActivity.this, "Data Updated Successfully", Toast.LENGTH_SHORT).show();
                else
                    Toast.makeText(MainActivity.this, "Data Not Updated", Toast.LENGTH_SHORT).show();
            }        });

    }


}