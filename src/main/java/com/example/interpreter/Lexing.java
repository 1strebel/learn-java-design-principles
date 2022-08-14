package com.example.interpreter;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

class Token {
    public enum Type {
        INTEGER, PLUS, MINUS, LPAREN, RPAREN
    }

    public Type type;
    public String text;

    public Token(Type type, String text) {
        this.type = type;
        this.text = text;
    }

    @Override
    public String toString() {
        return "`" + text + "`";
    }
}

public class Lexing {

    public static void main(String[] args) {
        String input = "(13+4)-(12+22)";
        List<Token> tokens = lex(input);
        System.out.println(tokens.stream()
                .map(Token::toString)
                .collect(Collectors.joining("\t")));
    }

    static List<Token> lex(String input) {
        List<Token> result = new ArrayList<>();

        for (int i = 0; i < input.length(); i++) {
            switch (input.charAt(i)) {
                case '+' -> result.add(new Token(Token.Type.PLUS, "+"));
                case '-' -> result.add(new Token(Token.Type.MINUS, "-"));
                case '(' -> result.add(new Token(Token.Type.LPAREN, "("));
                case ')' -> result.add(new Token(Token.Type.RPAREN, ")"));
                default -> {
                    StringBuilder sb = new StringBuilder("" + input.charAt(i));
                    for (int j = i + 1; j < input.length(); ++j) {
                        char ch = input.charAt(j);
                        if (Character.isDigit(ch)) {
                            sb.append(ch);
                            ++i;
                        } else {
                            result.add(new Token(Token.Type.INTEGER, sb.toString()));
                            break;
                        }
                    }
                }
            }
        }
        return result;
    }
}
