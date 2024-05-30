package com.example.myapplication;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    EditText TaiKhoan,MatKhau;
    TextView ThongBao;
    Button DangNhap,DangKy;
    private DatabaseHelper databaseHelper;
    private User user;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();
        databaseHelper = new DatabaseHelper(this);
        SetControl();
        Event();
    }
    public void SetControl()
    {
        TaiKhoan = findViewById(R.id.edtTK);
        MatKhau = findViewById(R.id.edtMK);
        DangNhap = findViewById(R.id.btnDangNhap);
        ThongBao = findViewById(R.id.tvSaiTKMK);
        DangKy = findViewById(R.id.btnDangKyDN);
    }
    public void Event(){
        DangNhap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String strTK = TaiKhoan.getText().toString();
                String strMK = MatKhau.getText().toString();
                user = databaseHelper.ktraDangNhap(strTK,strMK);

                if(strTK.equals("admin") && strMK.equals("1")){
                    Intent intent = new Intent(MainActivity.this, Play.class);
                    intent.putExtra("idUser",user.getId());
                    startActivity(intent);

                    ThongBao.setText("");
                }else if(strTK.equals(user.getUserName()) && strMK.equals(user.getPassWord()))
                {
                    Intent intent = new Intent(MainActivity.this, Player.class);
                    intent.putExtra("idUser",user.getId());
                    startActivity(intent);
                    ThongBao.setText("");
                }
                else
                {
                    ThongBao.setText("Sai tài khoản hoặc mật khẩu");
                }
            }
        });
        DangKy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, com.example.myapplication.DangKy.class);
                startActivity(intent);
            }
        });
    }
}