package com.example.navigation.Adapter;

import android.app.Activity;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.navigation.R;
import com.example.navigation.fragment.DetailsFragment;

import com.example.navigation.model.MovieData;
import com.squareup.picasso.Picasso;

import java.util.List;

public class MovieFavoriteAdapter extends RecyclerView.Adapter<MovieFavoriteAdapter.recyclerHolder> {
    private List<MovieData> lMovies;
    Context context;

    public void setData(List<MovieData> list){
        this.lMovies = list;
        notifyDataSetChanged();
    }

    public MovieFavoriteAdapter(final List<MovieData> lMovies) {
        this.lMovies = lMovies;
    }

    @NonNull
    @Override
    public recyclerHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_movie, parent, false);

        return new MovieFavoriteAdapter.recyclerHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull recyclerHolder holder, int position) {
        MovieData movie = lMovies.get(position);
        if(movie == null){
            return;
        }
        context = holder.itemView.getContext();//getting context
//        holder.imgMovie.setImageResource();
        holder.imgMovie.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager fragmentManager = ((Activity) context).getFragmentManager();
                DetailsFragment detailsFragment = DetailsFragment.getInstance(movie);
                FragmentTransaction fragmentTransaction =
                        fragmentManager.beginTransaction();

                //handeling twoPane
                Bundle bundle = new Bundle();
                bundle.putSerializable("movie", movie);
                detailsFragment.setArguments(bundle);

                fragmentManager.beginTransaction()
                        .replace(R.id.activity_main, detailsFragment).addToBackStack("fragment1").commit();
            }
        });

        holder.tvTitle.setText(movie.getTitle());

        Picasso.with(context).load("http://image.tmdb.org/t/p/w342/" + movie.getPoster()).placeholder(R.drawable.loading).into(holder.imgMovie);
    }

    @Override
    public int getItemCount() {
        if(lMovies != null) return lMovies.size();
        return 0;
    }

    public  class recyclerHolder extends  RecyclerView.ViewHolder{
        public ImageView imgMovie;
        public TextView tvTitle;
        public recyclerHolder(@NonNull View itemView) {
            super(itemView);
            imgMovie = itemView.findViewById(R.id.image_mv);
            tvTitle = itemView.findViewById(R.id.tv_title);
        }
    }
}
