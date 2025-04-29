

### 基础配置

- **`manifest_version`** 一个整数，用于指定清单文件的版本。唯一支持的值是 **3**。

- **`name`** 用于标识扩展程序的字符串，显示在 Chrome 网上应用店、安装对话框以及用户的 Chrome 扩展程序页面（`chrome://extensions`）中。

  - 长度上限为 **75 个字符**。如需本地化，请参考国际化文档。

- **`version`** 用于标识扩展程序版本号的字符串。具体格式请参考版本号规范。

- **`description`** 扩展的简短描述，显示在扩展管理页面。

- **`icons`**: 定义扩展图标，支持多种尺寸（如`48x48`、`128x128`），用于扩展管理页面和工具栏。

  - ```json
    {
        "manifest_version": 3,
        "name": "Minimal Manifest",
        "version": "1.0.0",
        "description": "A basic example extension with only required keys",
        "icons": {
            "48": "images/icon-48.png",
            "128": "images/icon-128.png"
        },
    }
    ```

### 可选键

- **`author`** 指定扩展程序的作者信息。
- **`action`** 定义扩展程序图标在 Chrome 工具栏中的行为，例如点击图标时显示的弹出窗口。
- **`background`** 指定扩展程序的 Service Worker 文件，该文件用于处理事件和后台任务。
- **`content_scripts`** 指定扩展程序在特定网页上运行的 JavaScript 或 CSS 文件。
- **`chrome_settings_overrides`**
  定义扩展程序对 Chrome 设置的覆盖选项。
- **`chrome_url_overrides`** 定义扩展程序对 Chrome 默认页面的替换设置。
- **`commands`** 定义扩展程序中的键盘快捷键。
- **`content_security_policy`** 定义扩展程序的安全策略，限制脚本、样式和其他资源的加载。
- **`declarative_net_request`** 定义扩展程序的静态网络请求规则，用于屏蔽或修改网络请求。
- **`default_locale`** 定义扩展程序的默认语言区域，例如 `"en"` 或 `"pt_BR"`。
- **`devtools_page`** 定义扩展程序的 DevTools 页面，用于调试和开发工具集成。
- **`externally_connectable`** 指定哪些其他网页或扩展程序可以与当前扩展程序通信。
- **`homepage_url`** 指定扩展程序的主页网址。如果未定义，默认为扩展程序的 Chrome 网上应用店页面。
- **`host_permissions`** 列出扩展程序可以访问的网页，使用网址匹配模式。
- **`incognito`** 定义扩展程序在无痕模式下的行为。支持的值包括 `"spanning"`、`"split"` 和 `"not_allowed"`。
- **`minimum_chrome_version`** 定义可以安装扩展程序的最低 Chrome 版本。如果用户的 Chrome 版本低于此值，将显示“不兼容”警告。
- **`oauth2`** 允许扩展程序使用 OAuth 2.0 进行身份验证。
- **`omnibox`** 允许扩展程序在 Chrome 地址栏中注册关键字。
- **`optional_host_permissions`** 声明扩展程序的可选主机权限。
- **`optional_permissions`** 声明扩展程序的可选权限。
- **`options_page`** 指定扩展程序的选项页面路径。
- **`options_ui`** 指定扩展程序的嵌入式选项页面路径。
- **`permissions`** 声明扩展程序所需的权限，例如访问特定 API 或网站。
- **`requirements`** 列出使用扩展程序所需的技术支持。
- **`sandbox`** 定义扩展程序的沙盒页面，用于隔离和安全运行代码。
- **`short_name`** 一个字符串，用于显示扩展程序名称的简短版本。长度上限为 **12 个字符**。如果未定义，将使用 `"name"` 的截断版本。
- **`side_panel`** 指定扩展程序的侧边栏页面路径。
- **`storage`** 声明扩展程序的存储架构。
- **`update_url`** 指定扩展程序的更新页面网址。如果扩展程序托管在 Chrome 网上应用店之外，需要使用此键。
- **`version_name`** 描述扩展程序版本的字符串，例如 `"1.0 beta"` 或 `"build rc2"`。如果未指定，将显示版本号。
- **`web_accessible_resources`** 定义扩展程序中可通过网页或其他工具访问的文件。



