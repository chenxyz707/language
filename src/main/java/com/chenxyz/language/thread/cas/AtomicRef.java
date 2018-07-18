package com.chenxyz.language.thread.cas;

import java.util.concurrent.atomic.AtomicReference;

/**
 * 原子更新对象
 *
 * @author chenxyz
 * @version 1.0
 * @date 2018-07-18
 */
public class AtomicRef {

    static AtomicReference<User> userAtomicReference = new AtomicReference<>();

    public static void main(String[] args) {
        User user = new User("Mark",25);
        userAtomicReference.set(user);
        User updateUser = new User("Mike",26);
        userAtomicReference.compareAndSet(user,updateUser);
        System.out.println(userAtomicReference.get().getName());
        System.out.println(userAtomicReference.get().getOld());
    }

    static class User{
        private String name;
        private int old;

        public User(String name, int old) {
            this.name = name;
            this.old = old;
        }

        public String getName() {
            return name;
        }

        public int getOld() {
            return old;
        }
    }
}
