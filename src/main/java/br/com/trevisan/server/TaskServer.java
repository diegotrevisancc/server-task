package br.com.trevisan.server;
import java.net.ServerSocket;
import java.net.Socket;

public class TaskServer {
    public static void main(String[] args) throws Exception{
        System.out.println("Starting server at port 12345");

        ServerSocket server = new ServerSocket(12345);
        while (true) {
            Socket socket = server.accept(); //return a socket, this is like an endpoint
            System.out.println("Client Accepted at port " + socket.getPort());
        }
    }
}
