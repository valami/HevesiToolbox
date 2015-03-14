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

import com.example.valamas.hevesitoolbox.R;
import hu.valamas.hevesitoolbox.szamolasok.felulet.tizedes;
import hu.valamas.hevesitoolbox.szamolasok.felulet.szogkezeles;
import hu.valamas.hevesitoolbox.szamolasok.alapmuvelet.geodezia;

public class iranyszog extends Activity   {
    private double szog,dist;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_iranyszog);
        final tizedes tizedes =new tizedes();
        final geodezia geodezia =new geodezia();
        final szogkezeles szogkezeles = new szogkezeles();
        final TextView tavolnag = (TextView) findViewById(R.id.tav);
        final TextView szogkiir = (TextView) findViewById(R.id.szogkiir);
        final TextView szog_text = (TextView) findViewById(R.id.szog_text);
        final TextView tav_text = (TextView) findViewById(R.id.tav_text);
        final EditText KX_in = (EditText) findViewById(R.id.KX_in);
        final EditText KY_in = (EditText) findViewById(R.id.KY_in);
        final EditText VX_in = (EditText) findViewById(R.id.VX_in);
        final EditText VY_in = (EditText) findViewById(R.id.VY_in);
        //Forgatás
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
                //Ellenörzés
                String KX_s = KX_in.getText().toString();
                String KY_s = KY_in.getText().toString();
                String VX_s = VX_in.getText().toString();
                String VY_s = VY_in.getText().toString();
                if (KX_s.matches("") | KY_s.matches("") |  VX_s.matches("") | VY_s.matches("") ) {
                    Toast.makeText(getApplicationContext(),
                            getString(R.string.iranyszog_ures), Toast.LENGTH_SHORT).show();
                            return;
                }
                //Számol
                double KX = Double.parseDouble(KX_s);
                double KY = Double.parseDouble(KY_s);
                double VX = Double.parseDouble(VX_s);
                double VY = Double.parseDouble(VY_s);
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

                tavolnag.setVisibility(View.VISIBLE);
                szogkiir.setVisibility(View.VISIBLE);
                szog_text.setVisibility(View.VISIBLE);
                tav_text.setVisibility(View.VISIBLE);

                szogkiir.setText(szogkezeles.kiiras(szog));

                dist = Math.sqrt((((VY - KY) * (VY - KY)) + ((VX - KX) * (VX - KX))));

                tavolnag.setText(tizedes.tizedes(dist,3));
            }
        });
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