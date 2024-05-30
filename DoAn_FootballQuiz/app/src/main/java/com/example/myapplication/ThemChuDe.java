package com.example.myapplication;



import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import java.util.List;

public class ThemChuDe extends AppCompatActivity {
    private EditText edtTen;
    private Button btnThem, btnLamMoi, btnQuayLai;
    private DatabaseHelper databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_them_chu_de);
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();
        databaseHelper = new DatabaseHelper(this);
        setControl();
        Event();
    }

    private void setControl() {
        edtTen = findViewById(R.id.edtTenChuDe);
        btnThem = findViewById(R.id.btnThemCD);
        btnLamMoi = findViewById(R.id.btnLamMoiChuDe);
        btnQuayLai = findViewById(R.id.btnQuayLaiChuDe);
    }

    private void Event() {
        btnThem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (edtTen.length() != 0) {
                    ChuDe chuDe = new ChuDe(edtTen.getText().toString());
                    databaseHelper.addChuDe(chuDe);
                    Toast.makeText(ThemChuDe.this,"Thêm thành công",Toast.LENGTH_SHORT).show();
                    updateChuDeList();
                    edtTen.setText("");
                }

            }
        });

        btnLamMoi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                edtTen.setText("");
            }
        });

        btnQuayLai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }

    private void updateChuDeList() {
        List<ChuDe> updatedList = databaseHelper.getAllChuDe();
        ChuDe1.getChuDe().clear();
        ChuDe1.getChuDe().addAll(updatedList);
        if (ChuDe1.adapter != null) {
            ChuDe1.adapter.notifyDataSetChanged();
        }
        //ChuDe1.adapter.notifyDataSetChanged();
    }

}