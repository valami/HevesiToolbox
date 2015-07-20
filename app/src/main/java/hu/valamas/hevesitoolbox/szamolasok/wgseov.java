package hu.valamas.hevesitoolbox.szamolasok;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.valamas.hevesitoolbox.R;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;

import hu.valamas.hevesitoolbox.szamolasok.eovwgs.api;
import hu.valamas.hevesitoolbox.szamolasok.network.ConnectionTest;

public class wgseov extends Activity {
    double be1 , be2 , be3;
    String mode = "ew" ;
    public Context mContext = this;

    private TextView ki1 ,ki2 ,ki3 ,ki4 ,ki5 ,ki6;
    private EditText be1_i ,be2_i,be3_i;
    private final String KEY1 = "key1" ,KEY2 = "key2",KEY3 = "key3",KEY4 = "key4",KEY5 = "key5",KEY6 = "key6",KEYMODE ="keymode";

    @Override
    public void onCreate(Bundle savedinstanceState) {
        super.onCreate(savedinstanceState);
        setContentView(R.layout.activity_wgseov);

        AdView mAdView = (AdView) findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);

        final api api = new api();
        final RadioButton ew = (RadioButton) findViewById(R.id.btn_ew);
        final RadioButton we = (RadioButton) findViewById(R.id.btn_we);
        final TextView be1_t = (TextView) findViewById(R.id.be1_text);
        final TextView be2_t = (TextView) findViewById(R.id.be2_text);
        final TextView be3_t = (TextView) findViewById(R.id.be3_text);
        be1_i = (EditText) findViewById(R.id.be1_in);
        be2_i = (EditText) findViewById(R.id.be2_in);
        be3_i = (EditText) findViewById(R.id.be3_in);
        ki1 = (TextView) findViewById(R.id.ki1_text);
        ki2 = (TextView) findViewById(R.id.ki2_text);
        ki3 = (TextView) findViewById(R.id.ki3_text);
        ki4 = (TextView) findViewById(R.id.ki4_text);
        ki5 = (TextView) findViewById(R.id.ki5_text);
        ki6 = (TextView) findViewById(R.id.ki6_text);


        //Visszaállítás
        if (savedinstanceState != null) {
            ki1.setText(savedinstanceState.getString(KEY1));
            ki2.setText(savedinstanceState.getString(KEY2));
            ki3.setText(savedinstanceState.getString(KEY3));

            mode = savedinstanceState.getString(KEYMODE);
            if (mode.equals("ew") ){
                ki4.setText(savedinstanceState.getString(KEY4));
                ki5.setText(savedinstanceState.getString(KEY5));
                ki6.setText(savedinstanceState.getString(KEY6));
            }
        }

        //Kapcsolat ellenörzés
       // kapcsolat();

        ew.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                mode = "ew";
                be1_t.setText("Y :");
                be2_t.setText("X :");
                be3_t.setText("H :");
                tisztit();
            }
        });

        we.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                mode = "we";
                be1_t.setText("X : (fi)");
                be2_t.setText("Y : (la)");
                be3_t.setText("Z : (h) ");
                tisztit();
            }
        });

        Button btnHttpGetAsync = (Button) findViewById(R.id.szamol);
        btnHttpGetAsync.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View arg0) {

                try {
                    be1 = Double.parseDouble(be1_i.getText().toString());
                    be2 = Double.parseDouble(be2_i.getText().toString());
                    be3 = Double.parseDouble(be3_i.getText().toString());
                } catch (Exception e) {
                    Toast.makeText(getApplicationContext(), "Valamelyik mező üres !", Toast.LENGTH_SHORT).show();
                    return;
                }

                api.atvalt(mContext, mode, be1, be2, be3);

                api.setOnkEventListener(new api.OnkEventListener() {
                    @Override
                    public void onErrorTrack(String Result) {
                        Toast.makeText(getApplicationContext(), "Probléma van a kapcsolattal!",Toast.LENGTH_LONG).show();
                    }

                    @Override
                    public void onReadyTrack(String[] Result) {
                        TextView ki1 = (TextView) findViewById(R.id.ki1_text);
                        TextView ki2 = (TextView) findViewById(R.id.ki2_text);
                        TextView ki3 = (TextView) findViewById(R.id.ki3_text);
                        TextView ki4 = (TextView) findViewById(R.id.ki4_text);
                        TextView ki5 = (TextView) findViewById(R.id.ki5_text);
                        TextView ki6 = (TextView) findViewById(R.id.ki6_text);

                        if (mode.equals("ew")){
                            ki1.setText("X  :" + Result[0]);
                            ki2.setText("Y  :" + Result[1]);
                            ki3.setText("Z  :" + Result[2]);
                            ki4.setText("Fi :" + Result[3]);
                            ki5.setText("La :" + Result[4]);
                            ki6.setText("h  :" + Result[5]);
                        } else {
                            ki1.setText("Y  :" + Result[0]);
                            ki2.setText("X  :" + Result[1]);
                            ki3.setText("H  :" + Result[2]);
                            ki4.setText("");
                            ki5.setText("");
                            ki6.setText("");
                        }
                    }
                }); ;
            }
        });
    }
    public void tisztit(){
        ki1.setText("");
        ki2.setText("");
        ki3.setText("");
        ki4.setText("");
        ki5.setText("");
        ki6.setText("");
        be1_i.setText("");
        be2_i.setText("");
        be3_i.setText("");
    }
    public void kapcsolat(){
        final ConnectionTest ConnectionTest = new ConnectionTest();
        if (!ConnectionTest.isInternetAvailable()){
            AlertDialog alertDialog = new AlertDialog.Builder(this).create();
            alertDialog.setTitle("Hiba");
            alertDialog.setMessage("Nincs kapcsolat a szerverrel!");
            alertDialog.setButton("OK", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int which) {
                    finish();
                }
            });
            alertDialog.show();
        }
    }

    public void onSaveInstanceState(Bundle savedInstanceState) {
        TextView ki1 = (TextView) findViewById(R.id.ki1_text);
        TextView ki2 = (TextView) findViewById(R.id.ki2_text);
        TextView ki3 = (TextView) findViewById(R.id.ki3_text);
        TextView ki4 = (TextView) findViewById(R.id.ki4_text);
        TextView ki5 = (TextView) findViewById(R.id.ki5_text);
        TextView ki6 = (TextView) findViewById(R.id.ki6_text);
        final RadioButton ew = (RadioButton) findViewById(R.id.btn_ew);

        savedInstanceState.putString(KEY1, ki1.getText().toString());
        savedInstanceState.putString(KEY2, ki2.getText().toString());
        savedInstanceState.putString(KEY3, ki3.getText().toString());
        savedInstanceState.putString(KEY4, ki4.getText().toString());
        savedInstanceState.putString(KEY5, ki5.getText().toString());
        savedInstanceState.putString(KEY6, ki6.getText().toString());

        savedInstanceState.putString(KEYMODE, mode);
        super.onSaveInstanceState(savedInstanceState);
    }
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_wgseov, menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_reset) {
            tisztit();
            return true;
        }
        if (id == R.id.action_info)
        {
            AlertDialog alertDialog = new AlertDialog.Builder(this).create();
            alertDialog.setTitle(getString(R.string.menu_sugo));
            alertDialog.setMessage(getString(R.string.wgs_sugo));
            alertDialog.setButton("OK", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int which) {
                }
            });
            alertDialog.show();
        }
        return super.onOptionsItemSelected(item);
    }
}