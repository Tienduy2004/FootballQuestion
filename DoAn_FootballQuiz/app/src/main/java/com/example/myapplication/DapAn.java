package com.example.myapplication;

import java.io.Serializable;

public class DapAn implements Serializable {
    private String noiDungDapAn;

    public String getNoiDungDapAn() {
        return noiDungDapAn;
    }

    public void setNoiDungDapAn(String noiDungDapAn) {
        this.noiDungDapAn = noiDungDapAn;
    }

    public boolean isKtra() {
        return Ktra;
    }

    public void setKtra(boolean ktra) {
        Ktra = ktra;
    }

    private boolean Ktra = false;
    public DapAn(String noiDungDapAn,boolean ktra)
    {
        this.noiDungDapAn = noiDungDapAn;
        this.Ktra = ktra;
    }
    public DapAn(){

    }
}
