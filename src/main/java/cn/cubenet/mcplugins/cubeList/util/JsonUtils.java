package cn.cubenet.mcplugins.cubeList.util;

import java.util.ArrayList;
import java.util.List;

public class JsonUtils {

    public static List<String> parseSimpleArray(String jsonString) {
        List<String> result = new ArrayList<>();
        if (jsonString == null || jsonString.trim().isEmpty()) return result;

        try {
            String cleaned = jsonString.trim()
                    .replace("[", "")
                    .replace("]", "")
                    .replace("\"", "")
                    .trim();

            if (cleaned.isEmpty()) return result;

            for (String item : cleaned.split(",")) {
                String trimmed = item.trim();
                if (!trimmed.isEmpty()) result.add(trimmed);
            }
        } catch (Exception e) {
            System.err.println("解析 Json 失败: " + e.getMessage());
        }

        return result;
    }
}
