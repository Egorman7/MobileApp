package app.and.mobileapp;

import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.util.List;

import app.and.mobileapp.API.ItemModel;

/**
 * Created by Egorman on 05.07.2017.
 */

class ItemListAdapter extends RecyclerView.Adapter<ItemListAdapter.ItemViewHolder> {
    private List<ItemModel> items;
    class ItemViewHolder extends RecyclerView.ViewHolder{
        ImageView image;
        TextView title, desc;
        ItemViewHolder(View itemView){
            super(itemView);
            image = (ImageView)itemView.findViewById(R.id.item_list_card_image);
            title = (TextView)itemView.findViewById(R.id.item_list_card_title);
            desc = (TextView)itemView.findViewById(R.id.item_list_card_desc);
        }
    }

    ItemListAdapter(List<ItemModel> items) {
        this.items = items;
    }

    @Override
    public ItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list_card_layout,parent,false);
        ItemViewHolder ivh = new ItemViewHolder(v);
        return ivh;
    }

    @Override
    public void onBindViewHolder(final ItemViewHolder holder, final int position) {
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Toast.makeText(holder.itemView.getContext(),items.get(position).getTitle(),Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(holder.itemView.getContext(),ItemActivity.class);
                intent.putExtra("title",items.get(position).getTitle());
                intent.putExtra("desc",items.get(position).getText());
                intent.putExtra("image",items.get(position).getImg());
                intent.putExtra("id",items.get(position).getId());
                holder.itemView.getContext().startActivity(intent);
            }
        });
        holder.title.setText(items.get(position).getTitle());
        holder.desc.setText(items.get(position).getText());
        Picasso.with(holder.itemView.getContext()).load("http://smktesting.herokuapp.com/static/"+items.get(position).getImg()).into(holder.image);
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }

    @Override
    public int getItemCount() {
        return items.size();
    }
}
