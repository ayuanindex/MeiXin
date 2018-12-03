package com.ayuan.utils;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Environment;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;

//获取网络图片对象
public class GetDrawable {
	Drawable drawable;
	private URL url;
	private String imgurl;

	public Drawable getdrawable(final String path, final Context context) {
		imgurl = Values.Http + path;
		url = null;
		Thread thread = new Thread() {
			@Override
			public void run() {
				try {
					url = new URL(imgurl);
					InputStream stream = url.openStream();
					drawable = Drawable.createFromResourceStream(context.getResources(), null, stream, "src", null);
					Drawable.createFromStream(url.openStream(), "");
					drawable.setBounds(0, 0, drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight());
				} catch (MalformedURLException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		};
		thread.start();
		try {
			thread.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		return drawable;
	}

	/**
	 * @param path     传入的文件网络地址
	 * @param context
	 * @param drawable
	 *//*
	public void getBitmap(final String path, final Context context, final BitmapDrawable drawable) {
		imgurl = Values.Http + path;
		url = null;
		new Thread() {
			@Override
			public void run() {
				super.run();
				Bitmap bitmap = drawable.getBitmap();
				File file = new File(Environment.getDownloadCacheDirectory().getPath(), path + ".png");
				FileOutputStream fileOutputStream = null;
				try {
					fileOutputStream = new FileOutputStream(file);
					bitmap.compress(Bitmap.CompressFormat.PNG, 100, fileOutputStream);
				} catch (FileNotFoundException e) {
					e.printStackTrace();
				} finally {
					if (fileOutputStream != null) {
						try {
							fileOutputStream.close();
						} catch (IOException e) {
							e.printStackTrace();
						}
					}
				}
			}
		}.start();
	}
*/
	/**
	 * 将Drawable转化为Bitmap
	 *
	 * @param drawable
	 * @return
	 */
	public Bitmap drawableToBitmap(Drawable drawable) {
		if (drawable == null)
			return null;
		return ((BitmapDrawable) drawable).getBitmap();
	}

	/**
	 * 将Bitmap以指定格式保存到指定路径
	 *
	 * @param bitmap
	 * @param name
	 */
	public void saveBitmap(Bitmap bitmap, String name, Bitmap.CompressFormat format) {
		// 创建一个位于SD卡上的文件
		File file = new File(Environment.getExternalStorageDirectory(),
				name);
		FileOutputStream out = null;
		try {
			// 打开指定文件输出流
			out = new FileOutputStream(file);
			// 将位图输出到指定文件
			bitmap.compress(format, 100, out);
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
