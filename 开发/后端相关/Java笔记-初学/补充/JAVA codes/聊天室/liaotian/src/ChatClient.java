import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.net.Socket;
import java.util.Scanner;

public class ChatClient {
    public static void main(String[] args) throws Exception{
        // 1.连接服务器
        Socket socket = new Socket("127.0.0.1",8989); // 127.0.0.1是本地回环地址

        // 2.开启两个线程：(1)负责接收服务器转发信息 (2)发送自己的信息
        Receive receive = new Receive(socket);
        Send send = new Send(socket);
        receive.start();
        send.start();

        // 3.发送线程结束，则整个程序结束
        send.join();

        // 4.关闭操作
        socket.close();
    }
}

class Receive extends Thread{
    private final Socket socket;
    public Receive(Socket socket){
        super();
        this.socket = socket;
    }
    public void run(){
        try{
            InputStream inputStream = socket.getInputStream();
            Scanner input = new Scanner(inputStream);

            while (input.hasNextLine())
                System.out.println(input.nextLine());
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
}

class Send extends Thread{
    private final Socket socket;
    public Send(Socket socket){
        super();
        this.socket = socket;
    }
    public void run(){
        try{

            Scanner input = new Scanner(System.in);
            OutputStream outputStream = socket.getOutputStream();
            PrintStream ps = new PrintStream(outputStream);

            while (true){
                System.out.print("自己：");
                String str = input.nextLine();
                if("quit".equals(str))
                    break;
                ps.println(str);
            }

            input.close();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
}