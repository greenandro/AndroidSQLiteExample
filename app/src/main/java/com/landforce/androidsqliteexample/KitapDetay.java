package com.landforce.androidsqliteexample;

import android.app.ActionBar;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.HashMap;

/**
 * Created by landforce on 10.08.2014.
 */
public class KitapDetay extends Activity {
    Button b1,b2;
    TextView t1,t2,t3,t4;
    int id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kitapdetay);

        ActionBar actionBar = getActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setTitle("Kitap Detayları");

        b1 = (Button)findViewById(R.id.button1);
        b2 = (Button)findViewById(R.id.button2);

        t1 = (TextView)findViewById(R.id.adi);
        t2 = (TextView)findViewById(R.id.yazari);
        t3 = (TextView)findViewById(R.id.yili);
        t4 = (TextView)findViewById(R.id.fiyati);

        Intent intent=getIntent();
        id = intent.getIntExtra("id", 0);//Burdaki 0 eğer değer alınmazsa default olarak verilecek değer

        Database db = new Database(getApplicationContext());
        HashMap<String, String> map = db.kitapDetay(id);

        t1.setText(map.get("kitap_adi"));
        t2.setText(map.get("yazar").toString());
        t3.setText(map.get("yil").toString());
        t4.setText(map.get("fiyat").toString());


        b1.setOnClickListener(new View.OnClickListener() {//Kitap düzenle butonuna týklandýgýnda tekrardan kitabýn id sini gönderdik

            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), KitapDuzenle.class);
                intent.putExtra("id", (int)id);
                startActivity(intent);
            }
        });

        b2.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                AlertDialog.Builder alertDialog = new AlertDialog.Builder(KitapDetay.this);
                alertDialog.setTitle("Uyarı");
                alertDialog.setMessage("Kitap Silinsin mi?");
                alertDialog.setPositiveButton("Evet", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog,int which) {
                        Database db = new Database(getApplicationContext());
                        db.kitapSil(id);
                        Toast.makeText(getApplicationContext(), "Kitap Başarıyla Silindi", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(getApplicationContext(), MyActivity.class);
                        startActivity(intent);
                        finish();

                    }
                });
                alertDialog.setNegativeButton("Hayır", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog,int which) {

                    }
                });
                alertDialog.show();
            }
        });
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                return true;
            default: return super.onOptionsItemSelected(item);
        }
    }
}
