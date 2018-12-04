package com.ayuan.utils;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;

//拨打电话
public class Callpeople {
	public static void call(String phonenumber, Context context) {
		Intent intent = new Intent();
		intent.setAction(Intent.ACTION_CALL);
		intent.setData(Uri.parse("tel:" + phonenumber));
		context.startActivity(intent);
	}
}
