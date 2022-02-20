package com.example.navigation;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.os.Message;
import android.view.MenuItem;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;

import com.example.navigation.Connection.ConnectionChecker;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

public class MainActivity extends AppCompatActivity {

    private BottomNavigationView navigationView;
    private ViewPager viewPager;
    private RecyclerView rcvCategory;
    ConnectionChecker checker = new ConnectionChecker();
//    private CategoryAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE); //will hide the title
        getSupportActionBar().hide(); // hide the title bar
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
//        setContentView(R.layout.activity_main);
        if (checker.checkConnection(this)) {
            setContentView(R.layout.activity_main);
            navigationView = findViewById(R.id.nav_bottom);
            viewPager = findViewById(R.id.view_pager);
//        rcvCategory = findViewById(R.id.rcv_category);
//        adapter = new CategoryAdapter();
//        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this , RecyclerView.HORIZONTAL,false);

            setUpViewPager();
            navigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                    switch (item.getItemId()){
                        case R.id.home:

//                            Toast.makeText(MainActivity.this, "Home", Toast.LENGTH_SHORT).show();
                            viewPager.setCurrentItem(0);
                            break;
                        case R.id.category:
//                            Toast.makeText(MainActivity.this, "Notification", Toast.LENGTH_SHORT).show();
                            viewPager.setCurrentItem(1);
                            break;
                        case R.id.search:
//                            Toast.makeText(MainActivity.this, "Notification", Toast.LENGTH_SHORT).show();
                            viewPager.setCurrentItem(2);
                            break;
                        case R.id.favorite:
//                            Toast.makeText(MainActivity.this, "Favorite", Toast.LENGTH_SHORT).show();
                            viewPager.setCurrentItem(3);
                            break;
                        case R.id.user:
//                            Toast.makeText(MainActivity.this, "User", Toast.LENGTH_SHORT).show();
                            viewPager.setCurrentItem(4);
                            break;
                    }
                    return true;
                }
            });

        } else {
            Toast.makeText(this, "Please check your internet connection", Toast.LENGTH_SHORT).show();
            setContentView(R.layout.no_connection);
        }

    }
    private void setUpViewPager() {
        ViewPagerAdapter viewPagerAdapter = new ViewPagerAdapter(getSupportFragmentManager(), FragmentStatePagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        viewPager.setAdapter(viewPagerAdapter);
    }
}