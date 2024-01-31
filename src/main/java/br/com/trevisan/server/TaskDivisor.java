package br.com.trevisan.server;
import br.com.trevisan.server.commands.C1Command;
import br.com.trevisan.server.commands.C2Command;

import java.io.IOException;
import java.io.PrintStream;
import java.net.Socket;
import java.util.Scanner;
import java.util.concurrent.ExecutorService;


public class TaskDivisor implements Runnable{
    private final Socket clientSocket;

    private final TaskServer server;

    private final ExecutorService serverThreadPool;

    public TaskDivisor(Socket socket, TaskServer server, ExecutorService serverThreadPool) {
        this.clientSocket = socket;
        this.server = server;
        this.serverThreadPool = serverThreadPool;
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
                        C1Command c1 = new C1Command(outputClient); // only will send back confirmations
                        this.serverThreadPool.execute(c1); //get one thread from thread pool and execute
                        break;
                    }
                    case "c2": {
                        outputClient.println("Server received command c2 with success");
                        C2Command c2 = new C2Command(outputClient); // only will send back confirmations
                        this.serverThreadPool.execute(c2); //get one thread from thread pool and execute
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
