package br.com.trevisan.server.exception;

public class ExceptionHandler implements Thread.UncaughtExceptionHandler {
    @Override
    public void uncaughtException(Thread thread, Throwable throwable) {
        System.out.println("Caught exception on " + thread.getName() + ", " + throwable.getMessage());
    }
}
