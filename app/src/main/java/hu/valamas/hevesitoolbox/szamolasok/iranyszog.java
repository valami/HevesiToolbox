package hu.valamas.hevesitoolbox.szamolasok;

import android.app.Activity;
import android.content.Intent;
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

public class iranyszog extends Activity {
    private double szog,dist;
    DecimalFormat df =new DecimalFormat("#");
    DecimalFormat dis =new DecimalFormat("#.###");

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_iranyszog);
        final TextView tavolnag = (TextView) findViewById(R.id.tav);
        final TextView szogkiir = (TextView) findViewById(R.id.szogkiir);
        final TextView szog_text = (TextView) findViewById(R.id.szog_text);
        final TextView tav_text = (TextView) findViewById(R.id.tav_text);
        final EditText KX_in = (EditText) findViewById(R.id.KX_in);
        final EditText KY_in = (EditText) findViewById(R.id.KY_in);
        final EditText VX_in = (EditText) findViewById(R.id.VX_in);
        final EditText VY_in = (EditText) findViewById(R.id.VY_in);

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
                            "Valamelyik mezö üres !", Toast.LENGTH_SHORT).show();
                            return;
                }
                double KX = Double.parseDouble(KX_s);
                double KY = Double.parseDouble(KY_s);
                double VX = Double.parseDouble(VX_s);
                double VY = Double.parseDouble(VY_s);

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
                    "A két koordináta egyezik !", Toast.LENGTH_SHORT).show();
                    return;
                    }

                tavolnag.setVisibility(View.VISIBLE);
                szogkiir.setVisibility(View.VISIBLE);
                szog_text.setVisibility(View.VISIBLE);
                tav_text.setVisibility(View.VISIBLE);

                String fokkiir = (df.format(Math.floor(szog)));
                double t1 = (szog - (Math.floor(szog))) * 60;
                double m = (Math.floor((szog - (Math.floor(szog))) * 60));
                String perckiir = (df.format(m));
                String masodkiir = (df.format((t1 - m) * 60));
                szogkiir.setText(fokkiir + " - " + perckiir + " - " + masodkiir);

                dist = Math.sqrt((((VY - KY) * (VY - KY)) + ((VX - KX) * (VX - KX))));
                tavolnag.setText(dis.format(dist));
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
        return super.onOptionsItemSelected(item);
    }
}
