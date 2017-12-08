package com.victorminerva.app.firebaserealtimedb.model;

/**
 * Created by victo on 07/12/2017.
 */

public class Favored {

    private String favoredID;
    private String name;
    private String email;
    private Long numberPhone;

    public String getFavoredID() {
        return favoredID;
    }

    public void setFavoredID(String favoredID) {
        this.favoredID = favoredID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Long getNumberPhone() {
        return numberPhone;
    }

    public void setNumberPhone(Long numberPhone) {
        this.numberPhone = numberPhone;
    }

    @Override
    public String toString() {
        return "Favored{" +
                "favoredID='" + favoredID + '\'' +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", numberPhone=" + numberPhone +
                '}';
    }
}
