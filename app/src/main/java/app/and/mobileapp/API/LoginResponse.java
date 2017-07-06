package app.and.mobileapp.API;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Egorman on 03.07.2017.
 */

public class LoginResponse {
    @SerializedName("success")
    String success;
    @SerializedName("token")
    String token;
    public LoginResponse(String success, String token) {

        this.success = success;
        this.token = token;
    }
    public String getSuccess() {
        return success;
    }

    public void setSuccess(String success) {
        this.success = success;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
