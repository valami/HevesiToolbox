package hu.valamas.hevesitoolbox;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.example.valamas.hevesitoolbox.R;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;

import hu.valamas.hevesitoolbox.szamolasok.numerikusterulet;
import hu.valamas.hevesitoolbox.szamolasok.polarispont;
import hu.valamas.hevesitoolbox.szamolasok.iranyszog;
import hu.valamas.hevesitoolbox.szamolasok.haromszog;
import hu.valamas.hevesitoolbox.szamolasok.atvalto;
import hu.valamas.hevesitoolbox.szamolasok.szelveny;
import hu.valamas.hevesitoolbox.szamolasok.metszesek.metszesek;
import hu.valamas.hevesitoolbox.szamolasok.wgseov;

public class MainActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        AdView mAdView = (AdView) findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);

        final Button iranyszog = (Button) findViewById(R.id.iranyszog);
        iranyszog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, iranyszog.class);
                startActivity(intent);
            }
        });

        final Button polaris = (Button) findViewById(R.id.polaris);
        polaris.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, polarispont.class);
                startActivity(intent);
            }
        });

        final Button numerikus = (Button) findViewById(R.id.numerikus);
        numerikus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, numerikusterulet.class);
                intent.putExtra("orientation", orient());
                startActivity(intent);
            }
        });

        final Button haromszog = (Button) findViewById(R.id.haromszog);
        haromszog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,  haromszog.class);
                startActivity(intent);
            }
        });

        final Button atvalto = (Button) findViewById(R.id.atvalto);
        atvalto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,  atvalto.class);
                intent.putExtra("orientation", orient());
                startActivity(intent);
            }
        });

        final Button szelveny = (Button) findViewById(R.id.szelveny);
        szelveny.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,  szelveny.class);
                startActivity(intent);
            }
        });

        final Button metszes = (Button) findViewById(R.id.metszesek);
        metszes.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(MainActivity.this,  metszesek.class);
                        startActivity(intent);
            }
        });

        final Button wgseov = (Button) findViewById(R.id.wgseov);
        wgseov.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,  wgseov.class);
                startActivity(intent);
            }
        });
    }

    public byte orient ()
    {
        int currentOrientation = getResources().getConfiguration().orientation;
        byte message;
        if (currentOrientation == Configuration.ORIENTATION_LANDSCAPE) {
            message = (byte) 0;  }
        else {
            message = (byte) 1;  }
        return (message);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_info)
        {
            AlertDialog alertDialog = new AlertDialog.Builder(this).create();
            alertDialog.setTitle(getString(R.string.menu_sugo));
            alertDialog.setMessage(getString(R.string.main_sugo));
            alertDialog.setButton("OK", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int which) {
                }
            });
            alertDialog.show();
        }
        return super.onOptionsItemSelected(item);
   }
}
