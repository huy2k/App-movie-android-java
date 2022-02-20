package com.example.navigation.login;

import android.app.Activity;
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

import androidx.fragment.app.Fragment;

import com.example.navigation.MainActivity;
import com.example.navigation.R;

import com.google.firebase.auth.FirebaseAuth;

public class LoginTabFragment extends Fragment {

    private FirebaseAuth mAuth;
    private EditText email, pass;
    protected TextView fgpass;
    //protected Button btn_login;
    TextView btn_login;
    float v=0;

    public LoginTabFragment(){

    }
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        ViewGroup root = (ViewGroup) inflater.inflate(R.layout.login_tab_fragment, container, false);

        email = root.findViewById(R.id.email);
        pass = root.findViewById(R.id.pass);
        fgpass = root.findViewById(R.id.fg_pass);
        btn_login = root.findViewById(R.id.button);

        email.setTranslationX(800);
        pass.setTranslationX(800);
        fgpass.setTranslationX(800);
        btn_login.setTranslationX(800);

        email.setAlpha(v);
        pass.setAlpha(v);
        fgpass.setAlpha(v);
        btn_login.setAlpha(v);

        email.animate().translationX(0).alpha(1).setDuration(800).setStartDelay(300).start();
        pass.animate().translationX(0).alpha(1).setDuration(800).setStartDelay(500).start();
        fgpass.animate().translationX(0).alpha(1).setDuration(800).setStartDelay(500).start();
        btn_login.animate().translationX(0).alpha(1).setDuration(800).setStartDelay(700).start();


        mAuth = FirebaseAuth.getInstance();
        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String Email = email.getText().toString();
                String passWord = pass.getText().toString();

                if (!TextUtils.isEmpty(Email) && !TextUtils.isEmpty(passWord)) {
                    Login(Email, passWord);
                } else {
                    email.setError("Required");
                    pass.setError("Required");
                }
            }
        });

        return root;
    }

    public void Login(String Email, String passWord) {
        mAuth.signInWithEmailAndPassword(Email, passWord)
                .addOnCompleteListener(getActivity(), task -> {
                    if (task.isSuccessful()) {
                        Toast.makeText(getActivity(), "Done",
                                Toast.LENGTH_SHORT).show();
                        Intent i = new Intent(getActivity(), MainActivity.class);
                        startActivity(i);
                    } else {
                        Toast.makeText(getActivity(), "sign Failed.",
                                Toast.LENGTH_SHORT).show();
                        email.setError("Invalid");
                        pass.setError("Invalid");
                    }
                });
    }
}
