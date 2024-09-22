package src.main;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;
import org.json.JSONObject;

public class SimpleServer {
    static Map<String, Socket> onlineClients = new HashMap<>(); // 存储所有在线的客户端，使用用户名作为 key

    public static void main(String[] args) throws Exception {
        ServerSocket server = new ServerSocket(8989);
        System.out.println("服务器已启动，等待客户端连接...");

        while (true) {
            Socket socket = server.accept();
            System.out.println("客户端连接成功：" + socket);

            // 处理身份信息
            BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            String identityJson = reader.readLine(); // 从客户端读取身份信息（JSON格式）
            System.out.println(identityJson);
            if (identityJson != null && !identityJson.isEmpty()) {
                try {
                    JSONObject identityObject = new JSONObject(identityJson);
                    String username = identityObject.getString("username"); // 假设 JSON 中有一个 "username" 字段

                    if (username != null && !username.isEmpty()) {
                        onlineClients.put(username, socket); // 将客户端添加到在线列表中
                        System.out.println("用户 " + username + " 上线了");

                        // 启动一个线程处理该客户端的消息
                        MessageHandler mh = new MessageHandler(socket, username);
                        mh.start();
                    } else {
                        System.out.println("无效的用户名，拒绝连接：" + socket);
                        socket.close();
                    }
                } catch (Exception e) {
                    System.out.println("解析身份信息失败，拒绝连接：" + socket);
                    e.printStackTrace();
                    socket.close();
                }
            } else {
                System.out.println("无效的身份信息，拒绝连接：" + socket);
                socket.close();
            }
        }
    }

    static class MessageHandler extends Thread {
        private final Socket socket;
        private final String identity;
        private String ip;

        public MessageHandler(Socket socket, String identity) {
            super();
            this.socket = socket;
            this.identity = identity;
        }

        public void run() {
            try {
                ip = socket.getInetAddress().getHostAddress();
                BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                PrintWriter pw = new PrintWriter(socket.getOutputStream(), true);

                String str;
                while ((str = br.readLine()) != null) {
                    // 根据接收到的消息中的目标用户进行转发
                    JSONObject identityObject = new JSONObject(str);
                    String targetUser = identityObject.getString("targetUser");
                    String message = identityObject.getString("message");

                    if (!targetUser.isEmpty() && onlineClients.containsKey(targetUser)) {
                        Socket targetSocket = onlineClients.get(targetUser);
                        PrintWriter targetPw = new PrintWriter(targetSocket.getOutputStream(), true);

                        // 构建JSON消息
                        JSONObject jsonMessage = new JSONObject();
                        jsonMessage.put("friend_name", identity);
                        jsonMessage.put("message", message);

                        targetPw.println(jsonMessage.toString());
                    }
                }

            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                onlineClients.remove(identity);
                System.out.println("用户 " + identity + " 下线了");
                try {
                    socket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
