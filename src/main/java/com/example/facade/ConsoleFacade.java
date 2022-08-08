package com.example.facade;

import java.util.ArrayList;
import java.util.List;

public class ConsoleFacade {

    static class Buffer {
        private char[] characters;
        private int lineWidth;

        public Buffer(int lineHeight, int lineWidth) {
            this.lineWidth = lineWidth;
            characters = new char[lineHeight * lineWidth];
        }

        public char charAt(int x, int y) {
            return characters[y*lineWidth + x];
        }
    }

    static class ViewPort {
        private final Buffer buffer;
        private final int width;
        private final int height;
        private final int offsetX;
        private final int offsetY;

        public ViewPort(Buffer buffer, int width, int height, int offsetX, int offsetY) {
            this.buffer = buffer;
            this.width = width;
            this.height = height;
            this.offsetX = offsetX;
            this.offsetY = offsetY;
        }

        public char charAt(int x, int y) {
            return buffer.charAt(x + offsetX, y + offsetY);
        }
    }

    static class Console {
        private List<ViewPort> viewPortList = new ArrayList<>();
        int width, height;

        public Console(int width, int height) {
            this.height = height;
            this.width = width;
        }

        public void addViewport(ViewPort viewPort) {
            viewPortList.add(viewPort);
        }

        public static Console newConsole(int width, int height) {
            Buffer buffer = new Buffer(30, 20);
            ViewPort viewPort = new ViewPort(buffer, 30, 20, 0, 0);
            Console console = new Console(30, 20);
            console.addViewport(viewPort);
            return console;
        }

        public void render() {
            for (int y = 0; y < height; ++y) {
                for (int x = 0; x < width; ++x) {
                    for (ViewPort viewPort : viewPortList) {
                        System.out.println(viewPort.charAt(x, y));
                    }
                }
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        Buffer buffer = new Buffer(30, 20);
        ViewPort viewPort = new ViewPort(buffer, 30, 20, 0, 0);
        Console console = new Console(30, 20);
        console.addViewport(viewPort);
        console.render();

        Console console1 = Console.newConsole(30, 20);
        console1.render();
    }
}
