package hu.valamas.hevesitoolbox.szamolasok;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.text.Editable;
import android.text.InputFilter;
import android.text.TextWatcher;
import android.text.method.DigitsKeyListener;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.text.DecimalFormat;

import com.example.valamas.hevesitoolbox.R;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;

import hu.valamas.hevesitoolbox.szamolasok.felulet.szogkezeles;

public class polarispont extends Activity {
     DecimalFormat df = new DecimalFormat("#.##");
    private final String KEYY = "y" , KEYX = "x";

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_polarispont);

        AdView mAdView = (AdView) findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);

        final szogkezeles szogkezeles = new szogkezeles();
        final EditText PX_eredm = (EditText) findViewById(R.id.PX_eredmeny);
        final EditText PY_eredm = (EditText) findViewById(R.id.PY_eredmeny);
        final EditText KX_in = (EditText) findViewById(R.id.KX_in);
        final EditText KY_in = (EditText) findViewById(R.id.KY_in);
        final EditText szog = (EditText) findViewById(R.id.szog_in);
        final EditText tav_in = (EditText) findViewById(R.id.tav_in);

        //Visszaállítás
        if (savedInstanceState != null) {
            PX_eredm.setText(savedInstanceState.getString(KEYX));
            PY_eredm.setText(savedInstanceState.getString(KEYY));
        }

        //Szögbevitel
        szog.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String szog_bent =szog.getText().toString();
                String szog_tagolt = szogkezeles.tagolas(szog_bent)[0];
                if (!szog_bent.equals(szog_tagolt)) {
                    szog.setText(szog_tagolt);
                    szog.setSelection(szog.getText().length());
                }
                if (szogkezeles.tagolas(szog_bent)[1].equals("1"))  {
                    szog.setFilters(new InputFilter[] {new InputFilter.LengthFilter(7)});
                    szog.setKeyListener(DigitsKeyListener.getInstance("0123456789"));
                }   else if (szogkezeles.tagolas(szog_bent)[1].equals("2"))  {
                    szog.setFilters(new InputFilter[] {new InputFilter.LengthFilter(8)});
                    szog.setKeyListener(DigitsKeyListener.getInstance("0123456789"));
                }   else if (szogkezeles.tagolas(szog_bent)[1].equals("3"))  {
                    szog.setFilters(new InputFilter[] {new InputFilter.LengthFilter(9)});
                    szog.setKeyListener(DigitsKeyListener.getInstance("0123456789"));
                } else {
                    szog.setFilters(new InputFilter[] {new InputFilter.LengthFilter(9)});
                    szog.setKeyListener(DigitsKeyListener.getInstance("0123456789-."));
                }
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });

        Button szamit = (Button) findViewById(R.id.button);
        szamit.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                String KX_s = KX_in.getText().toString();
                String KY_s = KY_in.getText().toString();
                String szog_t = szog.getText().toString();
                String tav_s = tav_in.getText().toString();

                //Ellenörzés
                double KX , KY ,tav;
                try {
                    KX = Double.parseDouble(KX_s);
                    KY = Double.parseDouble(KY_s);
                    tav = Double.parseDouble(tav_s);
                } catch (Exception e) {
                    Toast.makeText(getApplicationContext(), getString(R.string.polaris_ures), Toast.LENGTH_SHORT).show();
                    return;
                }

                String szog_bent =szog.getText().toString();
                String szog_s = szogkezeles.tagolas(szog_bent)[2];
                double szog = Double.parseDouble(szog_s);

                double PY = KY + (tav * Math.sin(szog));
                double PX = KX + (tav * Math.cos(szog));

                PX_eredm.setText(df.format(PX));
                PY_eredm.setText(df.format(PY));
            }
        });
    }

    //Batyu
    public void onSaveInstanceState(Bundle savedInstanceState) {
        final EditText PX_eredm = (EditText) findViewById(R.id.PX_eredmeny);
        final EditText PY_eredm = (EditText) findViewById(R.id.PY_eredmeny);
        savedInstanceState.putString(KEYY, (String) PY_eredm.getText().toString());
        savedInstanceState.putString(KEYX, (String) PX_eredm.getText().toString());
        super.onSaveInstanceState(savedInstanceState);
    }
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_polarispont, menu);
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
            alertDialog.setMessage(getString(R.string.polaris_sugo));
            alertDialog.setButton("OK", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int which) {
                }
            });
            alertDialog.show();
        }
        return super.onOptionsItemSelected(item);
    }
}