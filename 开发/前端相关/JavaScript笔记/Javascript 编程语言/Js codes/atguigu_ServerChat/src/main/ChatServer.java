package src.main;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class ChatServer {
    static ArrayList<Socket> online = new ArrayList<>(); //存储所有在线的客户端

    public static void main(String[] args) throws Exception{
        // 1.启动服务器，绑定端口号
        ServerSocket server =  new ServerSocket(8989);

        // 2.接收多个客户端同时连接
        while (true){
            Socket socket = server.accept();    // accept()接收来自客户端的Socket

            //accept接收到socket才会运行以下代码
            online.add(socket); // 将新连接的客户端添加到online列表中

            // 启动一个线程:获取当前socket中的数据，并分发数据
            MessageHandler mh = new MessageHandler(socket);
            mh.start();
        }
    }
    static class MessageHandler extends Thread{
        private final Socket socket;
        private String ip;

        public MessageHandler(Socket socket){
            super();
            this.socket = socket;
        }

        public void run(){
            try{
                ip = socket.getInetAddress().getHostAddress();
                //插入:给其他客户端转发上线信息
                sendToOther(ip+"上线了");

                // (1)接收该客户端发送的消息
                InputStream input = socket.getInputStream();
                InputStreamReader reader = new InputStreamReader(input);
                BufferedReader br = new BufferedReader(reader);

                String str;
                while((str = br.readLine())!=null){
                    //(2)给其他在线客户端转发
                    sendToOther(ip+"："+str);
                }

                sendToOther(ip+"下线了");

            }catch (IOException e){
                try{
                    sendToOther(ip+"掉线了");
                }catch (IOException e1){
                    e1.printStackTrace();
                }
                finally {
                    online.remove(socket);
                }
            }
        }
        public void sendToOther(String message) throws IOException{
            // 遍历所有在线客户端，逐个转发信息、
            for(Socket on : online){
                OutputStream every = on.getOutputStream();
                PrintStream ps = new PrintStream(every);
                ps.println(message);
            }
        }
    }
}