#### chrome.action

`chrome.action` API 用于控制 Chrome 浏览器工具栏中的扩展图标。通过此 API，您可以自定义扩展图标的行为、外观以及与用户的交互方式。

| 设置项          | 类型         | 是否必填 | 描述                                                         |
| :-------------- | :----------- | :------- | :----------------------------------------------------------- |
| `default_icon`  | 字典或字符串 | 可选     | 指定扩展程序的默认图标。可以是一个包含不同尺寸（如 16x16、24x24、32x32）的字典，也可以是一个字符串路径，指向单个图标文件。 |
| `default_title` | 字符串       | 可选     | 设置工具栏图标上的默认提示文本，鼠标悬停时显示。             |
| `default_popup` | 字符串       | 可选     | 指定点击图标时显示的弹出式窗口的 HTML 文件路径。             |
| `default_state` | 字符串       | 可选     | 设置扩展程序操作的默认状态，可选值为 `"enabled"` 或 `"disabled"`。 |

建议始终至少添加 `"action"` 和 `"default_icon"` 键，以便拓展程序的图标正常显示。但即使不添加 `"action"` 键，扩展程序也会在工具栏中显示一个默认图标。

##### 方法

- **`setIcon()`**：设置扩展程序的图标。
- **`setBadgeText()`**：设置徽章文本。
- **`setBadgeBackgroundColor()`**：设置徽章背景颜色。
- **`setPopup()`**：设置弹出式窗口的 HTML 文件。
- **`setTitle()`**：设置图标提示。
- **`enable()`** / **`disable()`**：启用或禁用图标。
- **`openPopup()`**：打开弹出式窗口。

##### 事件

- **`onClicked`**：当用户点击工具栏图标时触发。
- **`onUserSettingsChanged`**：当用户设置发生变化时

> 功能与示例：
>
> 1. 基本弹出式窗口
>
>    ```json
>    {
>      "name": "Hello Extensions",
>      "version": "1.0",
>      "manifest_version": 3,
>      "action": {
>        "default_icon": {
>          "16": "images/icon16.png",  // 设置拓展程序各规格的图标
>          "24": "images/icon24.png",
>          "32": "images/icon32.png"
>        },
>        "default_title": "Click Me",  // 鼠标悬停时显示的提示
>        "default_popup": "popup.html" // 点击图标时显示的弹出式窗口
>      }
>    }
>    ```
>
> 2. 在点击时注入内容脚本
>
>    ```json
>    // manifest.json
>    {
>        "name": "Action script injection demo",
>        "version": "1.0",
>        "manifest_version": 3,
>        "action": {
>            "default_title": "Click to show an alert"
>        },
>        "permissions": ["activeTab", "scripting"],
>        "background": {
>            "service_worker": "background.js"
>        }
>    }
>    ```
>
>    ```js
>    // background.js
>    chrome.action.onClicked.addListener((tab) => {
>        chrome.scripting.executeScript({
>            target: {tabId: tab.id},
>            files: ['content.js']
>        });
>    });
>    ```
>
>    ```js
>    // content.js
>    alert('Hello, world!');
>    ```
>
> 3. 通过编程方式动态更新弹出式窗口的内容
>
>    ```js
>    chrome.action.setPopup({ popup: "new_popup.html" });
>    ```
>
> 4. 动态控制图标的状态，默认情况下工具栏图标是启用状态（可点击）。
>
>    ```json
>    chrome.action.disable();  // 禁用全局图标
>    chrome.action.disable(tabId);  // 禁用特定标签页的图标
>    chrome.action.enable();  // 启用全局图标
>    chrome.action.enable(tabId);  // 启用特定标签页的图标
>    ```

详细请看：[chrome.action  | API  | Chrome for Developers](https://developer.chrome.com/docs/extensions/reference/api/action?hl=zh-cn)





