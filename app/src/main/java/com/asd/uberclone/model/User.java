package com.asd.uberclone.model;

public class User {
    private String id;
    private String name;
    private String email;

    public User(){}

    public User(String id, String name, String email){
        this.id = id;
        this.name = name;
        this.email = email;
    }
    public User(String name, String email){
        this.name = name;
        this.email = email;
    }

    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
