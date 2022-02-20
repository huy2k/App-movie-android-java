package com.example.navigation.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.navigation.R;
import com.example.navigation.model.Category;
import com.example.navigation.model.MovieData;
import com.example.navigation.movies.MovieParser;

import java.util.ArrayList;
import java.util.List;

public class CategoryAdapter1 extends RecyclerView.Adapter<CategoryAdapter1.categoryHolder> {
    Context context;
//    int layout;
    List<Category> categories;
    MovieAdapter1 movieAdapter1;
    public CategoryAdapter1(Context context) {
        this.context = context;
//        this.layout = layout;
    }

    public void setData(List<Category> list){
        this.categories = list;

        notifyDataSetChanged();
    }
    @NonNull
    @Override
    public categoryHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_category, parent, false);

        return new CategoryAdapter1.categoryHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull categoryHolder holder, int position) {
        Category category  = categories.get(position);
        if(category == null){
            return;
        }

        holder.tvCategory.setText(category.getCategoryName());

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(context, RecyclerView.HORIZONTAL,false);
        holder.rcvMovies.setLayoutManager(linearLayoutManager);

        jsonParsing(category.getCategoryField(), holder.rcvMovies);
    }

    @Override
    public int getItemCount() {
        return categories.size();
    }

    public class categoryHolder extends RecyclerView.ViewHolder{
        RecyclerView rcvMovies;
        ImageView imgBanner;
        TextView tvCategory;
        public categoryHolder(@NonNull View itemView) {

            super(itemView);
            rcvMovies = itemView.findViewById(R.id.rcv_movie);
            tvCategory = itemView.findViewById(R.id.tv_Category);

        }
    }
    private void jsonParsing(String x, RecyclerView rcv)
    {
        MovieParser movieParser =  new MovieParser() {
            @Override
            protected void onPostExecute(ArrayList<MovieData> movieData) {
                movieAdapter1 = new MovieAdapter1(movieData);
                rcv.setAdapter(movieAdapter1);
            }
        };
        movieParser.execute(x);
    }
}
