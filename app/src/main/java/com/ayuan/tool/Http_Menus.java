package com.ayuan.tool;

import com.ayuan.vo.Menuinfo;
import com.ayuan.vo.Request_menu;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class Http_Menus {
	private static List<Menuinfo> menuinfoList = new ArrayList<Menuinfo>();
	private static HttpURLConnection connection;
	private static InputStream is;
	private static ByteArrayOutputStream baos;

	public static List<Menuinfo> getmenus(Request_menu request) {
		URL url;
		try {
			url = new URL(Values.Http_menus);
			connection = (HttpURLConnection) url.openConnection();
			connection.setReadTimeout(5000);
			connection.setConnectTimeout(5000);
			connection.setRequestMethod("POST");
			connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
			connection.setDoOutput(true);
			connection.setDoInput(true);
			connection.setUseCaches(false);
			StringBuffer stringBuffer = new StringBuffer();
			stringBuffer.append("typecid=").append(request.getTypeid()).append("&").append("startid=").append(request.getStartid()).append("&").append("pagesize=").append(request.getPagesize());
			byte[] bytes = stringBuffer.toString().getBytes();
			connection.setRequestProperty("Content-Length", String.valueOf(bytes.length));
			OutputStream outputStream = connection.getOutputStream();
			outputStream.write(bytes);
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
				JSONArray menus = jsonObject.getJSONArray("menus");
				for (int i = 0; i < menus.length(); i++) {
					JSONObject menu = menus.getJSONObject(i);
					String spic = menu.getString("spic");
					String assistmaterial = menu.getString("assistmaterial");
					String notlikes = menu.getString("notlikes");
					String menuname = menu.getString("menuname");
					String abstracts = menu.getString("abstracts");
					String mainmaterial = menu.getString("mainmaterial");
					String menuid = menu.getString("menuid");
					String typeid = menu.getString("typeid");
					String likes = menu.getString("likes");
					Menuinfo Menuinfo = new Menuinfo(spic, assistmaterial, notlikes, menuname, abstracts, mainmaterial, menuid, typeid, likes);
					menuinfoList.add(Menuinfo);
				}
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
			return menuinfoList;
		}
	}
}
