package br.com.trevisan.server;
import java.net.Socket;


public class TaskDivisor implements Runnable{
    private Socket clientSocket;

    public TaskDivisor(Socket socket) {
        this.clientSocket = socket;
    }

    @Override
    public void run() {
        System.out.println("Dividing tasks for " + clientSocket.getPort());
        try {
            Thread.sleep(20000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
