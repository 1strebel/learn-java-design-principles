package com.example.singleton;

import com.google.common.collect.Iterables;
import org.junit.Test;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertSame;

import java.io.File;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Dictionary;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.List;

/**
 * Tests for singleton in TestSingleton
 */
class Testability
{

}
interface Database
{
    int getPopulation(String name);
}

class SingletonDatabase
{
    private Dictionary<String, Integer> capitals = new Hashtable<>();
    private static int instanceCount = 0;
    public static int getCount() { return instanceCount; }

    private SingletonDatabase()
    {
        instanceCount++;
        System.out.println("Initializing database");

        try {
            Path fullPath = Paths.get(new File("src/main/resources").getPath(),
                    "capitals.txt");
            List<String> lines = Files.readAllLines(fullPath);

            Iterables.partition(lines, 2)
                    .forEach(kv -> capitals.put(
                            kv.get(0).trim(),
                            Integer.parseInt(kv.get(1))
                    ));
        }
        catch (Exception e)
        {
            // handle it!
            System.err.println(e);
        }
    }

    private static final SingletonDatabase INSTANCE = new SingletonDatabase();

    public static SingletonDatabase getInstance()
    {
        return INSTANCE;
    }

    public int getPopulation(String name)
    {
        return capitals.get(name);
    }
}

class SingletonRecordFinder
{
    public int getTotalPopulation(List<String> names)
    {
        int result = 0;
        for (String name : names)
            result += SingletonDatabase.getInstance().getPopulation(name);
        return result;
    }
}

class ConfigurableRecordFinder
{
    private Database database;

    //using dependency inversion
    public ConfigurableRecordFinder(Database database) {
        this.database = database;
    }

    public int getTotalPopulation(List<String> names)
    {
        int result = 0;
        for (String name : names)
            result += database.getPopulation(name);
        return result;
    }
}

class SingletonTestabilityDemo
{
    public static void main(String[] args) {
        SingletonDatabase db = SingletonDatabase.getInstance();

        String city = "Tokyo";
        int pop = db.getPopulation(city);
        System.out.printf("%s has population %d%n", city, pop);
    }
}

class DummyDatabase implements Database
{
    private Dictionary<String, Integer> data = new Hashtable<>();

    public DummyDatabase() {
        data.put("alpha", 1);
        data.put("beta", 2);
        data.put("gamma", 3);
    }

    @Override
    public int getPopulation(String name)
    {
        return data.get(name);
    }
}
