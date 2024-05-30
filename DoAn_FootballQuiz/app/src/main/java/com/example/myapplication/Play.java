package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.material.navigation.NavigationView;

public class Play extends AppCompatActivity {


    Button btnChoi;
    NavigationView navView;
    DrawerLayout drawerLayout;
    ActionBarDrawerToggle drawerToggle;
    TextView username,tvDiem;
    private int idUser;
    private User user;
    private DatabaseHelper databaseHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play);
        databaseHelper = new DatabaseHelper(this);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("Football Quiz");
        if (actionBar != null) {
            actionBar.setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.xanhla)));
        }
        setControl();
        Event();
    }
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (drawerToggle.onOptionsItemSelected(item)) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    // Hàm này giúp đồng bộ trạng thái của DrawerLayout khi Activity được tạo lại
    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        drawerToggle.syncState();
    }
    private void setControl(){
        //setting = findViewById(R.id.setting);
        btnChoi = findViewById(R.id.btnchoi);
        navView = findViewById(R.id.navigation_viewPlay);
        drawerLayout = findViewById(R.id.drawerLayoutPlay);
        View headerView = navView.getHeaderView(0);
        username = headerView.findViewById(R.id.tvusername);
        tvDiem = headerView.findViewById(R.id.tvDiemPlayer);
    }
   private void Event(){
       Intent intent = getIntent();
       idUser = intent.getIntExtra("idUser", -1);
       user = databaseHelper.getUserById(idUser);
       username.setText(user.getUserName());
       tvDiem.setText("Điểm: "+ String.valueOf(user.getDiem()));
       drawerToggle = new ActionBarDrawerToggle(Play.this, drawerLayout, R.string.app_name, R.string.app_name);
       drawerLayout.addDrawerListener(drawerToggle);
       drawerToggle.syncState();
       // Tự động hiển thị nút back trên ActionBar
       if (getSupportActionBar() != null) {
           getSupportActionBar().setDisplayHomeAsUpEnabled(true);
       }
       navView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
           @Override
           public boolean onNavigationItemSelected(@NonNull MenuItem item) {
               //Fragment fragment = null;
               if (item.getItemId() == R.id.menu_themPlay) {
                   Intent intent = new Intent(Play.this,ThemChuDe.class);
                   startActivity(intent);
               }
               if (item.getItemId() == R.id.menu_separatorPlay) {
                   Intent intent = new Intent(Play.this,ChuDe1.class);
                   intent.putExtra("idUser",idUser);
                   startActivity(intent);
               }
               if (item.getItemId() == R.id.menu_xephangPlay) {
                   Intent intent = new Intent(Play.this,XepHang.class);
                   intent.putExtra("idUser",idUser);
                   startActivity(intent);
               }
               if (item.getItemId() == R.id.menu_dangxuatPlay) {
                   Intent intent = new Intent(Play.this,MainActivity.class);
                   startActivity(intent);

               }
               drawerLayout.closeDrawers();
               return false;
           }
       });
       btnChoi.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               Intent intent = new Intent(Play.this, BatDauChoi.class);
               intent.putExtra("idUser",idUser);
               startActivity(intent);
           }
       });
   }

}