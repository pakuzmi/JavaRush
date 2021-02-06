package com.javarush.task.task30.task3008.client;

public class BotClient extends Client{
    public class BotSocketThread extends SocketThread{

    }

    @Override
    protected SocketThread getSocketThread() {
        return new BotSocketThread();
    }

    @Override
    protected boolean shouldSendTextFromConsole() {
        return false;
    }

    @Override
    protected String getUserName() {
        int number = (int) (Math.random() * 100);
        String result = "date_bot_" + number;
        return result;
    }

    public static void main(String[] args) {
        BotClient botClient = new BotClient();
        botClient.run();
    }
}
