package com.example.singleton;

class ChiefExecOfficer {

    private static String name;
    private static int age;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        ChiefExecOfficer.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        ChiefExecOfficer.age = age;
    }

    @Override
    public String toString() {
        return "ChiefExecOfficer age = " + age + ", name = " + name;
    }

    public static void main(String[] args) {
        ChiefExecOfficer c1 = new ChiefExecOfficer();
        c1.setAge(1);
        c1.setName("C1");

        ChiefExecOfficer c2 = new ChiefExecOfficer();
        c2.setAge(2);
        c2.setName("C2");

        System.out.println(c1);
        System.out.println(c2);
    }
}

