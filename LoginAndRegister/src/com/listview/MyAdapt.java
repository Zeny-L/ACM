package com.listview;

import java.util.List;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.ext.R;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

public class MyAdapt extends BaseAdapter {
	private List<Friend> friend_list;
	private Context context;
	private RelativeLayout item;
	ChildView childView = null;

	public MyAdapt(Context context, List<Friend> friend_list) {
		this.friend_list = friend_list;
		this.context = context;
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return (friend_list == null) ? 0 : friend_list.size();
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return friend_list.get(position);
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return 0;
	}

	public class ChildView {
		ImageView imageView;
		TextView txt_username;
		TextView txt_info;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		final Friend friend = (Friend) getItem(position);
		Log.d("MyBaseAdapter", "新建convertView,position=" + position);
		convertView = LayoutInflater.from(context).inflate(R.layout.item_chat,
				null);

		childView = new ChildView();

		childView.txt_username = (TextView) convertView
				.findViewById(R.id.txt_username);
		childView.txt_info = (TextView) convertView.findViewById(R.id.txt_info);
		childView.imageView = (ImageView) convertView
				.findViewById(R.id.imageView1);
		item = (RelativeLayout) convertView.findViewById(R.id.item);
		convertView.setTag(childView);

		childView.txt_username.setText(friend.getTitle());
		String info = friend.getInfo();
		if (info.length() > 15) {
			info = friend.getInfo().substring(0, 20) + "...";
		}
		childView.txt_info.setText(info);
		childView.imageView.setImageResource(friend.getImage());
		childView.txt_username.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				childView.txt_username.setText("");
			}
		});
		//对ListView中的每一行信息配置OnClick事件  
		convertView.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Toast.makeText(
						context,
						"[convertView.setOnClickListener]点击了"
								+ friend.getTitle(), Toast.LENGTH_SHORT)
						.show();
				Intent intent = new Intent(context, showActivity.class);
				context.startActivity(intent);
				// item.setBackground(context.getResources().getDrawable(R.drawable.item_bg));
				// item.setBackgroundColor(context.getResources().getColor(R.color.bg_blue));
			}

		});

		return convertView;
	}

}
