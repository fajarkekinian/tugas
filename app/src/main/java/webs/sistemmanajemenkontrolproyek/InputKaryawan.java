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
import webs.sistemmanajemenkontrolproyek.R;

public class InputKaryawan extends AppCompatActivity {

    // Progress Dialog
    private ProgressDialog pDialog;

    JsonParser jsonParser = new JsonParser();
    EditText inputNik;
    EditText inputNama;
    EditText inputTgl;
    EditText inputJk;
    EditText inputAgama;

    // url to konek ke database
    private static String url_tambah_pendaftaran = "http://192.168.100.3/pendaftaran/create_pendaftaran.php";

    private static final String TAG_SUCCESS = "success";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_input_karyawan);

        // Input Text
        inputNik = (EditText) findViewById(R.id.nik);
        inputNama = (EditText) findViewById(R.id.nama);
        inputTgl = (EditText) findViewById(R.id.tgl_lahir);
        inputJk = (EditText) findViewById(R.id.jenis_kelamin);
        inputAgama = (EditText) findViewById(R.id.agama);


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
            pDialog = new ProgressDialog(InputKaryawan.this);
            pDialog.setMessage("Menunggu...");
            pDialog.setIndeterminate(false);
            pDialog.setCancelable(true);
            pDialog.show();
        }

        /**
         * Input karyawa baru
         * */
        protected String doInBackground(String... args) {
            String nik = inputNik.getText().toString();
            String nama = inputNama.getText().toString();
            String tgl = inputTgl.getText().toString();
            String jk = inputJk.getText().toString();
            String agama = inputAgama.getText().toString();

            // parameter
            List<NameValuePair> params = new ArrayList<NameValuePair>();
            params.add(new BasicNameValuePair("Nik", nik));
            params.add(new BasicNameValuePair("Nama", nama));
            params.add(new BasicNameValuePair("Tanggal Lahir", tgl));
            params.add(new BasicNameValuePair("Jenis Kelamin", jk));
            params.add(new BasicNameValuePair("Agama", agama));

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
