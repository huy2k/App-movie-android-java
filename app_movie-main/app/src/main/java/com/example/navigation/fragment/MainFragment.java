package com.example.navigation.fragment;

import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.navigation.Adapter.MoviePopularAdapter;
import com.example.navigation.R;
import com.example.navigation.model.MovieData;
import com.example.navigation.movies.MovieParser;

import java.util.ArrayList;
//
///**
// * A simple {@link Fragment} subclass.
// * Use the {@link MainFragment#newInstance} factory method to
// * create an instance of this fragment.
// */
public class MainFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
//    private static final String ARG_PARAM1 = "param1";
//    private static final String ARG_PARAM2 = "param2";
    MoviePopularAdapter adapter;
    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public MainFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_main, container, false);
        RecyclerView rcvMovie = view.findViewById(R.id.movies_list);
        jsonParsing("popular", rcvMovie);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this.getContext(), RecyclerView.HORIZONTAL,false);
        rcvMovie.setLayoutManager(linearLayoutManager);

        return view;
    }
    private void jsonParsing(String x, RecyclerView rcv)
    {
        MovieParser moviePaser =  new MovieParser() {
            @SuppressLint("StaticFieldLeak")
            @Override
            protected void onPostExecute(ArrayList<MovieData> movieData) {
                adapter = new MoviePopularAdapter(movieData);
                rcv.setAdapter(adapter);
            }

        };
        moviePaser.execute(x);
    }
}