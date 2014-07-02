package com.manageLogin;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class userManager {
	private DBOpenHelper dbopenhelper;

	public userManager(Context context) {

		this.dbopenhelper = new DBOpenHelper(context);
	}

	//保存注册的信息到数据库
	public void save(Usermes user) {
		SQLiteDatabase db = dbopenhelper.getWritableDatabase();
		db.execSQL(
				"insert into message(idname,password,name,phone,address,work,qq) values(?,?,?,?,?,?,?)",
				new Object[] { user.getIdName(), user.getPassword(),
						user.getName(), user.getPhone(), user.getAddress(),
						user.getWork(), user.getQQ() });
	}

	//登陆时 去茶盅是否有这个用户
	public int find(String idname, String password) {
		SQLiteDatabase db = dbopenhelper.getReadableDatabase();
		Cursor cursor = db.rawQuery("select *from message", null);
		while (cursor.moveToNext()) {
			if (idname
					.equals(cursor.getString(cursor.getColumnIndex("idname")))
					&& password.equals(cursor.getString(cursor
							.getColumnIndex("password"))))
				return 1;
		}
		cursor.close();
		return 0;
	}
}
