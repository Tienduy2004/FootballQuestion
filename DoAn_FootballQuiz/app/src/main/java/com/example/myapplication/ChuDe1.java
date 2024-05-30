package com.example.myapplication;



import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;


import com.google.android.material.navigation.NavigationView;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class ChuDe1 extends AppCompatActivity {
    private static List<ChuDe> itemList;
    public static ChuDeAdater adapter;
    private static ListView lvChuDe;
    private DatabaseHelper databaseHelper;
    NavigationView navView;
    DrawerLayout drawerLayout;
    ActionBarDrawerToggle drawerToggle;
    private int idUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chu_de1);
        databaseHelper = new DatabaseHelper(this);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("Danh sách chủ đề");
        if (actionBar != null) {
            actionBar.setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.xanhla)));
        }
        setControl();
        setEvent();
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

    private void setControl() {
        lvChuDe = findViewById(R.id.lvChuDe);
        navView = findViewById(R.id.navigation_view);
        drawerLayout = findViewById(R.id.drawerLayout);
    }
    private void setEvent() {
        Intent intent = getIntent();
        idUser = intent.getIntExtra("idUser", -1);
        drawerToggle = new ActionBarDrawerToggle(ChuDe1.this, drawerLayout, R.string.app_name, R.string.app_name);
        drawerLayout.addDrawerListener(drawerToggle);
        drawerToggle.syncState();
        // Tự động hiển thị nút back trên ActionBar
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        navView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                Fragment fragment = null;
                if (item.getItemId() == R.id.menu_them) {
                    Intent intent = new Intent(ChuDe1.this,ThemChuDe.class);
                    startActivity(intent);

                }
                if (item.getItemId() == R.id.menu_separator) {
                    Intent intent = new Intent(ChuDe1.this,DanhSachChuDe.class);
                    startActivity(intent);
                }
                if (item.getItemId() == R.id.menu_settings) {
                    Intent intent = new Intent(ChuDe1.this,Play.class);
                    intent.putExtra("idUser",idUser);
                    startActivity(intent);
                }
                if(fragment != null){

                }
                drawerLayout.closeDrawers();
                return false;
            }
        });
        itemList = databaseHelper.getAllChuDe();
        if (itemList == null) {
            itemList = new ArrayList<>();
        }
        adapter = new ChuDeAdater(ChuDe1.this, R.layout.dong_chude, itemList);
        lvChuDe.setAdapter(adapter);

        lvChuDe.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // Lấy ID của chủ đề được chọn
                int chuDeID = position + 1;

                // Chuyển ID này đến hoạt động mới (ThemCauHoi)
                Intent intent = new Intent(ChuDe1.this, ThemCauHoi.class);
                intent.putExtra("ChuDeID", itemList.get(position).getID());
                intent.putExtra("idUser",idUser);
                startActivity(intent);
            }
        });
    }



    public static List<ChuDe> getChuDe() {
        if (itemList == null) {
            itemList = new ArrayList<>(); // Initialize the list if it's null
        }
        return itemList;
    }


}