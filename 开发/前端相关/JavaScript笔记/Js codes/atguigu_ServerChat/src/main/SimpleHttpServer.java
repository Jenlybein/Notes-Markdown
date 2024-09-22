package src.main;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.jupiter.api.Test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class SimpleHttpServer {

    private static final int PORT = 9090;

    public static void main(String[] args) {
        try (ServerSocket serverSocket = new ServerSocket(PORT)) {
            System.out.println("Server is listening on port " + PORT);
            while (true) {
                Socket socket = serverSocket.accept();
                System.out.println("New Login Request");
                handleRequest(socket);
            }
        } catch (IOException ex) {
            System.out.println("Server exception: " + ex.getMessage());
            ex.printStackTrace();
        }
    }

    private static void handleRequest(Socket socket) {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
             OutputStream outputStream = socket.getOutputStream()) {

            StringBuilder requestBuilder = new StringBuilder();
            String line;
            int contentLength = 0;

            while (!(line = reader.readLine()).isBlank()) {
                requestBuilder.append(line).append("\r\n");
                if (line.startsWith("Content-Length:")) {
                    contentLength = Integer.parseInt(line.split(":")[1].trim());
                }
            }

            String requestBody = readRequestBody(reader, contentLength);

            String username = "";
            String password = "";

            if (!requestBody.isEmpty()) {
                try {
                    JSONObject jsonRequest = new JSONObject(requestBody);
                    username = jsonRequest.getString("username");
                    password = jsonRequest.getString("password");
                } catch (JSONException ex) {
                    System.out.println("Error parsing JSON: " + ex.getMessage());
                }
            }

            JSONObject jsonResponse = new JSONObject();
            if (!username.isEmpty() && !password.isEmpty()) {
                int userId = JDBC_TOOLS.checkUserCredentials(username, password);
                if (userId != -1) {
                    jsonResponse.put("message", "Login successful");

                    JSONArray ja = JDBC_TOOLS.getUserFriends(userId);
                    jsonResponse.put("friends", ja);
                    jsonResponse.put("friend_states", JDBC_TOOLS.getUserFriendsStates(ja));
                    jsonResponse.put("groups", JDBC_TOOLS.getUserGroups(userId));
                } else {
                    jsonResponse.put("message", "Login failed");
                }
            } else {
                jsonResponse.put("message", "Invalid request");
            }

            String responseString = jsonResponse.toString();
            String httpResponse = "HTTP/1.1 200 OK\r\n" +
                    "Access-Control-Allow-Origin: *\r\n" +
                    "Access-Control-Allow-Headers: Content-Type\r\n" +
                    "Content-Type: application/json\r\n" +
                    "Content-Length: " + responseString.length() + "\r\n" +
                    "\r\n" +
                    responseString;

            outputStream.write(httpResponse.getBytes());
            outputStream.flush();

        } catch (IOException | JSONException ex) {
            System.out.println("Error handling request: " + ex.getMessage());
            ex.printStackTrace();
        } finally {
            try {
                if (socket != null) {
                    socket.close();
                }
            } catch (IOException ex) {
                System.out.println("Error closing socket: " + ex.getMessage());
                ex.printStackTrace();
            }
        }
    }

    private static String readRequestBody(BufferedReader reader, int contentLength) throws IOException {
        char[] bodyChars = new char[contentLength];
        int bytesRead = reader.read(bodyChars, 0, contentLength);
        return bytesRead != -1 ? new String(bodyChars, 0, bytesRead) : "";
    }
}
