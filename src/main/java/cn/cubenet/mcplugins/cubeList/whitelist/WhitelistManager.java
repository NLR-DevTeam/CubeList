package cn.cubenet.mcplugins.cubeList.whitelist;

import cn.cubenet.mcplugins.cubeList.config.AppConfig;
import cn.cubenet.mcplugins.cubeList.config.ConfigManager;
import cn.cubenet.mcplugins.cubeList.util.JsonUtils;
import cn.cubenet.mcplugins.cubeList.util.NetUtils;
import org.slf4j.Logger;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class WhitelistManager {
    private final Logger logger;
    private AppConfig config;
    private final ConfigManager configManager;
    private List<String> allowList = new ArrayList<>();

    public WhitelistManager(Logger logger, AppConfig config, ConfigManager configManager) {
        this.logger = logger;
        this.config = config;
        this.configManager = configManager;
    }

    public void reloadWhitelist() {
        try {
            this.config = configManager.load();
        } catch (IOException e) {
            logger.error("加载配置文件失败: {}", e.getMessage());
            return;
        }
        logger.info("正在获取白名单...");
        try {
            String response = NetUtils.fetch(config.getUrl());
            allowList = JsonUtils.parseSimpleArray(response);
            logger.info("白名单加载完成！共 {} 位玩家。", allowList.size());
        } catch (Exception e) {
            logger.error("重新加载白名单时出错: {}", e.getMessage());
        }
    }

    public boolean isAllowed(String username) {
        return allowList.contains(username);
    }

    public int getAllowListSize() {
        return allowList.size();
    }
}
