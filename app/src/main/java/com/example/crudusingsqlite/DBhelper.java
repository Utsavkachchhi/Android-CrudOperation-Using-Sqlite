package com.example.crudusingsqlite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DBhelper  extends SQLiteOpenHelper {
    public static final String DBname = "Signup";

    public DBhelper( Context context) {
        super(context, DBname, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String query = "create table Employer (Empid integer primary key autoincrement, Ename text, Eage integer,Esalary integer,Edepartment text)";
        db.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table if exists Employer");
        onCreate(db);
    }


    public Boolean insertdata(int id,String name,int age,int salary,String department)
    {
        SQLiteDatabase DB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("id",id);
        contentValues.put("name", name);
        contentValues.put("age", age);
        contentValues.put("salary", salary);
        contentValues.put("department", department);

        long result=DB.insert("Employer", null, contentValues);
        if(result==-1){
            return false;
        }else{
            return true;
        }
    }

    public Cursor getdata()
    {
        SQLiteDatabase DB = this.getWritableDatabase();
        Cursor cursor = DB.rawQuery("Select * from Employer", null);
        return cursor;

    }

    public Boolean deletedata (String name)
    {
        SQLiteDatabase DB = this.getWritableDatabase();
        Cursor cursor = DB.rawQuery("Select * from Employer where name = ?", new String[]{name});
        if (cursor.getCount() > 0) {
            long result = DB.delete("Employer", "name=?", new String[]{name});
            if (result == -1) {
                return false;
            } else {
                return true;
            }
        } else {
            return false;
        }

    }


    public Boolean updatedata(int id,String name,int age,int salary,String department) {
        SQLiteDatabase DB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("id",id);
        contentValues.put("name", name);
        contentValues.put("age", age);
        contentValues.put("salary", salary);
        contentValues.put("department", department);
        Cursor cursor = DB.rawQuery("Select * from Employer where name = ?", new String[]{name});
        if (cursor.getCount() > 0) {
            long result = DB.update("Employer", contentValues, "name=?", new String[]{name});
            if (result == -1) {
                return false;
            } else {
                return true;
            }
        } else {
            return false;
        }}
}
