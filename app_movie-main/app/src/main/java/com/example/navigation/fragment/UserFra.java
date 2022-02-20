package com.example.navigation.fragment;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.bumptech.glide.Glide;
import com.example.navigation.MainActivity;
import com.example.navigation.R;
import com.example.navigation.login.LoginActivity;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class UserFra extends Fragment {

    // private FirebaseAuth mAuth;
    private ImageView imgAvatar, imgLogout;
    private TextView tvName, tvEmail;
    //private FirebaseUser mUser;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.frag_user, container, false);

        imgAvatar = view.findViewById(R.id.img_avatar);
        tvEmail = view.findViewById(R.id.tv_pf_email);
        tvName = view.findViewById(R.id.tv_pf_name);
        imgLogout = view.findViewById(R.id.img_logout);
        showUserInformation();

        return view;
    }

    private void showUserInformation(){
        DatabaseReference mData = FirebaseDatabase.getInstance().getReference("users");
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if(user == null){
            return;
        }

        //String name = user.getDisplayName();
        String email = user.getEmail();
        String uid = user.getUid();
        Uri photoUrl = user.getPhotoUrl();

        mData.child(uid).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                tvName.setText(snapshot.child("name").getValue().toString());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        imgLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity(), "Đăng xuất thành công!",  Toast.LENGTH_SHORT).show();
                Intent i = new Intent(getActivity(), LoginActivity.class);
                startActivity(i);
            }
        });
        tvEmail.setText(email);
        Glide.with(this).load(photoUrl).error(R.drawable.ic_avatar_default).into(imgAvatar);
    }
}