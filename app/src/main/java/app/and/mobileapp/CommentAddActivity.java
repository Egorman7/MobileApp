package app.and.mobileapp;

import android.content.Intent;
import android.graphics.BitmapFactory;
import android.media.Image;
import android.os.AsyncTask;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import app.and.mobileapp.API.CommentRequest;
import app.and.mobileapp.API.CommentResponse;

public class CommentAddActivity extends AppCompatActivity {

    private int mRating;
    private TextView mTitle;
    private EditText mComment;
    private Button mSend, mBack;
    private RatingBar mRatingBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comment_add);
        mRating = 0;
        mTitle = (TextView)findViewById(R.id.comment_add_title);
        mTitle.setText("Comment to " + getIntent().getStringExtra("title")+":");
        mComment = (EditText)findViewById(R.id.comment_add_comment);
        mSend = (Button)findViewById(R.id.comment_add_send);
        mSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PostCommentTask postCommentTask = new PostCommentTask();
                postCommentTask.execute(CommentAddActivity.this.getIntent().getStringExtra("id"),String.valueOf(mRating),mComment.getText().toString(),App.getUser().getToken());
            }
        });
        mBack = (Button)findViewById(R.id.comment_back_button);
        mBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.putExtra("success",false);
                setResult(Constants.COMMENT_SEND_REQUEST_CODE,intent);
                CommentAddActivity.this.finish();
            }
        });
        mRatingBar = (RatingBar)findViewById(R.id.comment_ratingbar);
        mRatingBar.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
                mRating=(int)rating;
            }
        });
    }

    private class PostCommentTask extends AsyncTask<String,Void,CommentResponse>{
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            mSend.setEnabled(false);
        }

        @Override
        protected CommentResponse doInBackground(String... params) {
            try{
                for(int i=0; i<4; i++){
                    Log.d("WTF",params[i]);
                }
                return App.getApi().postComment(" Token " + params[3], params[0],new CommentRequest(Integer.parseInt(params[1]),params[2])).execute().body();
            } catch (Exception ex) {ex.printStackTrace(); Log.d("COMMENT_POST", "Can't post a comment!");}
            return null;
        }

        @Override
        protected void onPostExecute(CommentResponse commentResponse) {
            super.onPostExecute(commentResponse);
            if(commentResponse!=null){
                Toast.makeText(getApplicationContext(),"Comment posted!",Toast.LENGTH_SHORT).show();
                Intent intent = new Intent();
                intent.putExtra("success",true);
                setResult(Constants.COMMENT_SEND_REQUEST_CODE,intent);
                CommentAddActivity.this.finish();
            } Toast.makeText(getApplicationContext(),"Can't post a comment!",Toast.LENGTH_SHORT).show();
            mSend.setEnabled(true);
        }
    }
}
