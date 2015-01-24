package hu.valamas.hevesitoolbox;

import android.app.Activity;
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
    private double KX , KY , VX ,VY ,szog,dist;
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
                EditText test1 = (EditText) findViewById(R.id.KX_in);
                EditText test2 = (EditText) findViewById(R.id.KY_in);
                EditText test3 = (EditText) findViewById(R.id.VX_in);
                EditText test4 = (EditText) findViewById(R.id.VY_in);
                String test11 = test1.getText().toString();
                String test21 = test2.getText().toString();
                String test31 = test3.getText().toString();
                String test41 = test4.getText().toString();
                if (test11.matches("") | test21.matches("") |  test31.matches("") | test41.matches("") ) {
                    Toast.makeText(getApplicationContext(),
                            "Valamelyik mezö üres !", Toast.LENGTH_LONG).show();
                    return;
                }

                float KX = Float.parseFloat(KX_in.getText().toString());
                float KY = Float.parseFloat(KY_in.getText().toString());
                float VX = Float.parseFloat(VX_in.getText().toString());
                float VY = Float.parseFloat(VY_in.getText().toString());

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
                    "A két koordináta egyezik !", Toast.LENGTH_LONG).show();
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
            final TextView tavolnag = (TextView) findViewById(R.id.tav);
            final TextView szogkiir = (TextView) findViewById(R.id.szogkiir);
            final TextView szog_text = (TextView) findViewById(R.id.szog_text);
            final TextView tav_text = (TextView) findViewById(R.id.tav_text);
            tavolnag.setVisibility(View.INVISIBLE);
            szogkiir.setVisibility(View.INVISIBLE);
            szog_text.setVisibility(View.INVISIBLE);
            tav_text.setVisibility(View.INVISIBLE);
            final EditText KX_in = (EditText) findViewById(R.id.KX_in);
            final EditText KY_in = (EditText) findViewById(R.id.KY_in);
            final EditText VX_in = (EditText) findViewById(R.id.VX_in);
            final EditText VY_in = (EditText) findViewById(R.id.VY_in);
            KX_in.setText("");
            KY_in.setText("");
            VX_in.setText("");
            VY_in.setText("");
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
