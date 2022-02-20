package com.example.navigation.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import com.example.navigation.Adapter.MovieFavoriteAdapter;
import com.example.navigation.R;

import com.example.navigation.model.MovieData;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class FavoriteFra extends Fragment {
    RecyclerView rcvMovie;
    ArrayList<MovieData> arrFavMovie;
    MovieFavoriteAdapter fav_adapter;
    TextView tv_note;
    Integer check;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.frag_favorite, container, false);
        rcvMovie = view.findViewById(R.id.rcv_favMovie);
        tv_note = view.findViewById(R.id.tv_no_fav);
        rcvMovie.setLayoutManager(new GridLayoutManager(getActivity(),3));
        // rcvMovie.setLayoutManager(linearLayoutManager);
        arrFavMovie  = new ArrayList<>();
        getListFavMovieFromFirebase();

        fav_adapter = new MovieFavoriteAdapter(arrFavMovie);
        rcvMovie.setAdapter(fav_adapter);

        return view;
    }

    private void getListFavMovieFromFirebase(){
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        String uid = user.getUid();
        DatabaseReference mData = FirebaseDatabase.getInstance().getReference("users/"+uid+"/FavMovie");
        DatabaseReference mDataFav = FirebaseDatabase.getInstance().getReference("users/"+uid);
        check = 0;

        mDataFav.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.hasChild("FavMovie")){
                    tv_note.setVisibility(View.GONE);
                }
                else{
                    tv_note.setVisibility(View.VISIBLE);
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {}
        });



        mData.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (arrFavMovie != null){
                    arrFavMovie.clear();
                }
                for(DataSnapshot dataSnapshot : snapshot.getChildren()) {
                    MovieData movie = dataSnapshot.getValue(MovieData.class);
                    arrFavMovie.add(movie);
                    System.out.println(movie.getTitle());
                }

                /*if(arrFavMovie != null){
                    rcvMovie.setVisibility(View.VISIBLE);
                    tv_note.setVisibility(View.GONE);
                    fav_adapter.notifyDataSetChanged();
                }
                else{
                    rcvMovie.setVisibility(View.GONE);
                    tv_note.setVisibility(View.VISIBLE);
                    fav_adapter.notifyDataSetChanged();
                }*/

                fav_adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}
