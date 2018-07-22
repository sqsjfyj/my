package com.register.project;

import org.apache.http.HttpHost;
import org.apache.http.client.config.CookieSpecs;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.config.Registry;
import org.apache.http.config.RegistryBuilder;
import org.apache.http.conn.socket.ConnectionSocketFactory;
import org.apache.http.conn.socket.PlainConnectionSocketFactory;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;

import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;

public class HttpClientManager {

    private static PoolingHttpClientConnectionManager getConnectionManager() throws NoSuchAlgorithmException, KeyManagementException {
        PoolingHttpClientConnectionManager connectionManager;

        SSLContext sslContext = SSLContext.getInstance("SSL");
        sslContext.init(null, new TrustManager[]{

                new X509TrustManager() {
                    @Override
                    public void checkClientTrusted(X509Certificate[] x509Certificates, String s) throws CertificateException {

                    }

                    @Override
                    public void checkServerTrusted(X509Certificate[] x509Certificates, String s) throws CertificateException {

                    }

                    @Override
                    public X509Certificate[] getAcceptedIssuers() {
                        return new X509Certificate[0];
                    }
                }
        }, new SecureRandom());

        RegistryBuilder<ConnectionSocketFactory> registryBuilder = RegistryBuilder.create();

        Registry<ConnectionSocketFactory> socketFactoryRegistry = registryBuilder.register("http", PlainConnectionSocketFactory.INSTANCE).register("https", new SSLConnectionSocketFactory(sslContext)).build();

        connectionManager = new PoolingHttpClientConnectionManager(socketFactoryRegistry);
        return connectionManager;
    }

    //创建无代理HttpClient
    public static CloseableHttpClient getHttpClient() throws NoSuchAlgorithmException, KeyManagementException {
        CloseableHttpClient httpClient;
        PoolingHttpClientConnectionManager connectionManager = getConnectionManager();
        RequestConfig requestConfig = RequestConfig.custom().setCookieSpec(CookieSpecs.IGNORE_COOKIES).build();
        httpClient = HttpClients.custom().setConnectionManager(connectionManager).setDefaultRequestConfig(requestConfig).build();
        return httpClient;
    }

    //创建具有代理的HttpClient
    public static CloseableHttpClient getHttpClient(String ipPort) throws KeyManagementException, NoSuchAlgorithmException, UnknownHostException {
        CloseableHttpClient httpClient;
        String[] ipPortArray = ipPort.split(":");
        PoolingHttpClientConnectionManager connectionManager = getConnectionManager();
        HttpHost httpHost = new HttpHost(ipPortArray[0], Integer.parseInt(ipPortArray[1]));
        System.out.println(ipPort);
        RequestConfig requestConfig = RequestConfig.custom().setCookieSpec(CookieSpecs.IGNORE_COOKIES).build();
        httpClient = HttpClients.custom().setConnectionManager(connectionManager).setDefaultRequestConfig(requestConfig).setProxy(httpHost).build();
        return httpClient;
    }


}
