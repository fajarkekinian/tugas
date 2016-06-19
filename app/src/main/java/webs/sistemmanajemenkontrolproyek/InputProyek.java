package webs.sistemmanajemenkontrolproyek;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import Setting.JsonParser;

public class InputProyek extends AppCompatActivity {

    // Progress Dialog
    private ProgressDialog pDialog;

    JsonParser jsonParser = new JsonParser();
    EditText inputID;
    EditText inputNama;
    EditText inputAnggaran;
    EditText inputWaktu;
    EditText inputKaryawan;

    // url to membuat produk barus
    private static String url_tambah_pendaftaran = "http://192.168.100.3/pendaftaran/create_pendaftaran.php";

    private static final String TAG_SUCCESS = "success";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_input_proyek);

        // Edit Text
        inputID = (EditText) findViewById(R.id.nik);
        inputNama = (EditText) findViewById(R.id.nama);
        inputAnggaran = (EditText) findViewById(R.id.tgl_lahir);
        inputWaktu = (EditText) findViewById(R.id.jenis_kelamin);
        inputKaryawan = (EditText) findViewById(R.id.agama);


        // button untuk buat pendaftaran baru
        Button simpan = (Button) findViewById(R.id.save);

        // event jika di klik
        simpan.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                // jalankan buat pendaftaran di background
                new CreateNewProduct().execute();
            }
        });
    }

    /**
     * Background Async Task untuk membuat buku tamu baru
     * */
    class CreateNewProduct extends AsyncTask<String, String, String> {

        /**
         * tampilkan progress dialog
         * */
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            pDialog = new ProgressDialog(InputProyek.this);
            pDialog.setMessage("Menunggu...");
            pDialog.setIndeterminate(false);
            pDialog.setCancelable(true);
            pDialog.show();
        }

        /**
         * buat bukutamu baru
         * */
        protected String doInBackground(String... args) {
            String id = inputID.getText().toString();
            String nama = inputNama.getText().toString();
            String anggaran = inputAnggaran.getText().toString();
            String waktu = inputWaktu.getText().toString();
            String karyawan = inputKaryawan.getText().toString();

            // parameter
            List<NameValuePair> params = new ArrayList<NameValuePair>();
            params.add(new BasicNameValuePair("ID Proyek", id));
            params.add(new BasicNameValuePair("Nama Proyek", nama));
            params.add(new BasicNameValuePair("Anggaran Proyek", anggaran));
            params.add(new BasicNameValuePair("Waktu dateline", waktu));
            params.add(new BasicNameValuePair("Karyawan", karyawan));

            // json object
            JSONObject json = jsonParser.makeHttpRequest(url_tambah_pendaftaran,
                    "POST", params);

            // cek respon di logcat
            Log.d("Create Response", json.toString());

            // cek tag success
            try {
                int success = json.getInt(TAG_SUCCESS);

                if (success == 1) {
                    // sukses buat pendaftaran
                    Intent i = new Intent(getApplicationContext(), MenuAdmin.class);
                    startActivity(i);

                    // tutup screen
                    finish();
                } else {
                    // jika gagal
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }

            return null;
        }

        /**
         * jika proses selesai maka hentikan progress dialog
         * **/
        protected void onPostExecute(String file_url) {
            pDialog.dismiss();
        }

    }
}
