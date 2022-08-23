package com.example.strategy;

import java.util.List;

enum OutputFormat {MARKDOWN, HTML}

interface ListStrategy2 {
    default void start(StringBuilder sb) {
    }

    void addListItem(StringBuilder stringBuilder, String item);

    default void end(StringBuilder sb) {
    }
}

class MarkdownListStrategy2 implements ListStrategy2 {
    @Override
    public void addListItem(StringBuilder stringBuilder, String item) {
        stringBuilder.append(" * ").append(item)
                .append(System.lineSeparator());
    }
}

class HtmlListStrategy2 implements ListStrategy2 {
    @Override
    public void start(StringBuilder sb) {
        sb.append("<ul>").append(System.lineSeparator());
    }

    @Override
    public void addListItem(StringBuilder stringBuilder, String item) {
        stringBuilder.append("  <li>")
                .append(item)
                .append("</li>")
                .append(System.lineSeparator());
    }

    @Override
    public void end(StringBuilder sb) {
        sb.append("</ul>").append(System.lineSeparator());
    }
}

class TextProcessor2 {
    private final StringBuilder sb = new StringBuilder();
    // cannot do this
    // private LS listStrategy = new LS();
    private ListStrategy2 listStrategy;

    public TextProcessor2(OutputFormat format) {
        setOutputFormat(format);
    }

    public void setOutputFormat(OutputFormat format) {
        switch (format) {
            case MARKDOWN -> listStrategy = new MarkdownListStrategy2();
            case HTML -> listStrategy = new HtmlListStrategy2();
        }
    }

    // the skeleton algorithm is here
    public void appendList(List<String> items) {
        listStrategy.start(sb);
        for (String item : items)
            listStrategy.addListItem(sb, item);
        listStrategy.end(sb);
    }

    public void clear() {
        sb.setLength(0);
    }

    @Override
    public String toString() {
        return sb.toString();
    }
}

public class DynamicStrategyDemo {
    public static void main(String[] args) {
        TextProcessor2 tp = new TextProcessor2(OutputFormat.MARKDOWN);
        tp.appendList(List.of("liberte", "egalite", "fraternite"));
        System.out.println(tp);

        tp.clear();
        tp.setOutputFormat(OutputFormat.HTML);
        tp.appendList(List.of("inheritance", "encapsulation", "polymorphism"));
        System.out.println(tp);
    }
}
