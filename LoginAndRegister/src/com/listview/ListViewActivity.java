package com.listview;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ext.R;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

public class ListViewActivity extends Activity {

	private ListView friend;
	private MyAdapt myAdapt;
	private AutoCompleteTextView findCard;
	private List<Friend> friend_list, friend_list_new;
	private Button add_friend;

	@SuppressWarnings("unchecked")
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.main_listview);
		friend = (ListView) findViewById(R.id.listview);

		friend_list = new ArrayList<Friend>();
		myAdapt = new MyAdapt(ListViewActivity.this, friend_list_new);
		friend.setAdapter(myAdapt);
		
		add_friend = (Button) findViewById(R.id.btn_addCard);

		add_friend.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {

				LayoutInflater factory = LayoutInflater
						.from(ListViewActivity.this);
				final View textEntryView = factory.inflate(R.layout.dialog,
						null);
				AlertDialog dlg = new AlertDialog.Builder(ListViewActivity.this)
						.setTitle("添加好友")
						.setView(textEntryView)
						.setPositiveButton("OK",
								new DialogInterface.OnClickListener() {
									public void onClick(DialogInterface dialog,
											int whichButton) {
										EditText name = (EditText) textEntryView
												.findViewById(R.id.edit_name);
										String nameString = name.getText()
												.toString();

										EditText info = (EditText) textEntryView
												.findViewById(R.id.edit_info);
										String infoString = info.getText()
												.toString();

										friend_list.add(new Friend(
												R.drawable.test, nameString,
												infoString));
										
										showByMyBaseAdapter();
									}
								})
						.setNegativeButton("Cancel",
								new DialogInterface.OnClickListener() {
									public void onClick(DialogInterface dialog,
											int whichButton) {
										return;
									}
								}).create();
				dlg.show();
			}
		});

		findCard = (AutoCompleteTextView) findViewById(R.id.findCardTextView);
		findCard.addTextChangedListener(new TextWatcher() {
			@Override
			public void onTextChanged(CharSequence arg0, int arg1, int arg2,
					int arg3) {
				// TODO Auto-generated method stub
				friend_list_new = new ArrayList<Friend>();
				// System.out.println(arg0);
				for (int i = 0; i < friend_list.size(); i++) {
					// System.out.println(friend_list.get(i).getUserName().indexOf(arg0.toString()));
					// 如果信息相匹配，加入到新的list中
					if (friend_list.get(i).getTitle().indexOf(arg0.toString()) != -1) {

						friend_list_new.add(friend_list.get(i));
					}
					// System.out.println(friend_list_new.size());
				}
				// 通知适配器，改变当前界面显示
				myAdapt = new MyAdapt(ListViewActivity.this, friend_list_new);
				friend.setAdapter(myAdapt);
			}

			@Override
			public void beforeTextChanged(CharSequence arg0, int arg1,
					int arg2, int arg3) {
				// TODO Auto-generated method stub

			}

			@Override
			public void afterTextChanged(Editable arg0) {
				// TODO Auto-generated method stub

			}
		});

		initList();
		showByMyBaseAdapter();
	}

	private void initList() {
		friend_list.add(new Friend(R.drawable.test, "Zeny", "福建长乐"));
		friend_list.add(new Friend(R.drawable.test, "Metis", "福建平潭"));
		friend_list.add(new Friend(R.drawable.test, "Themis", "辽宁抚顺"));
		friend_list.add(new Friend(R.drawable.test, "Eurynome", "呵呵呵呵"));
		friend_list.add(new Friend(R.drawable.i1, "Demeter", "呵呵呵呵"));
		friend_list.add(new Friend(R.drawable.i1, "Mnemosyne", "呵呵呵呵"));
		friend_list.add(new Friend(R.drawable.i1, "Hera", "呵呵呵呵"));
	}

	public void showByMyBaseAdapter() {
		myAdapt = new MyAdapt(this, friend_list);
		friend.setAdapter(myAdapt);
	}
}