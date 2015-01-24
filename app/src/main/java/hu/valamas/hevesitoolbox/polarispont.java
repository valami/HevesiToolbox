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

        Button szamit = (Button) findViewById(R.id.button);
        szamit.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                EditText test1 = (EditText) findViewById(R.id.KX_in);
                EditText test2 = (EditText) findViewById(R.id.KY_in);
                EditText test3 = (EditText) findViewById(R.id.szog_in_fok);
                EditText test4 = (EditText) findViewById(R.id.szog_in_perc);
                EditText test5 = (EditText) findViewById(R.id.szog_in_masod);
                EditText test6 = (EditText) findViewById(R.id.tav_in);
                String test11 = test1.getText().toString();
                String test21 = test2.getText().toString();
                String test31 = test3.getText().toString();
                String test41 = test4.getText().toString();
                String test51 = test5.getText().toString();
                String test61 = test6.getText().toString();
                if (test11.matches("") | test21.matches("") |  test61.matches("")  ) {
                    Toast.makeText(getApplicationContext(),
                            "Valamelyik mezö üres !", Toast.LENGTH_LONG).show();
                    return;
                } else if (test31.matches("") |test41.matches("") | test51.matches("") )
                    {
                    if (test31.matches(""))
                    {
                        szogfok.setText("0");
                    } if (test41.matches(""))
                    {
                        szogperc.setText("0");
                    } if (test51.matches(""))
                    {
                        szogmasod.setText("0");
                    }
                }

                float KX = Float.parseFloat(KX_in.getText().toString());
                float KY = Float.parseFloat(KY_in.getText().toString());
                float tav = Float.parseFloat(tav_in.getText().toString());
                float fok = Integer.parseInt(szogfok.getText().toString());
                float perc = Integer.parseInt(szogperc.getText().toString());
                float masod = Integer.parseInt(szogmasod.getText().toString());

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
            PX_eredm.setVisibility(View.INVISIBLE);
            PY_eredm.setVisibility(View.INVISIBLE);
            PY_text.setVisibility(View.INVISIBLE);
            PX_text.setVisibility(View.INVISIBLE);
            KX_in.setText("");
            KY_in.setText("");
            szogfok.setVisibility(View.INVISIBLE);
            szogperc.setVisibility(View.INVISIBLE);
            szogmasod.setVisibility(View.INVISIBLE);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
