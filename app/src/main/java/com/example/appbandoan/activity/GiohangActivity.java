package com.example.appbandoan.activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.appbandoan.R;
import com.example.appbandoan.adapter.GiohangAdapter;
import com.example.appbandoan.dal.SQLiteHelper;
import com.example.appbandoan.model.Giohang;
import com.example.appbandoan.model.taikhoan;
import com.example.appbandoan.utils.MySharedPreferences;

import java.util.List;

public class GiohangActivity extends AppCompatActivity implements View.OnClickListener {
    MySharedPreferences preferences;
    RecyclerView recyclerView;
    TextView diachinhank,tongtien;
    SQLiteHelper sqLiteHelper;
    Button themvaodonhang;
    GiohangAdapter adapter;//phở Hà Nội
    String dcnhan;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.giohang);
        preferences=new MySharedPreferences(this);
        sqLiteHelper=new SQLiteHelper(this);
        recyclerView=findViewById(R.id.recyclegiohang);
        themvaodonhang=findViewById(R.id.vodonhang);
        diachinhank=findViewById(R.id.diachinhan);
        tongtien=findViewById(R.id.tongsotien);
        String username=preferences.getUserName();
        Log.e("Test",username);
        taikhoan data=sqLiteHelper.laydatauser(username);
        dcnhan=data.getDiachi();
        diachinhank.setText(dcnhan);
        

        List<Giohang> list=sqLiteHelper.laydatagiohang(username);
        adapter=new GiohangAdapter();
        adapter.setGiohangList(list);
        LinearLayoutManager manager=new LinearLayoutManager(this,RecyclerView.VERTICAL,false);
        recyclerView.setLayoutManager(manager);
        recyclerView.setAdapter(adapter);
        long tongtiengh=0;
        if(list!=null) Log.e("Test","ok");
       if(list!=null) tongtiengh=adapter.gettongtien();
        tongtien.setText(Long.toString(tongtiengh));
        themvaodonhang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dcnhan=diachinhank.getText().toString();
                Intent intent1=new Intent(GiohangActivity.this,TrangchuActivity.class);
                startActivity(intent1);
            }
        });
    }

    @Override
    public void onClick(View view) {

    }
}
