package app.and.mobileapp.API;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Path;

/**
 * Created by Egorman on 02.07.2017.
 */

public interface HerokuappApi {
    @POST("/api/register/?format=json")
    Call<RegisterResponse> register(@Body RegisterRequest body);
    @POST("api/login/?format=json")
    Call<LoginResponse> login(@Body LoginRequest body);
    @GET("/api/products/?format=json")
    Call<ItemModel[]> getItems();
    @GET("/api/reviews/{id}?format=json")
    Call<CommentModel[]> getComments(@Path("id") int id);
    @POST("api/reviews/{id}?format=json")
    Call<CommentResponse> postComment(@Header("Authorization") String token, @Path("id") String id, @Body CommentRequest body);
}