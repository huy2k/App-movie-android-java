package com.example.navigation.login;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.navigation.MainActivity;
import com.example.navigation.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class SignupTabFragment extends Fragment {

    EditText suEmail, suPass, suRepass, suName;
   // Button btn_signup;
    TextView btn_signup;
    float v=0;
    private String type;
    private DatabaseReference db;
    private FirebaseAuth mAuth;
    FirebaseUser user;


    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        ViewGroup root = (ViewGroup) inflater.inflate(R.layout.signup_tab_fragment, container, false);

        suEmail = root.findViewById(R.id.su_email);
        suPass = root.findViewById(R.id.su_pass);
        suRepass = root.findViewById(R.id.su_repass);
        suName = root.findViewById(R.id.su_name);
        btn_signup = root.findViewById(R.id.button_su);

        suEmail.setTranslationX(800);
        suPass.setTranslationX(800);
        suRepass.setTranslationX(800);
        suName.setTranslationX(800);
        btn_signup.setTranslationX(800);

        suRepass.setAlpha(v);
        suPass.setAlpha(v);
        suRepass.setAlpha(v);
        suName.setAlpha(v);
        btn_signup.setAlpha(v);

        suEmail.animate().translationX(0).alpha(1).setDuration(800).setStartDelay(300).start();
        suPass.animate().translationX(0).alpha(1).setDuration(800).setStartDelay(700).start();
        suRepass.animate().translationX(0).alpha(1).setDuration(800).setStartDelay(700).start();
        suName.animate().translationX(0).alpha(1).setDuration(800).setStartDelay(500).start();
        btn_signup.animate().translationX(0).alpha(1).setDuration(800).setStartDelay(900).start();

        mAuth = FirebaseAuth.getInstance();
        db =  FirebaseDatabase.getInstance().getReference().child("users");


        btn_signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = suEmail.getText().toString();
                String password = suPass.getText().toString();
                String name = suName.getText().toString();
                String repassword = suRepass.getText().toString();
                if (!TextUtils.isEmpty(email)) {
                    if (!TextUtils.isEmpty(password)) {
                        if (!TextUtils.isEmpty(name)) {
                            if (!TextUtils.isEmpty(repassword)) {
                                if (repassword.equals(password)) {
                                    SignUp(email, password, name);
                                } else {
                                    suRepass.setError("Doesn't match Password");
                                }
                            } else
                                suPass.setError("reEnter your password");
                        } else
                            suName.setError("Enter name");
                    } else
                        suPass.setError("Enter Password");
                } else
                    suEmail.setError("Enter email");
            }
        });

        return root;
    }


    public void SignUp(String email, String pass, final String name)
    {
        mAuth.createUserWithEmailAndPassword(email, pass)
                .addOnCompleteListener(getActivity(), new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            Toast.makeText(getActivity(), "Done",
                                    Toast.LENGTH_SHORT).show();
                            db.child(task.getResult().getUser().getUid()).child("name").setValue(name);
                            Intent i = new Intent(getActivity(), MainActivity.class);
                            startActivity(i);
                        } else {
                            Toast.makeText(getActivity(), task.getException().getMessage(),
                                    Toast.LENGTH_LONG).show();
                        }
                    }
                });
    }

    public void updateProfile(String pass, final String name)
    {
        user.updatePassword(pass)
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        db.child(user.getUid()).child("name").setValue(name);
                        Intent i = new Intent(getActivity(), MainActivity.class);
                        startActivity(i);
                    }else{
                        suPass.setError("invalid password");
                    }
                });
    }
}
