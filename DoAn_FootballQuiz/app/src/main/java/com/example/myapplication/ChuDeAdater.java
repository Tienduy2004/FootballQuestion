package com.example.myapplication;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

public class ChuDeAdater extends ArrayAdapter {
    Context myContext;
    int myLayout;
    List<ChuDe> arrayChuDe;
    public ChuDeAdater(Context context, int layout, List<ChuDe> listChuDe){
        super(context,layout,listChuDe);
        myContext = context;
        myLayout = layout;
        arrayChuDe = listChuDe;
    }
    @Override
    public View getView(int position, @Nullable View convertView, @Nullable ViewGroup parent) {
        convertView = LayoutInflater.from(myContext).inflate(myLayout,null);
        TextView txtsttChuDe = convertView.findViewById(R.id.dongTenChuDe);
        ChuDe chuDe = arrayChuDe.get(position);
        txtsttChuDe.setText(chuDe.getTenChuDe());
        return convertView;
    }
}
