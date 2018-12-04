package com.ayuan.view;

import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.ayuan.utils.Callpeople;

public class AboutActivity extends AppCompatActivity {
	@Override
	protected void onCreate(@Nullable Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_about);

		initUI();
	}


	private void initUI() {
		TextView btn_call = (TextView) findViewById(R.id.btn_call);
		TextView tv_version = (TextView) findViewById(R.id.tv_version);
		TextView tv_developer = (TextView) findViewById(R.id.tv_developer);

		btn_call.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Callpeople.call("18175009828", AboutActivity.this);
			}
		});

		String version = initVersion();
		tv_version.setText("版本号v" + version);
	}

	/**
	 * 获取版本信息
	 */
	private String initVersion() {
		PackageManager packageManager = getPackageManager();
		try {
			PackageInfo packageInfo = packageManager.getPackageInfo(getPackageName(), PackageManager.GET_ACTIVITIES);
			String versionCode = packageInfo.versionName;
			return versionCode;
		} catch (PackageManager.NameNotFoundException e) {
			e.printStackTrace();
		}
		return "";
	}
}
