package com.example;

import java.io.BufferedReader;  // 导入用于读取输入流的类
import java.io.IOException;  // 导入用于处理IO异常的类
import java.io.InputStreamReader;  // 导入用于将字节输入流转换为字符输入流的类
import java.io.OutputStream;  // 导入用于写入输出流的类
import java.net.ServerSocket;  // 导入用于创建服务器套接字的类
import java.net.Socket;  // 导入用于创建套接字的类

public class SimpleHttpServer {
    public static void main(String[] args) {
        int port = 8080;  // 定义服务器监听的端口号

        // 创建一个服务器套接字，监听指定端口
        try (ServerSocket serverSocket = new ServerSocket(port)) {
            System.out.println("Server is listening on port " + port);

            // 无限循环，持续监听客户端连接
            while (true) {
                // 接受客户端连接，返回一个新的套接字对象
                Socket socket = serverSocket.accept();
                System.out.println("New client connected");

                // 读取客户端请求
                BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                StringBuilder requestBuilder = new StringBuilder();
                String line;
                int contentLength = 0;

                // 读取请求头，直到遇到空行
                while (!(line = reader.readLine()).isBlank()) {
                    requestBuilder.append(line + "\r\n");
                    // 提取 Content-Length 值
                    if (line.startsWith("Content-Length:")) {
                        contentLength = Integer.parseInt(line.split(":")[1].trim());
                    }
                }

                // 读取请求主体
                char[] bodyChars = new char[contentLength];
                reader.read(bodyChars, 0, contentLength);
                String requestBody = new String(bodyChars);

                //System.out.println("Request: " + requestBuilder.toString());
                System.out.println("Request Body: " + requestBody);

                // 解析请求数据
                String[] parts = requestBody.split("=");
                
                String response = "Invalid request";  // 默认响应消息
                if (parts.length == 2) {
                    String username = parts[0];
                    String password = parts[1];

                    // 简单认证逻辑
                    boolean isAuthenticated = authenticate(username, password);

                    response = isAuthenticated ? "Login successful" : "Login failed";
                }

                // 构建 HTTP 响应
                String httpResponse = "HTTP/1.1 200 OK\r\n" +
                                      "Access-Control-Allow-Origin: *\r\n" +  // 允许跨域请求
                                      "Content-Type: text/plain\r\n" +  // 设置响应内容类型
                                      "Content-Length: " + response.length() + "\r\n" +  // 设置响应内容长度
                                      "\r\n" +  // 空行，表示响应头结束
                                      response;  // 响应内容

                // 发送响应到客户端
                OutputStream outputStream = socket.getOutputStream();
                outputStream.write(httpResponse.getBytes());
                outputStream.flush();

                // 关闭套接字
                socket.close();
            }
        } catch (IOException ex) {
            // 处理服务器异常
            System.out.println("Server exception: " + ex.getMessage());
            ex.printStackTrace();
        }
    }

    // 简单的认证方法，检查用户名和密码是否匹配
    private static boolean authenticate(String username, String password) {
        return "admin".equals(username) && "password".equals(password);
    }
}
