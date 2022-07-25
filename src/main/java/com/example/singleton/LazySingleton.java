package com.example.singleton;

public class LazySingleton {

    private static LazySingleton instance;

    private LazySingleton() {
        System.out.println("initializing a laze singleton");
    }

    //performance implication for synchronized
    public static synchronized LazySingleton getInstance() {
        if (instance == null) {
            instance = new LazySingleton();
        }
        return instance;
    }
}
