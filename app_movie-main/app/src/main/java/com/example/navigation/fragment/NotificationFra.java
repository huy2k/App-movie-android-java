package com.example.navigation.fragment;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

//import com.example.navigation.Adapter.movieSearchAdapter;
import com.example.navigation.R;
import com.example.navigation.model.MovieData;
import com.example.navigation.Adapter.MovieSearchAdapter;
import com.example.navigation.movies.MovieSearchPaser;

import java.util.ArrayList;

public class NotificationFra extends Fragment {
    RecyclerView lv;
//    ArrayList<Movie> lvList;
//    movieAdapter adapter;
    ArrayList<MovieData> lvList;
    MovieSearchAdapter adapter;
    EditText txtSearch;
    View btnSearch;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.frag_notification, container, false);
        lv = view.findViewById(R.id.lv);
        txtSearch = view.findViewById(R.id.txt_search);
        btnSearch = view.findViewById(R.id.btn_search);
        btnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

//                jsonParsing(txtSearch.getText().toString(), lv);
                MovieSearchPaser movieSearchPaser =  new MovieSearchPaser() {
                    @SuppressLint("StaticFieldLeak")
                    @Override
                    protected void onPostExecute(ArrayList<MovieData> movieData) {
                        adapter = new MovieSearchAdapter(movieData);
                        NotificationFra.this.lv.setAdapter(adapter);
                    }

                };
                movieSearchPaser.execute(txtSearch.getText().toString().trim());
            }

        });
        lv.setLayoutManager(new GridLayoutManager(getActivity(),3));
//        lvList  = new ArrayList<>();
//        lvList.add(new Movie(1 ,"Sieu nhan",R.drawable.p5, "hihi", (float) 4.6));
//        lvList.add(new Movie(2 ,"Sieu nhan 1 ",R.drawable.p6 , "hihi", (float) 4.6));
//        lvList.add(new Movie(3 ,"Sieu nhan",R.drawable.p8, "hihi", (float) 4.6));
//        lvList.add(new Movie(4 ,"Sieu nhan",R.drawable.phim2, "hihi", (float) 4.6));
//
//        adapter = new movieAdapter(getContext(), R.layout.movies , lvList);
//        lv.setAdapter(adapter);
        return view;
    }
    private void jsonParsing(String x, RecyclerView rcv)
    {
        MovieSearchPaser movieSearchPaser =  new MovieSearchPaser() {
            @SuppressLint("StaticFieldLeak")
            @Override
            protected void onPostExecute(ArrayList<MovieData> movieData) {
                adapter = new MovieSearchAdapter(movieData);
                rcv.setAdapter(adapter);
            }

        };
        movieSearchPaser.execute(x);
    }
}
