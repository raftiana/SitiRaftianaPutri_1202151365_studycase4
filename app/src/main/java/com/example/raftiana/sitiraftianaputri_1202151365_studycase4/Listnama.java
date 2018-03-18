package com.example.raftiana.sitiraftianaputri_1202151365_studycase4;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.ProgressBar;

import java.util.ArrayList;

public class Listnama extends AppCompatActivity {

    private Button lnm; //deklarasi widget button
    private ListView Listview; //deklarasi widget list view
    private ProgressBar progressBar; //deklarasi widget progress bar
    private AddItemToListView AddItemToListView; //deklarasi item untuk dimasukan ke list view

    //inisialisasi Array beserta isinya
    private String [] nama= {
            "Siti","Raftiana","Putri","Linda","Nur",
            "Anisa","Amalia","Gita","Destrianti","Nabila",
            "Amalia","Khairani","Muhammad","Shaugi","Muthia",
            "Adrianty",};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listnama);

        progressBar = (ProgressBar) findViewById(R.id.progressBar); //inisialisasi widget berdasarkan id yang ditampung oleh sebuah variabel
        Listview = (ListView) findViewById(R.id.list_nama); //inisialisasi widget berdasarkan id yang ditampung oleh sebuah variabel
        lnm = (Button) findViewById(R.id.buttonFind);//inisialisasi widget berdasarkan id yang ditampung oleh sebuah variabel
        Listview.setVisibility(View.GONE);//inisialisasi widget berdasarkan id yang ditampung oleh sebuah variabel

        //Inisialisasi semua komponen yang akan digunakan dan melakukan setAdapter terhadap ListView dengan menggunakan Array
        Listview.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, new ArrayList<String>()));

        //ketika button lnm di click maka dia akan mengeksekusi perintah dibawah
        lnm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AddItemToListView = new AddItemToListView(); //pembuatan objek AddItemToListView
                AddItemToListView.execute(); // perintah untuk menjalankan objek yang tadi dibuat
            }
        });
    }

    //Method ini digunakan untuk melakukan eksekusi progress dialog sebelum method onPostExecute dijalankan
    public class AddItemToListView extends AsyncTask<Void, String, Void> {

        private ArrayAdapter<String> Adapter; //deklarasi widget adapter
        private int count=1; //deklarasi variabel count
        ProgressDialog ProgressDialog = new ProgressDialog(Listnama.this); //pembuatan objek ProgressDialog

        @Override
        protected void onPreExecute() {
            Adapter = (ArrayAdapter<String>) Listview.getAdapter(); //inisialisai adapter terhadap listview

            //pembuatan komponen apa saja yang akan ditampilkan oleh dialog
            ProgressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER); //set progress style dari ProgressDialog.STYLE_SPINNER
            ProgressDialog.setTitle("Loading Data"); //set title dari "loading data"
            ProgressDialog.setMessage("Please wait...."); // set massage isinya "please wait..."
            ProgressDialog.setCancelable(false); // set cancelable menjadi false agar tidak bisa di cancel
            ProgressDialog.setProgress(0); //set progress menjadi 0
            ProgressDialog.show(); //untuk menampilakn ProgressDialog (show)
        }

        //perulangan yang dilakukan dibackground aplikasi menggunakan asynctask
        @Override
        protected Void doInBackground(Void... voids) {
            for (String item : nama){ //perulangan for dengan isian String item : nama
                publishProgress(item); //publishprogress dengan item
                try { //mencoba melakukan perintah
                    Thread.sleep(200);
                }catch (Exception e){ //jika error akan ditangkap disini
                    e.printStackTrace(); //print error
                }
            }
            return null; //pengembalian nilai null
        }

        //method untuk menghidtung presentase progress dialog yang sedang berproses
        @Override
        protected void onProgressUpdate(String... values) {
            Adapter.add(values[0]); //set Adapter.add(values[0]);
            Integer current_status = (int) ((count/(float)nama.length)*100); // pembuatan variabel current_status isinya perhitungan ((count/(float)nama.length)*100)
            progressBar.setProgress(current_status); //set progress bar dengan current_status
            ProgressDialog.setProgress(current_status); //set progress dialog dengan current_status
            ProgressDialog.setMessage(String.valueOf(current_status+"%")); //set message dengan value dari current_status+"%"
            count++; //penambahan count++
        }

        //Method ini untuk melakukan eksekusi setImageBitmap setelah method doInBackground dijalankan
        @Override
        protected void onPostExecute(Void aVoid) {

            Listview.setVisibility(View.VISIBLE); //set visiblity menjadi gone
            progressBar.setVisibility(View.GONE); //set visiblity menjadi gone
            ProgressDialog.dismiss(); //progressdialog dismiss
        }
    }
}
