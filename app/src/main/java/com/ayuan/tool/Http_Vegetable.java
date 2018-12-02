package com.ayuan.tool;

import com.ayuan.vo.Vegetableinfo;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * 首页菜品分类的网络请求
 */
public class Http_Vegetable {
	private static List<Vegetableinfo> vegetablelist = new ArrayList<>();
	private static HttpURLConnection connection;
	private static InputStream is;
	private static ByteArrayOutputStream baos;

	public static List<Vegetableinfo> getVegetable() {
		URL url;
		try {
			url = new URL(Values.Http_Vegetable);
			connection = (HttpURLConnection) url.openConnection();
			connection.setReadTimeout(5000);
			connection.setConnectTimeout(5000);
			connection.setRequestMethod("POST");
			connection.setRequestProperty("charset", "utf-8");
			connection.setDoOutput(true);
			connection.setDoInput(true);
			if (connection.getResponseCode() == 200) {
				is = connection.getInputStream();
				baos = new ByteArrayOutputStream();
				int len = -1;
				byte[] buf = new byte[128];
				while ((len = is.read(buf)) != -1) {
					baos.write(buf, 0, len);
				}
				baos.flush();
				String str = baos.toString();
				System.out.println(str);
				JSONObject jsonObject = new JSONObject(str);
				JSONArray types = jsonObject.getJSONArray("types");
				for (int i = 0; i < types.length(); i++) {
					JSONObject type = types.getJSONObject(i);
					String typepic = type.getString("typepic");
					String description = type.getString("description");
					String typeid = type.getString("typeid");
					String typename = type.getString("typename");
					Vegetableinfo vegetableinfo = new Vegetableinfo(typepic, description, typeid, typename);
					vegetablelist.add(vegetableinfo);
				}
				System.out.println(vegetablelist.size());
			}
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (is != null && baos != null & connection != null) {
				try {
					url = null;
					is.close();
					baos.close();
					connection.disconnect();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			return vegetablelist;
		}
	}
}
