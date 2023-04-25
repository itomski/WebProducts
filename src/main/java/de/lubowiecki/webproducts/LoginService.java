package de.lubowiecki.webproducts;

import de.lubowiecki.webproducts.model.User;

import java.io.Serializable;

public class LoginService implements Serializable {

    private User user;

    public User getUser() {
        return user;
    }

    public void logIn(User user) {
        this.user = user;
    }

    public void logOut() {
        user = null;
    }

    public boolean isLoggedIn() {
        return user != null;
    }
}
