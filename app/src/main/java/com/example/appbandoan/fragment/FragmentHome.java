package com.example.appbandoan.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;


import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import  androidx.appcompat.widget.SearchView;

import com.example.appbandoan.R;
import com.example.appbandoan.activity.XemchitietmonanActivity;
import com.example.appbandoan.adapter.XemchitietAdapter;
import com.example.appbandoan.dal.SQLiteHelper;
import com.example.appbandoan.model.Item;

import java.util.List;

public class FragmentHome extends Fragment implements XemchitietAdapter.XemchitietItemListener {
    private XemchitietAdapter adapter;
    private RecyclerView recyclerView;
    private SQLiteHelper sqLiteHelper;
    SearchView searchView;
    Button sartcht;
    ImageButton all,moanman,douong,dochay,doannhanh,doansang,gantoi;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_home,container,false);

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        recyclerView=view.findViewById(R.id.recyclerView1);
        searchView=view.findViewById(R.id.searchh);
        all=view.findViewById(R.id.btnmonanall);
        moanman=view.findViewById(R.id.btnmonanman);
        douong=view.findViewById(R.id.btntrasua);
        dochay=view.findViewById(R.id.btndochay);
        doannhanh=view.findViewById(R.id.btndoannhanh);
        doansang=view.findViewById(R.id.btndoansan);
        gantoi=view.findViewById(R.id.btngan);


        sartcht=view.findViewById(R.id.btnsearchfh);
        sqLiteHelper=new SQLiteHelper(getContext());
        List<Item> list;
        list=sqLiteHelper.getAll();
        adapter=new XemchitietAdapter();
        adapter.setMonanList(list);
        LinearLayoutManager manager=new LinearLayoutManager(getContext(),RecyclerView.VERTICAL,false);
        recyclerView.setLayoutManager(manager);
        recyclerView.setAdapter(adapter);
        adapter.setClickListener(this);
        sartcht.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String tenma=searchView.toString();
                List<Item>list1=sqLiteHelper.timmonan(tenma);
                recyclerView.setAdapter(null);
                adapter=new XemchitietAdapter();
                adapter.setMonanList(list1);
                recyclerView.setLayoutManager(manager);
                recyclerView.setAdapter(adapter);
            }
        });
    }

    @Override
    public void onItemClick(View view, int postion) {
        Intent intent=new Intent(getActivity(), XemchitietmonanActivity.class);
        Item item=adapter.getItem(postion);
        String idsp=Integer.toString(item.getId());
        intent.putExtra("item_key",idsp);
        startActivity(intent);


    }

    @Override
    public void onResume() {
        super.onResume();
        List<Item> list=sqLiteHelper.getAll();
    }
}
