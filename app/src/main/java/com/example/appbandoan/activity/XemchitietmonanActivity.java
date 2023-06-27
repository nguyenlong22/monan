package com.example.appbandoan.activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
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
    Button themvaogiohang,heart;
    MySharedPreferences preferences;
    SQLiteHelper sqLiteHelper;
    ImageView img1;
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
        Item item=sqLiteHelper.laydatamonan(itemid);
        String tenmonan2=item.getTitle();
        String loaimonan2=item.getCategory();
        String giamonan2=item.getPrice();
        String diachimonan2=item.getDiachi();
        String motamonan2=item.getMota();
        int img=item.getImg();
        //mì nhà hàng  30000 Hà Nội ngon bún bò Huế
        if(img==1) img1.setImageResource(R.drawable.banhmi);
        if(img==2) img1.setImageResource(R.drawable.banhmi2);
        if(img==3) img1.setImageResource(R.drawable.bunbo);
        if(img==4) img1.setImageResource(R.drawable.bunbohue);
        if(img==5) img1.setImageResource(R.drawable.humbeger);
        if(img==6) img1.setImageResource(R.drawable.humbeger2);
        if(img==7) img1.setImageResource(R.drawable.mi);
        if(img==8) img1.setImageResource(R.drawable.mi2);
        if(img==9) img1.setImageResource(R.drawable.trasua);
        if(img==10) img1.setImageResource(R.drawable.trasua2);
        if(img==11) img1.setImageResource(R.drawable.tratac);
        tenmonan.setText(tenmonan2);
        loaimonan.setText(loaimonan2);
        giamonan.setText(giamonan2);
        diachimonan.setText(diachimonan2);
        motamonan.setText(motamonan2);
        Log.e("Test",Integer.toString(img));
        heart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                heart.setBackgroundResource(R.drawable.heart_red);
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
}
