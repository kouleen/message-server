package com.kouleen.message.service.infrastructure.utils;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * @author zhangqing
 * @since 2023/7/17 9:25
 */
public final class JsoupUtil {

    private static final Logger logger = LoggerFactory.getLogger(JsoupUtil.class);

    public static Document getDocument(String url) {
        int i = 0;
        while (true){
            try {
                Connection connection = Jsoup.connect(url);
                return connection.header("Host", "www.b5200.org")
                        .header("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/avif,image/webp,image/apng,*/*;q=0.8,application/signed-exchange;v=b3;q=0.9")
                        .header("Accept-Encoding", "gzip, deflate")
                        .header("Accept-Language", "zh-CN,zh;q=0.9").header("Cache-Control", "no-cache")
                        .header("Connection", "keep-alive").header("Pragma", "no-cache")
                        .header("Upgrade-Insecure-Requests", "1")
                        .timeout(5000).userAgent("Mozilla")//模拟浏览器
                        .get();
            } catch (IOException e) {
                i++;
                logger.info("[书籍爬取服务] 获取document失败重试 {} 次", i);
            }
        }
    }

    public static boolean isConnection(String fictionURL) {
        int counts = 0;
        if (null == fictionURL || fictionURL.length() == 0) {
            return false;
        }
        while (counts < 10) {
            try {
                URL url = new URL(fictionURL);
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                int state = connection.getResponseCode();
                if (state == 200) {
                    return true;
                }
            } catch (Exception exception) {
                counts++;
            }
        }
        return false;
    }
}
