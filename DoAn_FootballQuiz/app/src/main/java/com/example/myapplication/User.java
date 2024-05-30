package com.example.myapplication;

public class User {
    private int id;
    private String userName;
    private String passWord;
    private int diem;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassWord() {
        return passWord;
    }

    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }

    public int getDiem() {
        return diem;
    }

    public void setDiem(int diem) {
        this.diem = diem;
    }
    public User(){

    }
    public User(String userName,String passWord,int diem){
        this.userName = userName;
        this.passWord = passWord;
        this.diem = diem;
    }
}
