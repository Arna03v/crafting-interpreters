package com.craftinginterpreters.lox;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class Lox {
    public static void main(String[] args) throws IOException {
        if (args.length > 1) {
            System.out.println("Usage: jlox [script]");
            System.exit(64);
        } else if (args.length == 1) {
            runFile(args[0]);
        } else {
            runPrompt();
        }
    }

    // to read the file path from the argument provided to it when run using the command line
    private static void runFile(String filepath) throws IOException {
        byte[] bytes = Files.readAllBytes(Paths.get(filepath)); // reads all the bytes of the file
        run(new String(bytes, Charset.defaultCharset()));
    }

    // if we want to run it withu=out the command line argument,
    // a prompt will pop up
    // enter and execute one line of code at a time
    private static void runPrompt() throws IOException{
        InputStreamReader input = new InputStreamReader(System.in); // reads bytes and converts them into characters
        BufferedReader reader = new BufferedReader(input); // reads the characters and buffers them for better IO perf

        System.out.println("Welcome to Lox!");
        System.out.println("To exit, press Ctrl + D"); // signals end of file to the infinite loop
        System.out.println("Enter your code below: ");

        // infinite loop, same as while(true)
        for(;;){
            System.out.print("> ");
            String line = reader.readLine(); // reads the line from the prompt

            if(line == null) break; // if the line is null, break the loop
            run(line); // run the line
        }
    }

    // the run function whihc is being called by both runFile and runPrompt
    private static void run(String source){
        Scanner scanner = new Scanner(source);
        List<Token> tokens = scanner.scanTokens();

        // For now, just print the tokens.
        for (Token token : tokens) {
            System.out.println(token);
        }
    }
}