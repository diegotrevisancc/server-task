package br.com.trevisan.server.commands;

import java.io.OutputStream;
import java.io.PrintStream;

public class C1Command implements Runnable{
    private PrintStream output;

    public C1Command(PrintStream clientOutput) {
        this.output = clientOutput;
    }

    @Override
    public void run() {
        System.out.println("Executing c1 command");

        try {
            Thread.sleep(20000);
        } catch (InterruptedException exception) {
            throw new RuntimeException(exception);
        }

        output.println("C1 executed with success");

    }
}
