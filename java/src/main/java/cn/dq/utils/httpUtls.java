package cn.dq.utils;

import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
//import org.weixin4j.http.MyX509TrustManager;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;


import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class httpUtls {
    private   static String httpPost2(String url, String json) {

        String returnValue = "";
        CloseableHttpClient httpClient = HttpClients.createDefault();

        try {
            httpClient = HttpClients.createDefault();
            HttpPost httpPost = new HttpPost(url);


            StringEntity requestEntity = new StringEntity(json, "utf-8");
            requestEntity.setContentEncoding("UTF-8");
            httpPost.setHeader("Content-type", "application/json");
            httpPost.setEntity(requestEntity);

            HttpResponse response = httpClient.execute(httpPost);
            HttpEntity entity = response.getEntity();
            int code=200;
            if (entity != null&&response.getStatusLine().getStatusCode()==code) {
                InputStream inputStream = entity.getContent();

                BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream, "utf-8"));

                returnValue = reader.readLine();
                if (returnValue == null) {
                    returnValue = "";
                }
            }
        } catch (Exception e) {
            returnValue = "";
        } finally {
            try {
                httpClient.close();
            } catch (Exception e2) {
            }
        }
        return returnValue.trim();
    }

    public static String httpPost(String url0, String json) throws  Exception {
        OutputStream os = null;
        InputStream is = null;

        String result="";

        URL url = new URL(url0);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setDoOutput(true);
        conn.setDoInput(true);
        conn.setRequestMethod("POST");
        conn.setRequestProperty("Content-Type","application/json");
        conn.setConnectTimeout(1000*10);
        conn.setReadTimeout(5000*2);
        conn.setRequestProperty("charset", "utf-8");
        os = conn.getOutputStream();
        os.write(json.getBytes("utf-8"));
        is = conn.getInputStream();
        BufferedReader br = new BufferedReader(new InputStreamReader(is,"utf-8"));
        StringBuffer buffer = new StringBuffer();
        String line = null;
        while ((line = br.readLine()) != null) {
            buffer.append(line);
        }
        result=buffer.toString();

        try {
            if (os != null) {
                os.close();
            }
            if (is != null) {
                is.close();
            }
        }catch (Exception e){
            // e.printStackTrace();
        }
        return  result;

    }
}
