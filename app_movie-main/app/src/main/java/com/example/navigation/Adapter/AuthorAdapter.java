package com.example.navigation.Adapter;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.navigation.R;
import com.example.navigation.model.ReviewData;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;


public class AuthorAdapter extends RecyclerView.Adapter<AuthorAdapter.AuthorHolder> {
    private ArrayList<ReviewData> reviewsList;
    private Context context;

    public AuthorAdapter(ArrayList<ReviewData> reviewsList, Context context) {
        this.reviewsList = reviewsList;
        this.context = context;
    }

    @Override
    public AuthorAdapter.AuthorHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View item = LayoutInflater.from(parent.getContext()).inflate(R.layout.review, parent, false);
        AuthorHolder authorHolder = new AuthorHolder(item);
        return authorHolder;
    }

    @Override
    public void onBindViewHolder(AuthorHolder holder, int position) {

        final ReviewData review = reviewsList.get(position);// review object

        Picasso.with(context).load(review.getAuthorDetailData().getAvatar_path().substring(1)).into(holder.imageAuthor);
//        System.out.println("url"+review.getAuthorDetailData().getAvatar_path());
//        System.out.println(review.getAuthorDetailData().getAvatar_path().substring(1));

    }

    @Override
    public int getItemCount() {
        return reviewsList.size();
    }

    class AuthorHolder extends RecyclerView.ViewHolder {

        ImageView imageAuthor;
        RecyclerView recyclerView;

        public AuthorHolder(View itemView) {
            super(itemView);

            imageAuthor = itemView.findViewById((R.id.img_author));
            recyclerView = itemView.findViewById(R.id.revRecycler);
//            content = (TextView) itemView.findViewById(R.id.contents);
        }
    }
}

