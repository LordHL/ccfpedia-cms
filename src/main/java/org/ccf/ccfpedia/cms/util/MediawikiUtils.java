package org.ccf.ccfpedia.cms.util;

import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.Lists;
import org.apache.http.NameValuePair;
import org.apache.http.ParseException;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.ccf.ccfpedia.cms.bean.SessionBean;
import org.ccf.ccfpedia.cms.bean.UserBean;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import javax.ws.rs.core.MultivaluedHashMap;
import javax.ws.rs.core.MultivaluedMap;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MediawikiUtils {

    private final static String WIKI_URL = "http://term.ccf.org.cn/index.php/";

    public static void main(String[] args) {
        String result = getURL("王昊奋", "wanghaofen", "计算机");
        System.out.println(result);
    }

    public static String getURL(String name, String password, String word) {
        Map<String, String> map = getToken();
        String cookie = map.get("cookie");
        String token = map.get("token");
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        List<String> cookies = Lists.newArrayList();
        cookies.add(cookie);
        headers.put(HttpHeaders.COOKIE,cookies);
        headers.add(HttpHeaders.USER_AGENT,"Mozilla/5.0 (Windows NT 6.1; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/68.0.3440.106 Safari/537.36");
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        MultiValueMap<String, String> body = new LinkedMultiValueMap<>();
        body.add("lgpassword", password);
        body.add("lgtoken", token);
        HttpEntity<MultiValueMap<String, String>> requestEntity = new HttpEntity<>(body, headers);
        ResponseEntity<String> r = restTemplate.postForEntity("http://term.ccf.org.cn/api.php?action=login&format=json&lgname=" + name, requestEntity, String.class);
        List<String> cookieList = r.getHeaders().get(HttpHeaders.SET_COOKIE);
        String result = "";
        if(cookieList != null) {
            result = "?";
            for (String c : cookieList) {
                String t = "";
                if (c.contains(";")) {
                    t = c.split(";")[0];
                } else {
                    t = c;
                }
                result += t + "&";
            }
        }
        return WIKI_URL + word + result.substring(0, result.length() - 1);
    }

    public static Map<String, String> getToken(){
        Map<String, String> resultMap = new HashMap<>();
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.USER_AGENT,"Mozilla/5.0 (Windows NT 6.1; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/68.0.3440.106 Safari/537.36");
        MultiValueMap<String, String> body = new LinkedMultiValueMap<>();
        HttpEntity<MultiValueMap<String, String>> requestEntity = new HttpEntity<>(body, headers);
        ResponseEntity<String> r = restTemplate.postForEntity("http://term.ccf.org.cn/api.php?action=query&format=json&meta=tokens&type=login",requestEntity,String.class);
        JSONObject object = JSONObject.parseObject(r.getBody());
        resultMap.put("token", object.getJSONObject("query").getJSONObject("tokens").getString("logintoken"));
        String cookie = r.getHeaders().get(HttpHeaders.SET_COOKIE).toString();
        String split = cookie.split(";")[0].split("\\[")[1];
        resultMap.put("cookie", split);
        return resultMap;
    }
}
