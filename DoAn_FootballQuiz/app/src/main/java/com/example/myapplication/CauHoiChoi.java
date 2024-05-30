package com.example.myapplication;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class CauHoiChoi extends AppCompatActivity {

    TextView tvNoiDungCauHoi,Diem;
    Button DapAnA, DapAnB, DapAnC, DapAnD;
    ImageView iHinh;
    private int idChuDe;
    private int stt = 1;
    DatabaseHelper databaseHelper;
    private CauHoi cauHoi;
    private int size = 0;
    private int idUser;
    private User user;
    private int TinhDiem;
    private String soDiem;
    private String strDiem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cau_hoi_choi);
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();
        databaseHelper = new DatabaseHelper(this);
        setControl();
        Event();
    }

    public void setControl() {
        tvNoiDungCauHoi = findViewById(R.id.tvNdCauHoiChoi);
        DapAnA = findViewById(R.id.btnDapAnA);
        DapAnB = findViewById(R.id.btnDapAnB);
        DapAnC = findViewById(R.id.btnDapAnC);
        DapAnD = findViewById(R.id.btnDapAnD);
        iHinh = findViewById(R.id.ivHinhChoi);
        Diem = findViewById(R.id.tvDiem);
    }

    public void Event() {
        Intent intent = getIntent();
        idChuDe = intent.getIntExtra("idchude", -1);
        idUser = intent.getIntExtra("idUser", -1);
        user = databaseHelper.getUserById(idUser);
        TinhDiem = 0;
        soDiem = String.valueOf(TinhDiem);
        strDiem = "Điểm: " + soDiem;
        Diem.setText(strDiem);
        size = databaseHelper.demSoLuongCauHoiTheoChuDe(idChuDe);
        if(stt <= size){
            cauHoi = databaseHelper.printCauHoiById(idChuDe, stt);
            tvNoiDungCauHoi.setText(cauHoi.getTenCauHoi());
            DapAnA.setText(cauHoi.getDapAnA().getNoiDungDapAn());
            DapAnB.setText(cauHoi.getDapAnB().getNoiDungDapAn());
            DapAnC.setText(cauHoi.getDapAnC().getNoiDungDapAn());
            DapAnD.setText(cauHoi.getDapAnD().getNoiDungDapAn());
            if(cauHoi.getAnhCauHoi().equals("Cầu thủ"))
            {
                iHinh.setImageResource(R.drawable.andanh);
            } else if (cauHoi.getAnhCauHoi().equals("Cup")) {
                iHinh.setImageResource(R.drawable.cup);
            } else if (cauHoi.getAnhCauHoi().equals("Sân bóng")) {
                iHinh.setImageResource(R.drawable.sanbong);
            }
            DapAnA.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (cauHoi.getDapAnA().isKtra()) {
                        stt++;
                        DapAnA.setBackgroundColor(getColor(R.color.xanh));
                        DapAnB.setBackgroundColor(getColor(R.color.xanh));
                        DapAnC.setBackgroundColor(getColor(R.color.xanh));
                        DapAnD.setBackgroundColor(getColor(R.color.xanh));
                        TinhDiem += 300;
                        loadNextQuestion();
                    } else {
                        TinhDiem -= 100;
                        soDiem = String.valueOf(TinhDiem);
                        strDiem = "Điểm: " + soDiem;
                        Diem.setText(strDiem);
                        DapAnA.setBackgroundColor(getColor(R.color.red));
                    }

                }
            });
            DapAnB.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (cauHoi.getDapAnB().isKtra()) {
                        stt++;
                        DapAnA.setBackgroundColor(getColor(R.color.xanh));
                        DapAnB.setBackgroundColor(getColor(R.color.xanh));
                        DapAnC.setBackgroundColor(getColor(R.color.xanh));
                        DapAnD.setBackgroundColor(getColor(R.color.xanh));
                        TinhDiem += 300;

                        loadNextQuestion();
                    } else {
                        TinhDiem -= 100;
                        soDiem = String.valueOf(TinhDiem);
                        strDiem = "Điểm: " + soDiem;
                        Diem.setText(strDiem);
                        DapAnB.setBackgroundColor(getColor(R.color.red));
                    }
                }
            });
            DapAnC.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (cauHoi.getDapAnC().isKtra()) {
                        stt++;
                        DapAnA.setBackgroundColor(getColor(R.color.xanh));
                        DapAnB.setBackgroundColor(getColor(R.color.xanh));
                        DapAnC.setBackgroundColor(getColor(R.color.xanh));
                        DapAnD.setBackgroundColor(getColor(R.color.xanh));
                        TinhDiem += 300;

                        loadNextQuestion();
                    } else {
                        TinhDiem -= 100;
                        soDiem = String.valueOf(TinhDiem);
                        strDiem = "Điểm: " + soDiem;
                        Diem.setText(strDiem);
                        DapAnC.setBackgroundColor(getColor(R.color.red));
                    }
                }
            });
            DapAnD.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (cauHoi.getDapAnD().isKtra()) {
                        stt++;
                        DapAnA.setBackgroundColor(getColor(R.color.xanh));
                        DapAnB.setBackgroundColor(getColor(R.color.xanh));
                        DapAnC.setBackgroundColor(getColor(R.color.xanh));
                        DapAnD.setBackgroundColor(getColor(R.color.xanh));
                        TinhDiem += 300;

                        loadNextQuestion();
                    } else {
                        TinhDiem -= 100;
                        soDiem = String.valueOf(TinhDiem);
                        strDiem = "Điểm: " + soDiem;
                        Diem.setText(strDiem);
                        DapAnD.setBackgroundColor(getColor(R.color.red));
                    }
                }
            });
            DapAnA.setBackgroundColor(getColor(R.color.xanh));
            DapAnB.setBackgroundColor(getColor(R.color.xanh));
            DapAnC.setBackgroundColor(getColor(R.color.xanh));
            DapAnD.setBackgroundColor(getColor(R.color.xanh));
        }else {
            Intent intent1 = new Intent(CauHoiChoi.this,Play.class);
            startActivity(intent1);
        }


    }

    public void loadNextQuestion() {
            if(stt <= size) {
                cauHoi = databaseHelper.printCauHoiById(idChuDe, stt);
            }else
            {
                Intent intent1 = new Intent(CauHoiChoi.this, KetQua.class);
                intent1.putExtra("idUser",idUser);
                intent1.putExtra("Diem",TinhDiem);
                startActivity(intent1);
            }
            if (cauHoi != null) {
                DapAn dapAnA = cauHoi.getDapAnA();
                DapAn dapAnB = cauHoi.getDapAnB();
                DapAn dapAnC = cauHoi.getDapAnC();
                DapAn dapAnD = cauHoi.getDapAnD();
                if(cauHoi.getAnhCauHoi().equals("Cầu thủ"))
                {
                    iHinh.setImageResource(R.drawable.andanh);
                } else if (cauHoi.getAnhCauHoi().equals("Cup")) {
                    iHinh.setImageResource(R.drawable.cup);
                } else if (cauHoi.getAnhCauHoi().equals("Sân bóng")) {
                    iHinh.setImageResource(R.drawable.sanbong);
                }
                if (dapAnA != null && dapAnB != null && dapAnC != null && dapAnD != null) {
                    tvNoiDungCauHoi.setText(cauHoi.getTenCauHoi());
                    DapAnA.setText(dapAnA.getNoiDungDapAn());
                    DapAnB.setText(dapAnB.getNoiDungDapAn());
                    DapAnC.setText(dapAnC.getNoiDungDapAn());
                    DapAnD.setText(dapAnD.getNoiDungDapAn());
                    databaseHelper.close();
                }
            }
        soDiem = String.valueOf(TinhDiem);
        strDiem = "Điểm: " + soDiem;
        Diem.setText(strDiem);
    }
}