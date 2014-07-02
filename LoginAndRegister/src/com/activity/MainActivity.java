package com.activity;

import com.manageLogin.userManager;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ext.R;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends Activity {
	userManager db;
	private EditText idname;
	private EditText password;
	private Button login;
	private Button register;

	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main_mune);
		idname = (EditText) this.findViewById(R.id.edit_idname);
		password = (EditText) this.findViewById(R.id.edit_password);
		login = (Button) this.findViewById(R.id.btn_login);
		register = (Button) this.findViewById(R.id.btn_register);

		db = new userManager(this.getBaseContext());
		login.setOnClickListener(new OnClickListener() {

			public void onClick(View v) {
				// TODO Auto-generated method stub
				String Idname = idname.getText().toString();
				String Pass = password.getText().toString();
				int k = db.find(Idname, Pass);
				if (k == 1) {
					Bundle bundle = new Bundle();
					bundle.putString("idname", Idname);
					Intent intent = new Intent(MainActivity.this, FunctionActivity.class);
					intent.putExtras(bundle);
					startActivity(intent);
				} 
				else {
					Toast.makeText(getApplicationContext(), "登录失败",
							Toast.LENGTH_SHORT).show();
				}
			}
		});

		register.setOnClickListener(new OnClickListener() {

			public void onClick(View v) {
				Intent intent = new Intent(MainActivity.this, registerActivity.class);
				startActivity(intent);
			}

		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
}
