package br.com.trevisan.client;

import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

public class ClientTask {
    public static void main(String[] args) throws IOException {
        Socket socket = new Socket("localhost", 12345);
        System.out.println("Connection Stabilised");

        Scanner clientInput = new Scanner(System.in);

        clientInput.nextLine();
        socket.close();

    }
}
