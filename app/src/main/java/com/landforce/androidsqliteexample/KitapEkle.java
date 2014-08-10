package com.landforce.androidsqliteexample;

import android.app.ActionBar;
import android.app.Activity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

/**
 * Created by landforce on 10.08.2014.
 */
public class KitapEkle extends Activity {
    Button b1;
    EditText e1,e2,e3,e4;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kitapekle);

        ActionBar actionBar = getActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setTitle("Kitap Ekle");
        b1 = (Button)findViewById(R.id.button1);
        e1 = (EditText)findViewById(R.id.editText1);
        e2 = (EditText)findViewById(R.id.editText2);
        e3 = (EditText)findViewById(R.id.editText3);
        e4 = (EditText)findViewById(R.id.editText4);

        b1.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                String adi,yazari,yili,fiyati;
                adi = e1.getText().toString();
                yazari = e2.getText().toString();
                yili = e3.getText().toString();
                fiyati = e4.getText().toString();
                if(adi.matches("") || yazari.matches("") || yili.matches("") || fiyati.matches("")  ){
                    Toast.makeText(getApplicationContext(), "Tüm Bilgileri Eksiksiz Doldurunuz", Toast.LENGTH_SHORT).show();
                }else{
                    Database db = new Database(getApplicationContext());
                    db.kitapEkle(adi, yazari, yili, fiyati);
                    db.close();
                    Toast.makeText(getApplicationContext(), "Kitabınız Başarıyla Eklendi.", Toast.LENGTH_SHORT).show();
                    e1.setText("");
                    e2.setText("");
                    e3.setText("");
                    e4.setText("");
                }
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
