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
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class CauHoiThem extends AppCompatActivity {

    EditText edtCauHoi, edtDapAnA, edtDapAnB, edtDapAnC, edtDapAnD;
    RadioButton rdDapAnA, rdDapAnB, rdDapAnC, rdDapAnD;
    RadioGroup rdGroup;
    Button btnThem, btnLamMoi, btnQuayLai;
    Spinner spLoaiSP;
    ImageView ivHinh;
    List<String> data_LSP = new ArrayList<>();
    ArrayAdapter adapter_LSP;
    private DatabaseHelper databaseHelper;
    private int chuDeIndex;
    private int stt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cau_hoi_them);
        ActionBar actionBar = getSupportActionBar();
        databaseHelper = new DatabaseHelper(this);
        actionBar.hide();
        setControl();
        Event();
    }

    public void setControl() {
        edtCauHoi = findViewById(R.id.edtNhapCauHoi);
        edtDapAnA = findViewById(R.id.edtNhapDAnA);
        edtDapAnB = findViewById(R.id.edtNhapDAnB);
        edtDapAnC = findViewById(R.id.edtNhapDAnC);
        edtDapAnD = findViewById(R.id.edtNhapDAnD);
        btnThem = findViewById(R.id.btnThemCH);
        btnLamMoi = findViewById(R.id.btnLamMoiCauHoi);
        btnQuayLai = findViewById(R.id.btnQuayLaiCauHoi);
        rdDapAnA = findViewById(R.id.rdDapAnA);
        rdDapAnB = findViewById(R.id.rdDapAnB);
        rdDapAnC = findViewById(R.id.rdDapAnC);
        rdDapAnD = findViewById(R.id.rdDapAnD);
        spLoaiSP = findViewById(R.id.spLoaiSP);
        ivHinh = findViewById(R.id.ivHinh);
        rdGroup = findViewById(R.id.radioGroup);
    }

    public void Event() {
        KhoiTao();
        adapter_LSP = new ArrayAdapter(CauHoiThem.this, android.R.layout.simple_list_item_1,data_LSP);
        spLoaiSP.setAdapter(adapter_LSP);
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
        chuDeIndex = getIntent().getIntExtra("ChuDeID", -1);
        btnThem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int selectedRadioButtonId = rdGroup.getCheckedRadioButtonId();
                if(edtCauHoi.getText().toString().length() != 0 && edtDapAnA.getText().toString().length() != 0 && edtDapAnB.getText().toString().length() != 0 && edtDapAnC.getText().toString().length() != 0 && edtDapAnD.getText().toString().length() != 0 && selectedRadioButtonId != View.NO_ID){
                    themCauHoi();
                    stt = databaseHelper.demSoLuongCauHoiTheoChuDe(chuDeIndex);
                }else {
                    Toast.makeText(CauHoiThem.this,"Thêm không thành công",Toast.LENGTH_SHORT).show();
                }

            }
        });


        btnQuayLai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        btnLamMoi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clearInputFields();
            }
        });
    }
    private void themCauHoi() {
        String tenCauHoi = edtCauHoi.getText().toString();
        if (chuDeIndex == -1) {
            Toast.makeText(CauHoiThem.this, "Vui lòng chọn chủ đề trước khi thêm câu hỏi.", Toast.LENGTH_SHORT).show();
            return;
        }

        // Tạo đối tượng câu hỏi
        CauHoi cauHoi = new CauHoi();
        stt = 1;
        stt += databaseHelper.demSoLuongCauHoiTheoChuDe(chuDeIndex);
        cauHoi.setName("Câu hỏi "+stt);
        cauHoi.setTenCauHoi(tenCauHoi);
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
        // Thêm câu hỏi vào cơ sở dữ liệu
        databaseHelper.addCauHoiByChuDe(cauHoi, chuDeIndex,stt);
        updateCauHoiList();
        // Kiểm tra kết quả thêm câu hỏi
        Toast.makeText(CauHoiThem.this, "Thêm câu hỏi thành công.", Toast.LENGTH_SHORT).show();
        clearInputFields();

    }

    private void clearInputFields() {
        edtCauHoi.setText("");
        edtDapAnA.setText("");
        edtDapAnB.setText("");
        edtDapAnC.setText("");
        edtDapAnD.setText("");
        rdDapAnA.setChecked(false);
        rdDapAnB.setChecked(false);
        rdDapAnC.setChecked(false);
        rdDapAnD.setChecked(false);
        rdGroup.clearCheck();
        edtCauHoi.requestFocus();
    }
    private void updateCauHoiList() {
        List<CauHoi> updatedList = databaseHelper.getDanhSachCauHoiByChuDeId(chuDeIndex);
        ThemCauHoi.cauHoiList.clear();
        ThemCauHoi.cauHoiList.addAll(updatedList);
        ThemCauHoi.adapter.notifyDataSetChanged();
    }
    public void KhoiTao(){
        data_LSP.add("Cầu thủ");
        data_LSP.add("Cup");
        data_LSP.add("Sân bóng");
    }
}