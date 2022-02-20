package com.example.navigation;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;


import com.example.navigation.fragment.FavoriteFra;
import com.example.navigation.fragment.HomeFra;
import com.example.navigation.fragment.MainFragment;
import com.example.navigation.fragment.NotificationFra;
import com.example.navigation.fragment.UserFra;


public class ViewPagerAdapter extends FragmentStatePagerAdapter {


    public ViewPagerAdapter(@NonNull FragmentManager fm, int behavior) {
        super(fm, behavior);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 1:
                return new HomeFra();
            case 2:
                return new NotificationFra();
            case 3:
                return new FavoriteFra();
            case 4:
                return new UserFra();
            default:
                return new MainFragment();
        }

    }

    @Override
    public int getCount() {
        return 5;
    }

//    @NonNull
//    @Override
//   public Fragment createFragment(int position) {
////
////         }
//    }
//
//    @Override
//    public int getItemCount() {
//        return 3;
//    }
}
