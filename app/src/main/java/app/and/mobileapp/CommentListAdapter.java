package app.and.mobileapp;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

import app.and.mobileapp.API.CommentModel;

/**
 * Created by Egorman on 05.07.2017.
 */

class CommentListAdapter extends RecyclerView.Adapter<CommentListAdapter.CommentViewHolder> {
    private List<CommentModel> comments;
    class CommentViewHolder extends RecyclerView.ViewHolder{
        TextView username, text;
        RatingBar rating;
        CommentViewHolder(View itemView){
            super(itemView);
            username = (TextView)itemView.findViewById(R.id.comment_user);
            text = (TextView)itemView.findViewById(R.id.comment_text);
            rating = (RatingBar)itemView.findViewById(R.id.comment_rating_bar);
        }
    }

    CommentListAdapter(List<CommentModel> comments) {
        this.comments = comments;
    }

    @Override
    public CommentViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.comment_list_card_layout,parent,false);
        CommentViewHolder cvh = new CommentViewHolder(v);
        return cvh;
    }

    @Override
    public void onBindViewHolder(CommentViewHolder holder, int position) {
        holder.username.setText(comments.get(position).getUser());
        holder.text.setText(comments.get(position).getText());
        holder.rating.setRating(comments.get(position).getRate());
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }

    @Override
    public int getItemCount() {
        return comments.size();
    }
}
