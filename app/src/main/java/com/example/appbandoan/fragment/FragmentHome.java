package com.example.appbandoan.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.appbandoan.R;
import com.example.appbandoan.activity.XemchitietmonanActivity;
import com.example.appbandoan.adapter.XemchitietAdapter;
import com.example.appbandoan.dal.SQLiteHelper;
import com.example.appbandoan.model.Item;

import java.util.List;

public class FragmentHome extends Fragment implements XemchitietAdapter.XemchitietItemListener {
    private XemchitietAdapter adapter,adapter1;
    private RecyclerView recyclerView;
    private SQLiteHelper sqLiteHelper;
    SearchView searchView;
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

        sqLiteHelper=new SQLiteHelper(getContext());
        List<Item> list;
        list=sqLiteHelper.getAll();
        adapter=new XemchitietAdapter();
        adapter.setMonanList(list);
        LinearLayoutManager manager=new LinearLayoutManager(getContext(),RecyclerView.VERTICAL,false);
        recyclerView.setLayoutManager(manager);
        recyclerView.setAdapter(adapter);
        adapter.setClickListener(this);

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                searchten(query);
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                searchten(newText);
                return true;
            }
        });

        all.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                adapter.setMonanList(list);
                recyclerView.setAdapter(adapter);
            }
        });
        String tag=null;
        if(moanman.callOnClick()) {
            tag = "Món ăn mặn";
            locitem(tag);
        }
        if(doansang.callOnClick()) {
            tag = "Đồ ăn sáng";
            locitem(tag);
        }
        if(dochay.callOnClick()) {
            tag = "Đồ ăn chay";
            locitem(tag);
        }
        if(douong.callOnClick()) {
            tag = "Đồ uống";
            locitem(tag);
        }
        if(doannhanh.callOnClick()) {
            tag = "Đồ ăn nhanh";
            locitem(tag);
        }
        if(gantoi.callOnClick()) {
            tag = "gần tôi";
            locitem(tag);
        }

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
    public void searchten(String tenmonan){
        List<Item> listq=sqLiteHelper.timmonan(tenmonan);
        recyclerView.setAdapter(null);
        adapter1=new XemchitietAdapter();
        adapter1.setMonanList(listq);
        LinearLayoutManager manager1=new LinearLayoutManager(getContext(),RecyclerView.VERTICAL,false);
        recyclerView.setLayoutManager(manager1);
        recyclerView.setAdapter(adapter1);
    }
    public void locitem(String tag){
        List<Item> listq=sqLiteHelper.locitemmonan(tag);
        recyclerView.setAdapter(null);
        adapter1=new XemchitietAdapter();
        adapter1.setMonanList(listq);
        LinearLayoutManager manager1=new LinearLayoutManager(getContext(),RecyclerView.VERTICAL,false);
        recyclerView.setLayoutManager(manager1);
        recyclerView.setAdapter(adapter1);
    }

}
