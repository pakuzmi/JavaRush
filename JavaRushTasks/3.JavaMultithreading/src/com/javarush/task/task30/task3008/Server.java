package com.javarush.task.task30.task3008;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    private static class Handler extends Thread{
        private Socket socket;

        public Handler(Socket socket){
            this.socket = socket;
        }
    }

    public static void main(String[] args) {
        int serverPort = ConsoleHelper.readInt();

        try {
            ServerSocket socket = new ServerSocket(serverPort);
            System.out.println("Сервер запущен.");
            while (true){
                try {
                    Socket client = socket.accept();
                    if (client != null){
                        Handler handler = new Handler(client);
                        handler.start();
                    }
                } catch (Exception e){
                    socket.close();
                    System.out.println("Ошибка сервера.");
                    break;
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
