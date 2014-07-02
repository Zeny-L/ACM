package com.manageLogin;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBOpenHelper extends SQLiteOpenHelper {
	public DBOpenHelper(Context context) {
		super(context, "login.db", null, 1);
		// TODO Auto-generated constructor stub
	}

	public void onCreate(SQLiteDatabase db) {
		// TODO Auto-generated method stub
		db.execSQL("create table message(userid integer primary key autoincrement,idname varchar(20),password varchar(20),name varchar(20),phone varchar(20),address varchar(20),work varchar(20),qq varchar(20))");
	}

	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO Auto-generated method stub
		db.execSQL("create table message(userid integer primary key autoincrement,idname varchar(20), password varchar(20),name varchar(20),phone varchar(20),address varchar(20),work varchar(20),qq varchar(20))");
	}
}