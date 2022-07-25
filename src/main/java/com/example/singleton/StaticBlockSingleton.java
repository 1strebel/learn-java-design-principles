package com.example.singleton;

public class StaticBlockSingleton {

    private StaticBlockSingleton() throws Exception {
        System.out.println("Singleton is initializing");
    }

    private static StaticBlockSingleton instance;

    static {
        try {
            instance = new StaticBlockSingleton();
        } catch (Exception ex) {
            System.err.println("Failed to create singleton");
        }
    }

    public static StaticBlockSingleton getInstance() {
        return instance;
    }
}
