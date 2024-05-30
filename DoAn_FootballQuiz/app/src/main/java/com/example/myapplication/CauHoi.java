package com.example.myapplication;

import java.io.Serializable;
import java.util.jar.Attributes;

public class CauHoi implements Serializable {
    private String tenCauHoi;
    private String name;
    private DapAn DapAnA;
    private DapAn DapAnB;
    private int id;

    public String getAnhCauHoi() {
        return anhCauHoi;
    }

    public void setAnhCauHoi(String anhCauHoi) {
        this.anhCauHoi = anhCauHoi;
    }

    private String anhCauHoi;

    public DapAn getDapAnA() {
        return DapAnA;
    }

    public void setDapAnA(DapAn dapAnA) {
        DapAnA = dapAnA;
    }

    public DapAn getDapAnB() {
        return DapAnB;
    }

    public void setDapAnB(DapAn dapAnB) {
        DapAnB = dapAnB;
    }

    public DapAn getDapAnC() {
        return DapAnC;
    }

    public void setDapAnC(DapAn dapAnC) {
        DapAnC = dapAnC;
    }

    public DapAn getDapAnD() {
        return DapAnD;
    }

    public void setDapAnD(DapAn dapAnD) {
        DapAnD = dapAnD;
    }

    private DapAn DapAnC;
    private DapAn DapAnD;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTenCauHoi() {
        return tenCauHoi;
    }

    public void setTenCauHoi(String tenCauHoi) {
        this.tenCauHoi = tenCauHoi;
    }
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


    public CauHoi(String Name,String CauHoi,String DapAnA,String DapAnB,String DapAnC,String DapAnD,boolean kTra){
        this.name = Name;
        this.tenCauHoi = CauHoi;
        this.DapAnA = new DapAn(DapAnA,kTra);
        this.DapAnB = new DapAn(DapAnB,kTra);
        this.DapAnC = new DapAn(DapAnC,kTra);
        this.DapAnD = new DapAn(DapAnD,kTra);
    }
    public CauHoi(){

    }



}
