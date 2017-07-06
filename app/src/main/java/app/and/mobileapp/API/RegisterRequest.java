package app.and.mobileapp.API;

/**
 * Created by Egorman on 02.07.2017.
 */

public class RegisterRequest{
    String username;
    String password;

    public RegisterRequest(String username, String password) {
        this.username = username;
        this.password = password;
    }
}