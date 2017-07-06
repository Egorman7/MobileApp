package app.and.mobileapp;

import android.app.Application;
import android.provider.UserDictionary;

import app.and.mobileapp.API.HerokuappApi;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Egorman on 02.07.2017.
 */

public class App extends Application {
    private Retrofit retrofit;
    private static HerokuappApi herokuappApi;
    private static User user;
    @Override
    public void onCreate() {
        super.onCreate();
        retrofit = new Retrofit.Builder().baseUrl("http://smktesting.herokuapp.com/")
        .addConverterFactory(GsonConverterFactory.create())
        .build();
        herokuappApi = retrofit.create(HerokuappApi.class);
        user = null;
    }
    public static HerokuappApi getApi(){return herokuappApi;}
    public static User getUser(){return user;}
    public static void setUser(User nuser){user=nuser;}
    public static boolean isLogged(){return user!=null;}
}
