package com.example.prototype;

class EmploeeAddress
{
    public String streetAddress, city, country;

    public EmploeeAddress(String streetAddress, String city, String country)
    {
        this.streetAddress = streetAddress;
        this.city = city;
        this.country = country;
    }

    public EmploeeAddress(EmploeeAddress other)
    {
        this(other.streetAddress, other.city, other.country);
    }

    @Override
    public String toString()
    {
        return "Address{" +
                "streetAddress='" + streetAddress + '\'' +
                ", city='" + city + '\'' +
                ", country='" + country + '\'' +
                '}';
    }
}

class Employee
{
    public String name;
    public EmploeeAddress emploeeAddress;

    public Employee(String name, EmploeeAddress emploeeAddress)
    {
        this.name = name;
        this.emploeeAddress = emploeeAddress;
    }

    public Employee(Employee other)
    {
        name = other.name;
        emploeeAddress = new EmploeeAddress(other.emploeeAddress);
    }

    @Override
    public String toString()
    {
        return "Employee{" +
                "name='" + name + '\'' +
                ", address=" + emploeeAddress +
                '}';
    }
}

class CopyConstructorDemo
{
    public static void main(String[] args)
    {
        Employee john = new Employee("John",
                new EmploeeAddress("123 London Road", "London", "UK"));

        //Employee chris = john;
        Employee chris = new Employee(john);

        chris.name = "Chris";
        System.out.println(john);
        System.out.println(chris);
    }
}
