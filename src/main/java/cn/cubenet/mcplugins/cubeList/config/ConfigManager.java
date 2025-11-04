package cn.cubenet.mcplugins.cubeList.config;

import org.spongepowered.configurate.ConfigurationNode;
import org.spongepowered.configurate.yaml.YamlConfigurationLoader;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;

public class ConfigManager {
    private final Path dataDirectory;

    public ConfigManager(Path dataDirectory) {
        this.dataDirectory = dataDirectory;
    }

    public AppConfig load() throws IOException {
        File configFile = new File(dataDirectory.toFile(), "config.yml");
        YamlConfigurationLoader loader = YamlConfigurationLoader.builder()
                .path(configFile.toPath())
                .build();

        ConfigurationNode config = loader.load();

        if (configFile.length() == 0) {
            config.node("URL").set("{YOUR JSON PLAYERLIST URL}");
            config.node("ServiceName").set("{SERVICE NAME}");
            config.node("Quest").set("{OPERATOR INFORMATION}");
            loader.save(config);
            throw new IOException("首次启动：已生成默认配置文件，请编辑 config.yml 后重启。");
        }

        return new AppConfig(
                config.node("URL").getString(""),
                config.node("ServiceName").getString("CubeListService"),
                config.node("Quest").getString("CarlSkyCoding")
        );
    }
}
