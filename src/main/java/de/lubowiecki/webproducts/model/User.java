package de.lubowiecki.webproducts.model;

import jakarta.persistence.*;

import java.io.Serializable;

@Entity
@Table(name = "AppUser") // Bei H2 kann die Tabelle nicht USER oder USERS heißen
public class User implements Serializable {

    @Id
    @GeneratedValue
    private int id;

    @Column(length = 50, unique = true)
    private String email;

    @Column(length = 100)
    private String password;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
