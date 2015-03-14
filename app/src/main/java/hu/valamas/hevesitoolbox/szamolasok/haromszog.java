package hu.valamas.hevesitoolbox.szamolasok;

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
import android.widget.TextView;
import android.widget.Toast;

import com.example.valamas.hevesitoolbox.R;

import hu.valamas.hevesitoolbox.szamolasok.felulet.szogkezeles;
import hu.valamas.hevesitoolbox.szamolasok.opengl.haromszograjz;
import hu.valamas.hevesitoolbox.szamolasok.felulet.tizedes;
import hu.valamas.hevesitoolbox.szamolasok.alapmuvelet.trigonometria;

public class haromszog extends Activity {
    final tizedes tizedes =new tizedes();
    double pi = 3.141592653589793238462643383279502884197;

    double a;
    double b;
    double c;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_haromszog);

        final trigonometria trigonometria = new trigonometria();
        final szogkezeles szogkezeles = new szogkezeles();
        final EditText a_in = (EditText) findViewById(R.id.a_in);
        final EditText b_in = (EditText) findViewById(R.id.b_in);
        final EditText c_in = (EditText) findViewById(R.id.c_in);
        final EditText alfa_in = (EditText) findViewById(R.id.alfa_in);
        final EditText beta_in = (EditText) findViewById(R.id.beta_in);
        final EditText gamma_in = (EditText) findViewById(R.id.gamma_in);
        final Button szamit = (Button) findViewById(R.id.button);

        //Forgatás
        Bundle extras = getIntent().getExtras();
        Byte orientation = extras.getByte("orientation");
        if (orientation == 0)   {
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        }   else {
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        }

        //Alfa Szögbevitel
        alfa_in.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String szog_bent =alfa_in.getText().toString();
                String szog_tagolt = szogkezeles.tagolas(szog_bent)[0];
                if (!szog_bent.equals(szog_tagolt)) {
                    alfa_in.setText(szog_tagolt);
                    alfa_in.setSelection(alfa_in.getText().length());
                }
                if (szogkezeles.tagolas(szog_bent)[1].equals("1"))  {
                    alfa_in.setFilters(new InputFilter[] {new InputFilter.LengthFilter(7)});
                    alfa_in.setKeyListener(DigitsKeyListener.getInstance("0123456789"));
                }   else if (szogkezeles.tagolas(szog_bent)[1].equals("2"))  {
                    alfa_in.setFilters(new InputFilter[] {new InputFilter.LengthFilter(8)});
                    alfa_in.setKeyListener(DigitsKeyListener.getInstance("0123456789"));
                }   else if (szogkezeles.tagolas(szog_bent)[1].equals("3"))  {
                    alfa_in.setFilters(new InputFilter[] {new InputFilter.LengthFilter(9)});
                    alfa_in.setKeyListener(DigitsKeyListener.getInstance("0123456789"));
                } else {
                    alfa_in.setFilters(new InputFilter[] {new InputFilter.LengthFilter(9)});
                    alfa_in.setKeyListener(DigitsKeyListener.getInstance("0123456789-."));
                }
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });

        //Beta Szögbevitel
        beta_in.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String szog_bent =beta_in.getText().toString();
                String szog_tagolt = szogkezeles.tagolas(szog_bent)[0];
                if (!szog_bent.equals(szog_tagolt)) {
                    beta_in.setText(szog_tagolt);
                    beta_in.setSelection(beta_in.getText().length());
                }
                if (szogkezeles.tagolas(szog_bent)[1].equals("1"))  {
                    beta_in.setFilters(new InputFilter[] {new InputFilter.LengthFilter(7)});
                    beta_in.setKeyListener(DigitsKeyListener.getInstance("0123456789"));
                }   else if (szogkezeles.tagolas(szog_bent)[1].equals("2"))  {
                    beta_in.setFilters(new InputFilter[] {new InputFilter.LengthFilter(8)});
                    beta_in.setKeyListener(DigitsKeyListener.getInstance("0123456789"));
                }   else if (szogkezeles.tagolas(szog_bent)[1].equals("3"))  {
                    beta_in.setFilters(new InputFilter[] {new InputFilter.LengthFilter(9)});
                    beta_in.setKeyListener(DigitsKeyListener.getInstance("0123456789"));
                } else {
                    beta_in.setFilters(new InputFilter[] {new InputFilter.LengthFilter(9)});
                    beta_in.setKeyListener(DigitsKeyListener.getInstance("0123456789-."));
                }
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });

        //Gamma Szögbevitel
        gamma_in.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String szog_bent =gamma_in.getText().toString();
                String szog_tagolt = szogkezeles.tagolas(szog_bent)[0];
                if (!szog_bent.equals(szog_tagolt)) {
                    gamma_in.setText(szog_tagolt);
                    gamma_in.setSelection(gamma_in.getText().length());
                }
                if (szogkezeles.tagolas(szog_bent)[1].equals("1"))  {
                    gamma_in.setFilters(new InputFilter[] {new InputFilter.LengthFilter(7)});
                    gamma_in.setKeyListener(DigitsKeyListener.getInstance("0123456789"));
                }   else if (szogkezeles.tagolas(szog_bent)[1].equals("2"))  {
                    gamma_in.setFilters(new InputFilter[] {new InputFilter.LengthFilter(8)});
                    gamma_in.setKeyListener(DigitsKeyListener.getInstance("0123456789"));
                }   else if (szogkezeles.tagolas(szog_bent)[1].equals("3"))  {
                    gamma_in.setFilters(new InputFilter[] {new InputFilter.LengthFilter(9)});
                    gamma_in.setKeyListener(DigitsKeyListener.getInstance("0123456789"));
                } else {
                    gamma_in.setFilters(new InputFilter[] {new InputFilter.LengthFilter(9)});
                    gamma_in.setKeyListener(DigitsKeyListener.getInstance("0123456789-."));
                }
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });

        szamit.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                String a_in_s = a_in.getText().toString();
                String b_in_s = b_in.getText().toString();
                String c_in_s = c_in.getText().toString();
                String alfa_in_s = alfa_in.getText().toString();
                String beta_in_s = beta_in.getText().toString();
                String gamma_in_s = gamma_in.getText().toString();

                if (!a_in_s.matches("") & !b_in_s.matches("") & !c_in_s.matches("")) {
                    //Három oldal
                    a = Double.parseDouble(a_in_s);
                    b = Double.parseDouble(b_in_s);
                    c = Double.parseDouble(c_in_s);

                    double alfa_e = trigonometria.costetel_szog(c, b, a);
                    double beta_e = trigonometria.costetel_szog(c, a, b);
                    double gamma_e = trigonometria.costetel_szog(a, b, c);

                    if (  egyenlotlen(a,b,c) != 3)
                    {
                        Toast.makeText(getApplicationContext(),
                            getString(R.string.haromszog_egyenlotlen), Toast.LENGTH_SHORT).show();
                    } else {
                        alfa_in.setText(szogkezeles.kiiras(alfa_e));
                        beta_in.setText(szogkezeles.kiiras(beta_e));
                        gamma_in.setText(szogkezeles.kiiras(gamma_e));
                        terkiir(a,b,c);
                    }

                } else if (!a_in_s.matches("") & !b_in_s.matches("") & !gamma_in_s.matches("") ) {
                    // A-B-Gamma
                    a = Double.parseDouble(a_in_s);
                    b = Double.parseDouble(b_in_s);

                    double gamma_szog = Double.parseDouble(szogkezeles.tagolas(gamma_in_s)[2]);
                    c = trigonometria.costetel_oldal(a, b, gamma_szog);

                    double alfa_e = trigonometria.costetel_szog(c, b, a);
                    double beta_e = trigonometria.costetel_szog(c, a, b);

                    if (  egyenlotlen(a,b,c) != 3)
                    {
                        Toast.makeText(getApplicationContext(),
                                getString(R.string.haromszog_egyenlotlen), Toast.LENGTH_SHORT).show();
                    } else {
                        alfa_in.setText(szogkezeles.kiiras(alfa_e));
                        beta_in.setText(szogkezeles.kiiras(beta_e));
                        c_in.setText(tizedes.tizedes(c,2));
                        terkiir(a,b,c);
                    }

                } else if (!a_in_s.matches("") & !c_in_s.matches("") & !beta_in_s.matches("") ) {
                    //A-C-Béta
                    a = Double.parseDouble(a_in_s);
                    c = Double.parseDouble(c_in_s);

                    double beta_szog = Double.parseDouble(szogkezeles.tagolas(beta_in_s)[2]) ;
                    b = trigonometria.costetel_oldal(a, c, beta_szog);

                    double alfa_e = trigonometria.costetel_szog(c, b, a);
                    double gamma_e = trigonometria.costetel_szog(a, b, c);

                    if (  egyenlotlen(a,b,c) != 3)
                    {
                        Toast.makeText(getApplicationContext(),
                                getString(R.string.haromszog_egyenlotlen), Toast.LENGTH_SHORT).show();
                    } else {
                        alfa_in.setText(szogkezeles.kiiras(alfa_e));
                        gamma_in.setText(szogkezeles.kiiras(gamma_e));
                        b_in.setText(tizedes.tizedes(b,2));
                        terkiir(a,b,c);
                    }
                } else if (!c_in_s.matches("") & !b_in_s.matches("") & (!alfa_in_s.matches("") )) {
                    //B-C-Alfa
                    b = Double.parseDouble(b_in_s);
                    c = Double.parseDouble(c_in_s);
                    double alfa_szog = Double.parseDouble(szogkezeles.tagolas(alfa_in_s)[2]) ;
                    a = trigonometria.costetel_oldal(b, c, alfa_szog);

                    double beta_e = trigonometria.costetel_szog(c, a, b);
                    double gamma_e = trigonometria.costetel_szog(a, b, c);

                    if (  egyenlotlen(a,b,c) != 3)
                    {
                        Toast.makeText(getApplicationContext(),
                                getString(R.string.haromszog_egyenlotlen), Toast.LENGTH_SHORT).show();
                    } else {
                        beta_in.setText(szogkezeles.kiiras(beta_e));
                        gamma_in.setText(szogkezeles.kiiras(gamma_e));
                        a_in.setText(tizedes.tizedes(c,2));
                        terkiir(a,b,c);
                    }
                } else if (!a_in_s.matches("") &  !beta_in_s.matches("") & !gamma_in_s.matches("") ) {
                    //A -Béta-Gamma
                    a = Double.parseDouble(a_in_s);

                    double beta_szog = Double.parseDouble(szogkezeles.tagolas(beta_in_s)[2]) ;
                    double gamma_szog = Double.parseDouble(szogkezeles.tagolas(gamma_in_s)[2]) ;
                    double alfa_szog = pi-(beta_szog+gamma_szog);

                    b = trigonometria.szinusztetel_oldal(beta_szog,alfa_szog,a);
                    c = trigonometria.szinusztetel_oldal(gamma_szog,alfa_szog,a);

                    if (  egyenlotlen(a,b,c) != 3)
                    {
                        Toast.makeText(getApplicationContext(),
                                getString(R.string.haromszog_egyenlotlen), Toast.LENGTH_SHORT).show();
                    } else {
                        alfa_in.setText(szogkezeles.kiiras(Math.toDegrees(alfa_szog)));
                        b_in.setText(tizedes.tizedes(b,2));
                        c_in.setText(tizedes.tizedes(c,2));
                        terkiir(a,b,c);
                    }
                }   else if (!b_in_s.matches("") &  !alfa_in_s.matches("")  & !gamma_in_s.matches("")) {
                    //B -Alfa-Gamma
                    b = Double.parseDouble(b_in_s);
                    double alfa_szog = Double.parseDouble(alfa_in_s);

                    double gamma_szog = Double.parseDouble(szogkezeles.tagolas(gamma_in_s)[2]) ;
                    double beta_szog = pi-(alfa_szog+gamma_szog);

                    a = trigonometria.szinusztetel_oldal(alfa_szog,beta_szog,b);
                    c = trigonometria.szinusztetel_oldal(gamma_szog,beta_szog,b);

                    if (  egyenlotlen(a,b,c) != 3)
                    {
                        Toast.makeText(getApplicationContext(),
                                getString(R.string.haromszog_egyenlotlen), Toast.LENGTH_SHORT).show();
                    } else {
                        beta_in.setText(szogkezeles.kiiras(Math.toDegrees(beta_szog)));
                        a_in.setText(tizedes.tizedes(a,2));
                        c_in.setText(tizedes.tizedes(c,2));
                        terkiir(a,b,c);
                    }
                }else if (!c_in_s.matches("") & !alfa_in_s.matches("")  & !beta_in_s.matches("") ) {
                    //C -Alfa-Béta
                    c = Double.parseDouble(c_in_s);

                    double alfa_szog = Double.parseDouble(szogkezeles.tagolas(alfa_in_s)[2]) ;
                    double beta_szog = Double.parseDouble(szogkezeles.tagolas(beta_in_s)[2]) ;
                    double gamma_szog = pi-(alfa_szog+beta_szog);

                    a = trigonometria.szinusztetel_oldal(alfa_szog,gamma_szog,c);
                    b = trigonometria.szinusztetel_oldal(beta_szog,gamma_szog,c);

                    if (  egyenlotlen(a,b,c) != 3)
                    {
                        Toast.makeText(getApplicationContext(),
                                getString(R.string.haromszog_egyenlotlen), Toast.LENGTH_SHORT).show();
                    } else {
                        gamma_in.setText(szogkezeles.kiiras(Math.toDegrees(gamma_szog)));
                        a_in.setText(tizedes.tizedes(a,2));
                        b_in.setText(tizedes.tizedes(b,2));
                        terkiir(a,b,c);
                    }
                }else if (!a_in_s.matches("") & !b_in_s.matches("") &  !alfa_in_s.matches("")) {
                    //A-B-Alfa -A nagyobb
                    a = Double.parseDouble(a_in_s);
                    b = Double.parseDouble(b_in_s);
                    if (a>b) {
                        double alfa_szog = Double.parseDouble(alfa_in_s);
                        double beta_szog = trigonometria.szinusztetel_szog(a,b,alfa_szog);
                        double gamma_szog = pi-(alfa_szog+beta_szog);
                        c = trigonometria.costetel_oldal(a, b, gamma_szog);

                        if (  egyenlotlen(a,b,c) != 3)
                        {
                            Toast.makeText(getApplicationContext(),
                                    getString(R.string.haromszog_egyenlotlen), Toast.LENGTH_SHORT).show();
                        } else {
                            beta_in.setText(szogkezeles.kiiras(Math.toDegrees(beta_szog)));
                            gamma_in.setText(szogkezeles.kiiras(Math.toDegrees(gamma_szog)));
                            c_in.setText(tizedes.tizedes(c,2));
                            terkiir(a,b,c);
                        }
                    }
                }else if (!a_in_s.matches("") & !b_in_s.matches("") &  !beta_in_s.matches("") ) {
                //A-B-Béta -B nagyobb
                a = Double.parseDouble(a_in_s);
                b = Double.parseDouble(b_in_s);
                if (a<b) {
                    double beta_szog = Double.parseDouble(szogkezeles.tagolas(beta_in_s)[2]) ;
                    double alfa_szog = trigonometria.szinusztetel_szog(a, b, beta_szog);
                    double gamma_szog = pi - (alfa_szog + beta_szog);
                    c = trigonometria.costetel_oldal(a, b, gamma_szog);

                    if (  egyenlotlen(a,b,c) != 3)
                    {
                        Toast.makeText(getApplicationContext(),
                                getString(R.string.haromszog_egyenlotlen), Toast.LENGTH_SHORT).show();
                    } else {
                        alfa_in.setText(szogkezeles.kiiras(Math.toDegrees(alfa_szog)));
                        gamma_in.setText(szogkezeles.kiiras(Math.toDegrees(gamma_szog)));
                        c_in.setText(tizedes.tizedes(c,2));
                        terkiir(a,b,c);
                    }
                }
                }else if (!a_in_s.matches("") & !c_in_s.matches("") & (!alfa_in_s.matches(""))) {
                    //A-C-Alfa-A nagyobb
                    a = Double.parseDouble(a_in_s);
                    c = Double.parseDouble(c_in_s);
                    if (c<a) {
                        double alfa_szog = Double.parseDouble(alfa_in_s);
                        double gamma_szog = trigonometria.szinusztetel_szog(c,a,alfa_szog);
                        double beta_szog = pi-(alfa_szog+gamma_szog);
                        b = trigonometria.costetel_oldal(a, c, beta_szog);

                        if (  egyenlotlen(a,b,c) != 3)
                        {
                            Toast.makeText(getApplicationContext(),
                                    getString(R.string.haromszog_egyenlotlen), Toast.LENGTH_SHORT).show();
                        } else {
                            beta_in.setText(szogkezeles.kiiras(Math.toDegrees(beta_szog)));
                            gamma_in.setText(szogkezeles.kiiras(Math.toDegrees(gamma_szog)));
                            b_in.setText(tizedes.tizedes(b,2));
                            terkiir(a,b,c);
                        }
                    }

                }else if (!a_in_s.matches("") & !c_in_s.matches("") & !gamma_in_s.matches("") ) {
                    //A-C-Gamma -C nagyobb
                    a = Double.parseDouble(a_in_s);
                    c = Double.parseDouble(c_in_s);
                    if (a<c) {
                        double gamma_szog = Double.parseDouble(szogkezeles.tagolas(gamma_in_s)[2]) ;
                        double alfa_szog = trigonometria.szinusztetel_szog(a,c,gamma_szog);
                        double beta_szog = pi-(alfa_szog+gamma_szog);
                        b = trigonometria.costetel_oldal(a, c, beta_szog);

                        if (  egyenlotlen(a,b,c) != 3)
                        {
                            Toast.makeText(getApplicationContext(),
                                    getString(R.string.haromszog_egyenlotlen), Toast.LENGTH_SHORT).show();
                        } else {
                            alfa_in.setText(szogkezeles.kiiras(Math.toDegrees(alfa_szog)));
                            beta_in.setText(szogkezeles.kiiras(Math.toDegrees(beta_szog)));
                            b_in.setText(tizedes.tizedes(b,2));
                            terkiir(a,b,c);
                        }
                    }

                }else if (!b_in_s.matches("") & !c_in_s.matches("") & !beta_in_s.matches("") ) {
                    //B-C-Beta -B nagyobb
                    b = Double.parseDouble(b_in_s);
                    c = Double.parseDouble(c_in_s);
                    if (c<b) {

                        double beta_szog = Double.parseDouble(szogkezeles.tagolas(beta_in_s)[2]) ;
                        double gamma_szog = trigonometria.szinusztetel_szog(c,b,beta_szog);
                        double alfa_szog = pi-(beta_szog+gamma_szog);
                        a = trigonometria.costetel_oldal(b, c, alfa_szog);

                        if (  egyenlotlen(a,b,c) != 3)
                        {
                            Toast.makeText(getApplicationContext(),
                                    getString(R.string.haromszog_egyenlotlen), Toast.LENGTH_SHORT).show();
                        } else {
                            alfa_in.setText(szogkezeles.kiiras(Math.toDegrees(alfa_szog)));
                            gamma_in.setText(szogkezeles.kiiras(Math.toDegrees(gamma_szog)));
                            a_in.setText(tizedes.tizedes(a,2));
                            terkiir(a,b,c);
                        }
                    }

                }else if (!b_in_s.matches("") & !c_in_s.matches("") & !gamma_in_s.matches("") ) {
                    //b-C-Gamma -C nagyobb
                    b = Double.parseDouble(b_in_s);
                    c = Double.parseDouble(c_in_s);
                    if (b<c) {
                        double gamma_szog = Double.parseDouble(szogkezeles.tagolas(gamma_in_s)[2]) ;
                        double beta_szog = trigonometria.szinusztetel_szog(b,c,gamma_szog);
                        double alfa_szog = pi-(beta_szog+gamma_szog);
                        a = trigonometria.costetel_oldal(b, c, alfa_szog);

                        if (  egyenlotlen(a,b,c) != 3)
                        {
                            Toast.makeText(getApplicationContext(),
                                    getString(R.string.haromszog_egyenlotlen), Toast.LENGTH_SHORT).show();
                        } else {
                            alfa_in.setText(szogkezeles.kiiras(Math.toDegrees(alfa_szog)));
                            beta_in.setText(szogkezeles.kiiras(Math.toDegrees(beta_szog)));
                            a_in.setText(tizedes.tizedes(a,2));
                            terkiir(a,b,c);
                        }
                    }
                }
                else {
                    {
                        Toast.makeText(getApplicationContext(),
                                getString(R.string.haromszog_kevesadat), Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }

    public int egyenlotlen (double a ,double b ,double c){
        int  i = 0 ;
        if ( (a+b)>c ){
            i = i+1 ;
        }
        if ( (a+c)>b ){
            i = i+1 ;
        }
        if ( (b+c)>a ){
            i = i+1 ;
        }
        return (i);
    }
    public void terkiir(double a ,double b, double c)
    {
        final trigonometria trigonometria = new trigonometria();
        final TextView terulet = (TextView) findViewById(R.id.terulet);
        terulet.setText(getString(R.string.haromszog_terulet) + tizedes.tizedes(trigonometria.heron(a, b, c),2));
        terulet.setVisibility(View.VISIBLE);
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_haromszog, menu);
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
        if (id ==R.id.action_rajz)
        {
            if ( (a!=0) & (b!=0) & (c!=0) ) {
                Intent intent = new Intent(haromszog.this, haromszograjz.class);
                double[] message = new double[3];
                message[0] = a;
                message[1] = b;
                message[2] = c;
                intent.putExtra("numbers", message);
                startActivity(intent);
            }
            else {
                Toast.makeText(getApplicationContext(),
                        getString(R.string.haromszog_nincskesz), Toast.LENGTH_SHORT).show();
                }
            }
        if (id == R.id.action_info)
        {
            AlertDialog alertDialog = new AlertDialog.Builder(this).create();
            alertDialog.setTitle(getString(R.string.menu_sugo));
            alertDialog.setMessage(getString(R.string.haromszog_sugo));
            alertDialog.setButton("OK", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int which) {
                }
            });
            alertDialog.show();
        }

        return super.onOptionsItemSelected(item);
    }
}