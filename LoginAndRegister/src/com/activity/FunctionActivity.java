/*
 * Copyright (C) 2012 Capricorn
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.activity;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.app.LocalActivityManager;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.View.OnClickListener;
import android.view.ext.R;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TabHost;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.TabHost.OnTabChangeListener;
import android.widget.TabHost.TabSpec;

import com.capricorn.RayMenu;
import com.google.zxing.WriterException;
import com.listview.ListViewActivity;
import com.manageLogin.DBOpenHelper;
import com.zxing.encoding.EncodingHandler;

public class FunctionActivity extends Activity {

	private TabHost tabHost;
	List<View> listViews;
	FunctionActivity functionActivity;
	private Bitmap qrCodeBitmap;
	LocalActivityManager manager = null;
	TextView tvTab1, tvTab2;
	private ViewPager pager = null;
	private String userinfo = null;
	private String idName;
	private DBOpenHelper dbhelper;
	private Button to_friend;

	private static final int[] ITEM_DRAWABLES = { R.drawable.scan,
			R.drawable.exchange, R.drawable.share, R.drawable.qr };

	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.function);
		
		to_friend = (Button) findViewById(R.id.btn_to_friend);
		to_friend.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				Intent intent = new Intent(FunctionActivity.this,
						ListViewActivity.class);
				startActivity(intent);
			}
		});

		final Bundle bundle = getIntent().getExtras();
		idName = bundle.getString("idname");
		
		dbhelper = new DBOpenHelper(getApplicationContext());
		SQLiteDatabase db = dbhelper.getReadableDatabase();
		Cursor cursor = db.rawQuery("select *from message", null);
		while (cursor.moveToNext()) {
			if (idName.equals(cursor.getString(cursor
					.getColumnIndex("idname")))) {
				userinfo = userinfo + '\n'
						+ cursor.getString(cursor.getColumnIndex("name"));
				userinfo = userinfo + '\n'
						+ cursor.getString(cursor.getColumnIndex("phone"));
				userinfo = userinfo + '\n'
						+ cursor.getString(cursor.getColumnIndex("address"));
				userinfo = userinfo + '\n'
						+ cursor.getString(cursor.getColumnIndex("work"));
				userinfo = userinfo + '\n'
						+ cursor.getString(cursor.getColumnIndex("qq"));
				break;
			}
		}
		
		RayMenu rayMenu = (RayMenu) findViewById(R.id.ray_menu);
		final int itemCount = ITEM_DRAWABLES.length;
		for (int i = 0; i < itemCount; i++) {
			ImageView item = new ImageView(this);
			item.setImageResource(ITEM_DRAWABLES[i]);
			
			final int position = i;
			rayMenu.addItem(item, new OnClickListener() {

				public void onClick(View v) {					
					if (!userinfo.equals("")) {
						try {
							qrCodeBitmap = EncodingHandler.createQRCode(userinfo, 350);
						} catch (WriterException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					} else {
						Toast.makeText(FunctionActivity.this, "Userinfo is empty!",
								Toast.LENGTH_SHORT).show();
					}
					if (position == 0) {
						Intent intent = new Intent(FunctionActivity.this,
								CaptureActivity.class);
						startActivity(intent);
					} else if (position == 1) {
						Intent intent = new Intent(FunctionActivity.this,
								NfcChangeActivity.class);
						intent.putExtras(bundle);
						startActivity(intent);
					} else if (position == 2) {
						Intent intent = new Intent(Intent.ACTION_SEND);
						intent.putExtra(Intent.EXTRA_TEXT, "我是短屌鑫");
						intent.setType("text/plain");
						startActivity(intent);
					} else if (position == 3) {
						Intent intent = new Intent(FunctionActivity.this,
								CreateQrActivity.class);
						ByteArrayOutputStream baos = new ByteArrayOutputStream();
						qrCodeBitmap.compress(Bitmap.CompressFormat.PNG, 100,
								baos);
						byte[] bitmapByte = baos.toByteArray();
						intent.putExtra("qr", bitmapByte);
						startActivity(intent);
					}
				}
			});
		}

		manager = new LocalActivityManager(this, true);
		manager.dispatchCreate(savedInstanceState);
		functionActivity = this;
		tabHost = (TabHost) findViewById(R.id.tabhost);
		tabHost.setup();
		tabHost.setup(manager);

		pager = (ViewPager) findViewById(R.id.viewpager);

		listViews = new ArrayList<View>();

		Intent mycardIntent = new Intent(FunctionActivity.this,
				MycardActivity.class);
		mycardIntent.putExtras(bundle);
		listViews.add(getView("MycardActivity", mycardIntent));

		Intent personalizedIntent = new Intent(FunctionActivity.this,
				PersonalizedActivity.class);
		personalizedIntent.putExtras(bundle);
		listViews.add(getView("PersonalizedActivity", personalizedIntent));

		RelativeLayout tabIndicator1 = (RelativeLayout) LayoutInflater.from(
				this).inflate(R.layout.tabwidget, null);
		tvTab1 = (TextView) tabIndicator1.findViewById(R.id.tv_title);
		tvTab1.setText("我的名片");

		RelativeLayout tabIndicator2 = (RelativeLayout) LayoutInflater.from(
				this).inflate(R.layout.tabwidget, null);
		tvTab2 = (TextView) tabIndicator2.findViewById(R.id.tv_title);
		tvTab2.setText("设置中心");

		tabHost.addTab(tabHost.newTabSpec("page1").setIndicator(tabIndicator1)
				.setContent(mycardIntent));
		tabHost.addTab(tabHost.newTabSpec("page2").setIndicator(tabIndicator2)
				.setContent(personalizedIntent));

		tabHost.setOnTabChangedListener(new OnTabChangeListener() {
			public void onTabChanged(String tabId) {
				if ("page1".equals(tabId)) {
					tvTab1.setTextColor(functionActivity.getResources()
							.getColor(R.color.main_red));
					tvTab2.setTextColor(functionActivity.getResources()
							.getColor(R.color.black));
					pager.setCurrentItem(0);

				}
				if ("page2".equals(tabId)) {
					tvTab1.setTextColor(functionActivity.getResources()
							.getColor(R.color.black));
					tvTab2.setTextColor(functionActivity.getResources()
							.getColor(R.color.main_red));
					pager.setCurrentItem(1);
				}
			}
		});

		pager.setAdapter(new MyPageAdapter(listViews));
		pager.setOnPageChangeListener(new OnPageChangeListener() {
			public void onPageSelected(int position) {
				tabHost.setCurrentTab(position);
			}

			public void onPageScrolled(int arg0, float arg1, int arg2) {
			}

			public void onPageScrollStateChanged(int arg0) {
			}
		});
	}

	public class MyPageAdapter extends PagerAdapter {

		private List<View> list;

		private MyPageAdapter(List<View> list) {
			this.list = list;
		}

		public void destroyItem(ViewGroup view, int position, Object arg2) {
			ViewPager pViewPager = ((ViewPager) view);
			pViewPager.removeView(list.get(position));
		}

		public int getCount() {
			return list.size();
		}

		public Object instantiateItem(ViewGroup view, int position) {
			ViewPager pViewPager = ((ViewPager) view);
			pViewPager.addView(list.get(position));
			return list.get(position);
		}

		public boolean isViewFromObject(View arg0, Object arg1) {
			return arg0 == arg1;
		}

		public void restoreState(Parcelable arg0, ClassLoader arg1) {
		}

		public Parcelable saveState() {
			return null;
		}

		@Override
		public void startUpdate(View arg0) {
		}
	}

	@SuppressWarnings("deprecation")
	private View getView(String id, Intent intent) {
		return manager.startActivity(id, intent).getDecorView();
	}

	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is
		// present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
}
