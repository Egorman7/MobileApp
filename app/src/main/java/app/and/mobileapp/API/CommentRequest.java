package app.and.mobileapp.API;

/**
 * Created by Egorman on 05.07.2017.
 */

public class CommentRequest {
    private int rate;
    private String text;

    public CommentRequest(int rate, String text) {
        this.rate = rate;
        this.text = text;
    }
}
