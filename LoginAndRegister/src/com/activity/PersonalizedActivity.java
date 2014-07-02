package com.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.WindowManager;
import android.view.ext.R;
import android.widget.Button;

public class PersonalizedActivity extends Activity {

	private Button changeData;
	private Button changeToNight;
	private Button changeBackGround;
	private Button logout;

	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.personalized);

		changeData = (Button) this.findViewById(R.id.btn_changeData);
		changeToNight = (Button) this.findViewById(R.id.btn_changeToNight);
		changeBackGround = (Button) this
				.findViewById(R.id.btn_changeBackground);
		logout = (Button) this.findViewById(R.id.btn_logout);

		changeData.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				Bundle bundle = getIntent().getExtras();
				Intent intent = new Intent(PersonalizedActivity.this,
						ChangeDataActivity.class);
				intent.putExtras(bundle);
				startActivity(intent);
			}
		});

		changeToNight.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				WindowManager.LayoutParams lp = getParent().getWindow().getAttributes();
				lp.screenBrightness = 0.3f;
				getParent().getWindow().setAttributes(lp);
			}
		});

		changeBackGround.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
			}
		});

		logout.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				
			}
		});

	}
}
