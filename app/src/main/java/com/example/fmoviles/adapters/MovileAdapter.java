package com.example.fmoviles.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.fmoviles.R;
import com.example.fmoviles.models.MovileModel;

import java.util.ArrayList;

public class MovileAdapter extends BaseAdapter {

    private Context context;
    private ArrayList<MovileModel> modelArrayList;

    public MovileAdapter(Context context, ArrayList<MovileModel> modelArrayList) {
        this.context = context;
        this.modelArrayList = modelArrayList;
    }

    @Override
    public int getCount() {
        return modelArrayList.size();
    }

    @Override
    public MovileModel getItem(int position) {
        return modelArrayList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if(convertView == null){
            LayoutInflater layoutInflater = LayoutInflater.from(this.context);
            convertView = layoutInflater.inflate(R.layout.movile_list_item,parent,false);
        }

        TextView tv_movile_list_item_marca = convertView.findViewById(R.id.tv_movile_list_item_marca);
        TextView tv_movile_list_item_description = convertView.findViewById(R.id.tv_movile_list_item_description);



        tv_movile_list_item_marca.setText(getItem(position).getMarca());
        tv_movile_list_item_description.setText(getItem(position).getDescripcion());


        return convertView;
    }
}
