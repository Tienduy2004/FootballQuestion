package com.example.myapplication;



import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class ChuDe implements Serializable{
    private String tenChuDe;
    private int ID;
    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getTenChuDe() {
        return tenChuDe;
    }

    public void setTenChuDe(String tenChuDe) {
        this.tenChuDe = tenChuDe;
    }

    public List<CauHoi> getItemList() {
        return itemList;
    }

    public void setItemList(List<CauHoi> itemList) {
        this.itemList = itemList;
    }

    private List<CauHoi> itemList;
    public ChuDe()
    {

    }
    public  ChuDe(String ten,List<CauHoi> itemList){
        this.tenChuDe = ten;
        this.itemList = itemList;
    }
    public  ChuDe(String ten){
        this.tenChuDe = ten;
        this.itemList = new ArrayList<>();

    }
    public  ChuDe(List<CauHoi> itemList){

        this.itemList = itemList;
    }
    public void themCauHoi(CauHoi cauHoi) {
        itemList.add(cauHoi);
    }

}
