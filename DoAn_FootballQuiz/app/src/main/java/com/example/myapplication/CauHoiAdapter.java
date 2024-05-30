package com.example.myapplication;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.TextView;

import androidx.annotation.Nullable;

import java.util.List;

public class CauHoiAdapter extends ArrayAdapter {
    Context myContext;
    int myLayout;
    List<CauHoi> arrayCauHoi;
    public CauHoiAdapter(Context context, int layout, List<CauHoi> listCauHoi){
        super(context,layout,listCauHoi);
        myContext = context;
        myLayout = layout;
        arrayCauHoi = listCauHoi;
    }
    @Override
    public View getView(int position, @Nullable View convertView,@Nullable ViewGroup parent) {
        convertView = LayoutInflater.from(myContext).inflate(myLayout,null);
        TextView txtsttCauHoi = convertView.findViewById(R.id.sttCauHoi);
        CauHoi cauHoi = arrayCauHoi.get(position);
        txtsttCauHoi.setText(cauHoi.getName());
        return convertView;
    }
}
