package com.ayuan.view;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.ayuan.utils.GetDrawable;
import com.ayuan.utils.Http_Vegetable;
import com.ayuan.vo.Vegetableinfo;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

	private String[] strings = new String[]{"123", "234", "76", "dfh", "afg", "sdfg", "hjkd", "sfg", "tyu", "ert", "ert", "wetr"};
	private GridView gv_class;
	private List<Map<String, Object>> mapList = new ArrayList<Map<String, Object>>();
	private List<Vegetableinfo> vegetableinfoList;
	private Handler mHandler = new Handler() {
		@Override
		public void handleMessage(Message msg) {
			super.handleMessage(msg);
			int what = msg.what;
			switch (what) {
				case 0:
					MyGridViewAdapter myGridViewAdapter = new MyGridViewAdapter();
					gv_class.setAdapter(myGridViewAdapter);
					break;
			}
		}
	};

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_home);

		initUI();
		initData();
	}

	/**
	 * 从网络中访问数据
	 */
	private void initData() {
		vegetableinfoList = Http_Vegetable.getVegetable();

		if (vegetableinfoList.isEmpty()) {
			Toast.makeText(this, "获取服务器数据不成功", Toast.LENGTH_SHORT).show();
		}
		Message message = Message.obtain();
		message.what = 0;
		mHandler.sendMessage(message);

	}

	private void initUI() {
		gv_class = (GridView) findViewById(R.id.gv_class);

		gv_class.setOnItemClickListener(new AdapterView.OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				//将id和分类名称传入到下一个页面
				Intent intent = new Intent(MainActivity.this, ClassNameListActivity.class);
				if (!vegetableinfoList.isEmpty()) {
					String typeid = vegetableinfoList.get(position).getTypeid();
					String typename = vegetableinfoList.get(position).getTypename();
					intent.putExtra("typeid", typeid);
					intent.putExtra("typename", typename);
				}
				startActivity(intent);
			}
		});
	}

	private class MyGridViewAdapter extends BaseAdapter {

		@Override
		public int getCount() {
			if (vegetableinfoList.isEmpty()) {
				return 10;
			}
			return vegetableinfoList.size();
		}

		@Override
		public Vegetableinfo getItem(int position) {
			return vegetableinfoList.get(position);
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
			if (vegetableinfoList.isEmpty()) {
				/*tv_des.setText("产品类名");
				iv_logo.setImageResource(R.drawable.gridview_logo);*/
				return view;
			}

			String typename = getItem(position).getTypename();
			tv_des.setText(typename);

			String typepic = getItem(position).getTypepic();
			GetDrawable GetDrawable = new GetDrawable();
			Drawable logo = GetDrawable.getdrawable(typepic, MainActivity.this);
			iv_logo.setImageDrawable(logo);
			return view;
		}
	}
}
