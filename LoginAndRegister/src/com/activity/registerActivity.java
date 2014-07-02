package com.activity;

import com.manageLogin.Usermes;
import com.manageLogin.userManager;

import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ext.R;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class registerActivity extends MainActivity {
	private Usermes user;
	private userManager db;
	private EditText idnametext;
	private EditText passwordtext;
	private EditText confirmpassword;
	private EditText regis_name;
	private EditText regis_phone;
	private EditText regis_add;
	private EditText regis_work;
	private EditText regis_qq;
	private Button tijiao;
	
	private String idname;
	private String password;
	private String confirm_passwd;
	private String regisName;
	private String regisPhone;
	private String regisAdd;
	private String regisWork;
	private String regisQQ;

	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.register);

		user = new Usermes();
		db = new userManager(this.getBaseContext());

		idnametext = (EditText) this.findViewById(R.id.regist_idname);
		passwordtext = (EditText) this.findViewById(R.id.regist_passwd);
		confirmpassword = (EditText) this.findViewById(R.id.cofirmPasswd);
		regis_name = (EditText) this.findViewById(R.id.regist_name);
		regis_phone = (EditText) this.findViewById(R.id.regist_phone);
		regis_add = (EditText) this.findViewById(R.id.regist_add);
		regis_work = (EditText) this.findViewById(R.id.regist_work);
		regis_qq = (EditText) this.findViewById(R.id.regist_qq);
		
		tijiao = (Button) this.findViewById(R.id.tijiao);

		tijiao.setOnClickListener(new OnClickListener() {

			public void onClick(View v) {

				idname = idnametext.getText().toString().trim();
				password = passwordtext.getText().toString().trim();
				confirm_passwd = confirmpassword.getText().toString();
				regisName = regis_name.getText().toString();
				regisPhone = regis_phone.getText().toString();
				regisAdd = regis_add.getText().toString();
				regisWork = regis_work.getText().toString();
				regisQQ = regis_qq.getText().toString();
				
				user.setIdName(idname);
				user.setPassword(password);
				user.setName(regisName);
				user.setPhone(regisPhone);
				user.setAddress(regisAdd);
				user.setWork(regisWork);
				user.setQQ(regisQQ);
				
				if (user.getIdName().equals("")) {
					Toast.makeText(getApplicationContext(), "账号不能为空!",
							Toast.LENGTH_SHORT).show();
				}
				else {
					if (user.getPassword().equals(confirm_passwd)) {
						//数据库里保留下这个用户
						db.save(user);
						Toast.makeText(getApplicationContext(), "注册成功",
								Toast.LENGTH_SHORT).show();
						registerActivity.this.finish();
					}

					else {
						Toast.makeText(getApplicationContext(), "输入密码有误",
								Toast.LENGTH_SHORT).show();
					}
				}
			}
		});
	}
}
