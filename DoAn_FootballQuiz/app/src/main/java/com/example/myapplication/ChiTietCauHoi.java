package com.example.myapplication;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class ChiTietCauHoi extends AppCompatActivity {

    TextView tvSTTCauHoi,tvItem;
    EditText edtCauHoi,edtDapAnA,edtDapAnB,edtDapAnC,edtDapAnD;
    RadioButton rdDapAnA,rdDapAnB,rdDapAnC,rdDapAnD;
    RadioGroup radioGroup;
    Spinner spLoaiSP;
    ImageView ivHinh;
    List<String> data_LSP = new ArrayList<>();
    ArrayAdapter adapter_LSP;
    Button btnSua,btnXoa,btnQuayLai;
    private DatabaseHelper databaseHelper;
    private int chuDeIndex;
    private int idUser;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chi_tiet_cau_hoi);
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();
        databaseHelper = new DatabaseHelper(this);
        setControl();
        Event();
    }
    public void setControl(){
        tvSTTCauHoi = findViewById(R.id.tvsttCauHoi);
        edtCauHoi = findViewById(R.id.tvNdCauHoi);
        edtDapAnA = findViewById(R.id.edtNDDAnA);
        edtDapAnB = findViewById(R.id.edtNDDAnB);
        edtDapAnC = findViewById(R.id.edtNDDAnC);
        edtDapAnD = findViewById(R.id.edtNDDAnD);
        btnSua = findViewById(R.id.btnSuaND);
        btnXoa = findViewById(R.id.btnXoaND);
        btnQuayLai = findViewById(R.id.btnQuaiLaiND);
        tvItem = findViewById(R.id.tvttItem);
        rdDapAnA = findViewById(R.id.rdctDapAnA);
        rdDapAnB = findViewById(R.id.rdctDapAnB);
        rdDapAnC = findViewById(R.id.rdctDapAnC);
        rdDapAnD = findViewById(R.id.rdctDapAnD);
        ivHinh = findViewById(R.id.ivHinhCT);
        spLoaiSP = findViewById(R.id.spLoaiSPCT);
        radioGroup = findViewById(R.id.radioGroupCT);

    }
    public void Event(){
        Intent intent = getIntent();
        idUser = intent.getIntExtra("idUser", -1);
        if(intent != null && intent.hasExtra("n")) {
            chuDeIndex = intent.getIntExtra("n", -1);
            if(ThemCauHoi.chuDeID != -1){
                CauHoi item = databaseHelper.printCauHoiById(ThemCauHoi.chuDeID,chuDeIndex);
                if(item != null){
//                    String str = "Nội dung câu hỏi: "+item.getTenCauHoi().toString() +"\nĐán án A: "+ item.getDapAnA().getNoiDungDapAn().toString() + "\nĐán án B: "+ item.getDapAnB().getNoiDungDapAn().toString() +"\nĐán án C: "+ item.getDapAnB().getNoiDungDapAn().toString() + "\nĐán án D: "+ item.getDapAnD().getNoiDungDapAn().toString();
//                    tvSTTCauHoi.setText(item.getName().toString());
//                    tvItem.setText(str);
                    edtCauHoi.setText(item.getTenCauHoi());
                    edtDapAnA.setText(item.getDapAnA().getNoiDungDapAn());
                    edtDapAnB.setText(item.getDapAnB().getNoiDungDapAn());
                    edtDapAnC.setText(item.getDapAnC().getNoiDungDapAn());
                    edtDapAnD.setText(item.getDapAnD().getNoiDungDapAn());
                    rdDapAnA.setChecked(item.getDapAnA().isKtra());
                    rdDapAnB.setChecked(item.getDapAnB().isKtra());
                    rdDapAnC.setChecked(item.getDapAnC().isKtra());
                    rdDapAnD.setChecked(item.getDapAnD().isKtra());
                }
                KhoiTao();
                adapter_LSP = new ArrayAdapter<>(ChiTietCauHoi.this, android.R.layout.simple_list_item_1,data_LSP);
                spLoaiSP.setAdapter(adapter_LSP);

                for (int i = 0;i < 3;i++){
                    if(item.getAnhCauHoi().equals(data_LSP.get(i))) {
                        spLoaiSP.setSelection(i);
                    }
                }
                spLoaiSP.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                        if(spLoaiSP.getSelectedItem().equals("Cầu thủ")){
                            ivHinh.setImageResource(R.drawable.andanh);
                        }
                        if(spLoaiSP.getSelectedItem().equals("Cup")){
                            ivHinh.setImageResource(R.drawable.cup);
                        }
                        if(spLoaiSP.getSelectedItem().equals("Sân bóng")){
                            ivHinh.setImageResource(R.drawable.sanbong);
                        }
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> adapterView) {

                    }
                });
                btnXoa.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        databaseHelper.xoaCauHoi(ThemCauHoi.chuDeID,chuDeIndex);
                        databaseHelper.capNhatLaiIDCauHoi();
                        Intent intent = new Intent(ChiTietCauHoi.this, ChuDe1.class);
                        intent.putExtra("idUser",idUser);
                        startActivity(intent);
                        finish();
                    }
                });
                btnSua.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        int selectedRadioButtonId = radioGroup.getCheckedRadioButtonId();
                        if(edtCauHoi.getText().toString().length() != 0 && edtDapAnA.getText().toString().length() != 0 && edtDapAnB.getText().toString().length() != 0 && edtDapAnC.getText().toString().length() != 0 && edtDapAnD.getText().toString().length() != 0 && selectedRadioButtonId != View.NO_ID){
                            CauHoi cauHoi = new CauHoi();
                            cauHoi.setTenCauHoi(edtCauHoi.getText().toString());
                            cauHoi.setDapAnA( new DapAn(edtDapAnA.getText().toString(),false));
                            cauHoi.setDapAnB( new DapAn(edtDapAnB.getText().toString(),false));
                            cauHoi.setDapAnC( new DapAn(edtDapAnC.getText().toString(),false));
                            cauHoi.setDapAnD( new DapAn(edtDapAnD.getText().toString(),false));
                            if(rdDapAnA.isChecked() == true){
                                cauHoi.setDapAnA( new DapAn(edtDapAnA.getText().toString(),true));
                            }
                            if(rdDapAnB.isChecked() == true){
                                cauHoi.setDapAnB( new DapAn(edtDapAnB.getText().toString(),true));
                            }
                            if(rdDapAnC.isChecked() == true){
                                cauHoi.setDapAnC( new DapAn(edtDapAnC.getText().toString(),true));
                            }
                            if(rdDapAnD.isChecked() == true){
                                cauHoi.setDapAnD( new DapAn(edtDapAnD.getText().toString(),true));
                            }
                            cauHoi.setAnhCauHoi(spLoaiSP.getSelectedItem().toString());
                            databaseHelper.suaCauHoi(cauHoi,ThemCauHoi.chuDeID,chuDeIndex);
                            finish();
                        }else {
                            Toast.makeText(ChiTietCauHoi.this,"Sửa không thành công",Toast.LENGTH_SHORT).show();
                        }


                    }
                });

                btnQuayLai.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        finish();

                    }
                });
            }
            else {
                Toast.makeText(this, "Invalid ID_CAUHOI", Toast.LENGTH_SHORT).show();
            }
        }else {

        }
    }
    public void KhoiTao(){
        data_LSP.add("Cầu thủ");
        data_LSP.add("Cup");
        data_LSP.add("Sân bóng");
    }

}