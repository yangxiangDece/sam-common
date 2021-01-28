package com.yang.spring;

import com.yang.spring.bean.User;

import java.util.ArrayList;
import java.util.List;

public class Test {

    public static void main(String[] args) {
        List<User> users = new ArrayList<>();
        users.add(new User("1","11"));
        users.add(new User("2","22"));
        users.add(new User("3","33"));
        users.add(new User("4","44"));
        SUser sUser = new SUser();
        sUser.setUsers(users);
        buildUsers(sUser.getUsers());
        System.out.println(sUser.getUsers());
    }

    private static void buildUsers(List<User> users) {
        for (User user : users) {
            user.setName("12121212");
        }
    }

    public static class SUser {
        private List<User> users;

        public List<User> getUsers() {
            return users;
        }

        public void setUsers(List<User> users) {
            this.users = users;
        }


    }
}
