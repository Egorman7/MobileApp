package app.and.mobileapp;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.util.Arrays;

import app.and.mobileapp.API.CommentModel;

public class ItemActivity extends Activity {

    private RecyclerView mRecyclerView;
    private ImageView mItemImage;
    private TextView mTitle, mText;
    private Button mBackButton;
    private FloatingActionButton mFab;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item);
        mRecyclerView = (RecyclerView)findViewById(R.id.item_act_rv);
        mItemImage = (ImageView)findViewById(R.id.item_act_card_item_image);
        Picasso.with(getApplicationContext()).load("http://smktesting.herokuapp.com/static/"+getIntent().getStringExtra("image")).into(mItemImage);
        mTitle = (TextView)findViewById(R.id.item_act_card_item_title);
        mTitle.setText(getIntent().getStringExtra("title"));
        mText = (TextView)findViewById(R.id.item_act_card_item_desc);
        mText.setText(getIntent().getStringExtra("desc"));
        mBackButton  = (Button)findViewById(R.id.item_act_back_button);
        mBackButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ItemActivity.this.finish();
            }
        });
        mFab = (FloatingActionButton)findViewById(R.id.floatingActionButton2);
        mFab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ItemActivity.this.getApplicationContext(),CommentAddActivity.class);
                intent.putExtra("id",String.valueOf(ItemActivity.this.getIntent().getIntExtra("id",0)));
                intent.putExtra("title",ItemActivity.this.getIntent().getStringExtra("title"));
                startActivityForResult(intent,Constants.COMMENT_SEND_REQUEST_CODE);
            }
        });
        CommentListTask commentListTask = new CommentListTask();
        commentListTask.execute(getIntent().getIntExtra("id",0));
        if(!App.isLogged()){
            mFab.setEnabled(false);
            mFab.setVisibility(View.GONE);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode){
            case Constants.COMMENT_SEND_REQUEST_CODE:
                if(data!=null && data.getBooleanExtra("success",false)){
                    CommentListTask commentListTask = new CommentListTask();
                    commentListTask.execute(getIntent().getIntExtra("id",0));
                }
        }
    }

    private void loadComments(CommentModel[] comments){
        findViewById(R.id.comments_loading).setVisibility(View.GONE);
        mRecyclerView.setHasFixedSize(true);
        LinearLayoutManager llm = new LinearLayoutManager(getApplicationContext());
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        mRecyclerView.setLayoutManager(llm);
        CommentListAdapter commentListAdapter = new CommentListAdapter(Arrays.asList(comments));
        mRecyclerView.setAdapter(commentListAdapter);
    }

    class CommentListTask extends AsyncTask<Integer, Void, CommentModel[]>{
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected CommentModel[] doInBackground(Integer... params) {
            try{
                return App.getApi().getComments(params[0]).execute().body();
            } catch (Exception ex) {Log.d("COMMENT_LOAD", "Can't load comments!");}
            return null;
        }

        @Override
        protected void onPostExecute(CommentModel[] commentModels) {
            super.onPostExecute(commentModels);
            if(commentModels!=null){
                loadComments(commentModels);
            } else Toast.makeText(getApplicationContext(),"Can't load comments!",Toast.LENGTH_SHORT).show();
        }
    }
}
