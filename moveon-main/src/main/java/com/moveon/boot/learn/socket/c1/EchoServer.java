package com.moveon.boot.learn.socket.c1;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @ClassName EchoServer
 * @Description TODO
 * @Author huangzh
 * @Date 2024/7/18 15:25
 * @Version 1.0
 */
public class EchoServer {

    private final ServerSocket mServerSocket;

    public EchoServer(int port) throws IOException {
        mServerSocket = new ServerSocket(port);
    }

    public void run() throws IOException {
        Socket client = mServerSocket.accept();
        handleClient(client);
    }

    public void handleClient(Socket socket) throws IOException {
        InputStream is = socket.getInputStream();
        OutputStream os = socket.getOutputStream();
        byte[] buffer = new byte[1024];
        int n;
        while ((n = is.read(buffer)) > 0) {
            os.write(buffer, 0, n);
        }
    }

    public static void main(String[] args) {
        try {
            EchoServer server = new EchoServer(9877);
            server.run();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
