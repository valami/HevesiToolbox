package hu.valamas.hevesitoolbox;

import android.app.Activity;
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

public class MainActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Reklám
        AdView mAdView = (AdView) findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);


        final Button iranyszog = (Button) findViewById(R.id.iranyszog);
        iranyszog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, iranyszog.class);
                intent.putExtra("orientation", orient());
                startActivity(intent);
            }
        });

        final Button polaris = (Button) findViewById(R.id.polaris);
        polaris.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, polarispont.class);
                intent.putExtra("orientation", orient());
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
                intent.putExtra("orientation", orient());
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
                intent.putExtra("orientation", orient());
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
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
