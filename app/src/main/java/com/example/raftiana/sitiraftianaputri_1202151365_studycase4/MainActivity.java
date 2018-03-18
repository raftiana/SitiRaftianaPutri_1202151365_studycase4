package com.example.raftiana.sitiraftianaputri_1202151365_studycase4;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    Intent i ; //deklarasi intent

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    //method yang merespon jika button List Nama Mahasiswa di klik
    public void lnm (View view){
        i = new Intent(MainActivity.this, Listnama.class);
        startActivity(i);
    }

    //method yang merespon jika button Pencarian Gambar di klik
    public void pg (View view){
        i = new Intent(MainActivity.this, Pencariangambar.class);
        startActivity(i);
    }
}
