package com.project.easypark;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;


public class MyListFragment extends Fragment {

    ListView lv;
    SearchView searchView;
    CustomListAdapter adapter;
    List<LocationModel> data = new ArrayList<>();
    List<String> listItems = new ArrayList<>();
    List<String> dates = new ArrayList<>();
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        DatabaseHelper databaseHelper = new DatabaseHelper(getActivity());
        data = databaseHelper.getAll();
        Collections.reverse(data);
        for(LocationModel location:data){
            listItems.add(location.getAddress());
        }
        for(LocationModel date:data){
            dates.add(date.getTimestamp());
        }

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_my_list,container,false);
        lv = (ListView) view.findViewById(R.id.myList);
        adapter = new CustomListAdapter(getActivity(),listItems,dates);

        lv.setAdapter(adapter);
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                String coords = data.get(position).getLong()+","+data.get(position).getLat();
                String map = "https://maps.google.com/maps?q="+coords;
                Intent i = new Intent(Intent.ACTION_VIEW,Uri.parse(map));
                startActivity(i);

            }
        });
        return view;
    }
}
