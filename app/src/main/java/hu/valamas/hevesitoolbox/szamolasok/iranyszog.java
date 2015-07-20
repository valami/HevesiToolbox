package hu.valamas.hevesitoolbox.szamolasok;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.valamas.hevesitoolbox.R;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;

import hu.valamas.hevesitoolbox.szamolasok.felulet.tizedes;
import hu.valamas.hevesitoolbox.szamolasok.felulet.szogkezeles;
import hu.valamas.hevesitoolbox.szamolasok.alapmuvelet.geodezia;

public class iranyszog extends Activity   {
    private double szog,dist;
    private final String KEYSZOG = "szog" ,KEYTAV = "tav";


    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_iranyszog);
        final tizedes tizedes =new tizedes();
        final geodezia geodezia =new geodezia();
        final szogkezeles szogkezeles = new szogkezeles();
        final EditText tavolnag = (EditText) findViewById(R.id.tav);
        final EditText szogkiir = (EditText) findViewById(R.id.szogkiir);
        final EditText KX_in = (EditText) findViewById(R.id.KX_in);
        final EditText KY_in = (EditText) findViewById(R.id.KY_in);
        final EditText VX_in = (EditText) findViewById(R.id.VX_in);
        final EditText VY_in = (EditText) findViewById(R.id.VY_in);

        AdView mAdView = (AdView) findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);

        Button szamit = (Button) findViewById(R.id.button);
            szamit.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                //Visszaállítás
                if (savedInstanceState != null) {
                    tavolnag.setText(savedInstanceState.getString(KEYTAV));
                    szogkiir.setText(savedInstanceState.getString(KEYSZOG));
                }
                //Ellenörzés
                String KX_s , KY_s , VX_s , VY_s;
                double KX , KY ,VX ,VY;
                try {
                    KX_s = KX_in.getText().toString();
                    KY_s = KY_in.getText().toString();
                    VX_s = VX_in.getText().toString();
                    VY_s = VY_in.getText().toString();
                    KX = Double.parseDouble(KX_s);
                    KY = Double.parseDouble(KY_s);
                    VX = Double.parseDouble(VX_s);
                    VY = Double.parseDouble(VY_s);
                } catch (Exception e) {
                    Toast.makeText(getApplicationContext(), getString(R.string.iranyszog_ures), Toast.LENGTH_SHORT).show();
                    return;
                }

                //Számol
                szog = geodezia.iranyszog(KY,KX,VY,VX)[0];
                szog = geodezia.iranyszog(KY,KX,VY,VX)[0];

                double szogu = ((VY - KY) / (VX - KX));
                double szoge = Math.toDegrees(Math.atan(szogu));
                if ((VY - KY) > 0 & (VX - KX) > 0) {
                    szog = szoge;
                } else if ((VY - KY) > 0 & (VX - KX) < 0) {
                    szog = szoge + 180;
                } else if ((VY - KY) < 0 & (VX - KX) < 0) {
                    szog = szoge + 180;
                } else if ((VY - KY) < 0 & (VX - KX) > 0) {
                    szog = szoge + 360;
                }
                  else if ((VY - KY) > 0 & (VX - KX) == 0) {
                    szog = 90;
                } else if ((VY - KY) == 0 & (VX - KX) > 0) {
                    szog = 0;
                } else if ((VY - KY) < 0 & (VX - KX) == 0) {
                    szog = 270;
                } else if ((VY - KY) == 0 & (VX - KX) < 0) {
                    szog = 180;
                }else{
                    Toast.makeText(getApplicationContext(),
                        getString(R.string.iranyszog_egyezik), Toast.LENGTH_SHORT).show();
                    return;
                }
                dist = Math.sqrt((((VY - KY) * (VY - KY)) + ((VX - KX) * (VX - KX))));

                szogkiir.setText(szogkezeles.kiiras(szog));
                tavolnag.setText(tizedes.tizedes(dist,3));

                //Billentyüzet
                InputMethodManager inputManager = (InputMethodManager)
                        getSystemService(Context.INPUT_METHOD_SERVICE);

                inputManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(),
                        InputMethodManager.HIDE_NOT_ALWAYS);
                }
        });
    }

    //Batyu
    public void onSaveInstanceState(Bundle savedInstanceState) {
        final EditText tavolsag = (EditText) findViewById(R.id.tav);
        final EditText szogkiir = (EditText) findViewById(R.id.szogkiir);
        savedInstanceState.putString(KEYSZOG, (String) szogkiir.getText().toString());
        savedInstanceState.putString(KEYTAV, (String) tavolsag.getText().toString());
        super.onSaveInstanceState(savedInstanceState);
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_iranyszog, menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_reset) {
            Intent intent = getIntent();
            finish();
            startActivity(intent);
            return true;
        }
        if (id == R.id.action_info)
        {
            AlertDialog alertDialog = new AlertDialog.Builder(this).create();
            alertDialog.setTitle(getString(R.string.menu_sugo));
            alertDialog.setMessage(getString(R.string.iranyszog_sugo));
            alertDialog.setButton("OK", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int which) {
                }
            });
            alertDialog.show();
        }
        return super.onOptionsItemSelected(item);
    }
}