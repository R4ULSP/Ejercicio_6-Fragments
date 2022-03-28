package es.travelworld.ejercicio6_fragments.tools;

import java.io.Serializable;

public class User implements Serializable {
    private String name;
    private String lastname;
    private String ageGroup;
    private String password;

    public User() {
        this.password = "1234";
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getAgeGroup() {
        return ageGroup;
    }

    public void setAgeGroup(String ageGroup) {
        this.ageGroup = ageGroup;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
