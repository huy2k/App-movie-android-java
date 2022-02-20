package com.example.navigation.login;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;

import com.example.navigation.MainActivity;
import com.example.navigation.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


//import com.example.navigation.RegisterActivity;
//import com.example.navigation.Reset_password;


import java.util.List;

public class LoginActivity extends AppCompatActivity {

    private FirebaseAuth mAuth;
    protected TextView login_btn, signupButton;
    private EditText email_text,pass_text, nameTextedite;
    private EditText emailTextedite, repasswordTextedite, passwordTextedite;
    protected TextView forget_pass;
    private LoginAdapter loginAdapter;
    private DatabaseReference db;


    FirebaseUser user;
    String type;
    TabLayout tabLayout;
    ViewPager2 viewPager2;
    FloatingActionButton fb, google,twitter;

    float v=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE); //will hide the title
        getSupportActionBar().hide();
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_login);

        tabLayout = findViewById(R.id.tab_layout);
        viewPager2 = findViewById(R.id.view_pager);



        loginAdapter = new LoginAdapter(this);
        viewPager2.setAdapter(loginAdapter);

        new TabLayoutMediator(tabLayout,viewPager2,
                new TabLayoutMediator.TabConfigurationStrategy(){
                    @Override
                    public  void onConfigureTab(@NonNull TabLayout.Tab tab, int position){
                        switch (position){
                            case 0:
                                tab.setText("Login");
                                break;
                            case 1:
                                tab.setText("Register");
                                break;
                        }
                    }
                }).attach();


        ///Tao hieu ung dong cho 3 button
        fb = findViewById(R.id.fab_fb);
        google = findViewById(R.id.fab_google);
        twitter = findViewById(R.id.fab_twitter);

        fb.setTranslationY(300);
        google.setTranslationY(300);
        twitter.setTranslationY(300);
        tabLayout.setTranslationY(300);

        fb.setAlpha(v);
        google.setAlpha(v);
        twitter.setAlpha(v);
        tabLayout.setAlpha(v);

        fb.animate().translationY(0).alpha(1).setDuration(1000).setStartDelay(400).start();
        google.animate().translationY(0).alpha(1).setDuration(1000).setStartDelay(600).start();
        twitter.animate().translationY(0).alpha(1).setDuration(1000).setStartDelay(800).start();
        tabLayout.animate().translationY(0).alpha(1).setDuration(1000).setStartDelay(1000).start();

        ///Xu ly hand dong-------------------------------------------------
        mAuth = FirebaseAuth.getInstance();
        db =  FirebaseDatabase.getInstance().getReference().child("users");


        email_text =  findViewById(R.id.email);
        pass_text =  findViewById(R.id.pass);
        login_btn = findViewById(R.id.button);
//        login_btn = findViewById(R.id.login_tab_fragment).findViewById(R.id.button);
        forget_pass =  findViewById(R.id.fg_pass);


        /*--Xu ly hand dong----- LoginTab*/
//        login_btn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//                String Email = email_text.getText().toString();
//                String passWord = pass_text.getText().toString();
//
//                if (!TextUtils.isEmpty(Email) && !TextUtils.isEmpty(passWord)) {
//                    Login(Email, passWord);
//                } else {
//                    email_text.setError("Required");
//                    pass_text.setError("Required");
//                }
//            }
//        });

//        forget_pass.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(LoginActivity.this, Reset_password.class);
//                startActivity(intent);
//            }
//        });

        /*--Xu ly hand dong----- SignupTab*/
        Intent intent=getIntent();
        type=intent.getStringExtra("type");

        nameTextedite = findViewById(R.id.su_name);
        emailTextedite = findViewById(R.id.su_email);
        passwordTextedite =  findViewById(R.id.su_pass);
        repasswordTextedite = findViewById(R.id.su_repass);
        signupButton = findViewById(R.id.button_su);

//        if(type.equalsIgnoreCase("profile")){
//            user = FirebaseAuth.getInstance().getCurrentUser();
//            emailTextedite.setText(user.getEmail());
//            emailTextedite.setEnabled(false);
//
//            db.child(user.getUid()).child("name").addListenerForSingleValueEvent(new ValueEventListener() {
//                @Override
//                public void onDataChange(DataSnapshot dataSnapshot) {
//                    nameTextedite.setText(dataSnapshot.getValue(String.class));
//                    db.removeEventListener(this);
//                }
//                @Override
//                public void onCancelled(DatabaseError databaseError) {
//
//                }
//            });
//
//            signupButton.setText("update");
//        }

//        signupButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                String email = emailTextedite.getText().toString();
//                String password = passwordTextedite.getText().toString();
//                String name = nameTextedite.getText().toString();
//                String repassword = repasswordTextedite.getText().toString();
//                if (!TextUtils.isEmpty(email)) {
//                    if (!TextUtils.isEmpty(password)) {
//                        if (!TextUtils.isEmpty(name)) {
//                            if (!TextUtils.isEmpty(repassword)) {
//                                if (repassword.equals(password)) {
//                                    if (type.equalsIgnoreCase("register")) {
//                                        SignUp(email, password, name);
//                                    } else {
//                                        updateProfile(password, name);
//                                    }
//                                } else {
//                                    repasswordTextedite.setError("Doesn't match Password");
//                                }
//                            } else
//                                repasswordTextedite.setError("reEnter your password");
//                        } else
//                            nameTextedite.setError("Enter name");
//                    } else
//                        passwordTextedite.setError("Enter Password");
//                } else
//                    emailTextedite.setError("Enter email");
//            }
//        });
    }


//    public void Login(String Email, String passWord) {
//        mAuth.signInWithEmailAndPassword(Email, passWord)
//                .addOnCompleteListener(this, task -> {
//                    if (task.isSuccessful()) {
//                        Toast.makeText(LoginActivity.this, "Done",
//                                Toast.LENGTH_SHORT).show();
//                        Intent i = new Intent(LoginActivity.this, MainActivity.class);
//                        startActivity(i);
//                    } else {
//                        Toast.makeText(LoginActivity.this, "sign Failed.",
//                                Toast.LENGTH_SHORT).show();
//                        email_text.setError("Invalid");
//                        pass_text.setError("Invalid");
//                    }
//                });
//    }


//    public void SignUp(String email, String pass, final String name)
//    {
//        //String name="mostafa@mmm.com";
//        //String pass="123456789";
//        mAuth.createUserWithEmailAndPassword(email, pass)
//                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
//                    @Override
//                    public void onComplete(@NonNull Task<AuthResult> task) {
//                        if (task.isSuccessful()) {
//                            Toast.makeText(LoginActivity.this, "Done",
//                                    Toast.LENGTH_SHORT).show();
//                            db.child(task.getResult().getUser().getUid()).child("name").setValue(name);
//                            Intent i = new Intent(LoginActivity.this, MainActivity.class);
//                            startActivity(i);
//                        } else {
//                            Toast.makeText(LoginActivity.this, task.getException().getMessage(),
//                                    Toast.LENGTH_LONG).show();
//                        }
//                    }
//                });
//    }
//    public void updateProfile(String pass, final String name)
//    {
//        user.updatePassword(pass)
//                .addOnCompleteListener(task -> {
//                    if (task.isSuccessful()) {
//                        db.child(user.getUid()).child("name").setValue(name);
//                        Intent i = new Intent(LoginActivity.this, MainActivity.class);
//                        startActivity(i);
//                    }else{
//                        passwordTextedite.setError("invalid password");
//                    }
//                });
//    }
}


