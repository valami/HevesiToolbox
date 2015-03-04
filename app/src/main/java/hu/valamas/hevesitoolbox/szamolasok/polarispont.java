package hu.valamas.hevesitoolbox.szamolasok;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DecimalFormat;

import com.example.valamas.hevesitoolbox.R;
import hu.valamas.hevesitoolbox.szamolasok.felulet.szogkezeles;

public class polarispont extends Activity {
     DecimalFormat df = new DecimalFormat("#.##");

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_polarispont);

        final szogkezeles szogkezeles = new szogkezeles();
        final TextView PX_eredm = (TextView) findViewById(R.id.PX_eredmeny);
        final TextView PY_eredm = (TextView) findViewById(R.id.PY_eredmeny);
        final EditText KX_in = (EditText) findViewById(R.id.KX_in);
        final EditText KY_in = (EditText) findViewById(R.id.KY_in);
        final TextView  PX_text = (TextView) findViewById(R.id.PX_text);
        final TextView  PY_text = (TextView) findViewById(R.id.PY_text);
        final EditText szog = (EditText) findViewById(R.id.szog_in);
        final EditText tav_in = (EditText) findViewById(R.id.tav_in);


        //Forgatás
        Bundle extras = getIntent().getExtras();
        Byte orientation = extras.getByte("orientation");
        if (orientation == 0)   {
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        }   else {
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        }

        //Szögbevitel
        szog.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String szog_bent =szog.getText().toString();
                String szog_tagolt = szogkezeles.tagolas(szog_bent);
                if (!szog_bent.equals(szog_tagolt))
                {
                    szog.setText(szog_tagolt);
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

                //final String szog_s = szog.getText().toString();





                String tav_s = tav_in.getText().toString();
                if (KX_s.matches("") || KY_s.matches("") ||  tav_s.matches("") ) {
                    Toast.makeText(getApplicationContext(),
                            getString(R.string.polaris_ures), Toast.LENGTH_SHORT).show();
                            return;
                }

                double KX = Double.parseDouble(KX_s);
                double KY = Double.parseDouble(KY_s);
                double tav = Double.parseDouble(tav_s);

     //           double fok = Double.parseDouble(szogfok_s);

      //          double szog =Math.toRadians (fok + (perc/60) + (masod/3600)) ;

      //          double PY = KY + (tav * Math.sin(szog));
      //          double PX = KX + (tav * Math.cos(szog));

      //          PX_eredm.setText(df.format(PX));
       //         PY_eredm.setText(df.format(PY));

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
