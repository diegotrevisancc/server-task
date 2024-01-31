package br.com.trevisan.client.ui;

import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

public class ReceiveResponseTask implements Runnable {

	private Socket socket;
	private GraphicInterface graphicInterface;

	public ReceiveResponseTask(Socket socket, GraphicInterface ig) {
		this.socket = socket;
		this.graphicInterface = ig;
	}

	@Override
	public void run() {
		try {
			graphicInterface.printText("Getting server data");
			Scanner serverResponse = new Scanner(socket.getInputStream());

			while (serverResponse.hasNextLine()) {
				String line = serverResponse.nextLine();
				graphicInterface.printText(line);
			}

			serverResponse.close();

		} catch (IOException e) {
			throw new RuntimeException(e);
		}

	}

}
