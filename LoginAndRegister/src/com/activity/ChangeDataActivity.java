package com.activity;

import com.manageLogin.DBOpenHelper;

import android.app.Activity;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ext.R;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class ChangeDataActivity extends Activity {

	private String id;
	private EditText et_oldName;
	private EditText et_oldPhone;
	private EditText et_oldAdd;
	private EditText et_oldWork;
	private EditText et_oldQQ;
	private Button sure_change;

	private DBOpenHelper dbhelper;

	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.change_data);

		sure_change = (Button) this.findViewById(R.id.btn_change_data);
		et_oldName = (EditText) this.findViewById(R.id.new_name);
		et_oldPhone = (EditText) this.findViewById(R.id.new_phone);
		et_oldAdd = (EditText) this.findViewById(R.id.new_add);
		et_oldWork = (EditText) this.findViewById(R.id.new_work);
		et_oldQQ = (EditText) this.findViewById(R.id.new_qq);

		Bundle bundle = getIntent().getExtras();
		id = bundle.getString("idname");

		dbhelper = new DBOpenHelper(getApplicationContext());

		SQLiteDatabase db = dbhelper.getReadableDatabase();
		Cursor cursor = db.rawQuery("select * from message", null);
		while (cursor.moveToNext()) {
			if (id.equals(cursor.getString(cursor.getColumnIndex("idname")))) {
				et_oldName.setText(cursor.getString(cursor
						.getColumnIndex("name")));
				et_oldPhone.setText(cursor.getString(cursor
						.getColumnIndex("phone")));
				et_oldAdd.setText(cursor.getString(cursor
						.getColumnIndex("address")));
				et_oldWork.setText(cursor.getString(cursor
						.getColumnIndex("work")));
				et_oldQQ.setText(cursor.getString(cursor.getColumnIndex("qq")));
				break;
			}
		}
		cursor.close();

		sure_change.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				SQLiteDatabase db = dbhelper.getWritableDatabase();
				db.execSQL("update message set name='"
						+ et_oldName.getText().toString() + "', phone='"
						+ et_oldPhone.getText().toString() + "', address='"
						+ et_oldAdd.getText().toString() + "', work='"
						+ et_oldWork.getText().toString() + "', qq='"
						+ et_oldQQ.getText().toString() + "'" + "where idname="
						+ id);
				Toast.makeText(getApplicationContext(), "修改成功, 重新登录后生效",
					     Toast.LENGTH_SHORT).show();
			}
		});
	}
}
