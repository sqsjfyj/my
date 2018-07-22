package com.register.project;


import java.io.File;
import java.io.IOException;
import java.net.ConnectException;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.util.Map;

import org.apache.http.HttpEntity;
import org.apache.http.HttpStatus;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.mime.HttpMultipartMode;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.util.EntityUtils;


public class UploadImg {

	//上传头像
	public static String upload(String filePath, Map<String, String> loginCookies) throws KeyManagementException, NoSuchAlgorithmException, ClientProtocolException, IOException {
		String result = null;
		CloseableHttpClient httpClient = HttpClientManager.getHttpClient();
		HttpPost httpPost = new HttpPost("https://www.instagram.com/accounts/web_change_profile_picture/");
		httpPost.setHeader("User-Agent", "Mozilla/5.0 (Windows NT 10.0; WOW64; rv:59.0) Gecko/20100101 Firefox/59.0");
		httpPost.setHeader("Referer", "https://www.instagram.com/");
		httpPost.setHeader("Connection", "keep-alive");
		httpPost.setHeader("Cookie", "csrftoken=" + loginCookies.get("csrftoken") + "; " + "ds_user_id=" + loginCookies.get("ds_user_id") + "; " + "sessionid=" + loginCookies.get("sessionid"));
		httpPost.setHeader("X-CSRFToken", loginCookies.get("csrftoken"));
		File file = new File(filePath);
		System.out.println(filePath);
		MultipartEntityBuilder multipartEntityBuilder = MultipartEntityBuilder.create();
		multipartEntityBuilder.setMode(HttpMultipartMode.BROWSER_COMPATIBLE);
		HttpEntity httpRequestEntity = multipartEntityBuilder.addBinaryBody("profile_pic", file, ContentType.IMAGE_JPEG, file.getName()).build();
		httpPost.setEntity(httpRequestEntity);
		try {
			CloseableHttpResponse httpResponse = httpClient.execute(httpPost);
			if (httpResponse.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
				String json = EntityUtils.toString(httpResponse.getEntity(), "utf-8");
				result = json;
				return result;
			}else {
				if (httpResponse.getStatusLine().getStatusCode() == 429){
					result = "请求次数过多";
				}else {
					result = "状态码：" + String.valueOf(httpResponse.getStatusLine().getStatusCode());
				}
				return result;
			}

		}catch (ConnectException e){
			result = "连接超时";
			return result;
		}

	}

}
