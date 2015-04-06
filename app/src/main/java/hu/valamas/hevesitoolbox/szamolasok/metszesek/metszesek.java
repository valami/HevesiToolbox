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
                intent.putExtra("orientation", orient());
                startActivity(intent);
            }
        });

        final Button belsoszoges = (Button) findViewById(R.id.belsoszoges_btn);
        belsoszoges.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(metszesek.this,  belsoszoges.class);
                intent.putExtra("orientation", orient());
                startActivity(intent);
            }
        });

        final Button oldal = (Button) findViewById(R.id.oldal_btn);
        oldal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(metszesek.this,  oldal.class);
                intent.putExtra("orientation", orient());
                startActivity(intent);
            }
        });

        final Button iv = (Button) findViewById(R.id.iv_btn);
        iv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(metszesek.this,  iv.class);
                intent.putExtra("orientation", orient());
                startActivity(intent);
            }
        });

        final Button hatra = (Button) findViewById(R.id.hatra_btn);
        hatra.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(metszesek.this,  hatra.class);
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
        getMenuInflater().inflate(R.menu.menu_metszesek, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();



        return super.onOptionsItemSelected(item);
    }
}
