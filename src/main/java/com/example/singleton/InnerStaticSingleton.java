package com.example.singleton;

public class InnerStaticSingleton {

    private InnerStaticSingleton() {}

    private static class Impl {
        private static final InnerStaticSingleton INSTANCE = new InnerStaticSingleton();
    }

    private InnerStaticSingleton getInstance() {
        return Impl.INSTANCE;
    }
}
