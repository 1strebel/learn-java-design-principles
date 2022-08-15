package com.example.iterator;

import java.util.*;
import java.util.function.Consumer;
import java.util.stream.IntStream;

class SimpleCreature
{
    private int strength, agility, intelligence;

    public int max()
    {
        return Math.max(strength,
                Math.max(agility, intelligence));
    }

    public int sum()
    {
        return strength+agility+intelligence;
    }

    public double average()
    {
        return sum() / 3.0;
    }

    public int getStrength()
    {
        return strength;
    }

    public void setStrength(int strength)
    {
        this.strength = strength;
    }

    public int getAgility()
    {
        return agility;
    }

    public void setAgility(int agility)
    {
        this.agility = agility;
    }

    public int getIntelligence()
    {
        return intelligence;
    }

    public void setIntelligence(int intelligence)
    {
        this.intelligence = intelligence;
    }
}

class Creature implements Iterable<Integer>
{
    private final int [] stats = new int[3];

    private final int STRENGTH = 0;
    private final int AGILITY = 1;
    private final int INTELLIGENCE = 2;

    public int getStrength() { return stats[STRENGTH]; }
    public void setStrength(int value)
    {
        stats[STRENGTH] = value;
    }

    public int getAgility() { return stats[AGILITY]; }
    public void setAgility(int value)
    {
        stats[AGILITY] = value;
    }

    public int getIntelligence() { return stats[INTELLIGENCE]; }
    public void setIntelligence(int value)
    {
        stats[INTELLIGENCE] = value;
    }

    public int sum()
    {
        return IntStream.of(stats).sum();
    }

    public int max()
    {
        return IntStream.of(stats).max().getAsInt();
    }

    public double average()
    {
        return IntStream.of(stats).average().getAsDouble();
    }

    @Override
    public void forEach(Consumer<? super Integer> action)
    {
        for (int x : stats)
            action.accept(x);
    }

    @Override
    public Spliterator<Integer> spliterator()
    {
        return Arrays.spliterator(stats);
    }

    @Override
    public Iterator<Integer> iterator()
    {
        return IntStream.of(stats).iterator();
    }

    IntSummaryStatistics getStat() {
        return IntStream.of(stats).summaryStatistics();
    }
}

class ArrayBackedPropertiesDemo
{
    public static void main(String[] args)
    {
        Creature creature = new Creature();
        creature.setAgility(12);
        creature.setIntelligence(13);
        creature.setStrength(17);
        System.out.println(
                "Creature has a max stat of " + creature.max()
                        + ", total stats of " + creature.sum()
                        + " and an average stat of " + creature.average()
        );

        for (Integer num : creature) {
            System.out.println(num);
        }

        System.out.println(creature.getStat().getMax());
        System.out.println(creature.getStat().getMin());
        System.out.println(creature.getStat().getAverage());
    }
}


