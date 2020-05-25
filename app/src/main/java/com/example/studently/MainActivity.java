package com.example.studently;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.viewpager2.widget.ViewPager2;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import com.activeandroid.ActiveAndroid;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationView;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

public class MainActivity extends AppCompatActivity {

    private ViewPager2 viewPager2;
    private MainActivityPageAdapter mainActivityPageAdapter;
    private DrawerLayout drawer;
    public static int LastTabId;



    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        drawer = findViewById(R.id.drawer_layout);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = findViewById(R.id.navigation);

        // Onclick listener
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {

                    case R.id.navigation_schedule:
                        //Intent intent = new Intent(MainActivity.this, MainActivity.class);

                        break;
                    case R.id.navigation_tools:
                        startActivity(new Intent(MainActivity.this, ToolsActivity.class));
                        break;

                }
                return false;
            }
        });

        final TabLayout tabLayout = findViewById(R.id.tabLayout);

        viewPager2 = findViewById(R.id.viewpager2);

        mainActivityPageAdapter = new MainActivityPageAdapter(this);
        viewPager2.setAdapter(mainActivityPageAdapter);


        //check tab item on click
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @RequiresApi(api = Build.VERSION_CODES.M)
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager2.setCurrentItem(tab.getPosition());
                if (tab.getPosition() == 0) {
                    mainActivityPageAdapter.notifyDataSetChanged();

                    LastTabId = 0;
                } else if (tab.getPosition() == 1) {
                    mainActivityPageAdapter.notifyDataSetChanged();

                    LastTabId = 1;
                } else if (tab.getPosition() == 2) {
                    mainActivityPageAdapter.notifyDataSetChanged();

                    LastTabId = 2;
                } else if (tab.getPosition() == 3) {
                    mainActivityPageAdapter.notifyDataSetChanged();
                    LastTabId = 3;
                } else if (tab.getPosition() == 4) {
                    mainActivityPageAdapter.notifyDataSetChanged();
                    LastTabId = 4;
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });



        tabLayout.setTabMode(TabLayout.MODE_SCROLLABLE);


        ActiveAndroid.initialize(this);



    }


// when back is pressed the drawer is closed
    @Override
    public void onBackPressed() {
        if(drawer.isDrawerOpen(GravityCompat.START)){
            drawer.closeDrawer(GravityCompat.START);
        }else {
            super.onBackPressed();
        }
    }
}
