package com.example.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

class LoggingHandler implements InvocationHandler
{
    private final Object target;
    private Map<String, Integer> calls = new HashMap<>();

    LoggingHandler(Object target)
    {
        this.target = target;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable
    {
        String name = method.getName();

        if (name.contains("toString"))
        {
            return calls.toString();
        }
        if (args != null) {
            System.out.println("Arguments are " + Arrays.toString(args));
        }

        // add or increment number of calls
        calls.merge(name, 1, Integer::sum);
        return method.invoke(target, args);
    }
}

interface Human
{
    void walk();
    void talk();

    void test(Integer count);
}

class Person implements Human
{
    @Override
    public void walk()
    {
        System.out.println("I am walking");
    }

    @Override
    public void talk()
    {
        System.out.println("I am talking");
    }

    @Override
    public void test(Integer count) {
        System.out.println("Argument method");
    }
}

public class DynamicLoggingProxyDemo
{
    @SuppressWarnings("unchecked")
    public static <T> T withLogging(T target, Class<T> itf)
    {
        return (T) Proxy.newProxyInstance(
                itf.getClassLoader(),
                new Class<?>[] { itf },
                new LoggingHandler(target));
    }

    public static void main(String[] args)
    {
        Person person = new Person();
        Human logged = withLogging(person, Human.class);
        logged.walk();
        logged.talk();
        logged.talk();
        logged.test(15);

        System.out.println(logged);
    }
}

