package hu.valamas.hevesitoolbox.szamolasok.metszesek;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
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
import android.view.inputmethod.InputMethodManager;
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

public class belsoszoges extends Activity {
    private final String KEYPY = "py" ,KEYPX = "px";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        final trigonometria trigonometria = new trigonometria();
        final tizedes tizedes =new tizedes();
        setContentView(R.layout.activity_belsoszoges);

        AdView mAdView = (AdView) findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);

        final EditText alfa_e = (EditText) findViewById(R.id.alfa_in);
        final EditText beta_e = (EditText) findViewById(R.id.beta_in);
        final EditText AY_e = (EditText) findViewById(R.id.AY_in);
        final EditText AX_e = (EditText) findViewById(R.id.AX_in);
        final EditText BY_e = (EditText) findViewById(R.id.BY_in);
        final EditText BX_e = (EditText) findViewById(R.id.BX_in);
        final EditText PY_e = (EditText) findViewById(R.id.PY_in);
        final EditText PX_e = (EditText) findViewById(R.id.PX_in);
        final Button szamol = (Button) findViewById(R.id.szamol);

        //Visszaállítás
        if (savedInstanceState != null) {
            PX_e.setText(savedInstanceState.getString(KEYPX));
            PY_e.setText(savedInstanceState.getString(KEYPY));
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
        //Szögbevitel dBP
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
                double AY , AX ,BY,BX,alfa,beta;
                try {
                    String alfa_bent =alfa_e.getText().toString();
                    String beta_bent =beta_e.getText().toString();
                    alfa = Double.parseDouble(szogkezeles.tagolas(alfa_bent)[2]) ;
                    beta = Double.parseDouble(szogkezeles.tagolas(beta_bent)[2]) ;
                    AY = Double.parseDouble(AY_e.getText().toString())  ;
                    AX = Double.parseDouble(AX_e.getText().toString())  ;
                    BY = Double.parseDouble(BY_e.getText().toString())  ;
                    BX = Double.parseDouble(BX_e.getText().toString())  ;
                } catch (Exception e) {
                    Toast.makeText(getApplicationContext(), getString(R.string.iranyszog_ures), Toast.LENGTH_SHORT).show();
                    return;
                }
                double dAB = Math.toRadians(geodezia.iranyszog(AY, AX, BY, BX)[0]);
                double tAB = geodezia.iranyszog(AY, AX, BY, BX)[1];

                double dBA = 0;
                if (dAB < trigonometria.pi())   {
                    dBA = dAB + trigonometria.pi();
                } else {
                    dBA = dAB - trigonometria.pi();
                }

                double dAP = dAB - alfa;
                double dBP = dBA + beta ;
                double gamma = trigonometria.pi() - (alfa + beta);

                double tAP = trigonometria.szinusztetel_oldal(beta,gamma,tAB);
                double tBP = trigonometria.szinusztetel_oldal(alfa,gamma,tAB);

                double PY1 = AY + (tAP * Math.sin(dAP));
                double PX1 = AX + (tAP * Math.cos(dAP));
                double PY2 = BY + (tBP * Math.sin(dBP));
                double PX2 = BX + (tBP * Math.cos(dBP));
                double PY = (PY1+PY2) /2;
                double PX = (PX1+PX2) /2;

                PY_e.setText(tizedes.tizedes(PY,3));
                PX_e.setText(tizedes.tizedes(PX,3));

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
        final EditText PY_e = (EditText) findViewById(R.id.PY_in);
        final EditText PX_e = (EditText) findViewById(R.id.PX_in);
        savedInstanceState.putString(KEYPY, PY_e.getText().toString());
        savedInstanceState.putString(KEYPX, PX_e.getText().toString());
        super.onSaveInstanceState(savedInstanceState);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_belsoszoges, menu);
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
            alertDialog.setMessage(getString(R.string.belso_sugo));
            alertDialog.setButton("OK", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int which) {
                }
            });
            alertDialog.show();
        }
        return super.onOptionsItemSelected(item);
    }
}
