# CubeList 🎯

一个白名单 Velocity 服务端插件，支持在线 JSON 文件

## ✨ 特性

- 📡 支持远程 JSON 白名单配置
- ⚡ 实时重载白名单数据
- 🎮 多服务器支持
- 🔄 简单易用的配置

## 📁 配置文件

在 `cubelist/config.yml` 中配置：

```yaml
URL: '你的JSON文件地址'
ServiceName: '服务器名称'
Quest: '咨询人'
```

### 配置说明

| 字段 | 说明 | 示例 |
|------|------|------|
| `URL` | 白名单 JSON 数据地址 | `https://api.server.xd/whitelist.json` |
| `ServiceName` | 服务器标识名称 | `Star Server` |
| `Quest` | 问询者标识 | `CarlSkyCoding` |

## 🔄 重载命令

使用 `cbreload` 命令重新从 URL 获取白名单数据（会重新读取 config.yml）：

```bash
/cbreload
```

## 🚀 快速开始

1. 将插件放入 Velocity 的 plugins 文件夹
2. 重启服务器
3. 编辑 `cubelist/config.yml` 配置你的白名单源（启动一次会自动创建）
4. 启动服务器！

## 📄 JSON 格式要求

你的在线 JSON 文件应该包含玩家白名单数据，例如：

```json
[
  "player1",
  "player2",
  "player3"
]
```

⭐ 如果这个插件对你有帮助，请给我们一个 Star！