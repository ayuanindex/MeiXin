package com.ayuan.view;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.drawable.Drawable;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.ayuan.db.RecipeOpenhelp;
import com.ayuan.db.Recipedao;
import com.ayuan.utils.GetDrawable;
import com.ayuan.utils.Http_Vegetable;
import com.ayuan.utils.InternetUtils;
import com.ayuan.vo.Vegetableinfo;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

	private GridView gv_class;
	private List<Map<String, Object>> mapList = new ArrayList<Map<String, Object>>();
	private ConnectivityManager connectivityManager;
	private NetworkInfo activeNetworkInfo;
	private String TAG = "MainActivity";
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
					initDB();
					if (vegetableinfoList == null) {
						Toast.makeText(MainActivity.this, "获取数据不成功", Toast.LENGTH_SHORT).show();
					}
					break;
			}
		}
	};
	private SQLiteDatabase readableDatabase;
	private Recipedao recipedao;

	/**
	 * 将数据加载到数据库
	 */
	private void initDB() {
		RecipeOpenhelp recipeOpenhelp = new RecipeOpenhelp(this);
		readableDatabase = recipeOpenhelp.getReadableDatabase();

	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_home);

		initUI();
		boolean netWorkAvailable = InternetUtils.isNetWorkAvailable(this);
		if (netWorkAvailable) {
			initData();
		} else {
			Toast.makeText(this, "亲，您的网络没有连接哦", Toast.LENGTH_SHORT).show();
			vegetableinfoList = inDatabase();
			Message message = Message.obtain();
			message.what = 0;
			mHandler.sendMessage(message);
		}
	}

	/**
	 * 从网络中访问数据
	 */
	private void initData() {
		new Thread() {
			@Override
			public void run() {
				super.run();
				vegetableinfoList = Http_Vegetable.getVegetable();
				Message message = Message.obtain();
				message.what = 0;
				mHandler.sendMessage(message);
			}
		}.start();
	}

	private void initListener() {
		if (gv_class != null) {
			gv_class.setOnItemClickListener(new AdapterView.OnItemClickListener() {
				@Override
				public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
					//将id和分类名称传入到下一个页面
					Intent intent = new Intent(MainActivity.this, ClassNameListActivity.class);
					if (vegetableinfoList != null) {
						Log.i(TAG, "集合:" + vegetableinfoList.toString());
						String typeid = vegetableinfoList.get(position).getTypeid();
						String typename = vegetableinfoList.get(position).getTypename();
						intent.putExtra("typeid", typeid);
						intent.putExtra("typename", typename);
					}
					startActivity(intent);
				}
			});
		}
	}

	private void initUI() {
		recipedao = new Recipedao(this);
		vegetableinfoList = new ArrayList<Vegetableinfo>();

		gv_class = (GridView) findViewById(R.id.gv_class);

		initListener();
	}

	private class MyGridViewAdapter extends BaseAdapter {

		@Override
		public int getCount() {
			if (vegetableinfoList == null) {
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
			if (vegetableinfoList == null) {
				//从数据库中获取数据来显示页面
				return view;
			}
			insertData(position);

			String typename = getItem(position).getTypename();
			tv_des.setText(typename);

			String typepic = getItem(position).getTypepic();
			GetDrawable GetDrawable = new GetDrawable();
			Drawable logo = GetDrawable.getdrawable(typepic, MainActivity.this);
			iv_logo.setImageDrawable(logo);
			return view;
		}

		private boolean insertData(int position) {
			if (recipedao != null) {
				//单独将图片文件写道缓存里

				boolean b = recipedao.innest_type(vegetableinfoList.get(position));
				return b;
			}
			return false;
		}
	}

	/**
	 * 在没有网络的情况下，使用数据库里面的数据进行数据展示
	 */
	private List<Vegetableinfo> inDatabase() {
		if (recipedao != null) {
			List<Vegetableinfo> vegetableinfos = recipedao.select_types();
			return vegetableinfos;
		} else {
			recipedao = new Recipedao(this);
			List<Vegetableinfo> vegetableinfos = recipedao.select_types();
			return vegetableinfos;
		}
	}
}
