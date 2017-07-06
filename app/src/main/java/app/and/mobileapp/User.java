package app.and.mobileapp;

/**
 * Created by Egorman on 05.07.2017.
 */

public class User {
    private String username;
    private String token;

    public User(String username, String token) {
        this.username = username;
        this.token = token;
    }

    public String getUsername() {
        return username;
    }
    public String getToken() {
        return token;
    }
}
