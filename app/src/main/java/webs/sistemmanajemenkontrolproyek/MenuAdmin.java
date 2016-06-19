package webs.sistemmanajemenkontrolproyek;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MenuAdmin extends AppCompatActivity {

    Button karyawan, proyek, kegiatan, data, logout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_admin);
        addListenerOnklik();
    }

    public void addListenerOnklik() {
        final Context context = this;
        karyawan = (Button) findViewById(R.id.button1);
        proyek = (Button) findViewById(R.id.button2);
        kegiatan = (Button) findViewById(R.id.button3);
        data = (Button) findViewById(R.id.button4);
        logout = (Button) findViewById(R.id.button5);

        karyawan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                Intent intent = new Intent(context, InputKaryawan.class);
                startActivity(intent);

            }
        });
        proyek.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                Intent intent = new Intent(context, InputProyek.class);
                startActivity(intent);

            }
        });
        kegiatan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                Intent intent = new Intent(context, InputKegiatan.class);
                startActivity(intent);

            }
        });
        data.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                Intent intent = new Intent(context, LihatData.class);
                startActivity(intent);

            }
        });
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                Intent intent = new Intent(context, LoginAdmin.class);
                startActivity(intent);
                MenuAdmin.this.finish();

            }
        });
    }
}
