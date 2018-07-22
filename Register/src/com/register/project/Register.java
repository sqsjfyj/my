package com.register.project;

import java.io.EOFException;
import java.io.IOException;
import java.net.ConnectException;
import java.net.SocketException;
import java.net.SocketTimeoutException;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.http.*;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import static org.apache.http.Consts.UTF_8;


public class Register {

	//获取主页Cookie用于登录或注册
	public static Map<String, String> getCookie() throws KeyManagementException, NoSuchAlgorithmException, IOException {
		//List<String> cookies = new ArrayList<>();
		Map<String, String> cookies = new HashMap<>();
		CloseableHttpClient httpClient = HttpClientManager.getHttpClient();
		HttpGet httpGet = new HttpGet("https://www.instagram.com/");
		httpGet.setHeader("Connection", "keep-alive");
		httpGet.setHeader("User-Agent", "Mozilla/5.0 (Windows NT 6.1; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/65.0.3325.181 Safari/537.36");
		try {
			CloseableHttpResponse httpResponse = httpClient.execute(httpGet);
			Header[] headers = httpResponse.getHeaders("set-cookie");
			for (Header header : headers){
				if (header.getValue().startsWith("rur")) {
					cookies.put("rur", header.getValue().substring("rur=".length(), header.getValue().indexOf(";")));
					System.out.println("rur:" + cookies.get("rur"));
				}else if (header.getValue().startsWith("csrftoken")){
					cookies.put("csrftoken", header.getValue().substring("csrftoken=".length(), header.getValue().indexOf(";")));
					System.out.println("csrftoken:" + cookies.get("csrftoken"));
				}else if (header.getValue().startsWith("mid")){
					cookies.put("mid", header.getValue().substring("mid=".length(), header.getValue().indexOf(";")));
					System.out.println("mid:" + cookies.get("mid"));
				}
			}
		}catch (ConnectException e){
			System.out.println("ins连接超时");
		}finally {
			httpClient.close();
		}
		return cookies;
	}


	//代理注册
	public static String registerByEmail(String email, String allName, String username, String password, String ip, Map<String, String> cookies) throws IOException, NoSuchAlgorithmException, KeyManagementException {
		String result = null;
		CloseableHttpClient closeableHttpClient = HttpClientManager.getHttpClient(ip);
		List<NameValuePair> params = new ArrayList<>();
		params.add(new BasicNameValuePair("email", email));
		params.add(new BasicNameValuePair("first_name", allName));
		params.add(new BasicNameValuePair("username", username));
		params.add(new BasicNameValuePair("password", password));
		HttpPost httpPost = new HttpPost("https://www.instagram.com/accounts/web_create_ajax/");
		httpPost.setHeader("Connection", "keep-alive");
		httpPost.setHeader("User-Agent", "Mozilla/5.0 (Windows NT 10.0; WOW64; rv:59.0) Gecko/20100101 Firefox/59.0");
		httpPost.setHeader("Referer", "https://www.instagram.com/");
		httpPost.setHeader("Cookie", "rur=" + cookies.get("rur") + "; " + "csrftoken=" + cookies.get("csrftoken") + "; " + "mid=" + cookies.get("mid"));
		httpPost.setHeader("X-CSRFToken", cookies.get("csrftoken"));
		httpPost.setEntity(new UrlEncodedFormEntity(params, UTF_8));
		try {
			CloseableHttpResponse httpResponse = closeableHttpClient.execute(httpPost);
			if (httpResponse.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
				String json = EntityUtils.toString(httpResponse.getEntity(), "utf-8");
				result = json;
			}else {
				if (httpResponse.getStatusLine().getStatusCode() == 429){
					result = "请求次数过多";
				}else {
					result = "状态码：" + String.valueOf(httpResponse.getStatusLine().getStatusCode());
				}
			}

		}catch (SocketTimeoutException e) {
			// TODO: handle exception
			result = "发送请求超时";
		}catch (ConnectException e){
			result = "注册连接超时";
		}catch (NoHttpResponseException e){
			result = "无响应";
		}catch (SocketException e) {
			// TODO: handle exception
			result = "中断";
		}catch (EOFException e) {
			// TODO: handle exception
			result = "SSL peer shut down incorrectly";
		}finally {
			closeableHttpClient.close();
		}
		return result;
	}

