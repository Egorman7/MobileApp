package app.and.mobileapp.API;

/**
 * Created by Egorman on 03.07.2017.
 */

public class LoginRequest {
    private String username;
    private String password;

    public LoginRequest(String username, String password) {
        this.username = username;
        this.password = password;
    }
}
