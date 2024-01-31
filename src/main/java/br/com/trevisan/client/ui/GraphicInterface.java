package br.com.trevisan.client.ui;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.PrintStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class GraphicInterface {

	private JFrame jWindow;
	private  JTextArea jTextField;
	private final PrintStream output;
	
	public GraphicInterface(Socket socket) throws IOException {
		this.output = new PrintStream(socket.getOutputStream());
	}

	public void buildInterface() {
		buildWindow();
		buildButtons();
		buildTextField();
		showWindow();
	}
	
	public void printText(String text) {
		String line = System.getProperty("line.separator");
		jTextField.append(text + line);
	}

	private void buildButtons() {
		JPanel container = new JPanel();
		container.setBorder(BorderFactory.createTitledBorder("Commands"));

		GridLayout layout = new GridLayout(0, 4, 20, 20);
		container.setLayout(layout);
		
		List<JButton> buttons = create4Buttons();
		for (JButton button : buttons) {
			container.add(button);
		}
		jWindow.add(container);
	}

	private void buildTextField() {
		JPanel container = new JPanel();
		container.setBorder(BorderFactory.createTitledBorder("Server Response"));
		BoxLayout layout = new BoxLayout(container, BoxLayout.Y_AXIS);
		container.setLayout(layout);
		container.add(createResponseField());
		jWindow.add(container);
	}

	private void showWindow() {
		jWindow.setLayout(new GridLayout(2, 1));
		jWindow.pack();
		jWindow.setVisible(true);
	}

	private void buildWindow() {
		jWindow = new JFrame("Client Server-Task");
		jWindow.setMinimumSize(new Dimension(600, 400));
		jWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	private List<JButton> create4Buttons() {
		
		List<JButton> buttons = new ArrayList<>();
		
		for (int i = 0; i < 3; i++) { //3 commands
			
			final int commandNumber = i + 1;
			final JButton button = new JButton("Send c" + commandNumber);
			final String command = "c" + commandNumber;

			button.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					output.println(command);
				}
			});
			buttons.add(button);
		}

		JButton button = new JButton("Kill Server");
		button.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				output.println("good-bye");
			}
		});
		buttons.add(button);

		return buttons;
	}

	private JScrollPane createResponseField() {
		jTextField = new JTextArea();
		jTextField.setLineWrap(true);
		JScrollPane scroll = new JScrollPane(jTextField);

		scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		return scroll;
	}


}
