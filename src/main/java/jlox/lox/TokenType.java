package jlox.lox;

/*

When your program is being read, both a variable name like myVariable and a keyword like while look like simple sequences of letters. The quote is saying that the parser (the part that understands the grammar) needs to know immediately if a word is a special keyword or just a regular name.

Let's break it down:

Identifiers vs. Keywords:

An Identifier is a name you, the programmer, create (e.g., age, printName, x).

A Keyword is a word with a special, built-in meaning that controls the program's flow (e.g., if, for, while, return).

The Parser's Job:
The parser looks at a sequence of tokens to figure out what to do. It has rules like, "A while loop must start with the keyword while."

The Problem:
If the scanner is lazy, it might label both myVariable and while with the same generic token type: IDENTIFIER.

Token 1: { type: IDENTIFIER, lexeme: "myVariable" }

Token 2: { type: IDENTIFIER, lexeme: "while" }

This creates extra work for the parser. The parser would have to check every single IDENTIFIER token to see if its text happens to be "while", "if", "for", etc. This is messy and inefficient.

The Solution (What the Quote Means):
The quote argues that the scanner should be smart enough to recognize keywords. When the scanner sees the text "while", it shouldn't just call it an IDENTIFIER. It should check its list of reserved words and assign a more specific token type, like WHILE.

"while" becomes a token like: { type: WHILE, lexeme: "while" }

"myVariable" becomes a token like: { type: IDENTIFIER, lexeme: "myVariable" }

This makes the parser's job much easier. It can simply look at the token's type to understand the grammar, like the quote says: “If the next token is while then do . . . ”. It doesn't have to inspect the text of every identifier.
 */

enum TokenType {

    // single character tokens
    //
    LEFT_PAREN,
    RIGHT_PAREN,
    LEFT_BRACE,
    RIGHT_BRACE,
    COMMA,
    DOT,
    MINUS,
    PLUS,
    SEMICOLON,
    SLASH,
    STAR,

    // one or two character tokens
    //
    BANG, // "!"
    BANG_EQUAL,
    EQUAL,
    EQUAL_EQUAL,
    GREATER,
    GREATER_EQUAL,
    LESS,
    LESS_EQUAL,

    // literals
    //
    IDENTIFIER,
    NUMBER,
    STRING,

    // keywords
    //
    AND,
    CLASS,
    ELSE,
    FALSE,
    FUN,
    FOR,
    IF,
    NIL,
    OR,
    PRINT,
    RETURN,
    THIS,
    SUPER,
    TRUE,
    WHILE,

    EOF
}
