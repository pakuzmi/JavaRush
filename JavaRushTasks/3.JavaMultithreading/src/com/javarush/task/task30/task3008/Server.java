package com.javarush.task.task30.task3008;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketAddress;
import java.sql.SQLOutput;
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

        private void notifyUsers(Connection connection, String userName) throws IOException{
            for (Map.Entry<String, Connection> entry : connectionMap.entrySet()){
                String clientName = entry.getKey();
                if (!clientName.equals(userName)){
                    connection.send(new Message(MessageType.USER_ADDED, clientName));
                }
            }
        }

        private void serverMainLoop(Connection connection, String userName) throws IOException, ClassNotFoundException{
            while(true){
                Message recieveMessage = connection.receive();
                if (recieveMessage.getType() == MessageType.TEXT){
                    String sendMessage = userName + ": " + recieveMessage.getData();
                    sendBroadcastMessage(new Message(MessageType.TEXT, sendMessage));
                } else {
                    ConsoleHelper.writeMessage("Сообщение не является текстом.");
                }
            }
        }

        @Override
        public void run() {
             SocketAddress remoteSocketAddress = socket.getRemoteSocketAddress();
             ConsoleHelper.writeMessage("Установлено соединение с удаленным адресом: " + remoteSocketAddress);
             Connection connection;
             String userName = "";
             try {
                connection = new Connection(socket);
                userName = serverHandshake(connection);
                sendBroadcastMessage(new Message(MessageType.USER_ADDED, userName));
                notifyUsers(connection,userName);
                serverMainLoop(connection, userName);
            } catch (ClassNotFoundException | IOException e) {
                    e.printStackTrace();
            } finally {
                connectionMap.remove(userName);
                sendBroadcastMessage(new Message(MessageType.USER_REMOVED, userName));
            }
        }
    }

    private static Map<String, Connection> connectionMap = new ConcurrentHashMap<>();

    public static void main(String[] args) {
        System.out.println("Для запуска сервера введите номер порта.");
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
