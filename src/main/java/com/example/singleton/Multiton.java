package com.example.singleton;

import java.util.HashMap;
import java.util.Map;

enum Subsystem
{
    PRIMARY,
    AUXILIARY,
    FALLBACK
}

class Printer
{
    private static int instanceCount = 0;

    private Printer()
    {
        instanceCount++;
        System.out.println("A total of " + instanceCount + " instances created so far.");
    }

    private static Map<Subsystem, Printer> instances = new HashMap<>();

    public static Printer get(Subsystem subsystem)
    {
        if (instances.containsKey(subsystem)) {
            return instances.get(subsystem);
        }
        Printer instance = new Printer();
        instances.put(subsystem, instance);
        return instance;
    }
}

public class Multiton {
    public static void main(String[] args) {
        Printer main = Printer.get(Subsystem.PRIMARY);
        Printer aux = Printer.get(Subsystem.AUXILIARY);
        Printer aux2 = Printer.get(Subsystem.AUXILIARY);
    }
}