	//注册
	public static String registerByEmail(String email, String allName, String username, String password, Map<String, String> cookies) throws IOException, NoSuchAlgorithmException, KeyManagementException {
		System.out.println(email + " " + allName + " " + username + " " + password);
		String result = null;
		CloseableHttpClient closeableHttpClient = HttpClientManager.getHttpClient();
		List<NameValuePair> params = new ArrayList<>();
		params.add(new BasicNameValuePair("email", email));
		params.add(new BasicNameValuePair("first_name", allName));
		params.add(new BasicNameValuePair("username", username));
		params.add(new BasicNameValuePair("password", password));
		HttpPost httpPost = new HttpPost("https://www.instagram.com/accounts/web_create_ajax/");
		httpPost.setHeader("Accept", "*/*");
		httpPost.setHeader("Accept-Encoding", "gzip, deflate, br");
		//httpPost.setHeader("Accept-Language", "zh-CN,zh;q=0.8,zh-TW;q=0.7,zh-HK;q=0.5,en-US;q=0.3,en;q=0.2");
		httpPost.setHeader("Connection", "keep-alive");
		httpPost.setHeader("User-Agent", "Mozilla/5.0 (Windows NT 6.1; Win64; x64; rv:59.0) Gecko/20100101 Firefox/59.0");
		//httpPost.setHeader("User-Agent", "Mozilla/5.0 (Windows NT 6.1; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/65.0.3325.181 Safari/537.36");
		httpPost.setHeader("Referer", "https://www.instagram.com/");
		httpPost.setHeader("Cookie", "rur=" + cookies.get("rur") + "; " + "csrftoken=" + cookies.get("csrftoken") + "; " + "mid=" + cookies.get("mid"));
		httpPost.setHeader("X-CSRFToken", cookies.get("csrftoken"));
		httpPost.setHeader("Cache-Control", "no-cache");
		httpPost.setHeader("Content-Type", "application/x-www-form-urlencoded");
		httpPost.setHeader("Host", "www.instagram.com");
		httpPost.setHeader("Pragma", "no-cache");
		httpPost.setHeader("X-Instagram-AJAX", "1");
		httpPost.setHeader("X-Requested-With", "XMLHttpRequest");
		httpPost.setEntity(new UrlEncodedFormEntity(params, UTF_8));
		try {
			CloseableHttpResponse httpResponse = closeableHttpClient.execute(httpPost);
			if (httpResponse.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
				String json = EntityUtils.toString(httpResponse.getEntity(), "utf-8");
				result = json;
			}else {
				if (httpResponse.getStatusLine().getStatusCode() == 429){
					result = "请求次数过多";
				}else {
					result = "状态码：" + String.valueOf(httpResponse.getStatusLine().getStatusCode());
				}
			}
		}catch (ConnectException e){
			result = "请求超时";
		}catch (NoHttpResponseException e){
			result = "无响应";
		}catch (SocketException e) {
			// TODO: handle exception
			result = "中断";
		}catch (EOFException e) {
			// TODO: handle exception
			result = "SSL peer shut down incorrectly";
		}finally {
			closeableHttpClient.close();
		}
		return result;
	}

	//注册100个
	public static void registerOneHundred(List<Account> accountsList) throws IOException, KeyManagementException, NoSuchAlgorithmException, InterruptedException {
		String[] userAgents = new String[] {"Mozilla/5.0 (Windows NT 6.1; Win64; x64; rv:59.0) Gecko/20100101 Firefox/59.0", "Mozilla/5.0 (Windows NT 6.1; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/65.0.3325.181 Safari/537.36"};
		int i = 0;
		while(i < 20) {
			Map<String, String> cookies = Register.getCookie();
			if (cookies.size() > 0) {
				for(int j = 0; j < 5; j++) {
					int k = i%2;
					int sleep = (int)(Math.random() * 5 * 1000) + 5000;
					System.out.println(sleep);
					System.out.println(registerByEmail(accountsList.get(j + i * 5).getEmail(), accountsList.get(j + i * 5).getAllName(), accountsList.get(j + i * 5).getUsername(), accountsList.get(j + i * 5).getPassword(), cookies));
					Thread.sleep(sleep);
				}
				i++;
			}
			Thread.sleep(30000);
		}
	}


}
