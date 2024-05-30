package com.example.myapplication;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class KetQua extends AppCompatActivity {

    TextView tvDiem;
    Button ChoiLai;
    private int idUser;
    private int Diem;
    private DatabaseHelper databaseHelper;
    private User user;
    String admin;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ket_qua);
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();
        databaseHelper = new DatabaseHelper(this);
        setControl();
        Event();
    }
    private void setControl(){
        tvDiem = findViewById(R.id.tvkqDiem);
        ChoiLai = findViewById(R.id.ChoiLai);
    }
    private void Event(){
        Intent intent = getIntent();
        Diem = intent.getIntExtra("Diem", -1);
        idUser = intent.getIntExtra("idUser", -1);
        user = databaseHelper.getUserById(idUser);
        admin = "";
        admin = user.getUserName();
        tvDiem.setText(Diem+"đ");
        if(Diem > user.getDiem())
        {
            databaseHelper.updateDiem(idUser,Diem);
        }
        ChoiLai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(admin.equals("admin")){
                    Intent intent1 = new Intent(KetQua.this,Play.class);
                    intent1.putExtra("idUser",idUser);
                    startActivity(intent1);
                }else {
                    Intent intent1 = new Intent(KetQua.this,Player.class);
                    intent1.putExtra("idUser",idUser);
                    startActivity(intent1);
                }

            }
        });
    }
}