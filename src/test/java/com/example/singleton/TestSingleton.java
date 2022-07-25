package com.example.singleton;

import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertSame;

public class TestSingleton {

    @Test
    public void isSingletonTest()
    {
        SingletonDatabase db = SingletonDatabase.getInstance();
        SingletonDatabase db2 = SingletonDatabase.getInstance();
        assertSame(db, db2);
        assertEquals(1, SingletonDatabase.getCount());
    }

    @Test
    public void singletonTotalPopulationTest()
    {
        // testing on a live database (integration test)
        SingletonRecordFinder rf = new SingletonRecordFinder();
        List<String> names = List.of("Seoul", "Mexico City");
        int tp = rf.getTotalPopulation(names);
        assertEquals(1200+1992, tp);
    }

    @Test
    public void dependentPopulationTest()
    {
        //configure test database
        DummyDatabase db = new DummyDatabase();
        ConfigurableRecordFinder rf = new ConfigurableRecordFinder(db);
        assertEquals(4, rf.getTotalPopulation(
                List.of("alpha", "gamma")
        ));
    }
}
