package com.itheima.ws.pojo;

import lombok.Data;

/**
 * @version v1.0
 * @ClassName: Message
 * @Description: 用于封装浏览器发送给服务端的消息数据
 * @Author: 黑马程序员
 */
@Data
public class Message {
    private String toName;
    private String message;
}
