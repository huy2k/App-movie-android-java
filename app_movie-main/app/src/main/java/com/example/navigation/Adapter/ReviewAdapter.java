package com.example.navigation.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.navigation.R;
import com.example.navigation.model.ReviewData;

import java.util.ArrayList;


public class ReviewAdapter extends RecyclerView.Adapter<ReviewAdapter.ReviewHolder> {
    private ArrayList<ReviewData> reviewsList;
    private Context context;

    public ReviewAdapter(ArrayList<ReviewData> reviewsList, Context context) {
        this.reviewsList = reviewsList;
        this.context = context;
    }

    @Override
    public ReviewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View item = LayoutInflater.from(parent.getContext()).inflate(R.layout.review, parent, false);
        ReviewHolder reviewHolder = new ReviewHolder(item);
        return reviewHolder;
    }

    @Override
    public void onBindViewHolder(ReviewHolder holder, int position) {

        final ReviewData review = reviewsList.get(position);// review object
        holder.author.setText(review.getAuthor());
        holder.content.setText(review.getContent());
//        Picasso.with(context).load(review.getAuthorDetailData().getAvatar_path().substring(1)).into(holder.imageAuthor);
//        System.out.println("url"+review.getAuthorDetailData().getAvatar_path());
//        System.out.println(review.getAuthorDetailData().getAvatar_path().substring(1));

    }

    @Override
    public int getItemCount() {
        return reviewsList.size();
    }

    class ReviewHolder extends RecyclerView.ViewHolder {
        TextView author;
        TextView content;
        ImageView imageAuthor;
        RecyclerView recyclerView;

        public ReviewHolder(View itemView) {
            super(itemView);
            author = (TextView) itemView.findViewById(R.id.authors);
            imageAuthor = itemView.findViewById((R.id.img_author));
            recyclerView = itemView.findViewById(R.id.revRecycler);
            content = (TextView) itemView.findViewById(R.id.contents);
        }
    }
}
