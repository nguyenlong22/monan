package com.example.appbandoan.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.appbandoan.R;
import com.example.appbandoan.adapter.MonanAdapter;
import com.example.appbandoan.adapter.SpinnerAdapter;
import com.example.appbandoan.dal.SQLiteHelper;
import com.example.appbandoan.model.Item;
import com.example.appbandoan.utils.MySharedPreferences;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Updatencmonan extends AppCompatActivity {
    private EditText tenmonan,loaimonan,giamonan,diachimonan,motamonan;
    private Button quaylai,update;
    private Spinner spinner;
    private int pcrr;
    private SQLiteHelper sqLiteHelper;
    private MySharedPreferences preferences;
    private int[] imgs={R.drawable.banhmi,R.drawable.banhmi2,R.drawable.bunbo,
            R.drawable.bunbohue,R.drawable.humbeger,R.drawable.humbeger2,R.drawable.mi,
            R.drawable.mi2,R.drawable.trasua,R.drawable.trasua2,R.drawable.tratac};

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.update_monan);
        MonanAdapter monanAdapter=new MonanAdapter(this);
        tenmonan=findViewById(R.id.edtudma2);
        loaimonan=findViewById(R.id.udloaima);
        giamonan=findViewById(R.id.udgiama);
        diachimonan=findViewById(R.id.uddiachiban);
        motamonan=findViewById(R.id.udmotama);
        quaylai=findViewById(R.id.quaylaityh);
        update=findViewById(R.id.updatemonan2);
        spinner=findViewById(R.id.imgssmonan);
        sqLiteHelper=new SQLiteHelper(this);
        preferences=new MySharedPreferences(this);

        SpinnerAdapter spinnerAdapter=new SpinnerAdapter(this);
        spinner.setAdapter(spinnerAdapter);
        String itemid= preferences.getId();
        Item item1=sqLiteHelper.laydatamonan(itemid);
        String tenmonan2=item1.getTitle();
        String loaimonan2=item1.getCategory();
        String giamonan2=item1.getPrice();
        String diachimonan2=item1.getDiachi();
        String motamonan2=item1.getMota();
        //trà sữa màu Hà Nội
        //humberger thịt bò
        tenmonan.setText(tenmonan2);
        loaimonan.setText(loaimonan2);
        giamonan.setText(giamonan2);
        diachimonan.setText(diachimonan2);
        motamonan.setText(motamonan2);
        int img1=item1.getImg();
        spinner.setSelection(img1);
        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Item item=new Item();
                String i=spinner.getSelectedItem().toString();
                String name=tenmonan.getText().toString();
                String gia=giamonan.getText().toString();
                String diachi=diachimonan.getText().toString();
                String mota=motamonan.getText().toString();
                String loai=loaimonan.getText().toString();
                Date date=new Date();
                SimpleDateFormat dateFormat=new SimpleDateFormat("dd//mm/yyyy");
                String strdate=dateFormat.format(date);
                int img=R.drawable.banhmi;
                double price =0;
                try {
                    img=imgs[Integer.parseInt(i)];
                    price=Double.parseDouble(gia);
                    item.setImg(img);
                    item.setCategory(loai);
                    item.setTitle(name);
                    item.setDiachi(diachi);
                    item.setPrice(gia);
                    item.setMota(mota);
                    item.setDate(strdate);
                    monanAdapter.update(pcrr,item);
                }
                catch(NumberFormatException e){
                    Toast.makeText(getApplication(),"Nhap lai",Toast.LENGTH_LONG).show();
                }
                Intent intent=new Intent(Updatencmonan.this,ThemmonanActivity.class);
                startActivity(intent);
            }
        });
    }
}
