package cn.cubenet.mcplugins.cubeList.util;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.StandardCharsets;

public class NetUtils {

    public static String fetch(String url) {
        StringBuilder json = new StringBuilder();
        try {
            URL urlObject = new URL(url);
            URLConnection conn = urlObject.openConnection();
            conn.setRequestProperty("User-Agent", "Mozilla/5.0");

            try (BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream(), StandardCharsets.UTF_8))) {
                String line;
                while ((line = in.readLine()) != null) {
                    json.append(line);
                }
            }
        } catch (Exception e) {
            System.err.println("网络请求失败: " + e.getMessage());
        }
        return json.toString();
    }
}
