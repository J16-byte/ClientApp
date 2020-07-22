package com.example.respodentapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toolbar;

import com.example.respodentapp.ui.login.LoginActivity;
import com.google.android.material.navigation.NavigationView;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    Animation top, bottom;
    ImageView logo;
    Button locationbutton;
    Button responcebutton;
    TextView name;
    DrawerLayout drawerLayout;
  //  NavigationView navigationView;


    androidx.appcompat.widget.Toolbar toolbar;
    ActionBarDrawerToggle toogle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setTitle("Respondent");

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);
        top= AnimationUtils.loadAnimation(this,R.anim.top_animtion);
        bottom= AnimationUtils.loadAnimation(this,R.anim.bottom_animation);
        logo=findViewById( R.id.image_View );
        locationbutton=findViewById( R.id.locbutton );
        responcebutton=findViewById(R.id.respondbutton);
        name=findViewById( R.id.textView );
        toolbar=findViewById(R.id.toolbar);
        toolbar.setNavigationIcon(R.drawable.ic_baseline_menu_24);

        logo.setAnimation(top);
        name.setAnimation(bottom);
        locationbutton.setAnimation(bottom);
        drawerLayout=findViewById(R.id.drawer_layout);

        toogle= new ActionBarDrawerToggle(this,drawerLayout,toolbar,R.string.nav_app_bar_open_drawer_description,R.string.nav_app_bar_navigate_up_description);
        drawerLayout.addDrawerListener(toogle);
        toogle.setDrawerIndicatorEnabled(true);
        toogle.syncState();
        init();
        locationbutton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                // Assume Login in was successful
                Intent intent = new Intent(MainActivity.this, MapsActivity.class);
                MainActivity.this.startActivity(intent);
                // Login();


            }
        });
    }

    private void init(){


      //  navigationView.setNavigationItemSelectedListener(this);

    }
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.profile:{

                break;
            }
            case R.id.Car:{
                break;
            }
            case R.id.Location:{
                Intent intent = new Intent(MainActivity.this, MapsActivity.class);
                MainActivity.this.startActivity(intent);
                break;
            }
            case R.id.Case_History:{
                break;
            }
            case R.id.logout:{
                break;
            }

        }
        item.setChecked(true);
       // drawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }
}