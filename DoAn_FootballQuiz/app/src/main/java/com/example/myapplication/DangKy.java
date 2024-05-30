package com.example.myapplication;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class DangKy extends AppCompatActivity {

    EditText TaiKhoan,MatKhau;
    TextView ThongBao;
    Button DangKy;
    private DatabaseHelper databaseHelper;
    private User user;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dang_ky);
        databaseHelper = new DatabaseHelper(this);
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();
        setControl();
        Event();
    }
    public void setControl()
    {
        TaiKhoan = findViewById(R.id.edtTKDK);
        MatKhau = findViewById(R.id.edtMKDK);
        DangKy = findViewById(R.id.btnDangKy);
        ThongBao = findViewById(R.id.tvTaiKhoan);
    }
    public void Event(){
        DangKy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(TaiKhoan.getText().length() != 0 && MatKhau.getText().length() != 0){
                    boolean ktra = databaseHelper.kiemTraUser(TaiKhoan.getText().toString());
                    if(ktra == false){
                        user = new User(TaiKhoan.getText().toString(),MatKhau.getText().toString(),0);
                        Toast.makeText(com.example.myapplication.DangKy.this,"Đăng kí thành công",Toast.LENGTH_SHORT).show();
                        databaseHelper.addUser(user);
                        Intent intent = new Intent(com.example.myapplication.DangKy.this,MainActivity.class);
                        startActivity(intent);
                    }
                    else {
                        ThongBao.setText("Tài khoản đã tồn tại");
                        TaiKhoan.setText("");
                        MatKhau.setText("");
                    }
                }else {
                    ThongBao.setText("Chưa nhập thông tin");
                    TaiKhoan.setText("");
                    MatKhau.setText("");
                }

            }
        });

    }
}