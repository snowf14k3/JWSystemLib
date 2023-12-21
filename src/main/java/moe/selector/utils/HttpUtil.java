package moe.selector.utils;

import org.jsoup.Connection;
import org.jsoup.Jsoup;

import java.util.Map;

public class HttpUtil {
    public static Connection.Response sendGet(String url) {
        try {
            return Jsoup.connect(url)
                    .followRedirects(true)
                    .execute();
        } catch (Exception e) {
            return null;
        }
    }

    public static Connection.Response sendGet(String url, Map<String, String> headers) {
        try {
            return Jsoup.connect(url)
                    .followRedirects(true)
                    .headers(headers)
                    .execute();
        } catch (Exception e) {
            return null;
        }
    }

    public static Connection.Response sendPost(String url, Map<String, String> form) {
        try {
            return Jsoup.connect(url)
                    .data(form)
                    .method(Connection.Method.POST)
                    .followRedirects(true)
                    .execute().charset("UTF-8");
        } catch (Exception e) {
            return null;
        }
    }

    public static Connection.Response sendPost(String url, Map<String, String> headers, Map<String, String> form, Map<String, String> cookies) {
        try {
            return Jsoup.connect(url)
                    .data(form).method(Connection.Method.POST)
                    .followRedirects(false)
                    .headers(headers)
                    .cookies(cookies)
                    .execute().charset("UTF-8");
        } catch (Exception e) {
            return null;
        }
    }

    public static Connection.Response sendPost(String url, Map<String, String> form, Map<String, String> headers) {
        try {
            return Jsoup.connect(url)
                    .data(form).method(Connection.Method.POST)
                    .followRedirects(false)
                    .headers(headers)
                    .execute().charset("UTF-8");
        } catch (Exception e) {
            return null;
        }
    }

}
