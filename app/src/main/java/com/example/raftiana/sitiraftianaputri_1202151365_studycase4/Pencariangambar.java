package com.example.raftiana.sitiraftianaputri_1202151365_studycase4;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.InputStream;

public class Pencariangambar extends AppCompatActivity {

    ImageView imageViewURL; //deklarasi widget image view
    Button buttonFind; //deklarasi widget button
    EditText editText; //deklarasi widget edit text

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pencariangambar);

        imageViewURL = (ImageView)findViewById(R.id.imageView);//inisialisasi widget berdasarkan id yang ditampung oleh sebuah variabel
        buttonFind = (Button)findViewById(R.id.buttonFind);//inisialisasi widget berdasarkan id yang ditampung oleh sebuah variabel
        editText = (EditText)findViewById(R.id.editText);//inisialisasi widget berdasarkan id yang ditampung oleh sebuah variabel

        //ketika button find di click maka dia akan mengeksekusi perintah dibawah
        buttonFind.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Image link from internet
                new DownloadImageFromInternet((ImageView) findViewById(R.id.imageView))
                        .execute(editText.getText().toString());//BIAR BISA AMBIL URL DR EDITTEXT
            }
        });

    }

    private class DownloadImageFromInternet extends AsyncTask<String, Void, Bitmap> {
        ImageView imageView; //deklarasi widget image view

        //method yang digunakan untuk mendownload gambar ini
        public DownloadImageFromInternet(ImageView imageView) {
            this.imageView = imageView;
            //pembuatan toast untuk pemberitahuan
            Toast.makeText(getApplicationContext(), "Please wait, it may take a few moment...", Toast.LENGTH_SHORT).show();
        }

        //method untuk memproses segala perintah pada backgroun
        protected Bitmap doInBackground(String... urls) {
            String imageURL = urls[0]; //deklarasi imageURL menjadi urls[0]
            Bitmap bimage = null; //deklarasi bimage menjadi null
            try { //mencoba serangkaian perintah
                InputStream in = new java.net.URL(imageURL).openStream();
                bimage = BitmapFactory.decodeStream(in);

            } catch (Exception e) { //penangkap error jika terjadi kesalahan
                Log.e("Error Message", e.getMessage());
                e.printStackTrace();
            }
            return bimage; //pengembalian nilai bimage
        }

        //method untuk setelah beres tereksekusi dan hasilnya di tampilkan pada image view
        protected void onPostExecute(Bitmap result) {
            imageView.setImageBitmap(result);
        }
    }

}
