package br.com.trevisan.server;

import br.com.trevisan.server.exception.ExceptionHandler;

import java.util.concurrent.ThreadFactory;

public class CustomizedThreadFactory implements ThreadFactory {
    private static int number = 1;
    @Override
    public Thread newThread(Runnable runnable) {
        Thread thread = new Thread(runnable, "Server Task Thread " + number);
        //every thread inside our pool will use this ExceptionHandler
        thread.setUncaughtExceptionHandler(new ExceptionHandler());


        number++;
        return thread;
    }
}
