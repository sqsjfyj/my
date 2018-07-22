package com.register.project;

import java.io.IOException;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;



public class MyTest {

	public static void main(String[] args) throws IOException, KeyManagementException, NoSuchAlgorithmException, InterruptedException {

		Map<String, String> cookies = Register.getCookie();//获取Cookie
		//注册一个
		//MyTest.register(cookies);
		//注册100个
		//MyTest.registerHundred();
		//上传头像;
		System.out.println(UploadImg.upload("I:\\c.jpg", Login.login("1493025634@qq.com", "qwj7758258", cookies)));
	}


	//注册一个
	public static void register(Map<String, String> cookies) throws KeyManagementException, NoSuchAlgorithmException, IOException {
		if (cookies.size() > 0) {
			String email = GetEmailAndUsername.getMailByYopMail();
			System.out.println(email);
			String html = GetEmailAndUsername.getUsername("chifantht", "1");
			Document document = Jsoup.parse(html);
			Elements elements = document.getElementsByClass("bizname");
			String username = elements.get(0).text();
			System.out.println(username);
			System.out.println(Register.registerByEmail(email, "uisfgtugb", username, "osifui456446", cookies));
		}
	}

	//100个
	public static void registerHundred(/*Map<String, String> cookies8*/) throws KeyManagementException, NoSuchAlgorithmException, IOException, InterruptedException {
		//100个账户
		List<Account> accountsList = new ArrayList<>();
		List<String> email = MyTest.getHundredEmail();
		List<String> username = MyTest.getHundredUsername();
		//List<String> ipList = ProxyIPAndPort.getIPAndPort();
		for (int i = 0; i < 100; i++){
			Account account = new Account(email.get(i), "dsufighdi", username.get(i), "a123456789");
			accountsList.add(account);
		}

		//注册100个
		//if (cookies.size() > 0) {
		Register.registerOneHundred(accountsList);
		//}

	}


	//获取100个邮箱
	public static List<String> getHundredEmail() throws KeyManagementException, NoSuchAlgorithmException, IOException, InterruptedException {
		List<String> emailList = new ArrayList<>();
		for (int i = 0; i < 100; i++) {
			String email = GetEmailAndUsername.getMailByYopMail();
			emailList.add(email);
			System.out.println(email);
			Thread.sleep(1000);
		}
		return emailList;
	}

	//获取100个用户名
	public static List<String> getHundredUsername() throws KeyManagementException, NoSuchAlgorithmException, IOException {
		List<String> usernameList = new ArrayList<>();
		String html = GetEmailAndUsername.getUsername("sbt", "40");
		Document document = Jsoup.parse(html);
		Elements biznames = document.getElementsByClass("bizname");
		for(int i = 0; i < 100; i++) {
			String username = biznames.get(i).text();
			usernameList.add(username);
			System.out.println(username);
		}
		return usernameList;
	}


}
