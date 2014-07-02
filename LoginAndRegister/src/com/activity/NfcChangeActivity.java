package com.activity;

import java.nio.charset.Charset;
import java.util.Locale;

import org.w3c.dom.Text;

import com.manageLogin.DBOpenHelper;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.nfc.NdefMessage;
import android.nfc.NdefRecord;
import android.nfc.NfcAdapter;
import android.nfc.NfcAdapter.CreateNdefMessageCallback;
import android.nfc.NfcEvent;
import android.nfc.NfcManager;
import android.nfc.Tag;
import android.nfc.tech.NdefFormatable;
import android.os.Bundle;
import android.os.Parcelable;
import android.text.format.Time;
import android.util.Log;
import android.view.ext.R;
import android.view.ext.R.layout;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class NfcChangeActivity extends Activity implements CreateNdefMessageCallback {

	Context context;
	private TextView set_name;
	private TextView set_phone;
	private TextView set_add;
	private TextView set_work;
	private TextView set_qq;

	private NfcAdapter mAdapter;

	private String setName;
	private String text;
	private DBOpenHelper dbhelper;

	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.nfc_card);

		mAdapter = NfcAdapter.getDefaultAdapter(this);

		if (mAdapter == null)
			Toast.makeText(getApplicationContext(), "该设备不支持nfc",
					Toast.LENGTH_SHORT).show();

		if (mAdapter.isEnabled() == false)
			Toast.makeText(getApplicationContext(), "nfc还未打开, 请进入手机设定打开nfc功能",
					Toast.LENGTH_SHORT).show();

		set_name = (TextView) findViewById(R.id.nfcname);
		set_phone = (TextView) findViewById(R.id.nfcphone);
		set_add = (TextView) findViewById(R.id.nfcadd);
		set_work = (TextView) findViewById(R.id.nfcwork);
		set_qq = (TextView) findViewById(R.id.nfcqq);

		PendingIntent pendingIntent = PendingIntent.getActivity(this, 0,
				new Intent(this, getClass())
						.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP), 0);

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
		mAdapter.setNdefPushMessageCallback(this, this);
	}

	public NdefRecord createMimeRecord(String mimeType, byte[] payload) {
		byte[] mimeBytes = mimeType.getBytes(Charset.forName("US-ASCII"));
		NdefRecord mimeRecord = new NdefRecord(NdefRecord.TNF_MIME_MEDIA,
				mimeBytes, new byte[0], payload);
		return mimeRecord;
	}

	public NdefMessage createNdefMessage(NfcEvent event) {
		Time time = new Time();
		time.setToNow();
		text = set_name.getText().toString() + set_phone.getText().toString()
				+ set_add.getText().toString() + set_work.getText().toString()
				+ set_qq.getText().toString();

		NdefMessage msg = new NdefMessage(new NdefRecord[] { createMimeRecord(
				"This is a new card!", text.getBytes()) });
		return msg;
	}

	// 第一步，接收Intent
	@Override
	protected void onNewIntent(Intent intent) {
		// super.onNewIntent(intent);
		setIntent(intent);
	}

	// 第二步，判断Intent
	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		if (NfcAdapter.ACTION_NDEF_DISCOVERED.equals(getIntent().getAction())) {
			processIntent(getIntent());
		}
	}

	// 第三步。处理Intent
	void processIntent(Intent intent) {
		Parcelable[] rawMsgs = intent
				.getParcelableArrayExtra(NfcAdapter.EXTRA_NDEF_MESSAGES);
		// only one message sent during the beam
		NdefMessage msg = (NdefMessage) rawMsgs[0];
		// record 0 contains the MIME type, record 1 is the AAR, if present
		Toast.makeText(getApplicationContext(), msg.getRecords()[0].getPayload().toString(),
			     Toast.LENGTH_SHORT).show();
	}
}