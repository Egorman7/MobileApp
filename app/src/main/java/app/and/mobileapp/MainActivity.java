package app.and.mobileapp;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Arrays;

import app.and.mobileapp.API.ItemModel;

public class MainActivity extends Activity {

    private Button mLoginButton;
    private RecyclerView mRecyclerView;
    private TextView mUsername;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mUsername = (TextView)findViewById(R.id.main_username);
        if(App.isLogged()) mUsername.setText(App.getUser().getUsername());
        mRecyclerView = (RecyclerView)findViewById(R.id.main_rv);
        mLoginButton = (Button)findViewById(R.id.main_menuButton);
        if(!App.isLogged()) mLoginButton.setBackgroundResource(R.drawable.login);
        else mLoginButton.setBackgroundResource(R.drawable.logout);
        mLoginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(App.isLogged()) {
                    new AlertDialog.Builder(MainActivity.this)
                            .setTitle("Logout")
                            .setMessage("Are you sure want to logout?")
                            .setIcon(android.R.drawable.ic_dialog_alert)
                            .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    App.setUser(null);
                                    updateUI();
                                }
                            })
                            .setNegativeButton("No", null).show();
                }
                else {
                    Intent intent = new Intent(MainActivity.this,LoginActivity.class);
                    startActivityForResult(intent, Constants.LOGIN_REQUEST_CODE);
                }
            }
        });
        ItemsTask itemsTask = new ItemsTask();
        itemsTask.execute();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode){
            case Constants.LOGIN_REQUEST_CODE:
                if(data!=null && data.getBooleanExtra("data",false)){
                    updateUI();
                }
                break;
        }
    }

    private void fillItemList(ItemModel[] itemModels){
        mRecyclerView.setHasFixedSize(true);
        LinearLayoutManager llm = new LinearLayoutManager(getApplicationContext());
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        mRecyclerView.setLayoutManager(llm);
        ItemListAdapter itemListAdapter = new ItemListAdapter(Arrays.asList(itemModels));
        mRecyclerView.setAdapter(itemListAdapter);
    }

    private void updateUI(){
        if(App.isLogged()){
            mUsername.setText(App.getUser().getUsername());
            mLoginButton.setBackgroundResource(R.drawable.logout);
        }else
        {
            mUsername.setText("");
            mLoginButton.setBackgroundResource(R.drawable.login);
        }
    }

    private class ItemsTask extends AsyncTask<Void,Void,ItemModel[]>{
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected ItemModel[] doInBackground(Void... params) {
            ItemModel[] itemModels = null;
            try {
                itemModels = App.getApi().getItems().execute().body();
            } catch (Exception ex){ Log.d("ITEM_LOAD", "Failed to load items!");}
            return itemModels;
        }

        @Override
        protected void onPostExecute(ItemModel[] itemModels) {
            super.onPostExecute(itemModels);
            if(itemModels!=null){
               findViewById(R.id.main_loading).setVisibility(View.GONE);
               fillItemList(itemModels);
            } else Toast.makeText(getApplicationContext(),"Can't load item data!",Toast.LENGTH_SHORT).show();
        }
    }

}
