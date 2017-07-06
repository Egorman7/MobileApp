package app.and.mobileapp;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import app.and.mobileapp.API.HerokuappApi;
import app.and.mobileapp.API.RegisterRequest;
import app.and.mobileapp.API.RegisterResponse;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class RegisterActivity extends Activity {

    private Button mRegisterButton, mBackButton;
    private EditText mUsername, mPassword;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        mUsername = (EditText)findViewById(R.id.register_username);
        mPassword = (EditText)findViewById(R.id.register_password);
        mRegisterButton = (Button)findViewById(R.id.register_button);
        mRegisterButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mUsername.getText().toString().length()<=0 || mPassword.getText().toString().length()<=0){
                    Toast.makeText(getApplicationContext(), "Fill username and password!", Toast.LENGTH_SHORT).show();
                }else {
                    RegisterTask registerTask = new RegisterTask();
                    registerTask.execute(mUsername.getText().toString(), mPassword.getText().toString());
                }
            }
        });
        mBackButton = (Button)findViewById(R.id.register_back_button);
        mBackButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RegisterActivity.this.finish();
            }
        });
    }

    private class RegisterTask extends AsyncTask<String, Void, String>{
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            mRegisterButton.setEnabled(false);
        }

        @Override
        protected String doInBackground(String... params) {
            String token = null;
            try{
                RegisterResponse resp = App.getApi().register(new RegisterRequest(params[0], params[1])).execute().body();
                token = resp.getToken();
            } catch (Exception ex){Log.d("REGISTER", "Registration failed!");}
            return token;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            mRegisterButton.setEnabled(true);
            if(s!=null) {
                Toast.makeText(getApplicationContext(),"Registration successful!",Toast.LENGTH_SHORT).show();
                RegisterActivity.this.finish();
            }
            else Toast.makeText(getApplicationContext(),"Registration failed!",Toast.LENGTH_SHORT).show();
        }
    }
}
