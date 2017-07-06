package app.and.mobileapp.API;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Egorman on 05.07.2017.
 */

public class CommentModel {
    @SerializedName("id")
    private int id;
    @SerializedName("product")
    private int product;
    @SerializedName("created_by")
    private CreatedBy created_by;
    @SerializedName("rate")
    private int rate;
    @SerializedName("text")
    private String text;

    public CommentModel(int id, int product, CreatedBy created_by, int rate, String text) {
        this.id = id;
        this.product = product;
        this.created_by = created_by;
        this.rate = rate;
        this.text = text;
    }

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public int getProduct() {
        return product;
    }
    public void setProduct(int product) {
        this.product = product;
    }
    public CreatedBy getCreated_by() {
        return created_by;
    }
    public void setCreated_by(CreatedBy created_by) {
        this.created_by = created_by;
    }
    public int getRate() {
        return rate;
    }
    public void setRate(int rate) {
        this.rate = rate;
    }
    public String getText() {
        return text;
    }
    public void setText(String text) {
        this.text = text;
    }
    public String getUser(){return created_by.getUsername();}
    public int getUserId(){return created_by.getId();}
    class CreatedBy{
        @SerializedName("id")
        int id;
        @SerializedName("username")
        String username;

        public CreatedBy(int id, String username) {
            this.id = id;
            this.username = username;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }
    }
}
