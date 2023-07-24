package com.example.appbandoan.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.appbandoan.R;
import com.example.appbandoan.dal.SQLiteHelper;
import com.example.appbandoan.model.Item;
import com.example.appbandoan.utils.MySharedPreferences;

public class XemchitietmonanActivity extends AppCompatActivity {
    private EditText tenmonan,loaimonan,giamonan,diachimonan,motamonan;
     EditText soluongmonan;
    Button themvaogiohang;
    ImageButton heart;
    MySharedPreferences preferences;
    SQLiteHelper sqLiteHelper;
    ImageView img1;
    Boolean checkyeuthich;
    Item item;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.xemchitietmonan);
        img1=findViewById(R.id.imgssmonan11);
        soluongmonan=findViewById(R.id.soluonmonan1);
        themvaogiohang=findViewById(R.id.themvaogiohang);
        tenmonan=findViewById(R.id.edtudma21);
        loaimonan=findViewById(R.id.udloaima1);
        giamonan=findViewById(R.id.udgiama1);
        diachimonan=findViewById(R.id.uddiachiban1);
        motamonan=findViewById(R.id.udmotama1);
        heart=findViewById(R.id.heart);
        sqLiteHelper=new SQLiteHelper(this);
        preferences=new MySharedPreferences(this);

        Intent intent=getIntent();
        String itemid= intent.getStringExtra("item_key");
         item=sqLiteHelper.laydatamonan(itemid);
        String tenmonan2=item.getTitle();
        String loaimonan2=item.getCategory();
        String giamonan2=item.getPrice();
        String diachimonan2=item.getDiachi();
        String motamonan2=item.getMota();
        String ghichu1="yeuthich";
        img1.setImageResource(item.getImg());
        tenmonan.setText(tenmonan2);
        loaimonan.setText(loaimonan2);
        giamonan.setText(giamonan2);
        diachimonan.setText(diachimonan2);
        motamonan.setText(motamonan2);
         checkyeuthich=sqLiteHelper.checkyeuthich(itemid,ghichu1);
        if(checkyeuthich==true) heart.setBackgroundResource(R.drawable.anhtimdo);
        else heart.setBackgroundResource(R.drawable.anhtimtrang);

        heart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                    heartclick();
            }
        });
        themvaogiohang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String soluong=soluongmonan.getText().toString().trim();
                String username=preferences.getUserName();
                String idsp=Integer.toString(item.getId());
                int imgsp=item.getImg();
                String tensp=item.getTitle();
                String giasp=item.getPrice();
                Boolean insertgiohang=sqLiteHelper.Insertgiohang(username,imgsp,idsp,tensp,giasp,soluong);
                if(insertgiohang==true) {
                    Intent intent1 = new Intent(XemchitietmonanActivity.this, GiohangActivity.class);
                    startActivity(intent1);
                }
                else Toast.makeText(getApplicationContext(),"Loi",Toast.LENGTH_LONG).show();
            }
        });

    }
    private void heartclick(){
        checkyeuthich=sqLiteHelper.checkyeuthich(Integer.toString(item.getId()),"yeuthich");
        if(checkyeuthich==true){
            heart.setBackgroundResource(R.drawable.anhtimtrang);
            sqLiteHelper.updateghichu1(item.getId());
        }
        else{
            heart.setBackgroundResource(R.drawable.anhtimdo);
            sqLiteHelper.updateghichu(item.getId());
        }
    }

}
