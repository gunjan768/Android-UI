package com.example.substringcolor;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.substringcolor.Fragment.ChatFragment;
import com.example.substringcolor.Fragment.MessageFragment;
import com.example.substringcolor.Fragment.ProfileFragment;
import com.google.android.material.navigation.NavigationView;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener, ProfileFragment.SwitchActivity
{
    @Override
    public void changeActivityHandler()
    {
        Intent intent = new Intent(this, OtherActivity.class);
        startActivity(intent);
    }

    private DrawerLayout drawerLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        drawerLayout = findViewById(R.id.drawer_layout);

        NavigationView navigationView = findViewById(R.id.nav_view);

        // You can call OnNavigationItemSelectedListener and all related methods will be override directly below it.
//        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener()
//        {
//            @Override
//            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem)
//            {
//                return false;
//            }
//        });


        // Second method is used to implements the interface and then all it's method will be override but you can put that methods anywhere inside this activity.
        // Here the second method is used.
        navigationView.setNavigationItemSelectedListener(this);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.navigation_drawer_open,
                R.string.navigation_drawer_open
        );

        drawerLayout.addDrawerListener(toggle);

        // This will add hamburger for the drawer menu.
        toggle.syncState();

        // When you will open the app for the first time then savedInstanceState will be null. Per after that initial load, say you rotate the device then
        // savedInstanceState will not be null. After rotating onCreate() method will be called again hence again the same fragment will get load that was
        // loaded previously before the rotation. Hence it will lead to memory kill so to avoid that we first checked the condition then load.
        if(savedInstanceState == null)
        {
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new MessageFragment()).commit();

            // This will select the Message fragment option in the drawer menu ( Initially required to know which fragment will load on app start ).
            navigationView.setCheckedItem(R.id.nav_message);
        }
    }

    @Override
    public void onBackPressed()
    {
        // Since our drawer is in Start. If it's in End then use 'END'.
        if(drawerLayout.isDrawerOpen(GravityCompat.START))
        {
            drawerLayout.closeDrawer(GravityCompat.START);
        }
        else
        {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem)
    {
        switch(menuItem.getItemId())
        {
            case R.id.nav_message:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new MessageFragment()).commit();

                break;

            case R.id.nav_chat:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new ChatFragment()).commit();

                break;

            case R.id.nav_profile:
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new ProfileFragment()).commit();

            break;

            case R.id.nav_share:
                Toast.makeText(this, "Share", Toast.LENGTH_SHORT).show();

                break;

            case R.id.nav_send:
                Toast.makeText(this, "Send", Toast.LENGTH_SHORT).show();

                break;

        }

        drawerLayout.closeDrawer(GravityCompat.START);

        // Returning true means any one of the item is selected ( which is triggered ).
        return true;
    }
}