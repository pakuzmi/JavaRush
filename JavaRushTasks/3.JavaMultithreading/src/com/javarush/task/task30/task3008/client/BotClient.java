package com.javarush.task.task30.task3008.client;

import com.javarush.task.task30.task3008.ConsoleHelper;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class BotClient extends Client{
    public class BotSocketThread extends SocketThread{
        @Override
        protected void clientMainLoop() throws IOException, ClassNotFoundException {
            sendTextMessage("Привет чатику. Я бот. Понимаю команды: дата, день, месяц, год, время, час, минуты, секунды.");
            super.clientMainLoop();
        }

        @Override
        protected void processIncomingMessage(String message) {
            ConsoleHelper.writeMessage(message);
            if (!message.contains(":")){
                return;
            }
            String[] messageGroup = message.split(": ");
            String userName = messageGroup[0];
            String userMessage = messageGroup[1];
            Calendar calendar = new GregorianCalendar();
            String dateFormatted = "";
            switch (userMessage) {
                case "дата": {
                    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("d.MM.YYYY");
                    dateFormatted = simpleDateFormat.format(calendar.getTime());
                    break;
                }
                case "день": {
                    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("d");
                    dateFormatted = simpleDateFormat.format(calendar.getTime());
                    break;
                }
                case "месяц": {
                    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MMMM");
                    dateFormatted = simpleDateFormat.format(calendar.getTime());
                    break;
                }
                case "год": {
                    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("YYYY");
                    dateFormatted = simpleDateFormat.format(calendar.getTime());
                    break;
                }
                case "время": {
                    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("H:mm:ss");
                    dateFormatted = simpleDateFormat.format(calendar.getTime());
                    break;
                }
                case "час": {
                    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("H");
                    dateFormatted = simpleDateFormat.format(calendar.getTime());
                    break;
                }
                case "минуты": {
                    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("m");
                    dateFormatted = simpleDateFormat.format(calendar.getTime());
                    break;
                }
                case "секунды": {
                    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("s");
                    dateFormatted = simpleDateFormat.format(calendar.getTime());
                    break;
                }
                default:{
                    return;
                }
            }
            sendTextMessage(String.format("Информация для %s: %s", userName, dateFormatted));
        }
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
