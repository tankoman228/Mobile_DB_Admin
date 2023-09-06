package com.example.mobiledbadmin;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.Socket;

class Connection  {

    public static String host = "your.server.ip.address";
    public static String Output = "";


    private static Socket socket; //Connection socket
    private static InputStream in;
    private static OutputStream out;


    //try to connect, returns true if successfully
    public static boolean try_connection() {

        try {

            socket = new Socket(host, 22);
            in = socket.getInputStream();
            out = socket.getOutputStream();

            // Читаем ответ сервера
            Output = readResponse(in);

            return socket.isConnected();

        } catch (IOException e) {
            //e.printStackTrace();
            return false;
        }
    }

    public static void close_connection()  {
        try {
            socket.close();
        } catch (IOException e) {

        }
    }

    private static String readResponse(InputStream in) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(in));
        StringBuilder response = new StringBuilder();

        String line;
        while ((line = reader.readLine()) != null) {
            response.append(line).append("\n");
            if (line.endsWith("$ ")) {
                break;
            }
        }

        return response.toString();
    }

    public static void sendCommand(String command) throws IOException {
        out.write((command + "\r\n").getBytes());
        out.flush();
    }
}
