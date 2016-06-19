package webs.sistemmanajemenkontrolproyek;

import android.app.TabActivity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TabHost;

public class LihatData extends TabActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lihat_data);
        TabHost tabhost = getTabHost();
        TabHost.TabSpec spec;
        Intent intent;

        intent = new Intent().setClass(this, LihatDataKaryawan.class);//content pada tab yang akan kita buat
        spec = tabhost.newTabSpec("Karyawan").setIndicator("Karyawan", null).setContent(intent);
        //mengeset nama tab dan mengisi content pada menu tab anda.
        tabhost.addTab(spec);//untuk membuat tabbaru disini bisa diatur sesuai keinginan anda

        intent = new Intent().setClass(this, LihatDataKaryawan.class);
        spec = tabhost.newTabSpec("Proyek").setIndicator("Proyek", null).setContent(intent);
        tabhost.addTab(spec);

        intent = new Intent().setClass(this, LihatDataKaryawan.class);
        spec = tabhost.newTabSpec("Kegiatan").setIndicator("Kegiatan", null).setContent(intent);
        tabhost.addTab(spec);
    }
}
