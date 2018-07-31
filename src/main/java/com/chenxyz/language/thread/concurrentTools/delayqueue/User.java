package com.chenxyz.language.thread.concurrentTools.delayqueue;

/**
 * 用户信息
 *
 * @author chenxyz
 * @version 1.0
 * @date 2018-07-31
 */
public class User {

    private String id;
    private String name;

    public User(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
