package cn.cubenet.mcplugins.cubeList.command;

import cn.cubenet.mcplugins.cubeList.whitelist.WhitelistManager;
import com.velocitypowered.api.command.SimpleCommand;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import org.slf4j.Logger;

public class ReloadCommand implements SimpleCommand {

    private final Logger logger;
    private final WhitelistManager whitelistManager;

    public ReloadCommand(Logger logger, WhitelistManager whitelistManager) {
        this.logger = logger;
        this.whitelistManager = whitelistManager;
    }

    @Override
    public void execute(Invocation invocation) {
        invocation.source().sendMessage(Component.text("正在重新加载白名单...", NamedTextColor.GREEN));
        whitelistManager.reloadWhitelist();
        invocation.source().sendMessage(Component.text(
                "白名单重新加载完成！当前白名单玩家数量: " + whitelistManager.getAllowListSize(),
                NamedTextColor.GREEN
        ));
        logger.info("执行白名单重载命令。");
    }

    @Override
    public boolean hasPermission(Invocation invocation) {
        return invocation.source().hasPermission("cubelist.reload");
    }
}
