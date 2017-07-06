package app.and.mobileapp;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import app.and.mobileapp.API.LoginRequest;
import app.and.mobileapp.API.LoginResponse;

public class LoginActivity extends Activity {

    private Button mRegisterButton, mBackButton, mLoginButton;
    private EditText mUsername, mPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        mUsername = (EditText)findViewById(R.id.login_username);
        mPassword = (EditText)findViewById(R.id.login_password);
        mRegisterButton = (Button)findViewById(R.id.login_register_button);
        mRegisterButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(intent);
            }
        });
        mBackButton = (Button)findViewById(R.id.login_back_button);
        mBackButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.putExtra("data", false);
                setResult(Constants.LOGIN_REQUEST_CODE, intent);
                LoginActivity.this.finish();
            }
        });
        mLoginButton = (Button)findViewById(R.id.login_button);
        mLoginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mUsername.getText().toString().length()<=0 || mPassword.getText().toString().length()<=0){
                    Toast.makeText(getApplicationContext(), "Fill username and password!", Toast.LENGTH_SHORT).show();
                }else {
                    LoginTask loginTask = new LoginTask();
                    loginTask.execute(mUsername.getText().toString(), mPassword.getText().toString());
                }
            }
        });
    }

    private class LoginTask extends AsyncTask<String, Void, String>{
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            mBackButton.setEnabled(false);
            mRegisterButton.setEnabled(false);
            mLoginButton.setEnabled(false);
        }

        @Override
        protected String doInBackground(String... params) {
            String token = null;
            try{
                LoginResponse resp = App.getApi().login(new LoginRequest(params[0],params[1])).execute().body();
                token=resp.getToken();
            } catch (Exception ex) {
                Log.d("LOGIN","Login failed");}
            return token;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            mBackButton.setEnabled(true);
            mRegisterButton.setEnabled(true);
            mLoginButton.setEnabled(true);
            if(s!=null){
                Toast.makeText(getApplicationContext(),"Login successful!",Toast.LENGTH_SHORT).show();
                App.setUser(new User(mUsername.getText().toString(), s));
                Intent intent = new Intent();
                intent.putExtra("data",true);
                setResult(Constants.LOGIN_REQUEST_CODE, intent);
                LoginActivity.this.finish();
            } else Toast.makeText(getApplicationContext(),"Login failed!",Toast.LENGTH_SHORT).show();
        }
    }

}
