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

import com.example.valamas.hevesitoolbox.R;

import java.text.DecimalFormat;


public class haromszog extends Activity {
    DecimalFormat df = new DecimalFormat("#");
    DecimalFormat df2 = new DecimalFormat("#.##");
    DecimalFormat df3 = new DecimalFormat("##");

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_haromszog);
        final EditText a_in = (EditText) findViewById(R.id.a_in);
        final EditText b_in = (EditText) findViewById(R.id.b_in);
        final EditText c_in = (EditText) findViewById(R.id.c_in);
        final EditText alfa_fok_in = (EditText) findViewById(R.id.alfa_fok_in);
        final EditText alfa_perc_in = (EditText) findViewById(R.id.alfa_perc_in);
        final EditText alfa_masod_in = (EditText) findViewById(R.id.alfa_masod_in);
        final EditText beta_fok_in = (EditText) findViewById(R.id.beta_fok_in);
        final EditText beta_perc_in = (EditText) findViewById(R.id.beta_perc_in);
        final EditText beta_masod_in = (EditText) findViewById(R.id.beta_masod_in);
        final EditText gamma_fok_in = (EditText) findViewById(R.id.gamma_fok_in);
        final EditText gamma_perc_in = (EditText) findViewById(R.id.gamma_perc_in);
        final EditText gamma_masod_in = (EditText) findViewById(R.id.gamma_masod_in);
        final TextView terulet = (TextView) findViewById(R.id.terulet);

        Button szamit = (Button) findViewById(R.id.button);
        szamit.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                String test1 = a_in.getText().toString();
                String test2 = b_in.getText().toString();
                String test3 = c_in.getText().toString();
                String test4 = alfa_fok_in.getText().toString();
                String test5 = alfa_perc_in.getText().toString();
                String test6 = alfa_masod_in.getText().toString();
                String test7 = beta_fok_in.getText().toString();
                String test8 = beta_perc_in.getText().toString();
                String test9 = beta_masod_in.getText().toString();
                String test10 = gamma_fok_in.getText().toString();
                String test11 = gamma_perc_in.getText().toString();
                String test12 = gamma_masod_in.getText().toString();

                if (!test1.matches("") & !test2.matches("") & !test3.matches("")) {
                    //három oldal
                    double a = Double.parseDouble(a_in.getText().toString());
                    double b = Double.parseDouble(b_in.getText().toString());
                    double c = Double.parseDouble(c_in.getText().toString());

                    double alfa_e = costetel_szog(c, b, a);
                    double beta_e = costetel_szog(c, a, b);
                    double gamma_e = costetel_szog(a, b, c);

                    double[] alfa = visszavalt(alfa_e);
                    double[] beta = visszavalt(beta_e);
                    double[] gamma = visszavalt(gamma_e);

                    alfa_fok_in.setText(df.format(alfa[0]));
                    alfa_perc_in.setText(df.format(alfa[1]));
                    alfa_masod_in.setText(df.format(alfa[2]));
                    beta_fok_in.setText(df.format(beta[0]));
                    beta_perc_in.setText(df3.format(beta[1]));
                    beta_masod_in.setText(df3.format(beta[2]));
                    gamma_fok_in.setText(df.format(gamma[0]));
                    gamma_perc_in.setText(df3.format(gamma[1]));
                    gamma_masod_in.setText(df3.format(gamma[2]));

                    terulet.setVisibility(View.VISIBLE);
                    terulet.setText("Terület :" + df2.format(heron(a, b, c)));
                } else if (!test1.matches("") & !test2.matches("") & !test10.matches("") & !test11.matches("") & !test12.matches("")) {
                    //a-b-gamma
                    double a = Double.parseDouble(a_in.getText().toString());
                    double b = Double.parseDouble(b_in.getText().toString());
                    double gamma_fok = Double.parseDouble(gamma_fok_in.getText().toString());
                    double gamma_perc = Double.parseDouble(gamma_perc_in.getText().toString());
                    double gamma_masod = Double.parseDouble(gamma_masod_in.getText().toString());

                    double gamma_szog = valt(gamma_fok, gamma_perc, gamma_masod);
                    double c = costetel_oldal(a, b, gamma_szog);

                    double alfa_e = costetel_szog(c, b, a);
                    double beta_e = costetel_szog(c, a, b);
                    double[] alfa = visszavalt(alfa_e);
                    double[] beta = visszavalt(beta_e);

                    alfa_fok_in.setText(df.format(alfa[0]));
                    alfa_perc_in.setText(df3.format(alfa[1]));
                    alfa_masod_in.setText(df3.format(alfa[2]));
                    beta_fok_in.setText(df.format(beta[0]));
                    beta_perc_in.setText(df3.format(beta[1]));
                    beta_masod_in.setText(df3.format(beta[2]));
                    c_in.setText(df2.format(c));

                    terulet.setText("Terület :" + df2.format(heron(a, b, c)));
                    terulet.setVisibility(View.VISIBLE);
                } else if (!test1.matches("") & !test3.matches("") & !test7.matches("") & !test8.matches("") & !test9.matches("")) {
                    //a-c-béta
                    double a = Double.parseDouble(a_in.getText().toString());
                    double c = Double.parseDouble(c_in.getText().toString());
                    double beta_fok = Double.parseDouble(beta_fok_in.getText().toString());
                    double beta_perc = Double.parseDouble(beta_perc_in.getText().toString());
                    double beta_masod = Double.parseDouble(beta_masod_in.getText().toString());

                    double beta_szog = valt(beta_fok, beta_perc, beta_masod);
                    double b = costetel_oldal(a, c, beta_szog);

                    double alfa_e = costetel_szog(c, b, a);
                    double gamma_e = costetel_szog(a, b, c);
                    double[] alfa = visszavalt(alfa_e);
                    double[] gamma = visszavalt(gamma_e);

                    alfa_fok_in.setText(df.format(alfa[0]));
                    alfa_perc_in.setText(df3.format(alfa[1]));
                    alfa_masod_in.setText(df3.format(alfa[2]));
                    gamma_fok_in.setText(df.format(gamma[0]));
                    gamma_perc_in.setText(df3.format(gamma[1]));
                    gamma_masod_in.setText(df3.format(gamma[2]));
                    b_in.setText(df2.format(b));

                    terulet.setText("Terület :" + df2.format(heron(a, b, c)));
                    terulet.setVisibility(View.VISIBLE);
                } else if (!test3.matches("") & !test2.matches("") & !test4.matches("") & !test5.matches("") & !test6.matches("")) {
                    //b-c-alfa
                    double b = Double.parseDouble(b_in.getText().toString());
                    double c = Double.parseDouble(c_in.getText().toString());
                    double alfa_fok = Double.parseDouble(alfa_fok_in.getText().toString());
                    double alfa_perc = Double.parseDouble(alfa_perc_in.getText().toString());
                    double alfa_masod = Double.parseDouble(alfa_masod_in.getText().toString());

                    double alfa_szog = valt(alfa_fok, alfa_perc, alfa_masod);
                    double a = costetel_oldal(b, c, alfa_szog);

                    double beta_e = costetel_szog(c, a, b);
                    double gamma_e = costetel_szog(a, b, c);
                    double[] beta = visszavalt(beta_e);
                    double[] gamma = visszavalt(gamma_e);

                    beta_fok_in.setText(df.format(beta[0]));
                    beta_perc_in.setText(df3.format(beta[1]));
                    beta_masod_in.setText(df3.format(beta[2]));
                    gamma_fok_in.setText(df.format(gamma[0]));
                    gamma_perc_in.setText(df3.format(gamma[1]));
                    gamma_masod_in.setText(df3.format(gamma[2]));
                    a_in.setText(df2.format(c));

                    terulet.setText("Terület :" + df2.format(heron(a, b, c)));
                    terulet.setVisibility(View.VISIBLE);
                }

                //egy oldal és raja két szög
                //két oldala és a hosszabb oldallal szemközti szöge.

            }
        });
    }

    public double valt(double fok, double perc, double masod) {
        double valt = Math.toRadians(fok + (perc / 60) + (masod / 3600));
        return (valt);
    }

    public double[] visszavalt(double szog) {
        double fok = Math.floor(szog);
        double t1 = (szog - (Math.floor(szog))) * 60;
        double perc = (Math.floor((szog - (Math.floor(szog))) * 60));
        double masod = (t1 - perc) * 60;
        double[] valt = new double[3];
        valt[0] = fok;
        valt[1] = perc;
        valt[2] = masod;
        return (valt);
    }

    public double heron(double a, double b, double c) {
        double s = (a + b + c) / 2;
        double terulet = Math.sqrt(s * (s - a) * (s - b) * (s - c));
        return (terulet);
    }

    public double costetel_szog(double a, double b, double c) {
        double szog = Math.toDegrees(Math.acos((Math.pow(a, 2) + Math.pow(b, 2) - Math.pow(c, 2)) / (2 * a * b)));
        return (szog);
    }

    public double costetel_oldal(double a, double b, double gamma) {
        double oldal = Math.sqrt(Math.pow(a, 2) + Math.pow(b, 2) - 2 * a * b * (Math.cos(gamma)));
        return (oldal);
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_haromszog, menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_reset) {
            final EditText a_in = (EditText) findViewById(R.id.a_in);
            final EditText b_in = (EditText) findViewById(R.id.b_in);
            final EditText c_in = (EditText) findViewById(R.id.c_in);
            final EditText alfa_fok_in = (EditText) findViewById(R.id.alfa_fok_in);
            final EditText alfa_perc_in = (EditText) findViewById(R.id.alfa_perc_in);
            final EditText alfa_masod_in = (EditText) findViewById(R.id.alfa_masod_in);
            final EditText beta_fok_in = (EditText) findViewById(R.id.beta_fok_in);
            final EditText beta_perc_in = (EditText) findViewById(R.id.beta_perc_in);
            final EditText beta_masod_in = (EditText) findViewById(R.id.beta_masod_in);
            final EditText gamma_fok_in = (EditText) findViewById(R.id.gamma_fok_in);
            final EditText gamma_perc_in = (EditText) findViewById(R.id.gamma_perc_in);
            final EditText gamma_masod_in = (EditText) findViewById(R.id.gamma_masod_in);
            final TextView terulet = (TextView) findViewById(R.id.terulet);

            terulet.setVisibility(View.INVISIBLE);

            a_in.setText("");
            b_in.setText("");
            c_in.setText("");
            alfa_fok_in.setText("");
            alfa_perc_in.setText("");
            alfa_masod_in.setText("");
            beta_fok_in.setText("");
            beta_perc_in.setText("");
            beta_masod_in.setText("");
            gamma_fok_in.setText("");
            gamma_perc_in.setText("");
            gamma_masod_in.setText("");

            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
