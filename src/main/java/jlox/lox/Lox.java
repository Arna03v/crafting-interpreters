package jlox.lox;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
//import java.util.Scanner;

// let's define the lox class
// there are 2 ways to run the interpreter
    // runFile : takes the filepath to read and execute
    // runPrompt :  interactive, we can enter and execute teh code one line at a time

// it’s good engineering practice to separate the code that generates the errors from the code that reports them.

// The lexemes are only the raw substrings of the source code.
// When we take the lexeme and bundle it together with that other data, the result is a token.

public class Lox{

    // to indicate if an error has occured
    static boolean hadError = false;
    public static void main(String[] args) throws IOException{
        if (args.length > 1) {
            System.out.println("Usage: jlox [script]");
            System.exit(64);
        } else if (args.length == 1) {
            runFile(args[0]);
        } else {
            runPrompt();
        }
    }

    // stores the file in a byte[]
    // runs this byte array
    public static void runFile(String file_path) throws IOException {
        Path parsed_file_path = Paths.get(file_path);
        byte[] bytes = Files.readAllBytes(parsed_file_path);

        run(new String(bytes, Charset.defaultCharset())); // takes a string and runs it

        // if an error occurs?
        if(hadError) System.exit(65);
    }

    /*
    * InputStreamReader is a bridge that converts byte streams into character streams.
    * BufferedReader is a wrapper that makes reading characters more efficient.
     *  */
    private static void runPrompt() throws IOException {
        InputStreamReader input = new InputStreamReader(System.in);
        BufferedReader reader = new BufferedReader(input);

        while (true){
            System.out.println("> "); // for indicating the start of the line
            String line = reader.readLine(); // reads a line of input from the user on the command line and returns the result

            // to kill an interactive loop -> ctrl + D; signals a null

            // if the line is nothing
            if(line == null) {
                break;
            }

            // run
            run(line);

            // if the user makes a mistake we shouldn't have to start again
            hadError = false;
        }
    }

    // the run function
    // for now lets print the tokens it is getting to see if its working correctly
    private static void run(String line) {
        // make a list of these tokens
        Scanner sc =  new Scanner(line);
        List<Token> tokens = sc.scanTokens();

        // printing them for now
        for(Token token : tokens) {
            System.out.println(token);
        }
    }

    // we also want the language to include some form of error handling
    static void error(int line_number, String error_message){
        report(line_number, "", error_message);
    }

    // where should point to where in the line the error is occuring!
    // leaving it as a placeholder in as it is a lot of string manipulation :(
    private static void report(int line_number, String where, String error_message){
        System.err.println("[line " + line_number + "] Error" + where + ": " + error_message);
        hadError = true; // a flag to indicate if an error has occured
    }
}