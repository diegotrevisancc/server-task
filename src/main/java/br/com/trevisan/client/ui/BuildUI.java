package br.com.trevisan.client.ui;

import java.io.IOException;
import java.net.Socket;

public class BuildUI {

	public static void main(String[] args){

		try(Socket socket = new Socket("localhost", 12345)){
			
			GraphicInterface ig = new GraphicInterface(socket);
			ig.buildInterface();
			ig.printText("Connection Stabilised");
			initializeThreadThatReceivesResponse(socket, ig);
			ig.printText("Close server");
			
		} catch (IOException | InterruptedException e) {
			throw new RuntimeException(e);
		} 

	}

	private static void initializeThreadThatReceivesResponse(Socket socket, GraphicInterface ig)
			throws InterruptedException {
		
		Runnable threadReceiveResponse = new ReceiveResponseTask(socket, ig);
		Thread threadResponse = new Thread(threadReceiveResponse);
		threadResponse.start();
		threadResponse.join(); //main will wait receive response end
	}

}
