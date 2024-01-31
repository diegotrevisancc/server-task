package br.com.trevisan.server.commands;

import java.io.PrintStream;

public class C2Command implements Runnable{
    private PrintStream output;

    public C2Command(PrintStream clientOutput) {
        this.output = clientOutput;
    }

    @Override
    public void run() {
        System.out.println("Executing c2 command");

        try {
            Thread.sleep(20000);
        } catch (InterruptedException exception) {
            throw new RuntimeException(exception);
        }

        output.println("C2 executed with success");

    }
}
