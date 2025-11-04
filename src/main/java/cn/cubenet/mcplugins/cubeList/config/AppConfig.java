package cn.cubenet.mcplugins.cubeList.config;

public class AppConfig {
    private final String url;
    private final String serviceName;
    private final String quest;

    public AppConfig(String url, String serviceName, String quest) {
        this.url = url;
        this.serviceName = serviceName;
        this.quest = quest;
    }

    public String getUrl() {
        return url;
    }

    public String getServiceName() {
        return serviceName;
    }

    public String getQuest() {
        return quest;
    }
}
