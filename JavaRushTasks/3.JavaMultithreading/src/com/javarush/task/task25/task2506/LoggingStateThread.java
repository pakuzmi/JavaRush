package com.javarush.task.task25.task2506;

public class LoggingStateThread extends Thread{
    Thread thread;
    State state;
    boolean terminate = false;

    @Override
    public void run() {
        while (!terminate) {
            State newState = thread.getState();
            if (newState != state) {
                state = newState;
                System.out.println(newState.name());
                if (state.name().equals("TERMINATED")){
                    terminate = true;
                }
            }
        }
    }

    public LoggingStateThread(Thread thread){
        this.thread = thread;
    }


}
