package com.ayuan.meixin;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.ayuan.vo.DishesBean;

import java.util.ArrayList;
import java.util.List;

public class ClassNameListActivity extends AppCompatActivity {

	private TextView tv_class_name;
	private ListView lv_menu_item;
	private List<DishesBean> dishesBeanList = new ArrayList<DishesBean>();
	private String classname;
	private int typeid;

	@Override
	protected void onCreate(@Nullable Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_class);

		initUI();
		initData();
	}

	/**
	 * 从网络上获取数据，放到数据库中，图片加载到
	 */
	private void initData() {
		//从网络上获取的菜谱数据通过JavaBean封装到集合中

	}

	private void initUI() {
		//取得从上一个页面传过来的参数(用于数据获取和标题显示)
		typeid = getIntent().getIntExtra("typeid", 0);
		classname = getIntent().getStringExtra("classname");

		tv_class_name = (TextView) findViewById(R.id.tv_class_name);
		lv_menu_item = (ListView) findViewById(R.id.lv_menu_item);

		MyMenuItemAdapter myMenuItemAdapter = new MyMenuItemAdapter();//创建数据适配器对象
		lv_menu_item.setAdapter(myMenuItemAdapter);
		tv_class_name.setText(classname);
		lv_menu_item.setOnItemClickListener(new AdapterView.OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

			}
		});
	}

	private class MyMenuItemAdapter extends BaseAdapter {

		@Override
		public int getCount() {
			return 20;
		}

		@Override
		public Object getItem(int position) {
			return null;
		}

		@Override
		public long getItemId(int position) {
			return 0;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			View view;
			if (convertView == null) {
				view = View.inflate(ClassNameListActivity.this, R.layout.menu_item, null);
			} else {
				view = convertView;
			}
			ImageView iv_logo = (ImageView) view.findViewById(R.id.iv_logo);
			TextView tv_name = (TextView) view.findViewById(R.id.tv_name);

			tv_name.setText(classname + ":" + typeid);
			iv_logo.setImageResource(R.drawable.logo);
			return view;
		}
	}
}
