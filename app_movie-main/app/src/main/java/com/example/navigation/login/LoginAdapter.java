package com.example.navigation.login;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager2.adapter.FragmentStateAdapter;

public class LoginAdapter extends FragmentStateAdapter {


    LoginTabFragment loginTabFragment;
    SignupTabFragment signupTabFragment;

    public LoginAdapter(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch (position){
            case 0:
                this.loginTabFragment = new LoginTabFragment();
                return loginTabFragment;
            default:
                this.signupTabFragment = new SignupTabFragment();
                return signupTabFragment;
        }
    }

    @Override
    public int getItemCount() {
        return 2;
    }
}
