package br.com.trevisan.server;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;


public class TaskDivisor implements Runnable{
    private Socket clientSocket;

    public TaskDivisor(Socket socket) {
        this.clientSocket = socket;
    }

    @Override
    public void run() {
        try {
            System.out.println("Dividing tasks for " + clientSocket.getPort());
            Scanner clientInput = new Scanner(clientSocket.getInputStream()); //get the c1 dispatched from clientTask

            while(clientInput.hasNextLine()) {
                String commandInput = clientInput.nextLine();
                System.out.println(commandInput);
            }
            clientInput.close();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
