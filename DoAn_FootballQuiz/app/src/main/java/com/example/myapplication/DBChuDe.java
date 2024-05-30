package com.example.myapplication;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class DBChuDe extends SQLiteOpenHelper {

    // Tên cơ sở dữ liệu và phiên bản
    private static final String DATABASE_NAME = "QuizzApp";
    private static final int DATABASE_VERSION = 1;

    // Tên bảng và các cột của bảng CHUDE
    private static final String TABLE_CHUDE = "CHUDE";
    private static final String COLUMN_ID = "ID";
    private static final String COLUMN_TEN_CHUDE = "TEN_CHUDE";

    // Tên bảng và các cột của bảng CAUHOI
    private static final String TABLE_CAUHOI = "CAUHOI";
    private static final String COLUMN_ID_CAUHOI = "ID_CAUHOI";
    private static final String COLUMN_TEN_CAUHOI = "TEN_CAUHOI";
    private static final String COLUMN_ID_CHUDE = "ID_CHUDE";

    public DBChuDe(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // Tạo bảng CHUDE
        String createChuDeTable = "CREATE TABLE " + TABLE_CHUDE + "("
                + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + COLUMN_TEN_CHUDE + " TEXT)";
        db.execSQL(createChuDeTable);

        // Tạo bảng CAUHOI
        String createCauHoiTable = "CREATE TABLE " + TABLE_CAUHOI + "("
                + COLUMN_ID_CAUHOI + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + COLUMN_TEN_CAUHOI + " TEXT,"
                + COLUMN_ID_CHUDE + " INTEGER)";
        db.execSQL(createCauHoiTable);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_CHUDE);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_CAUHOI);
        onCreate(db);
    }

    // Thêm Chủ Đề mới vào cơ sở dữ liệu
    public long addChuDe(ChuDe chuDe) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_TEN_CHUDE, chuDe.getTenChuDe());

        long insertedID = db.insert(TABLE_CHUDE, null, values);
        db.close();

        return insertedID;
    }

    // Thêm Câu Hỏi mới vào cơ sở dữ liệu
    public long addCauHoi(CauHoi cauHoi, int idChuDe) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_TEN_CAUHOI, cauHoi.getTenCauHoi());
        values.put(COLUMN_ID_CHUDE, idChuDe);

        long insertedID = db.insert(TABLE_CAUHOI, null, values);
        db.close();

        return insertedID;
    }

    // Lấy danh sách Chủ Đề từ cơ sở dữ liệu
    public List<ChuDe> getAllChuDe() {
        List<ChuDe> chuDeList = new ArrayList<>();
        String selectQuery = "SELECT * FROM " + TABLE_CHUDE;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            do {
                ChuDe chuDe = new ChuDe();
                chuDe.setID(Integer.parseInt(cursor.getString(0)));
                chuDe.setTenChuDe(cursor.getString(1));
                chuDeList.add(chuDe);
            } while (cursor.moveToNext());
        }

        cursor.close();
        db.close();

        return chuDeList;
    }

    // Lấy danh sách Câu Hỏi theo ID Chủ Đề từ cơ sở dữ liệu
    public List<CauHoi> getCauHoiByChuDe(int idChuDe) {
        List<CauHoi> cauHoiList = new ArrayList<>();
        String selectQuery = "SELECT * FROM " + TABLE_CAUHOI + " WHERE " + COLUMN_ID_CHUDE + " = " + idChuDe;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            do {
                CauHoi cauHoi = new CauHoi();
                cauHoi.setTenCauHoi(cursor.getString(1));
                cauHoiList.add(cauHoi);
            } while (cursor.moveToNext());
        }

        cursor.close();
        db.close();

        return cauHoiList;
    }
}
