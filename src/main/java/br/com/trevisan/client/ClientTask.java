package br.com.trevisan.client;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;
import java.net.Socket;
import java.util.Scanner;

public class ClientTask {
    public static void main(String[] args) throws IOException {
        Socket socket = new Socket("localhost", 12345);
        System.out.println("Connection Stabilised");

        PrintStream output = new PrintStream(socket.getOutputStream());
        output.println("c1"); //send this to server

        Scanner clientInput = new Scanner(System.in); //just pause the thread
        clientInput.nextLine();

        output.close();
        clientInput.close();
        socket.close();

    }
}
