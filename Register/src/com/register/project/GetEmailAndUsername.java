package com.register.project;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.ConnectException;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class GetEmailAndUsername {

	/**
	 * 获取邮箱
	 * @return
	 * @throws NoSuchAlgorithmException
	 * @throws KeyManagementException
	 */
	public static String getMail () throws KeyManagementException, NoSuchAlgorithmException, IOException {
		CloseableHttpClient httpClient = HttpClientManager.getHttpClient();
		HttpGet httpGet = new HttpGet("https://10minutemail.net/");
		httpGet.setHeader("Connection", "keep-alive");
		httpGet.setHeader("User-Agent", "Mozilla/5.0 (Windows NT 10.0; WOW64; rv:59.0) Gecko/20100101 Firefox/59.0");
		String body;
		String mail = null;
		try {
			CloseableHttpResponse mailResponse = httpClient.execute(httpGet);
			body = EntityUtils.toString(mailResponse.getEntity(), "UTF-8");
			//write("D:\\mail.txt",body);
			//File file = new File("E:\\mail.txt");
			//Document doc = Jsoup.parse(file, "UTF-8", "http://example.com/");
			Document doc = Jsoup.parse(body);
			Elements biznames = doc.getElementsByClass("mailtext");
			for (Element bizname : biznames) {
				mail = bizname.val();
				//System.out.println("mail = " + mail);

			}
		} catch (ConnectException e) {
			// TODO: handle exception
			e.printStackTrace();
			System.out.println("获取邮箱超时");
		}catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}finally {
			httpClient.close();
		}
		return mail;

	}

	public static String getMailByYopMail() throws KeyManagementException, NoSuchAlgorithmException, IOException {
		String yopMail = null;
		CloseableHttpClient httpClient = HttpClientManager.getHttpClient();
		HttpGet httpGet = new HttpGet("http://www.yopmail.com/zh/email-generator.php");
		httpGet.setHeader("Connection", "keep-alive");
		httpGet.setHeader("User-Agent", "Mozilla/5.0 (Windows NT 10.0; WOW64; rv:59.0) Gecko/20100101 Firefox/59.0");
		String body = null;
		try {
			CloseableHttpResponse mailResponse = httpClient.execute(httpGet);
			body = EntityUtils.toString(mailResponse.getEntity(), "UTF-8");
			//write("D:\\mail.txt",body);
			//File file = new File("E:\\mail.txt");
			//Document doc = Jsoup.parse(file, "UTF-8", "http://example.com/");
			Document doc = Jsoup.parse(body);
			Element biznames = doc.getElementById("login");
			String result = biznames.attr("value");
			System.out.println(result);
			if (result != null && !result.equals("")) {
				yopMail = result;
			}

		} catch (ConnectException e) {
			// TODO: handle exception
			e.printStackTrace();
			System.out.println("获取邮箱超时");
		}catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}finally {
			httpClient.close();
		}
		return yopMail;

	}


	/**
	 * 获取username
	 * @param keyword
	 * @param numlines
	 * @return
	 * @throws NoSuchAlgorithmException
	 * @throws KeyManagementException
	 */
	public static String getUsername(String keyword, String numlines) throws KeyManagementException, NoSuchAlgorithmException, IOException {
		CloseableHttpClient httpClient = HttpClientManager.getHttpClient();
		//http://namegenerators.org/username-generator/
		String body = null;
		try {
			HttpPost post = new HttpPost("http://namegenerators.org/username-generator/");
			post.setHeader("Connection", "keep-alive");
			post.setHeader("User-Agent", "Mozilla/5.0 (Windows NT 10.0; WOW64; rv:59.0) Gecko/20100101 Firefox/59.0");
			Map<String, String> userMap = new HashMap<>();
			userMap.put("keyword", keyword);
			userMap.put("numlines", numlines);
			userMap.put("formsubmit", "Generate+Username");
			List<NameValuePair> params = new ArrayList<NameValuePair>();
			for (Map.Entry<String, String> entry : userMap.entrySet()) {
				//System.out.println("key = " + entry.getKey() + " value = " + entry.getValue());
				params.add(new BasicNameValuePair(entry.getKey(), entry.getValue()));
			}
			post.setEntity(new UrlEncodedFormEntity(params));

			CloseableHttpResponse response = httpClient.execute(post);

			body = EntityUtils.toString(response.getEntity(), "UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}finally {
			httpClient.close();
		}
		return body;

	}





}
