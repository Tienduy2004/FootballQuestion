package com.example.myapplication;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class XepHang extends AppCompatActivity {
    TextView nameTop1,nameTop2,nameTop3,diemTop1,diemTop2,diemTop3,DiemCuaToi;
    private DatabaseHelper databaseHelper;
    private List<User> users = new ArrayList<>();
    private int idUser;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_xep_hang);
        databaseHelper = new DatabaseHelper(this);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("Xếp hạng");
        if (actionBar != null) {
            actionBar.setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.xanhla)));
        }
        setControl();
        Event();
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_exit, menu);
        return super.onCreateOptionsMenu(menu);
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId()==R.id.mmExit){
            finish();
        }
        return super.onOptionsItemSelected(item);
    }
    private void setControl(){
        nameTop1 = findViewById(R.id.tvnameTop1);
        nameTop2 = findViewById(R.id.tvnameTop2);
        nameTop3 = findViewById(R.id.tvnameTop3);
        diemTop1 = findViewById(R.id.tvdiemTop1);
        diemTop2 = findViewById(R.id.tvdiemTop2);
        diemTop3 = findViewById(R.id.tvdiemTop3);
        DiemCuaToi = findViewById(R.id.tvXepHangDiem);
    }
    private void Event(){
        Intent intent = getIntent();
        idUser = intent.getIntExtra("idUser",-1);
        User user = databaseHelper.getUserById(idUser);
        DiemCuaToi.setText("Điểm của bạn: "+ String.valueOf(user.getDiem()));
        users = databaseHelper.getAllUsers();
        nameTop1.setText(users.get(0).getUserName());
        nameTop2.setText(users.get(1).getUserName());
        nameTop3.setText(users.get(2).getUserName());
        diemTop1.setText(String.valueOf(users.get(0).getDiem()));
        diemTop2.setText(String.valueOf(users.get(1).getDiem()));
        diemTop3.setText(String.valueOf(users.get(2).getDiem()));
    }
}