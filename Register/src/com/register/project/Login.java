package com.register.project;

import static org.apache.http.Consts.UTF_8;

import java.io.IOException;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.http.Header;
import org.apache.http.HttpStatus;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

public class Login {

    //登录获取后Cookie，用于上传图片
    public static Map<String, String> login(String email, String password, Map<String, String> cookies) throws KeyManagementException, NoSuchAlgorithmException, IOException {
        Map<String, String> loginCookies = new HashMap<>();
        CloseableHttpClient httpClient = HttpClientManager.getHttpClient();
        HttpPost httpPost = new HttpPost("https://www.instagram.com/accounts/login/ajax/");
        List<NameValuePair> params = new ArrayList<>();
        params.add(new BasicNameValuePair("username", email));
        params.add(new BasicNameValuePair("password", password));
        params.add(new BasicNameValuePair("queryParams", "{}"));
        httpPost.setHeader("User-Agent", "Mozilla/5.0 (Windows NT 10.0; WOW64; rv:59.0) Gecko/20100101 Firefox/59.0");
        httpPost.setHeader("Referer", "https://www.instagram.com/");
        httpPost.setHeader("Cookie", "rur=" + cookies.get("rur") + "; " + "mid=" + cookies.get("mid") + "; " + "csrftoken=" + cookies.get("csrftoken"));
        httpPost.setHeader("X-CSRFToken", cookies.get("csrftoken"));
        httpPost.setEntity(new UrlEncodedFormEntity(params, UTF_8));
        try {
            CloseableHttpResponse httpResponse = httpClient.execute(httpPost);
            if (httpResponse.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
                String authenticated = EntityUtils.toString(httpResponse.getEntity(), "utf-8");
                System.out.println(authenticated);
                if (authenticated.contains("\"authenticated\": true")) {
                    Header[] headers = httpResponse.getHeaders("set-cookie");
                    for (Header header : headers){
                        if (header.getValue().startsWith("rur")) {
                            String rur = header.getValue().substring("rur=".length(), header.getValue().indexOf(";"));
                            if (rur != null && !rur.equals("\"\"")) {
                                loginCookies.put("rur", header.getValue().substring("rur=".length(), header.getValue().indexOf(";")));
                                //System.out.println("rur:" + loginCookies.get("rur"));
                            }
                        }else if (header.getValue().startsWith("csrftoken")){
                            String csrftoken = header.getValue().substring("csrftoken=".length(), header.getValue().indexOf(";"));
                            if (csrftoken != null && !csrftoken.equals("\"\"")) {
                                loginCookies.put("csrftoken", header.getValue().substring("csrftoken=".length(), header.getValue().indexOf(";")));
                                //System.out.println("csrftoken:" + loginCookies.get("csrftoken"));
                            }
                        }else if (header.getValue().startsWith("ds_user_id")){
                            loginCookies.put("ds_user_id", header.getValue().substring("ds_user_id=".length(), header.getValue().indexOf(";")));
                            //System.out.println("ds_user_id:" + loginCookies.get("ds_user_id"));
                        }else if (header.getValue().startsWith("sessionid")) {
                            loginCookies.put("sessionid", header.getValue().substring("sessionid=".length(), header.getValue().indexOf(";")));
                            //System.out.println("sessionid:" + loginCookies.get("sessionid"));
                        }
                    }
                }else {
                    System.out.println("帐号或密码错误！");
                }
            }else if (httpResponse.getStatusLine().getStatusCode() == 400) {
                System.out.println("该帐号未通过认证！");
            }
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }finally {
            httpClient.close();
        }
        return loginCookies;
    }

    //登录后重定向至主页获取Cookie rur值
//	public static String getLoginRur(String username, Map<String, String> loginCookies) throws KeyManagementException, NoSuchAlgorithmException{
//		String r = null;
//		CloseableHttpClient httpClient = HttpClientManager.getHttpClient();
//		HttpGet httpGet = new HttpGet("https://www.instagram.com/");
//		httpGet.setHeader("User-Agent", "Mozilla/5.0 (Windows NT 10.0; WOW64; rv:59.0) Gecko/20100101 Firefox/59.0");
//		httpGet.setHeader("Referer", "https://www.instagram.com/" + username + "/");
//		httpGet.setHeader("Cookie", "csrftoken=" + loginCookies.get("csrftoken") + "; " + "ds_user_id=" + loginCookies.get("ds_user_id") + "; " + "sessionid=" + loginCookies.get("sessionid"));
//		try {
//			CloseableHttpResponse httpResponse = httpClient.execute(httpGet);
//			if (httpResponse.getStatusLine().getStatusCode() == 200) {
//				String json = EntityUtils.toString(httpResponse.getEntity(), "utf-8");
//				r = json;
//			}
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		return r;
//	}



}
