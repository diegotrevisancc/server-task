package br.com.trevisan.client;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;
import java.net.Socket;
import java.util.Scanner;

public class ClientTask {
    public static void main(String[] args) throws IOException, InterruptedException {
        Socket socket = new Socket("localhost", 12345);
        System.out.println("Connection Stabilised");


        Thread threadSendCommandFromClient = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    PrintStream output = new PrintStream(socket.getOutputStream());
                    output.println("c1"); //send this to the server
                    Scanner clientInput = new Scanner(System.in);

                    while(clientInput.hasNext()) {
                        String line = clientInput.nextLine();
                        if (line.trim().equals("exit")) {
                            break;
                        }
                        output.println(line);
                    }
                    output.close();
                    clientInput.close();
                } catch (IOException exception) {
                    throw new RuntimeException(exception);
                }
            }
        });


        Thread threadServerResponse = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    // receive data from serversocket
                    System.out.println("Getting Data from ServerSocket");
                    Scanner serverResponse = new Scanner(socket.getInputStream());
                    while(serverResponse.hasNext()) {
                        String line = serverResponse.nextLine();
                        if (line.trim().equals("exit")) {
                            break;
                        }
                        System.out.println(line);
                    }


                    serverResponse.close();
                } catch (Exception exception) {
                    throw new RuntimeException(exception);
                }

            }
        });

        //we have to execute both threads before close the socket
        threadSendCommandFromClient.start();
        threadServerResponse.start();
        /**
         * Using join command, we can join two threads in one. So in this way, the main Thread will await to serverResponse thread
         * finish to resume it execution, and we will have certainty the socket will not close before it is necessary.
         */
        threadServerResponse.join();
        socket.close();

    }
}
