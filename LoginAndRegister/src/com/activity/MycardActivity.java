package com.activity;

import com.manageLogin.DBOpenHelper;

import android.app.Activity;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.ext.R;
import android.widget.TextView;

public class MycardActivity extends Activity {

	private TextView set_name;
	private TextView set_phone;
	private TextView set_add;
	private TextView set_work;
	private TextView set_qq;

	private String setName;
	private DBOpenHelper dbhelper;

	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.mycard);

		set_name = (TextView) findViewById(R.id.setname);
		set_phone = (TextView) findViewById(R.id.setphone);
		set_add = (TextView) findViewById(R.id.setadd);
		set_work = (TextView) findViewById(R.id.setwork);
		set_qq = (TextView) findViewById(R.id.setqq);
		
		showInfo();
	}

	public void showInfo() {
		Bundle bundle = getIntent().getExtras();
		setName = bundle.getString("idname");

		dbhelper = new DBOpenHelper(getApplicationContext());

		SQLiteDatabase db = dbhelper.getReadableDatabase();
		Cursor cursor = db.rawQuery("select *from message", null);
		while (cursor.moveToNext()) {
			if (setName
					.equals(cursor.getString(cursor.getColumnIndex("idname")))) {
				set_name.setText(cursor.getString(cursor.getColumnIndex("name")));
				set_phone.setText(cursor.getString(cursor
						.getColumnIndex("phone")));
				set_add.setText(cursor.getString(cursor
						.getColumnIndex("address")));
				set_work.setText(cursor.getString(cursor.getColumnIndex("work")));
				set_qq.setText(cursor.getString(cursor.getColumnIndex("qq")));
				break;
			}
		}
		cursor.close();
	}
}
