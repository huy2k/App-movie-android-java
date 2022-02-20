package com.example.navigation.fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.navigation.Adapter.CommentAdapter;
import com.example.navigation.Adapter.MovieFavoriteAdapter;
import com.example.navigation.Adapter.TrailerAdapter;
import com.example.navigation.R;
import com.example.navigation.model.CommentData;
import com.example.navigation.model.MovieData;
import com.example.navigation.model.TrailerData;
import com.example.navigation.Adapter.ReviewAdapter;
import com.example.navigation.model.ReviewData;
import com.example.navigation.movies.ReviewParser;
import com.example.navigation.movies.TrailerParser;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;


public class DetailsFragment extends Fragment {
    MovieData model = new MovieData();

    DatabaseReference db;
//    valuesEventLisnter v;
//    commentAdapter commentAdapter;
    ArrayList<CommentData> arrComments;
    private ImageView posterView;
    private TextView titleView;
    private TextView yearView;
    private TextView rateView;
    private TextView storyView;
    private ImageView coverView;
    public RecyclerView videosView;
    public RecyclerView reviewsView;
    public RecyclerView commentsView;
    private TrailerAdapter videosAdapter;
    private ReviewAdapter reviewAdapter;
    private ImageButton favorite;
    private ImageButton delete;
    private ImageButton sendComment;
    private EditText writeComment;
    private ImageView btnBack;
    private View btnComment;
    CommentAdapter comment_adapter;
    String movie_id;
    Integer check;
    public static DetailsFragment getInstance(MovieData movie) {
        DetailsFragment detailsFragment = new DetailsFragment();
        Bundle args = new Bundle();
        args.putSerializable("movie", movie);
        detailsFragment.setArguments(args);
        return detailsFragment;
    }

