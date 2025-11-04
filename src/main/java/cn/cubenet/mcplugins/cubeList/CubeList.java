package cn.cubenet.mcplugins.cubeList;

import cn.cubenet.mcplugins.cubeList.command.ReloadCommand;
import cn.cubenet.mcplugins.cubeList.config.AppConfig;
import cn.cubenet.mcplugins.cubeList.config.ConfigManager;
import cn.cubenet.mcplugins.cubeList.whitelist.WhitelistManager;
import com.google.inject.Inject;
import com.velocitypowered.api.command.CommandManager;
import com.velocitypowered.api.event.Subscribe;
import com.velocitypowered.api.event.connection.LoginEvent;
import com.velocitypowered.api.event.proxy.ProxyInitializeEvent;
import com.velocitypowered.api.plugin.Plugin;
import com.velocitypowered.api.plugin.annotation.DataDirectory;
import com.velocitypowered.api.proxy.ProxyServer;
import net.kyori.adventure.text.Component;
import org.slf4j.Logger;

import java.io.IOException;
import java.nio.file.Path;

@Plugin(
        id = "cubelist",
        name = "CubeList",
        version = BuildConstants.VERSION,
        description = "A Velocity plugin to verify user by WEBAPI",
        url = "www.carlsky.cn",
        authors = {"CarlSky(Charlotte169)"}
)
public class CubeList {

    private final Logger logger;
    private final ProxyServer server;
    private final Path dataDirectory;

    private AppConfig config;
    private WhitelistManager whitelistManager;

    @Inject
    public CubeList(ProxyServer server, Logger logger, @DataDirectory Path dataDirectory) {
        this.server = server;
        this.logger = logger;
        this.dataDirectory = dataDirectory;
    }

    @Subscribe
    public void onProxyInitialization(ProxyInitializeEvent event) {
        logger.info("CubeList 插件正在初始化...");

        try {
            ConfigManager cfgManager = new ConfigManager(dataDirectory);
            this.config = cfgManager.load();
        } catch (IOException e) {
            logger.error("加载配置文件失败: {}", e.getMessage());
            return;
        }

        this.whitelistManager = new WhitelistManager(logger, config, new ConfigManager(dataDirectory));

        // 注册命令
        CommandManager cmdManager = server.getCommandManager();
        cmdManager.register("cbreload", new ReloadCommand(logger, whitelistManager), "cbr");
        logger.info("命令 /cbreload 已注册");

        // 初次加载白名单
        whitelistManager.reloadWhitelist();
        logger.info("CubeList 插件启动完成。");
    }

    @Subscribe
    public void onPlayerLogin(LoginEvent event) {
        String username = event.getPlayer().getUsername();
        if (!whitelistManager.isAllowed(username)) {
            event.setResult(LoginEvent.ComponentResult.denied(
                    Component.text("§c你不在 " + config.getServiceName() +
                            " 白名单中！请联系 " + config.getQuest())
            ));
            logger.warn("玩家 {} 不在白名单中，已阻止登录。", username);
        } else {
            logger.info("玩家 {} 登录通过白名单验证。", username);
        }
    }
}
