package com.javarush.task.task30.task3008;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class Server {
    private static class Handler extends Thread{
        private Socket socket;
        public Handler(Socket socket){
            this.socket = socket;
        }

        private String serverHandshake(Connection connection) throws IOException, ClassNotFoundException{
            Message requestMessage = new Message(MessageType.NAME_REQUEST);
            String userName;
            while (true){
                connection.send(requestMessage);
                Message recieveMessage = connection.receive();
                if (recieveMessage.getType().equals(MessageType.USER_NAME)){
                    userName = recieveMessage.getData();
                    if (userName != null && !userName.equals("") && !connectionMap.containsKey(userName)){
                        connectionMap.put(userName, connection);
                        connection.send(new Message(MessageType.NAME_ACCEPTED));
                        break;
                    }
                }
            }
            return  userName;
        }
    }

    private static Map<String, Connection> connectionMap = new ConcurrentHashMap<>();

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

    public static void sendBroadcastMessage(Message message){
        for (Map.Entry<String, Connection> pair : connectionMap.entrySet()){
            try {
                pair.getValue().send(message);
            } catch (IOException e) {
                System.out.println("Не удалось отправить сообщение");
            }
        }
    }
}
