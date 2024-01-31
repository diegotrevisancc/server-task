package br.com.trevisan.server;
import java.io.IOException;
import java.io.PrintStream;
import java.net.Socket;
import java.util.Scanner;


public class TaskDivisor implements Runnable{
    private Socket clientSocket;

    private TaskServer server;

    public TaskDivisor(Socket socket, TaskServer server) {
        this.clientSocket = socket;
        this.server = server;
    }

    @Override
    public void run() {
        try {
            System.out.println("Dividing tasks for " + clientSocket.getPort());
            Scanner clientInput = new Scanner(clientSocket.getInputStream()); //get the commands dispatched from clientTask
            //we are going to use this variable to send messages to our client
            PrintStream outputClient = new PrintStream(this.clientSocket.getOutputStream());

            while(clientInput.hasNextLine()) {
                String commandInput = clientInput.nextLine();
                System.out.println("Command received " + commandInput);
                switch (commandInput) {
                    case "c1": {
                        outputClient.println("Server received command c1 with success");
                        break;
                    }
                    case "c2": {
                        outputClient.println("Server received command c2 with success");
                        break;
                    }
                    case "end": {
                        outputClient.println("Shutdown server");
                        this.server.stop();
                        break;
                    }
                    default : {
                        outputClient.println("Command not found");
                        break;
                    }
                }
                System.out.println(commandInput);
            }
            clientInput.close();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
