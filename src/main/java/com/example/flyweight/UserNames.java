package com.example.flyweight;
import com.google.common.base.Function;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class UserNames {
    static class User
    {
        private String fullName;

        public User(String fullName) {
            this.fullName = fullName;
        }
    }

    static class BetterUser
    {
        static List<String> strings = new ArrayList<>();
        private int[] names;

        public BetterUser(String fullName)
        {
            Function<String, Integer> getOrAdd = (String s) -> {
                int idx = strings.indexOf(s);
                if (idx != -1) return idx;
                else {
                    strings.add(s);
                    return strings.size() - 1;
                }
            };

            names = Arrays.stream(fullName.split(" "))
                    .mapToInt(s -> getOrAdd.apply(s)).toArray();
        }

        public String getFullName()
        {
            return Arrays.stream(names).mapToObj(i -> strings.get(i))
                    .collect(Collectors.joining(","));
        }
    }

    static class UsersDemo
    {
        public static void main(String[] args) {
            BetterUser user1 = new BetterUser("John Smith");
            BetterUser user2 = new BetterUser("Jane Smith");
            BetterUser user3 = new BetterUser("John Taylor");

            // have "Smith" in common
        }
    }
}
