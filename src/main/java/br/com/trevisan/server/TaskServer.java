package br.com.trevisan.server;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class TaskServer {
    public volatile boolean executing;
    private final ServerSocket server;
    private final ExecutorService threadPool;
    public TaskServer() throws IOException {
        this.executing = true;

        System.out.println("Starting server at port 12345");

        this.server = new ServerSocket(12345);

        //thread pool example
        //ExecutorService threadPool = Executors.newFixedThreadPool(4);
        //my connection pool will have 4 threads available. Only 4 clientSockets can use our application simultaneously

        this.threadPool = Executors.newCachedThreadPool(); //grows dynamically.
    }

    public void execute() throws IOException {
        while (this.executing) {
            try {
                Socket socket = server.accept(); //return a socket, this is like an endpoint
                System.out.println("Client Accepted at port " + socket.getPort());

                TaskDivisor taskDivisor = new TaskDivisor(socket, this);
                threadPool.execute(taskDivisor); // get one thread from threadPool and execute this runnable
            } catch (Exception exception) {
                System.out.println("Server is closed");
            }
        }
    }

    public void stop() throws IOException {
        this.executing = false;
        this.server.close();
        this.threadPool.shutdown(); //await all client threads to end.
    }

    public static void main(String[] args) throws Exception{
        TaskServer server = new TaskServer();
        server.execute();
        server.stop();
    }
}
