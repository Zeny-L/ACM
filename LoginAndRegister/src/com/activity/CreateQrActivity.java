package com.activity;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.ext.R;
import android.widget.ImageView;

public class CreateQrActivity extends Activity {

	private ImageView myqr;

	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.myqr_layout);
		
		myqr = (ImageView) this.findViewById(R.id.where_myqr);
		
		Intent intent = getIntent();

		if(intent != null) {
			byte [] bis = intent.getByteArrayExtra("qr");
			Bitmap bitmap = BitmapFactory.decodeByteArray(bis, 0, bis.length);
			myqr.setImageBitmap(bitmap);
		}
	}
}
