package app.and.mobileapp.API;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Egorman on 05.07.2017.
 */

public class ItemModel {
    @SerializedName("id")
    int id;
    @SerializedName("img")
    String img;
    @SerializedName("text")
    String text;
    @SerializedName("title")
    String title;

    public ItemModel(int id, String img, String text, String title) {
        this.id = id;
        this.img = img;
        this.text = text;
        this.title = title;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
