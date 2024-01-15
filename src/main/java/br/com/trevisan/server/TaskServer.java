package br.com.trevisan.server;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class TaskServer {
    public static void main(String[] args) throws Exception{
        System.out.println("Starting server at port 12345");

        ServerSocket server = new ServerSocket(12345);

        //thread pool example
        //ExecutorService threadPool = Executors.newFixedThreadPool(4);
        //my connection pool will have 4 threads available. Only 4 clientSockets can use our application simultaneously

        ExecutorService threadPool = Executors.newCachedThreadPool(); //grows dinamically.

        while (true) {
            Socket socket = server.accept(); //return a socket, this is like an endpoint
            System.out.println("Client Accepted at port " + socket.getPort());

            TaskDivisor taskDivisor = new TaskDivisor(socket);
            threadPool.execute(taskDivisor); // get one thread from threadPool and execute this runnable
        }
    }
}
