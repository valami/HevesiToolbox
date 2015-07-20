package hu.valamas.hevesitoolbox.szamolasok.metszesek;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.example.valamas.hevesitoolbox.R;
import hu.valamas.hevesitoolbox.MainActivity;


public class metszesek extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_metszesek);

        AdView mAdView = (AdView) findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);

        final Button tajekozott = (Button) findViewById(R.id.tajekozott_btn);
        tajekozott.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(metszesek.this,  tajekozott.class);
                startActivity(intent);
            }
        });

        final Button belsoszoges = (Button) findViewById(R.id.belsoszoges_btn);
        belsoszoges.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(metszesek.this,  belsoszoges.class);
                startActivity(intent);
            }
        });

        final Button oldal = (Button) findViewById(R.id.oldal_btn);
        oldal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(metszesek.this,  oldal.class);
                startActivity(intent);
            }
        });

        final Button iv = (Button) findViewById(R.id.iv_btn);
        iv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(metszesek.this,  iv.class);
                startActivity(intent);
            }
        });

        final Button hatra = (Button) findViewById(R.id.hatra_btn);
        hatra.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(metszesek.this,  hatra.class);
                startActivity(intent);
            }
        });
    }
}