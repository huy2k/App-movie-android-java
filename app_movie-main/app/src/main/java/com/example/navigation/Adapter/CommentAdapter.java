package com.example.navigation.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.navigation.R;
import com.example.navigation.model.CommentData;
import com.example.navigation.model.ReviewData;

import java.util.ArrayList;

public class CommentAdapter extends RecyclerView.Adapter<CommentAdapter.CommentHolder>{

    private ArrayList<CommentData> commentList;
    private Context context;

    public CommentAdapter(final ArrayList<CommentData> arrComments) {
        this.commentList = arrComments;
    }

    @NonNull
    @Override
    public CommentHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View item = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_comment, parent, false);
        CommentAdapter.CommentHolder commentHolder = new CommentHolder(item);
        return commentHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull CommentHolder holder, int position) {
        final CommentData review = commentList.get(position);// review object
        holder.username.setText(review.getUserID());
        holder.content.setText(review.getContent());
    }

    @Override
    public int getItemCount() {
        return commentList.size();
    }

    public class CommentHolder extends RecyclerView.ViewHolder {
        TextView username;
        TextView content;

        public CommentHolder(View itemView) {
            super(itemView);
            username = (TextView) itemView.findViewById(R.id.tv_user_comment);
            content = (TextView) itemView.findViewById(R.id.tv_comment_contents);
        }
    }
}
