package com.example.interpreter;

import java.util.List;
import java.util.stream.Collectors;

import static com.example.interpreter.Lexing.lex;

interface Element {
    int eval();
}
class IntegerInput implements Element {
    private int value;

    public IntegerInput(int value) {
        this.value = value;
    }

    @Override
    public int eval() {
        return value;
    }
}
class BinaryOperation implements Element {

    public enum Type {
        ADDITION,
        SUBTRACTION
    }

    public Type type;
    public Element left, right;

    @Override
    public int eval() {
        return switch (type) {
            case ADDITION -> left.eval() + right.eval();
            case SUBTRACTION -> left.eval() - right.eval();
        };
    }
}

public class Parsing {

    public static void main(String[] args) {
        String exp1 = "(13+4)-(12+22)";
        System.out.println(exp1 + " = " + parse(lex(exp1)).eval());

        String exp2 = "13+(21-12)";
        System.out.println(exp2 + " = " + parse(lex(exp2)).eval());
    }

    static Element parse(List<Token> tokens) {
        BinaryOperation result = new BinaryOperation();
        boolean haveLHS = false;

        for (int i = 0; i < tokens.size(); ++i) {
            Token token = tokens.get(i);
            switch (token.type) {
                case INTEGER -> {
                    IntegerInput integer = new IntegerInput(Integer.parseInt(token.text));
                    if (!haveLHS) {
                        result.left = integer;
                        haveLHS = true;
                    } else {
                        result.right = integer;
                    }
                }
                case PLUS -> result.type = BinaryOperation.Type.ADDITION;
                case MINUS -> result.type = BinaryOperation.Type.SUBTRACTION;
                case LPAREN -> {
                    int j = i; //location of rparen
                    for (; j < tokens.size(); ++j) {
                        if (tokens.get(j).type == Token.Type.RPAREN) {
                            break;
                        }
                    }
                    List<Token> subExpression = tokens.stream()
                            .skip(i + 1)
                            .limit(j - i - 1)
                            .collect(Collectors.toList());
                    Element element = parse(subExpression);
                    if (!haveLHS) {
                        result.left = element;
                        haveLHS = true;
                    } else {
                        result.right = element;
                    }
                    i = j++;
                }
            }
        }
        return result;
    }
}
