package hu.valamas.hevesitoolbox.szamolasok.metszesek;

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

import com.example.valamas.hevesitoolbox.R;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;

import hu.valamas.hevesitoolbox.szamolasok.alapmuvelet.geodezia;
import hu.valamas.hevesitoolbox.szamolasok.alapmuvelet.trigonometria;
import hu.valamas.hevesitoolbox.szamolasok.felulet.szogkezeles;
import hu.valamas.hevesitoolbox.szamolasok.felulet.tizedes;

public class hatra extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hatra);

        AdView mAdView = (AdView) findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);

        final trigonometria trigonometria = new trigonometria();
        final tizedes tizedes =new tizedes();
        final EditText alfa_e = (EditText) findViewById(R.id.alfa_in);
        final EditText beta_e = (EditText) findViewById(R.id.beta_in);
        final EditText AY_e = (EditText) findViewById(R.id.AY_in);
        final EditText AX_e = (EditText) findViewById(R.id.AX_in);
        final EditText BY_e = (EditText) findViewById(R.id.BY_in);
        final EditText BX_e = (EditText) findViewById(R.id.BX_in);
        final EditText KY_e = (EditText) findViewById(R.id.KY_in);
        final EditText KX_e = (EditText) findViewById(R.id.KX_in);
        final EditText PY_e = (EditText) findViewById(R.id.PY_in);
        final EditText PX_e = (EditText) findViewById(R.id.PX_in);
        final Button szamol = (Button) findViewById(R.id.szamol);

        //Forgatás
        Bundle extras = getIntent().getExtras();
        Byte orientation = extras.getByte("orientation");
        if (orientation == 0)   {
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        }   else {
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        }

        //Szögbevitel alfa
        alfa_e.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String szog_bent =alfa_e.getText().toString();
                String szog_tagolt = szogkezeles.tagolas(szog_bent)[0];
                if (!szog_bent.equals(szog_tagolt)) {
                    alfa_e.setText(szog_tagolt);
                    alfa_e.setSelection(alfa_e.getText().length());
                }
                if (szogkezeles.tagolas(szog_bent)[1].equals("1"))  {
                    alfa_e.setFilters(new InputFilter[] {new InputFilter.LengthFilter(7)});
                    alfa_e.setKeyListener(DigitsKeyListener.getInstance("0123456789"));
                }   else if (szogkezeles.tagolas(szog_bent)[1].equals("2"))  {
                    alfa_e.setFilters(new InputFilter[] {new InputFilter.LengthFilter(8)});
                    alfa_e.setKeyListener(DigitsKeyListener.getInstance("0123456789"));
                }   else if (szogkezeles.tagolas(szog_bent)[1].equals("3"))  {
                    alfa_e.setFilters(new InputFilter[] {new InputFilter.LengthFilter(9)});
                    alfa_e.setKeyListener(DigitsKeyListener.getInstance("0123456789"));
                } else {
                    alfa_e.setFilters(new InputFilter[] {new InputFilter.LengthFilter(9)});
                    alfa_e.setKeyListener(DigitsKeyListener.getInstance("0123456789-."));
                }
            }
            @Override
            public void afterTextChanged(Editable s) {
            }
        });
        //Szögbevitel beta
        beta_e.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String szog_bent =beta_e.getText().toString();
                String szog_tagolt = szogkezeles.tagolas(szog_bent)[0];
                if (!szog_bent.equals(szog_tagolt)) {
                    beta_e.setText(szog_tagolt);
                    beta_e.setSelection(beta_e.getText().length());
                }
                if (szogkezeles.tagolas(szog_bent)[1].equals("1"))  {
                    beta_e.setFilters(new InputFilter[] {new InputFilter.LengthFilter(7)});
                    beta_e.setKeyListener(DigitsKeyListener.getInstance("0123456789"));
                }   else if (szogkezeles.tagolas(szog_bent)[1].equals("2"))  {
                    beta_e.setFilters(new InputFilter[] {new InputFilter.LengthFilter(8)});
                    beta_e.setKeyListener(DigitsKeyListener.getInstance("0123456789"));
                }   else if (szogkezeles.tagolas(szog_bent)[1].equals("3"))  {
                    beta_e.setFilters(new InputFilter[] {new InputFilter.LengthFilter(9)});
                    beta_e.setKeyListener(DigitsKeyListener.getInstance("0123456789"));
                } else {
                    beta_e.setFilters(new InputFilter[] {new InputFilter.LengthFilter(9)});
                    beta_e.setKeyListener(DigitsKeyListener.getInstance("0123456789-."));
                }
            }
            @Override
            public void afterTextChanged(Editable s) {
            }
        });

        szamol.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (AY_e.equals("") || AX_e.equals("") || BY_e.equals("")|| BX_e.equals("") || alfa_e.equals("")|| beta_e.equals(""))
                {
                    Toast.makeText(getApplicationContext(),
                            getString(R.string.polaris_ures), Toast.LENGTH_SHORT).show();
                    return;
                }
                String alfa_bent =alfa_e.getText().toString();
                double alfa = Double.parseDouble(szogkezeles.tagolas(alfa_bent)[2]) ;
                String beta_bent =beta_e.getText().toString();
                double beta = Double.parseDouble(szogkezeles.tagolas(beta_bent)[2]) ;
                double AY = Double.parseDouble(AY_e.getText().toString())  ;
                double AX = Double.parseDouble(AX_e.getText().toString())  ;
                double BY = Double.parseDouble(BY_e.getText().toString())  ;
                double BX = Double.parseDouble(BX_e.getText().toString())  ;
                double KY = Double.parseDouble(KY_e.getText().toString())  ;
                double KX = Double.parseDouble(KX_e.getText().toString())  ;

                double dAB = Math.toRadians(geodezia.iranyszog(AY, AX, BY, BX)[0]);
                double tAB = geodezia.iranyszog(AY, AX, BY, BX)[1];

                double sugar = tAB / Math.sin(alfa+beta);
                double dAC = dAB - beta;
                double tAC = sugar * Math.sin(alfa);
                double CY = AY + tAC * Math.sin(dAC);
                double CX = AX + tAC * Math.cos(dAC);
                double dCK =Math.toRadians( geodezia.iranyszog(CY,CX,KY,KX)[0]);
                double tCK = geodezia.iranyszog(CY,CX,KY,KX)[1];
                double dCA = 0;
                if (dAC < trigonometria.pi())   {
                    dCA = dAC + trigonometria.pi();
                } else {
                    dCA = dAC - trigonometria.pi();
                }
                double alfaj = dCA -dCK;
                double tCP = sugar * Math.sin(alfa+alfaj);
                double PY = CY + tCP * Math.sin(dCK);
                double PX = CX + tCP * Math.cos(dCK);

                PY_e.setText(tizedes.tizedes(PY,3))  ;
                PX_e.setText(tizedes.tizedes(PX,3));
            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_hatra, menu);
        return true;
    }

    @Override
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
            alertDialog.setMessage(getString(R.string.hatra_sugo));
            alertDialog.setButton("OK", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int which) {
                }
            });
            alertDialog.show();
        }
        return super.onOptionsItemSelected(item);
    }
}
