package app.and.mobileapp.API;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Egorman on 05.07.2017.
 */

public class CommentResponse {
    @SerializedName("review_id")
    int review_id;

    public CommentResponse(int review_id) {
        this.review_id = review_id;
    }

    public int getReview_id() {
        return review_id;
    }

    public void setReview_id(int review_id) {
        this.review_id = review_id;
    }
}
