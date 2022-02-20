package com.example.navigation.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.navigation.R;
import com.example.navigation.model.Category;
import com.example.navigation.Adapter.CategoryAdapter1;
import com.example.navigation.model.Movie;

import java.util.ArrayList;
import java.util.List;

public class HomeFra extends Fragment {
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.frag_home, container, false);
        RecyclerView rcvCategory = view.findViewById(R.id.rcv_category);
        CategoryAdapter1 adapter = new CategoryAdapter1(this.getContext());
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this.getContext(), RecyclerView.VERTICAL,false);
        rcvCategory.setLayoutManager(linearLayoutManager);
        adapter.setData(getList1());
        rcvCategory.setAdapter(adapter);
        return view;
    }

//    private List<Category> getList() {
//        List<Category> categoryList = new ArrayList<>();
//        List<Movie> movieList = new ArrayList<>();
//        movieList.add(new Movie(1 ,"Sieu nhan",R.drawable.p5, "hihi", (float) 4.6));
//        movieList.add(new Movie(2 ,"Sieu nhan 1 ",R.drawable.p6 , "hihi", (float) 4.6));
//        movieList.add(new Movie(3 ,"Sieu nhan",R.drawable.p8, "hihi", (float) 4.6));
//        movieList.add(new Movie(4 ,"Sieu nhan",R.drawable.phim3, "hihi", (float) 4.6));
//        movieList.add(new Movie(4 ,"Sieu nhan",R.drawable.phim2, "hihi", (float) 4.6));
//        categoryList.add(new Category(1, "popular" , movieList));
//        categoryList.add(new Category(2 , "top_rated" , movieList));
//        categoryList.add(new Category(3 , "Hanh dong" , movieList));
//        return  categoryList;
//
//    }
    private List<Category> getList1() {
        List<Category> categoryList = new ArrayList<>();
        List<Movie> movieList = new ArrayList<>();

        categoryList.add((new Category("Upcoming", "upcoming")));
        categoryList.add(new Category( "Popular", "popular" ));
        categoryList.add(new Category( "Top Rate", "top_rated" ));
        categoryList.add(new Category("Now Playing", "now_playing"));
        return  categoryList;

    }
}
