package com.project.easypark;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.gms.maps.model.LatLng;

import java.util.ArrayList;
import java.util.List;

public class CustomListAdapter extends ArrayAdapter<String>{

    private final Activity context;
    private final List<String> address;
    private final List<String> date;

    public CustomListAdapter(Activity context,List<String> address,List<String> date){
        super(context,R.layout.mylist,date);
        this.context = context;
        this.address = address;
        this.date = date;
    }
    public View getView(int position, View view, ViewGroup parent) {
        LayoutInflater inflater=context.getLayoutInflater();
        View rowView=inflater.inflate(R.layout.mylist, null,true);

        TextView titleText = (TextView) rowView.findViewById(R.id.title);
        TextView subtitleText = (TextView) rowView.findViewById(R.id.subtitle);

        titleText.setText(date.get(position));
        subtitleText.setText(address.get(position));
        return rowView;
    }

}
