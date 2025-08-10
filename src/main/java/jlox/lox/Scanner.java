package jlox.lox;

import java.util.ArrayList;
import java.util.List;

import static jlox.lox.TokenType.*;

/*
*
* The core of the scanner is a loop
* Starting at the first character of the source code, the scanner figures
* out what lexeme the character belongs to, and consumes it and any following
* characters that are part of that lexeme. When it reaches the end of that lexeme, it emits a token.
*
* Then it loops back and does it again, starting from the very next character in the source code
*
*
* takes in a string and produces a list of tokens from it
 *  */
class Scanner {
    private final String source;
    private final List<Token> tokens = new ArrayList<>();

    private int start = 0; // first character of the lexeme being scanned
    private int current = 0; // which character of the lexeme is being scanned
    private int line = 1; // on which line

    // constructor
    Scanner(String source) {
        this.source = source;
    }

    // now we need to scan the tokens
    List<Token> scanTokens() {
        // can use a simple boolean check as well but we might have to use this check elsewhere
        // plus better naming always helps
        while (!isAtEnd()){
            // we are at the beginning of the next lexeme
            start = current;
            scanToken();
        }

        // now we have finished scanning all the tokens
        // add the EOF token
        tokens.add(new Token(TokenType.EOF, "", null, line));
        return tokens;
    }

    private boolean isAtEnd() {
        return current >= source.length();
    }

    /*
    *
    * contain the logic to look at the current character,
    * figure out what kind of token it is (e.g., a number, a + sign, a keyword),
    * and consume all the characters that belong to it.
    *
    *  */
    private void scanToken() {
        // lets start simple
        // assuking each token has only one lexeme
        // which is true for many lexemes

        char c = advance(); // gets the current character and advances the pointer
        switch (c){
            case '(': addToken(LEFT_PAREN); break;
            case ')': addToken(RIGHT_PAREN); break;
            case '{': addToken(LEFT_BRACE); break;
            case '}': addToken(RIGHT_BRACE); break;
            case ',': addToken(COMMA); break;
            case '.': addToken(DOT); break;
            case '-': addToken(MINUS); break;
            case '+': addToken(PLUS); break;
            case ';': addToken(SEMICOLON); break;
            case '*': addToken(STAR); break;

            // what if there is a character that is not supported by our interpreter
            default:
                Lox.error(line, "Unexpected character -> " + c + ".");
                break;
        }


    }

    // returns the current character and advances the pointer to the next character
    private char advance() {
        char res = source.charAt(current);
        current++;

        return res;
    }

    private void addToken(TokenType type) {
        addToken(type, null); // single character tokens that we have implemented till now do not represent any value
    }

    private void addToken(TokenType type, String value) {
        String text = source.substring(start, current); // get the entire lexeme being processed currently
        tokens.add(new Token(type, text, value, line));
    }
}