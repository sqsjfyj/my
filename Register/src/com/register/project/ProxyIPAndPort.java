package com.register.project;

import java.io.IOException;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.message.BasicNameValuePair;

public class ProxyIPAndPort {

	public static void getIPAndPort() throws KeyManagementException, NoSuchAlgorithmException, ClientProtocolException, IOException {
		CloseableHttpClient httpClient = HttpClientManager.getHttpClient();
		List<NameValuePair> params = new ArrayList<>();
		params.add(new BasicNameValuePair("num", "3000"));
		params.add(new BasicNameValuePair("port", ""));
		params.add(new BasicNameValuePair("kill_port", ""));
		params.add(new BasicNameValuePair("address", ""));
		params.add(new BasicNameValuePair("kill_address", "中国"));
		params.add(new BasicNameValuePair("anonymity", "2"));
		params.add(new BasicNameValuePair("type", ""));
		params.add(new BasicNameValuePair("post", "1"));
		params.add(new BasicNameValuePair("sort", "1"));
		params.add(new BasicNameValuePair("key", "03d12bfc8b5d61153efc8d19393350e6"));
		HttpPost httpPost = new HttpPost("https://ip.ihuan.me/tqdl.html");


	}

}
