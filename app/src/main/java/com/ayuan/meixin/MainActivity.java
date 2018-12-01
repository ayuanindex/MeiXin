package com.ayuan.meixin;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

	private String[] strings = new String[]{"123", "234", "76", "dfh", "afg", "sdfg", "hjkd", "sfg", "tyu", "ert", "ert", "wetr"};
	private GridView gv_class;


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_home);

		initUI();
	}

	private void initUI() {
		gv_class = (GridView) findViewById(R.id.gv_class);
		final MyGridViewAdapter myGridViewAdapter = new MyGridViewAdapter();
		gv_class.setAdapter(myGridViewAdapter);
		gv_class.setOnItemClickListener(new AdapterView.OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				//Toast.makeText(MainActivity.this, "您点击了第:" + position + "条\n类名:" + myGridViewAdapter.getItem(position), Toast.LENGTH_SHORT).show();
				//将id和分类名称传入到下一个页面
				Intent intent = new Intent(MainActivity.this, ClassNameListActivity.class);
				intent.putExtra("typeid", position);//将类编号传给下一页进行处理
				intent.putExtra("classname", myGridViewAdapter.getItem(position));
				startActivity(intent);
			}
		});
	}

	private class MyGridViewAdapter extends BaseAdapter {

		@Override
		public int getCount() {
			return strings.length;
		}

		@Override
		public String getItem(int position) {
			return strings[position];
		}

		@Override
		public long getItemId(int position) {
			return position;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			View view;
			if (convertView == null) {
				view = View.inflate(MainActivity.this, R.layout.gridview_list, null);
			} else {
				view = convertView;
			}
			TextView tv_des = (TextView) view.findViewById(R.id.tv_des);
			ImageView iv_logo = (ImageView) view.findViewById(R.id.iv_logo);
			tv_des.setText(getItem(position));
			iv_logo.setImageResource(R.mipmap.gridview_logo);
			return view;
		}
	}
}