    public DetailsFragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_details, container, false);

        //initialize views
        coverView = (ImageView) rootView.findViewById(R.id.cover);
        posterView = (ImageView) rootView.findViewById(R.id.img2);
        titleView = (TextView) rootView.findViewById(R.id.title);
        yearView = (TextView) rootView.findViewById(R.id.year);
        rateView = (TextView) rootView.findViewById(R.id.rate);
        storyView = (TextView) rootView.findViewById(R.id.story);
        videosView = (RecyclerView) rootView.findViewById(R.id.vidRecycler);
        reviewsView = (RecyclerView) rootView.findViewById(R.id.revRecycler);
        writeComment = rootView.findViewById(R.id.txt_comment);
        btnComment = rootView.findViewById(R.id.btn_insert_comment);
        commentsView = rootView.findViewById(R.id.rcv_comment);

        videosView.setLayoutManager(new LinearLayoutManager(getActivity()));
        reviewsView.setLayoutManager(new LinearLayoutManager(getActivity()));
        commentsView.setLayoutManager(new LinearLayoutManager(getActivity()));

        model = (MovieData) getArguments().getSerializable("movie");
        btnBack = rootView.findViewById(R.id.back);


        //setting data into views
        titleView.setText(model.getTitle());
        rateView.setText(model.getRate());
        yearView.setText(model.getRelease_date());
        storyView.setText(model.getOverview());
        Picasso.with(getActivity()).load("http://image.tmdb.org/t/p/w342/" + model.getPoster()).placeholder(R.drawable.loading).into(posterView);
        Picasso.with(getActivity()).load("http://image.tmdb.org/t/p/w780/" + model.getBackdropPath()).placeholder(R.drawable.loading).into(coverView);


        TrailerParser trailerParser = new TrailerParser() {
            @Override
            protected void onPostExecute(ArrayList<TrailerData> trailersList) {
                videosAdapter = new TrailerAdapter(trailersList, getActivity());
                videosView.setAdapter(videosAdapter);
            }
        };
        trailerParser.execute(model.getId());

        ReviewParser reviewParser = new ReviewParser() {
            @Override
            protected void onPostExecute(ArrayList<ReviewData> reviewsList) {
                reviewAdapter = new ReviewAdapter(reviewsList, getActivity());
                reviewsView.setAdapter(reviewAdapter);

            }
        };
        reviewParser.execute(model.getId());

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getActivity().onBackPressed();
            }
        });


        View btnAddFav = rootView.findViewById(R.id.btn_fav);
        movie_id = model.getId().toString();
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        String uid = user.getUid();
        DatabaseReference mData = FirebaseDatabase.getInstance().getReference("users/"+uid);
        check = 0; //check = 0 khi phim khong nam trong danh sach yeu thich

        mData.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.hasChild("FavMovie/"+movie_id)){
                    btnAddFav.setBackgroundResource(R.drawable.ic_favorite_like);
                    check = 1;
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {}
        });


        btnAddFav.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(check == 1){
                    btnAddFav.setBackgroundResource(R.drawable.ic_favorite_dislike);
                    check = 0;
                    mData.child("FavMovie").child(movie_id).removeValue(new DatabaseReference.CompletionListener() {
                        @Override
                        public void onComplete(@Nullable DatabaseError error, @NonNull DatabaseReference ref) {
                            Toast.makeText(getActivity(),"Đã xóa khỏi danh sách yêu thích!", Toast.LENGTH_LONG).show();
                        }
                    });
                }
                else{
                    btnAddFav.setBackgroundResource(R.drawable.ic_favorite_like);
                    check = 1;

                    mData.child("FavMovie").child(movie_id).setValue(model, new DatabaseReference.CompletionListener() {
                        @Override
                        public void onComplete(@Nullable DatabaseError error, @NonNull DatabaseReference ref) {
                            Toast.makeText(getActivity(),"Đã thêm vào danh sách yêu thích!", Toast.LENGTH_LONG).show();
                        }
                    });
                }
            }
        });


        /*-----Insert Comment-------*/
        DatabaseReference mRefComment = FirebaseDatabase.getInstance().getReference("comments/"+movie_id);
        btnComment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String comment_content = writeComment.getText().toString().trim();
                if (TextUtils.isEmpty(comment_content)) {
                    writeComment.setError("Chưa nhập nội dung");
                } else {
                    CommentData commentData = new CommentData(uid, comment_content);
                    mRefComment.push().setValue(commentData, new DatabaseReference.CompletionListener() {
                        @Override
                        public void onComplete(@Nullable DatabaseError error, @NonNull DatabaseReference ref) {
                            Toast.makeText(getActivity(),"Đã thêm!", Toast.LENGTH_LONG).show();
                        }
                    });
                }
            }
        });

        /*-----Hien thi Comment------*/
        arrComments = new ArrayList<>();
        getComment();
        comment_adapter = new CommentAdapter(arrComments);
        commentsView.setAdapter(comment_adapter);
        return rootView;
   }

   public void getComment(){
       DatabaseReference mRefComment = FirebaseDatabase.getInstance().getReference("comments/"+movie_id);
       mRefComment.addValueEventListener(new ValueEventListener() {
           @Override
           public void onDataChange(@NonNull DataSnapshot snapshot) {

           }

           @Override
           public void onCancelled(@NonNull DatabaseError error) {

           }
       });
       mRefComment.addChildEventListener(new ChildEventListener() {
           @Override
           public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
               CommentData commentData= snapshot.getValue(CommentData.class);
               if(commentData != null){
                   arrComments.add(commentData);
                   comment_adapter.notifyDataSetChanged();
               }
               System.out.println(commentData.getContent());
           }

           @Override
           public void onChildChanged(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

           }

           @Override
           public void onChildRemoved(@NonNull DataSnapshot snapshot) {

           }

           @Override
           public void onChildMoved(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

           }

           @Override
           public void onCancelled(@NonNull DatabaseError error) {

           }
       });
   }

}

