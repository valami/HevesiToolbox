package hu.valamas.hevesitoolbox.szamolasok;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DecimalFormat;

import com.example.valamas.hevesitoolbox.R;

public class polarispont extends Activity {
     DecimalFormat df = new DecimalFormat("#.##");

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_polarispont);

        final TextView PX_eredm = (TextView) findViewById(R.id.PX_eredmeny);
        final TextView PY_eredm = (TextView) findViewById(R.id.PY_eredmeny);
        final EditText KX_in = (EditText) findViewById(R.id.KX_in);
        final EditText KY_in = (EditText) findViewById(R.id.KY_in);
        final TextView  PX_text = (TextView) findViewById(R.id.PX_text);
        final TextView  PY_text = (TextView) findViewById(R.id.PY_text);
        final EditText szogfok = (EditText) findViewById(R.id.szog_in_fok);
        final EditText szogperc = (EditText) findViewById(R.id.szog_in_perc);
        final EditText szogmasod = (EditText) findViewById(R.id.szog_in_masod);
        final EditText tav_in = (EditText) findViewById(R.id.tav_in);

        Bundle extras = getIntent().getExtras();
        Byte orientation = extras.getByte("orientation");
        if (orientation == 0)   {
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        }   else {
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        }

        Button szamit = (Button) findViewById(R.id.button);
        szamit.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                String KX_s = KX_in.getText().toString();
                String KY_s = KY_in.getText().toString();
                String szogfok_s = szogfok.getText().toString();
                String szogperc_s = szogperc.getText().toString();
                String szogmasod_s = szogmasod.getText().toString();
                String tav_s = tav_in.getText().toString();
                if (KX_s.matches("") | KY_s.matches("") |  tav_s.matches("")  ) {
                    Toast.makeText(getApplicationContext(),
                            getString(R.string.polaris_ures), Toast.LENGTH_SHORT).show();
                            return;
                } else if (szogfok_s.matches("") |szogperc_s.matches("") | szogmasod_s.matches("") )
                    {
                    if (szogfok_s.matches(""))
                    {
                        szogfok.setText("0");
                        szogfok_s ="0";
                    } if (szogperc_s.matches(""))
                    {
                        szogperc.setText("0");
                        szogperc_s="0";
                    } if (szogmasod_s.matches(""))
                    {
                        szogmasod.setText("0");
                        szogmasod_s="0";
                    }
                }

                double KX = Double.parseDouble(KX_s);
                double KY = Double.parseDouble(KY_s);
                double tav = Double.parseDouble(tav_s);
                double fok = Double.parseDouble(szogfok_s);
                double perc = Double.parseDouble(szogperc_s);
                double masod = Double.parseDouble(szogmasod_s);

                double szog =Math.toRadians (fok + (perc/60) + (masod/3600)) ;

                double PY = KY + (tav * Math.sin(szog));
                double PX = KX + (tav * Math.cos(szog));

                PX_eredm.setText(df.format(PX));
                PY_eredm.setText(df.format(PY));

                PX_eredm.setVisibility(View.VISIBLE);
                PY_eredm.setVisibility(View.VISIBLE);
                PX_text.setVisibility(View.VISIBLE);
                PY_text.setVisibility(View.VISIBLE);
            }
        });
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
