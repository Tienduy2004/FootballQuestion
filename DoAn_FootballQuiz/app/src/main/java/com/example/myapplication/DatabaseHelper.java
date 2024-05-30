package com.example.myapplication;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "footballquiz";
    private static final int DATABASE_VERSION = 1;
    private static final String COLUMN_STT = "stt";
    private static final String TABLE_CHUDE = "chude";
    private static final String COLUMN_ID = "id";
    private static final String COLUMN_TENCHUDE = "tenchude";
    private static final String TABLE_CAUHOI = "cauhoi";
    private static final String COLUMN_ID_CHUDE = "id_chude";
    private static final String COLUMN_TENCAUHOI = "tencauhoi";
    private static final String COLUMN_DAPANA = "dapanA";
    private static final String COLUMN_DAPANB = "dapanB";
    private static final String COLUMN_DAPANC = "dapanC";
    private static final String COLUMN_DAPAND = "dapanD";
    private static final String COL_DAP_AN_A_DUNG = "DAP_AN_A_DUNG";
    private static final String COL_DAP_AN_B_DUNG = "DAP_AN_B_DUNG";
    private static final String COL_DAP_AN_C_DUNG = "DAP_AN_C_DUNG";
    private static final String COL_DAP_AN_D_DUNG = "DAP_AN_D_DUNG";
    private static final String COLUMN_NAME = "name";
    private static final String COLUMN_ANH ="anh";
    private static final String TABLE_USER = "user";
    private static final String COLUMN_USERNAME = "username";
    private static final String COLUMN_PASSWORD = "password";
    private static final String COLUMN_DIEM = "diem";
    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        // Tạo bảng chủ đề
        String createTableChuDe = "CREATE TABLE " + TABLE_CHUDE + "(" +
                COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                COLUMN_TENCHUDE + " TEXT)";
        db.execSQL(createTableChuDe);

        // Tạo bảng câu hỏi
        String createTableCauHoi = "CREATE TABLE " + TABLE_CAUHOI + "(" +
                COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                COLUMN_ID_CHUDE + " INTEGER," +
                COLUMN_NAME + " TEXT," +
                COLUMN_TENCAUHOI + " TEXT," +
                COLUMN_DAPANA + " TEXT," +
                COLUMN_DAPANB + " TEXT," +
                COLUMN_DAPANC + " TEXT," +
                COLUMN_DAPAND + " TEXT," +
                COL_DAP_AN_A_DUNG + " INTEGER," + //
                COL_DAP_AN_B_DUNG + " INTEGER," +
                COL_DAP_AN_C_DUNG + " INTEGER," +
                COL_DAP_AN_D_DUNG + " INTEGER," +
                COLUMN_STT + " INTEGER," +
                COLUMN_ANH + " TEXT," +
                "FOREIGN KEY(" + COLUMN_ID_CHUDE + ") REFERENCES " + TABLE_CHUDE + "(" + COLUMN_ID + "))";
        db.execSQL(createTableCauHoi);
        // Tạo bảng user
        String createTableUser =  "CREATE TABLE " + TABLE_USER + "(" +
                COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                COLUMN_USERNAME + " TEXT," +
                COLUMN_PASSWORD + " TEXT," +
                COLUMN_DIEM + " INTEGER)";
        db.execSQL(createTableUser);
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_CHUDE);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_CAUHOI);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_USER);
        onCreate(db);
    }
    public void addUser(User user){
        String sql = "INSERT INTO user(username,password,diem) VALUES(?,?,?)";
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        sqLiteDatabase.execSQL(sql, new Object[]{user.getUserName(),user.getPassWord(),user.getDiem()});
    }
    public boolean kiemTraUser(String username){
        SQLiteDatabase db = this.getReadableDatabase();
        boolean ktra = false;
        // Viết câu truy vấn SQL để đếm số lượng câu hỏi từ bảng cauhoi theo idChuDe
        String countQuery = "SELECT COUNT(*) FROM " + TABLE_USER +
                " WHERE " + COLUMN_USERNAME + " = '" + username + "'";
        Cursor cursor = db.rawQuery(countQuery, null);
        int soLuong = 0;
        if (cursor.moveToFirst()) {
            soLuong = cursor.getInt(0);
        }
        if(soLuong > 0){
            ktra = true;
        }
        // Đóng cursor và database
        cursor.close();
        db.close();

        // Trả về số lượng câu hỏi
        return  ktra;
    }
    public User ktraDangNhap(String TaiKhoan,String MatKhau){
        User user = new User();
        // Viết câu truy vấn SQL để lấy danh sách câu hỏi từ bảng cauhoi theo ID của chủ đề
        String selectQuery = "SELECT * FROM " + TABLE_USER +
                " WHERE " + COLUMN_USERNAME + " = '" + TaiKhoan + "' AND " + COLUMN_PASSWORD + " = '" + MatKhau + "'";
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        if (cursor.moveToFirst()) {
            do {

                user.setId(cursor.getInt(0));
                user.setUserName(cursor.getString(1));
                user.setPassWord(cursor.getString(2));
                user.setDiem(cursor.getInt(3));
            } while (cursor.moveToNext());
        }

        // Đóng cursor và database
        cursor.close();
        db.close();

        // Trả về danh sách câu hỏi
        return user;
    }
    public User getUserById(int userId) {
        SQLiteDatabase db = this.getReadableDatabase();
        User user = new User();

        // Viết câu truy vấn SQL để lấy thông tin user từ bảng user theo ID
        String selectQuery = "SELECT * FROM " + TABLE_USER +
                " WHERE " + COLUMN_ID + " = " + userId;

        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            user.setId(cursor.getInt(0));
            user.setUserName(cursor.getString(1));
            user.setPassWord(cursor.getString(2));
            user.setDiem(cursor.getInt(3));
        }

        cursor.close();
        db.close();

        return user;
    }
    public void updateDiem(int userId, int newDiem) {
        SQLiteDatabase db = getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(COLUMN_DIEM, newDiem);

        db.update(TABLE_USER, values, COLUMN_ID + " = ?", new String[]{String.valueOf(userId)});

        db.close();
    }
    public List<User> getAllUsers() {
        List<User> userList = new ArrayList<>();
        String selectQuery = "SELECT * FROM " + TABLE_USER + " ORDER BY " + COLUMN_DIEM + " DESC LIMIT 3";
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        if (cursor.moveToFirst()) {
            do {
                User user = new User();
                user.setId(Integer.parseInt(cursor.getString(0)));
                user.setUserName(cursor.getString(1));
                user.setPassWord(cursor.getString(2));
                user.setDiem(Integer.parseInt(cursor.getString(3)));
                userList.add(user);
            } while (cursor.moveToNext());
            cursor.close();
        }
        db.close();
        return userList;
    }
    public void addChuDe(ChuDe chuDe) {
        String sql = "INSERT INTO chude(tenchude) VALUES(?)";
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        sqLiteDatabase.execSQL(sql, new String[]{chuDe.getTenChuDe()});
    }
    public List<ChuDe> getAllChuDe() {
        List<ChuDe> chudeList = new ArrayList<>();

        String selectQuery = "SELECT * FROM " + TABLE_CHUDE;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            do {
                ChuDe chude = new ChuDe();
                chude.setID(Integer.parseInt(cursor.getString(0)));
                chude.setTenChuDe(cursor.getString(1));
                chudeList.add(chude);
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return chudeList;
    }
    public List<CauHoi> getDanhSachCauHoiByChuDeId(int chuDeId) {
        List<CauHoi> danhSachCauHoi = new ArrayList<>();

        // Viết câu truy vấn SQL để lấy danh sách câu hỏi từ bảng cauhoi theo ID của chủ đề
        String selectQuery = "SELECT * FROM " + TABLE_CAUHOI +
                " WHERE " +COLUMN_ID_CHUDE + " = " + chuDeId;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // Duyệt qua các dòng kết quả và thêm vào danh sách
        if (cursor.moveToFirst()) {
            do {
                CauHoi cauHoi = new CauHoi();
                cauHoi.setName(cursor.getString(2));
                cauHoi.setTenCauHoi(cursor.getString(3));
                DapAn dapAnA = new DapAn(cursor.getString(4), false);
                DapAn dapAnB = new DapAn(cursor.getString(5), false);
                DapAn dapAnC = new DapAn(cursor.getString(6), false);
                DapAn dapAnD = new DapAn(cursor.getString(7), false);
                // Đặt đáp án đúng thành true
                if (cursor.getInt(8) == 1) {
                    dapAnA.setKtra(true);
                } else if (cursor.getInt(9) == 1) {
                    dapAnB.setKtra(true);
                } else if (cursor.getInt(10) == 1) {
                    dapAnC.setKtra(true);
                } else if (cursor.getInt(11) == 1) {
                    dapAnD.setKtra(true);
                }
                cauHoi.setDapAnA(dapAnA);
                cauHoi.setDapAnB(dapAnB);
                cauHoi.setDapAnC(dapAnC);
                cauHoi.setDapAnD(dapAnD);
                cauHoi.setAnhCauHoi((cursor.getString(13)));
                // Thêm câu hỏi vào danh sách
                danhSachCauHoi.add(cauHoi);
            } while (cursor.moveToNext());
        }

        // Đóng cursor và database
        cursor.close();
        db.close();

        // Trả về danh sách câu hỏi
        return danhSachCauHoi;
    }
    public void addCauHoiByChuDe(CauHoi cauHoi, int chuDeId,int stt) {
        String sql = "INSERT INTO cauhoi(id_chude,name,tencauhoi,dapanA,dapanB,dapanC,dapanD,DAP_AN_A_DUNG,DAP_AN_B_DUNG,DAP_AN_C_DUNG,DAP_AN_D_DUNG,stt,anh) VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?)";
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        String name = cauHoi.getName();
        String DapAnA = cauHoi.getDapAnA().getNoiDungDapAn();
        String DapAnB = cauHoi.getDapAnB().getNoiDungDapAn();
        String DapAnC = cauHoi.getDapAnC().getNoiDungDapAn();
        String DapAnD = cauHoi.getDapAnD().getNoiDungDapAn();
        boolean DungA = cauHoi.getDapAnA().isKtra();
        boolean DungB = cauHoi.getDapAnB().isKtra();
        boolean DungC = cauHoi.getDapAnC().isKtra();
        boolean DungD = cauHoi.getDapAnD().isKtra();
        int DapAnADung = 0;
        int DapAnBDung = 0;
        int DapAnCDung = 0;
        int DapAnDDung = 0;
        if(DungA == true){
            DapAnADung = 1;
        }
        if(DungB == true){
            DapAnBDung = 1;
        }
        if(DungC == true){
            DapAnCDung = 1;
        }
        if(DungD == true){
            DapAnDDung = 1;
        }
        sqLiteDatabase.execSQL(sql, new Object[]{chuDeId,name,cauHoi.getTenCauHoi(),DapAnA,DapAnB,DapAnC,DapAnD,DapAnADung,DapAnBDung,DapAnCDung,DapAnDDung,stt,cauHoi.getAnhCauHoi()});
    }
    public CauHoi printCauHoiById(int chudeId,int stt) {
        CauHoi cauHoi = new CauHoi();

        // Viết câu truy vấn SQL để lấy danh sách câu hỏi từ bảng cauhoi theo ID của chủ đề
        String selectQuery = "SELECT * FROM " + TABLE_CAUHOI +
                " WHERE " +COLUMN_ID_CHUDE + " = " + chudeId+ " AND " + COLUMN_STT + " = " + stt;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // Duyệt qua các dòng kết quả và thêm vào danh sách
        if (cursor.moveToFirst()) {
            do {
                cauHoi.setName(cursor.getString(2));
                cauHoi.setTenCauHoi(cursor.getString(3));
                DapAn dapAnA = new DapAn(cursor.getString(4), false);
                DapAn dapAnB = new DapAn(cursor.getString(5), false);
                DapAn dapAnC = new DapAn(cursor.getString(6), false);
                DapAn dapAnD = new DapAn(cursor.getString(7), false);
                // Đặt đáp án đúng thành true
                if (cursor.getInt(8) == 1) {
                    dapAnA.setKtra(true);
                } else if (cursor.getInt(9) == 1) {
                    dapAnB.setKtra(true);
                } else if (cursor.getInt(10) == 1) {
                    dapAnC.setKtra(true);
                } else if (cursor.getInt(11) == 1) {
                    dapAnD.setKtra(true);
                }
                cauHoi.setDapAnA(dapAnA);
                cauHoi.setDapAnB(dapAnB);
                cauHoi.setDapAnC(dapAnC);
                cauHoi.setDapAnD(dapAnD);
                cauHoi.setAnhCauHoi(cursor.getString(13));
            } while (cursor.moveToNext());
        }

        // Đóng cursor và database
        cursor.close();
        db.close();

        // Trả về danh sách câu hỏi
        return cauHoi;
    }
    public void xoaCauHoi(int idChuDe,int stt) {
        SQLiteDatabase db = this.getWritableDatabase();

        // Xóa câu hỏi dựa vào idChuDe và stt
        db.delete(TABLE_CAUHOI, COLUMN_ID_CHUDE + " = ? AND " + COLUMN_STT + " = ?",
                new String[]{String.valueOf(idChuDe), String.valueOf(stt)});

        // Cập nhật lại stt trong bảng Cauhoi, bắt đầu từ 1

        capNhatTenCauHoi(idChuDe);
        capNhatLaiSTTCauHoi(db,idChuDe);

        db.close();
    }
    private void capNhatTenCauHoi(int idChuDe) {
        SQLiteDatabase db = this.getWritableDatabase();

        // Truy vấn tất cả các câu hỏi của chủ đề
        String query = "SELECT * FROM " + TABLE_CAUHOI +
                " WHERE " + COLUMN_ID_CHUDE + " = " + idChuDe;
        Cursor cursor = db.rawQuery(query, null);

        // Kiểm tra xem cột có tồn tại không
        int sttColumnIndex = cursor.getColumnIndex(COLUMN_ID);
        if (sttColumnIndex == -1) {
            // Cột không tồn tại, xử lý lỗi hoặc thoát khỏi phương thức
            cursor.close();
            db.close();
            return;
        }

        // Đặt lại trường name cho từng câu hỏi
        if (cursor.moveToFirst()) {
            int sttMoi = 1;
            do {
                // Lấy giá trị ID từ cột
                int idCauHoi = cursor.getInt(sttColumnIndex);

                // Cập nhật lại trường name
                String tenMoi = "Câu hỏi " + sttMoi;
                ContentValues values = new ContentValues();
                values.put(COLUMN_NAME, tenMoi);
                String whereClause = COLUMN_ID + " = ?";
                String[] whereArgs = {String.valueOf(idCauHoi)};
                db.update(TABLE_CAUHOI, values, whereClause, whereArgs);

                sttMoi++;
            } while (cursor.moveToNext());
        }

        // Đóng cursor
        cursor.close();
    }
    private void capNhatLaiSTTCauHoi(SQLiteDatabase db, int idChuDe) {
        // Truy vấn tất cả các câu hỏi của một ID Chủ đề
        String query = "SELECT " + COLUMN_ID + " FROM " + TABLE_CAUHOI +
                " WHERE " + COLUMN_ID_CHUDE + " = " + idChuDe;
        Cursor cursor = db.rawQuery(query, null);

        // Kiểm tra xem cột có tồn tại không
        int idColumnIndex = cursor.getColumnIndex(COLUMN_ID);
        int sttMoi = 1;

        // Đặt lại stt cho từng câu hỏi
        if (cursor.moveToFirst()) {
            do {
                // Lấy giá trị ID từ cột
                int idCauHoi = cursor.getInt(idColumnIndex);

                // Cập nhật lại stt của câu hỏi
                ContentValues values = new ContentValues();
                values.put(COLUMN_STT, sttMoi);
                String whereClause = COLUMN_ID + " = ?";
                String[] whereArgs = {String.valueOf(idCauHoi)};
                db.update(TABLE_CAUHOI, values, whereClause, whereArgs);

                sttMoi++;
            } while (cursor.moveToNext());
        }

        // Đóng cursor
        cursor.close();
    }
    public void capNhatLaiIDCauHoi() {
        SQLiteDatabase db = this.getWritableDatabase();

        // Truy vấn tất cả các câu hỏi còn lại
        String query = "SELECT * FROM " + TABLE_CAUHOI;
        Cursor cursor = db.rawQuery(query, null);

        // Kiểm tra xem cột có tồn tại không
        int idColumnIndex = cursor.getColumnIndex(COLUMN_ID);
        if (idColumnIndex == -1) {
            // Cột không tồn tại, xử lý lỗi hoặc thoát khỏi phương thức
            cursor.close();
            db.close();
            return;
        }

        // Đặt ID mới cho từng câu hỏi
        if (cursor.moveToFirst()) {
            int idMoi = 1;
            do {
                // Lấy giá trị ID từ cột
                String idCu = cursor.getString(idColumnIndex);

                // Nếu ID cũ không bằng ID mới, cập nhật lại ID
                if (!idCu.equals(String.valueOf(idMoi))) {
                    ContentValues values = new ContentValues();
                    values.put(COLUMN_ID, String.valueOf(idMoi));
                    String whereClause = COLUMN_ID + " = ?";
                    String[] whereArgs = {idCu};
                    db.update(TABLE_CAUHOI, values, whereClause, whereArgs);
                }

                idMoi++;
            } while (cursor.moveToNext());
        }

        // Đóng cursor và kết nối
        cursor.close();
        db.close();
    }
    public int demSoLuongCauHoiTheoChuDe(int idChuDe) {
        SQLiteDatabase db = this.getReadableDatabase();

        // Viết câu truy vấn SQL để đếm số lượng câu hỏi từ bảng cauhoi theo idChuDe
        String countQuery = "SELECT COUNT(*) FROM " + TABLE_CAUHOI +
                " WHERE " + COLUMN_ID_CHUDE + " = " + idChuDe;

        Cursor cursor = db.rawQuery(countQuery, null);

        int soLuong = 0;
        if (cursor.moveToFirst()) {
            soLuong = cursor.getInt(0);
        }

        // Đóng cursor và database
        cursor.close();
        db.close();

        // Trả về số lượng câu hỏi
        return soLuong;
    }
    public void suaCauHoi(CauHoi cauHoi,int idChuDe,int stt){
        String sql = "UPDATE " + TABLE_CAUHOI + " SET " +
                COLUMN_TENCAUHOI + " = ?, " +
                COLUMN_DAPANA + " = ?, " +
                COLUMN_DAPANB + " = ?, " +
                COLUMN_DAPANC + " = ?, " +
                COLUMN_DAPAND + " = ?, " +
                COL_DAP_AN_A_DUNG + " = ?, " +
                COL_DAP_AN_B_DUNG + " = ?, " +
                COL_DAP_AN_C_DUNG + " = ?, " +
                COL_DAP_AN_D_DUNG + " = ?, " +
                COLUMN_ANH + "= ?" +
                "WHERE " + COLUMN_ID_CHUDE + " = ? AND " + COLUMN_STT + " = ?";

        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        String name = cauHoi.getName();
        String DapAnA = cauHoi.getDapAnA().getNoiDungDapAn();
        String DapAnB = cauHoi.getDapAnB().getNoiDungDapAn();
        String DapAnC = cauHoi.getDapAnC().getNoiDungDapAn();
        String DapAnD = cauHoi.getDapAnD().getNoiDungDapAn();
        boolean DungA = cauHoi.getDapAnA().isKtra();
        boolean DungB = cauHoi.getDapAnB().isKtra();
        boolean DungC = cauHoi.getDapAnC().isKtra();
        boolean DungD = cauHoi.getDapAnD().isKtra();
        int DapAnADung = 0;
        int DapAnBDung = 0;
        int DapAnCDung = 0;
        int DapAnDDung = 0;
        if(DungA == true){
            DapAnADung = 1;
        }
        if(DungB == true){
            DapAnBDung = 1;
        }
        if(DungC == true){
            DapAnCDung = 1;
        }
        if(DungD == true){
            DapAnDDung = 1;
        }
        sqLiteDatabase.execSQL(sql, new Object[]{cauHoi.getTenCauHoi(),DapAnA,DapAnB,DapAnC,DapAnD,DapAnADung,DapAnBDung,DapAnCDung,DapAnDDung,cauHoi.getAnhCauHoi(),idChuDe,stt});

    }
    public void suaChuDe(String tenChuDe,int idChuDe){
        String sql = "UPDATE " + TABLE_CHUDE + " SET " + COLUMN_TENCHUDE + " = ? WHERE " + COLUMN_ID + " = ?";
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        sqLiteDatabase.execSQL(sql,new Object[]{tenChuDe,idChuDe});
    }
    public void XoaChuDe(int idChuDe){
        String sql = "DELETE FROM " + TABLE_CHUDE + " WHERE " + COLUMN_ID + " = ?";
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        sqLiteDatabase.execSQL(sql,new Object[]{idChuDe});
    }

}

