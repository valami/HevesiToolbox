package hu.valamas.hevesitoolbox.szamolasok;

import android.app.Activity;
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
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.valamas.hevesitoolbox.R;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;

import hu.valamas.hevesitoolbox.szamolasok.felulet.szogkezeles;

import java.text.DecimalFormat;

public class atvalto extends Activity {
    DecimalFormat df = new DecimalFormat("#");
    DecimalFormat df2 = new DecimalFormat("#.###");
    double pi = 3.141592653589793238462643383279502884197;
    byte funkcio = 0;
    byte funkcioal = 0;
    String[] from_merteke;
    String[] to_merteke;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_atvalto);

        final RadioButton radio_meret = (RadioButton) findViewById(R.id.radio_meret);
        final RadioButton radio_mereta = (RadioButton) findViewById(R.id.radio_mereta);
        final RadioButton radio_tav = (RadioButton) findViewById(R.id.radio_tav);
        final RadioButton radio_ter = (RadioButton) findViewById(R.id.radio_ter);
        final RadioButton radio_szog = (RadioButton) findViewById(R.id.radio_szog);
        final Button szamol = (Button) findViewById(R.id.szamol);
        final Spinner from = (Spinner) findViewById(R.id.from);
        final Spinner to = (Spinner) findViewById(R.id.to);

        final EditText from_in = (EditText) findViewById(R.id.from_in);
        final EditText to_in = (EditText) findViewById(R.id.to_in);
        final EditText mereta_in = (EditText) findViewById(R.id.mereta_in);

        final LinearLayout sor3 = (LinearLayout) findViewById(R.id.sor3);
        final LinearLayout sor4 = (LinearLayout) findViewById(R.id.sor4);
        final LinearLayout sor5 = (LinearLayout) findViewById(R.id.sor5);
        final LinearLayout sor6 = (LinearLayout) findViewById(R.id.sor6);
        final szogkezeles szogkezeles = new szogkezeles();

        AdView mAdView = (AdView) findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);

        //Forgatás
        Bundle extras = getIntent().getExtras();
        Byte orientation = extras.getByte("orientation");
        if (orientation == 0)   {
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        }   else {
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        }

        //Almüvelet
        radio_meret.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                radio_tav.setVisibility(View.VISIBLE);
                radio_ter.setVisibility(View.VISIBLE);
                radio_szog.setVisibility(View.VISIBLE);
                sor3.setVisibility(View.INVISIBLE);
                sor4.setVisibility(View.INVISIBLE);
                sor5.setVisibility(View.INVISIBLE);
                sor6.setVisibility(View.INVISIBLE);
                to_in.setFocusable(false);
                to_in.setFocusableInTouchMode(false);
                funkcioal = 1;
            }
        });
        radio_mereta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                radio_tav.setVisibility(View.VISIBLE);
                radio_ter.setVisibility(View.VISIBLE);
                radio_szog.setVisibility(View.INVISIBLE);
                sor3.setVisibility(View.INVISIBLE);
                sor4.setVisibility(View.INVISIBLE);
                sor5.setVisibility(View.INVISIBLE);
                sor6.setVisibility(View.INVISIBLE);
                to_in.setFocusable(true);
                to_in.setFocusableInTouchMode(true);
                funkcioal = 2;
            }
        });
        //Müvelet
        radio_tav.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sor3.setVisibility(View.VISIBLE);
                sor4.setVisibility(View.VISIBLE);
                sor6.setVisibility(View.VISIBLE);
                to_in.setText("");

                if (funkcioal == 1) {
                    // Meret - Hossz
                    from_in.setText("1");
                    funkcio = 1;
                    from_merteke = new String[]{"méter","bécsi öl","láb (foot)","yard","mérföld (mile)" };
                    to_merteke = from_merteke;
                }   else if (funkcioal == 2)    {
                    //Mertekegyeg - Hossz
                    from_in.setText("");
                    funkcio = 11;
                    from_merteke = new String[]{"mm","cm","dm" };
                    to_merteke = new String[]{"m","km"};
                    sor5.setVisibility(View.VISIBLE);
                }
                lista(from_merteke,to_merteke);
            }
        });
        radio_ter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sor3.setVisibility(View.VISIBLE);
                sor4.setVisibility(View.VISIBLE);
                sor6.setVisibility(View.VISIBLE);
                to_in.setText("");

                if (funkcioal == 1) {
                    // Meret - Terület
                    from_merteke = new String[]{"méter²","hektár","bécsi öl²","kat. hold" };
                    to_merteke = from_merteke;
                    from_in.setText("1");
                    funkcio = 2;
                }   else if (funkcioal == 2)    {
                    //Mertekegyeg - Terület
                    from_in.setText("");
                    funkcio = 12;
                    from_merteke = new String[]{"mm²","cm²","dm²" };
                    to_merteke = new String[]{"km²","ha","m²" };
                    sor5.setVisibility(View.VISIBLE);
                }
                lista(from_merteke,to_merteke);
            }
        });
        radio_szog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sor3.setVisibility(View.VISIBLE);
                sor4.setVisibility(View.VISIBLE);
                sor6.setVisibility(View.VISIBLE);
                from_merteke = new String[]{"fok","grád","vonás (6000)","vonás (6400)","radián" };
                from_in.setText("1");
                to_in.setText("");
                funkcio = 3;
                lista(from_merteke,from_merteke);
            }
        });
        from_merteke = new String[]{"" };
        lista(from_merteke,from_merteke);
        //Szögbevitel
        from_in.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String from_ert = from.getSelectedItem().toString();
                if (funkcio==3 && from_ert.equals("fok")) {
                    String szog_bent = from_in.getText().toString();
                    String szog_tagolt = szogkezeles.tagolas(szog_bent)[0];
                    if (!szog_bent.equals(szog_tagolt)) {
                        from_in.setText(szog_tagolt);
                        from_in.setSelection(from_in.getText().length());
                    }
                    if (szogkezeles.tagolas(szog_bent)[1].equals("1")) {
                        from_in.setFilters(new InputFilter[]{new InputFilter.LengthFilter(7)});
                        from_in.setKeyListener(DigitsKeyListener.getInstance("0123456789"));
                    } else if (szogkezeles.tagolas(szog_bent)[1].equals("2")) {
                        from_in.setFilters(new InputFilter[]{new InputFilter.LengthFilter(8)});
                        from_in.setKeyListener(DigitsKeyListener.getInstance("0123456789"));
                    } else if (szogkezeles.tagolas(szog_bent)[1].equals("3")) {
                        from_in.setFilters(new InputFilter[]{new InputFilter.LengthFilter(9)});
                        from_in.setKeyListener(DigitsKeyListener.getInstance("0123456789"));
                    } else {
                        from_in.setFilters(new InputFilter[]{new InputFilter.LengthFilter(9)});
                        from_in.setKeyListener(DigitsKeyListener.getInstance("0123456789-."));
                    }
                } else {
                    from_in.setFilters(new InputFilter[]{new InputFilter.LengthFilter(9)});
                    from_in.setKeyListener(DigitsKeyListener.getInstance("0123456789."));
                }
            }
            @Override
            public void afterTextChanged(Editable s) {
            }
        });

        szamol.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String test1 = from_in.getText().toString();
                String test2 = to_in.getText().toString();
                String test3 = mereta_in.getText().toString();
                String from_ert = from.getSelectedItem().toString();
                String to_ert = to.getSelectedItem().toString();

                if (funkcioal==1) {
                    if (!test1.matches("")) {
                        if (funkcio==3 && to_ert.equals("fok")){
                            double in =  Math.toDegrees(Double.parseDouble(szogkezeles.tagolas(from_in.getText().toString())[2])) ;
                            to_in.setText(  szogkezeles.kiiras (valt(in,from_ert,to_ert)) );
                        }   else {
                            double in = Double.parseDouble(from_in.getText().toString());
                            to_in.setText(  Double.toString(valt(in,from_ert,to_ert)) );
                        }
                    }else {
                        Toast.makeText(getApplicationContext(),
                                getString(R.string.atvalto_ures), Toast.LENGTH_SHORT).show();
                    }
                }   else if (funkcio==11) {
                    double terkepi = 0;
                    double terepi = 0;
                    double mereta;

                    if (!test1.matches("") & !test2.matches(""))
                    //Terep- Térképi
                    {
                        double in = Double.parseDouble(from_in.getText().toString());
                        double out = Double.parseDouble(to_in.getText().toString());

                        switch (from_ert) {
                            case "mm":
                                terkepi = in / 1000;
                                break;
                            case "cm":
                                terkepi = in / 100;
                                break;
                            case "dm":
                                terkepi = in / 10;
                                break;
                        }
                        if (to_ert.equals("m") ) {
                            terepi = out;
                        }   else if (to_ert.equals("m")) {
                            terepi = out*1000;
                        }
                        mereta = terepi / terkepi;
                        mereta_in.setText(df.format(mereta));
                    }   else if (!test1.matches("") & !test3.matches("") )  {
                    //Térképi-Méretarány
                        double in = Double.parseDouble(from_in.getText().toString());
                        float mert = Float.parseFloat(mereta_in.getText().toString());

                        switch (from_ert) {
                            case "mm":
                                terkepi = in / 1000;
                                break;
                            case "cm":
                                terkepi = in / 100;
                                break;
                            case "dm":
                                terkepi = in / 10;
                                break;
                        }
                        terepi = terkepi * mert;

                        if (terepi < 1000) {
                            to.setSelection(1);
                            to_in.setText(df2.format(terepi));
                        }   else {
                            to.setSelection(2);
                            terepi = terepi /1000;
                            to_in.setText(df2.format(terepi));
                        }
                    }   else if (!test2.matches("") & !test3.matches("") )   {
                    //Terepi-Méretarány
                        double out = Double.parseDouble(to_in.getText().toString());
                        float mert = Float.parseFloat(mereta_in.getText().toString());

                        if (to_ert.equals("m")) {
                            terepi = out;
                        } else if (to_ert.equals("km")) {
                            terepi = out * 1000;
                        }
                        terkepi = terepi / mert;

                        if (terkepi >= 0.1) {
                            terkepi=terkepi*10;
                            from.setSelection(3);
                            from_in.setText(df2.format(terkepi));
                        }  else if (terkepi >= 0.01) {
                            terkepi=terkepi*100;
                            from.setSelection(2);
                            from_in.setText(df2.format(terkepi));
                        } else {
                            terkepi=terkepi*1000;
                            from.setSelection(1);
                            from_in.setText(df2.format(terkepi));
                        }
                    } else {
                        Toast.makeText(getApplicationContext(),
                                getString(R.string.atvalto_keves), Toast.LENGTH_SHORT).show();
                    }
                }
                    if (funkcio==12)
                    {
                        double terkepi = 0;
                        double terepi = 0;
                        double mereta;

                        if (!test1.matches("") & !test2.matches(""))
                        //Terep- Térképi
                        {
                            double in = Double.parseDouble(from_in.getText().toString());
                            double out = Double.parseDouble(to_in.getText().toString());

                            switch (from_ert) {
                                case "mm²":
                                    terkepi = in / 1000000;
                                    break;
                                case "cm²":
                                    terkepi = in / 10000;
                                    break;
                                case "dm²":
                                    terkepi = in / 100;
                                    break;
                            }
                            switch (to_ert) {
                                case "m²":
                                    terepi = out;
                                    break;
                                case "ha":
                                    terepi = out * 10000;
                                    break;
                                case "km²":
                                    terepi = out * 1000000;
                                    break;
                            }
                            double mereta_e = terepi / terkepi;
                            mereta= Math.sqrt(mereta_e);

                            mereta_in.setText(df.format(mereta));
                        }
                        else if (!test1.matches("") & !test3.matches(""))
                        //Térképi-Méretarány
                        {
                            double in = Double.parseDouble(from_in.getText().toString());
                            float mert = Float.parseFloat(mereta_in.getText().toString());

                            switch (from_ert) {
                                case "mm²":
                                    terkepi = in / 1000000;
                                    break;
                                case "cm²":
                                    terkepi = in / 10000;
                                    break;
                                case "dm²":
                                    terkepi = in / 100;
                                    break;
                            }
                            terepi = terkepi * Math.pow(mert,2);

                            if (terepi >= 1000000) {
                                terepi = terepi /1000000;
                                to.setSelection(3);
                                to_in.setText(df2.format(terepi));
                            } else if (terepi >= 10000) {
                                terepi = terepi /10000;
                                to.setSelection(2);
                                to_in.setText(df2.format(terepi));
                            } else {
                                to.setSelection(1);
                                to_in.setText(df2.format(terepi));
                            }
                        }
                        else if (!test2.matches("") & !test3.matches(""))   {
                        //Terepi-Méretarány
                            double out = Double.parseDouble(to_in.getText().toString());
                            float mert = Float.parseFloat(mereta_in.getText().toString());
                            switch (to_ert) {
                                case "m²":
                                    terepi = out;
                                    break;
                                case "ha":
                                    terepi = out * 10000;
                                    break;
                                case "km²":
                                    terepi = out * 1000000;
                                    break;
                            }
                            terkepi = terepi / Math.pow(mert,2);

                            if (terkepi >= 0.01) {
                                terkepi = terkepi *100;
                                from.setSelection(3);
                                from_in.setText(df2.format(terkepi));
                            } else if (terkepi >= 0.0001) {
                                terkepi = terkepi *10000;
                                from.setSelection(3);
                                from_in.setText(df2.format(terkepi));
                            } else {
                                from.setSelection(3);
                                terkepi = terkepi *1000000;
                                from_in.setText(df2.format(terkepi));
                            }
                        } else {
                            Toast.makeText(getApplicationContext(),
                                    getString(R.string.atvalto_keves), Toast.LENGTH_SHORT).show();
                        }
                    }
            }
        });
    }
    public void lista (String[] from,String[] to)   {
        final Spinner froms = (Spinner) findViewById(R.id.from);
        final Spinner tos = (Spinner) findViewById(R.id.to);

        ArrayAdapter<String> from_adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, from);
        from_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        ArrayAdapter<String> to_adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, to);
        to_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        froms.setAdapter(from_adapter);
        tos.setAdapter(to_adapter);
    }

    public double valt (double in,String from,String to)   {
        double r=0 ;

        switch (from) {
            //Hossz
            case "méter":
                r = in;
                break;
            case "bécsi öl":
                r = in * 1.8964838;
                break;
            case "láb (foot)":
                r = in * 0.3048000;
                break;
            case "yard":
                r = in*0.9144000;
                break;
            case "mérföld (mile)":
                r = in*1609.3440000;
                break;
            //Terület
            case "méter²":
                r = in;
                break;
            case "hektár":
                r = in*10000;
                break;
            case "bécsi öl²":
                r = in*3.5966510;
                break;
            case "kat. hold":
                r = in*5754.6415286;
                break;
            //Szög
            case "fok":
                r = in;
                break;
            case "grád":
                r = in*0.9000000;
                break;
            case "vonás (6000)":
                r = in*0.0600000;
                break;
            case "vonás (6400)":
                r = in*0.0562500;
                break;
            case "radián":
                r = in*57.2957795;
                break;
        }
        switch (to) {
            //Hossz
            case "méter":
                return r;
            case "bécsi öl":
                return r * 0.5272916;
            case "láb (foot)":
                return r * 3.2808399;
            case "yard":
                return r * 1.0936133;
            case "mérföld (mile)":
                return r * 0.0006214;
            //Terület
            case "méter²":
                return r;
            case "hektár":
                return r * 0.0001;
            case "bécsi öl²":
                return r * 0.2780364;
            case "kat. hold":
                return r * 0.00017377275;
            //Szög
            case "fok":
                return r;
            case "grád":
                return r * (400 / 360);
            case "vonás (6000)":
                return r * (6000 / 360);
            case "vonás (6400)":
                return r * (6400 / 360);
            case "radián":
                return r * pi / 180;
        }
        return r;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_atvalto, menu);
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
        return super.onOptionsItemSelected(item);
    }
}