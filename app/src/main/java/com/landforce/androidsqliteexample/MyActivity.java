package com.landforce.androidsqliteexample;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;


public class MyActivity extends Activity {

    ListView lv;
    ArrayAdapter<String> adapter;
    ArrayList<HashMap<String, String>> kitap_liste;
    String kitap_adlari[];
    int kitap_idler[];
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ActionBar actionBar = getActionBar();
        actionBar.setDisplayHomeAsUpEnabled(false);
        actionBar.setDisplayShowTitleEnabled(false);
    }

    @Override
    public void onResume(){
        super.onResume();
        Database db = new Database(getApplicationContext());
        kitap_liste = db.kitaplar();
        if(kitap_liste.size()==0){
            Toast.makeText(getApplicationContext(), "Henüz Kitap Eklenmemiş.\nYukarıdaki + Butonundan Ekleyiniz", Toast.LENGTH_LONG).show();
        }else{
            kitap_adlari = new String[kitap_liste.size()];
            kitap_idler = new int[kitap_liste.size()];
            for(int i=0;i<kitap_liste.size();i++){
                kitap_adlari[i] = kitap_liste.get(i).get("kitap_adi");
                kitap_idler[i] = Integer.parseInt(kitap_liste.get(i).get("id"));
            }
            lv = (ListView) findViewById(R.id.list_view);
            adapter = new ArrayAdapter<String>(this, R.layout.list_item, R.id.kitap_adi, kitap_adlari);
            lv.setAdapter(adapter);

            lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
                                        long arg3) {

                    Intent intent = new Intent(getApplicationContext(), KitapDetay.class);
                    intent.putExtra("id", kitap_idler[arg2]);
                    startActivity(intent);

                }
            });
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.my, menu);

        return super.onCreateOptionsMenu(menu);
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle presses on the action bar items
        switch (item.getItemId()) {
            case R.id.ekle:
                KitapEkle();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void KitapEkle() {
        Intent i = new Intent(MyActivity.this, KitapEkle.class);
        startActivity(i);
    }
}

