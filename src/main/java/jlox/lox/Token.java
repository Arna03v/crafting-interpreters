package jlox.lox;

/*
*
* We need to store some information about the token
* in our implementation only keep track of where the token occurs
* but in more sophisticated implementation they also keep track of col and row
*
* The lexeme is the raw text from the source code.
* The literal is the actual data value that the text represents
*
* imagine code contains this line
*
* var version = 1.0;
*
* type: NUMBER
* lexeme: "1.0"
* literal: 1.0
* line: 1
*
* For tokens that don't represent a data value, the literal field is null. for example "="
* literal is only used when the lexeme represents a data value
 *  */

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;


public class Token {

    final TokenType type; // is an enum
    final String lexeme;
    final Object literal;
    final int line;

    Token(TokenType type, String lexeme, Object literal, int line) {
        this.type = type;
        this.lexeme = lexeme;
        this.literal = literal;
        this.line = line;
    }

    public String toString() {
        return "Token type: " + type + "-> " + "Lexeme: " + lexeme + "-> " + "Literal: " + literal + "-> " + "Line: " + line;
    }
}
