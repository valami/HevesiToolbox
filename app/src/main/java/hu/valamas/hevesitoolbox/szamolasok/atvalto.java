package hu.valamas.hevesitoolbox.szamolasok;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TableRow;
import android.widget.Toast;

import com.example.valamas.hevesitoolbox.R;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

public class atvalto extends Activity {
    DecimalFormat df = new DecimalFormat("#");
    DecimalFormat df2 = new DecimalFormat("#.###");
    byte from_ert = 0;
    byte to_ert = 0;
    byte funkcio = 0;
    byte funkcioal = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_atvalto);

        final RadioButton radio_meret = (RadioButton) findViewById(R.id.radio_meret);
        final RadioButton radio_mereta = (RadioButton) findViewById(R.id.radio_mereta);
        final RadioButton radio_tav = (RadioButton) findViewById(R.id.radio_tav);
        final RadioButton radio_ter = (RadioButton) findViewById(R.id.radio_ter);
        final RadioButton radio_szog = (RadioButton) findViewById(R.id.radio_szog);

        final Button from = (Button) findViewById(R.id.from);
        final Button to = (Button) findViewById(R.id.to);
        final Button szamol = (Button) findViewById(R.id.szamol);

        final EditText from_in = (EditText) findViewById(R.id.from_in);
        final EditText to_in = (EditText) findViewById(R.id.to_in);
        final EditText mereta_in = (EditText) findViewById(R.id.mereta_in);

        final TableRow sor3 = (TableRow) findViewById(R.id.sor3);
        final TableRow sor4 = (TableRow) findViewById(R.id.sor4);
        final TableRow sor5 = (TableRow) findViewById(R.id.sor5);
        final TableRow sor6 = (TableRow) findViewById(R.id.sor6);

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
        radio_tav.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sor3.setVisibility(View.VISIBLE);
                sor4.setVisibility(View.VISIBLE);
                sor6.setVisibility(View.VISIBLE);

                from_ert = 0;
                to_ert = 0;
                to_in.setText("");

                if (funkcioal == 1)
                {
                    // Meret - Hossz
                    from_in.setText("1");
                    from.setText("Innen");
                    to.setText("Ide");
                    funkcio = 1;
                }
                else if (funkcioal == 2)
                {
                    //Mertekegyeg - Hossz
                    from_in.setText("");
                    from.setText("Térképi");
                    to.setText("Terepi");
                    funkcio = 11;
                    sor5.setVisibility(View.VISIBLE);
                }
            }
        });
        radio_ter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sor3.setVisibility(View.VISIBLE);
                sor4.setVisibility(View.VISIBLE);
                sor6.setVisibility(View.VISIBLE);

                from_ert = 0;
                to_ert = 0;
                to_in.setText("");

                if (funkcioal == 1)
                {
                    // Meret - Terület
                    from_in.setText("1");
                    from.setText("Innen");
                    to.setText("Ide");
                    funkcio = 2;
                }
                else if (funkcioal == 2)
                {
                    //Mertekegyeg - Terület
                    from_in.setText("");
                    from.setText("Térképi");
                    to.setText("Terepi");
                    funkcio = 12;
                    sor5.setVisibility(View.VISIBLE);
                }
            }
        });
        radio_szog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sor3.setVisibility(View.VISIBLE);
                sor4.setVisibility(View.VISIBLE);
                sor6.setVisibility(View.VISIBLE);

                from.setText("Innen");
                to.setText("Ide");
                from_ert = 0;
                to_ert = 0;
                from_in.setText("1");
                to_in.setText("");

                if (funkcioal == 1)
                {
                    // Meret - Szög
                    funkcio = 3;
                }
            }
        });

        from.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final AlertDialog.Builder menuAleart = new        AlertDialog.Builder(atvalto.this);
                final List<String> menuList = new ArrayList<>();

                if (funkcio==1) {
                    menuList.add("méter");
                    menuList.add("bécsi öl");
                    menuList.add("GB láb (foot)");
                    menuList.add("GB yard");
                    menuList.add("GB mérföld (mile)");
                }
                if (funkcio==2) {
                    menuList.add("méter²");
                    menuList.add("hektár");
                    menuList.add("bécsi öl²");
                    menuList.add("kat. hold");
                }
                if (funkcio==3) {
                    menuList.add("fok");
                    menuList.add("grád");
                    menuList.add("vonás (6000)");
                    menuList.add("vonás (6400)");
                    menuList.add("radián");
                }
                if (funkcio==11) {
                    menuList.add("mm");
                    menuList.add("cm");
                    menuList.add("dm");
                }
                if (funkcio==12) {
                    menuList.add("mm²");
                    menuList.add("cm²");
                    menuList.add("dm²");
                }


                if (funkcioal == 1)
                {
                    menuAleart.setTitle("Innen");
                }
                else if (funkcioal == 2)
                {
                    menuAleart.setTitle("Térképi");
                }
                if (funkcio==1 | funkcio==3){
                final String menu[]=menuList.toArray(new String[menuList.size()]);
                menuAleart.setItems(menu,new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int item) {
                        switch (item) {
                            case 0:
                                from.setText(menu[0]);
                                from_ert = 1;
                                break;
                            case 1:
                                from.setText(menu[1]);
                                from_ert = 2;
                                break;
                            case 2:
                                from.setText(menu[2]);
                                from_ert = 3;
                                break;
                            case 3:
                                from.setText(menu[3]);
                                from_ert = 4;
                                break;
                            case 4:
                                from.setText(menu[4]);
                                from_ert = 5;
                                break;
                        }
                    }
                });
                }
                if (funkcio==2){
                    final String menu[]=menuList.toArray(new String[menuList.size()]);
                    menuAleart.setItems(menu,new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int item) {
                            switch (item) {
                                case 0:
                                    from.setText(menu[0]);
                                    from_ert = 1;
                                    break;
                                case 1:
                                    from.setText(menu[1]);
                                    from_ert = 2;
                                    break;
                                case 2:
                                    from.setText(menu[2]);
                                    from_ert = 3;
                                    break;
                                case 3:
                                    from.setText(menu[3]);
                                    from_ert = 4;
                                    break;
                            }
                        }
                    });
                }
                if (funkcio==11 | funkcio==12){
                    final String menu[]=menuList.toArray(new String[menuList.size()]);
                    menuAleart.setItems(menu,new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int item) {
                            switch (item) {
                                case 0:
                                    from.setText(menu[0]);
                                    from_ert = 1;
                                    break;
                                case 1:
                                    from.setText(menu[1]);
                                    from_ert = 2;
                                    break;
                                case 2:
                                    from.setText(menu[2]);
                                    from_ert = 3;
                                    break;
                            }
                        }
                    });
                }
                AlertDialog menuDrop = menuAleart.create();
                menuDrop.show();
            }
        });


        to.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final AlertDialog.Builder menuAleart = new        AlertDialog.Builder(atvalto.this);
                final List<String> menuList = new ArrayList<>();

                if (funkcio==1) {
                    if (from_ert!=1) {
                        menuList.add("méter");
                    }
                if (from_ert!=2) {
                        menuList.add("bécsi öl");
                    }
                if (from_ert!=3) {
                        menuList.add("GB láb (foot)");
                    }
                if (from_ert!=4) {
                        menuList.add("GB yard");
                    }
                if (from_ert!=5) {
                        menuList.add("GB mérföld (mile)");
                    }
                }
                if (funkcio==2) {
                    if (from_ert!=1) {
                        menuList.add("méter²");
                    }
                    if (from_ert!=2) {
                        menuList.add("hektár");
                    }
                    if (from_ert!=3) {
                        menuList.add("bécsi öl²");
                    }
                    if (from_ert!=4) {
                        menuList.add("kat. hold");
                    }
                }
                if (funkcio==3) {
                    if (from_ert!=1) {
                        menuList.add("fok");
                    }
                    if (from_ert!=2) {
                        menuList.add("grád");
                    }
                    if (from_ert!=3) {
                        menuList.add("vonás (6000)");
                    }
                    if (from_ert!=4) {
                        menuList.add("vonás (6400)");
                    }
                    if (from_ert!=5) {
                        menuList.add("radián");
                    }
                }
                if (funkcio==11) {
                    menuList.add("m");
                    menuList.add("km");
                }
                if (funkcio==12) {
                    menuList.add("m²");
                    menuList.add("ha");
                    menuList.add("km²");
                }

                if (funkcioal == 1)
                {
                    menuAleart.setTitle("Ide");
                }
                else if (funkcioal == 2)
                {
                    menuAleart.setTitle("Terepi");
                }
                if (funkcio==1 | funkcio==3){
                    final String menu[]=menuList.toArray(new String[menuList.size()]);
                    menuAleart.setItems(menu,new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int item) {
                            switch (item) {
                                case 0:
                                    to.setText(menu[0]);
                                    to_ert = 1;
                                    break;
                                case 1:
                                    to.setText(menu[1]);
                                    to_ert = 2;
                                    break;
                                case 2:
                                    to.setText(menu[2]);
                                    to_ert = 3;
                                    break;
                                case 3:
                                    to.setText(menu[3]);
                                    to_ert = 4;
                                    break;
                                case 4:
                                    to.setText(menu[4]);
                                    to_ert = 5;
                                    break;
                            }
                        }
                    });
                }
                if (funkcio==2){
                    final String menu[]=menuList.toArray(new String[menuList.size()]);
                    menuAleart.setItems(menu,new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int item) {
                            switch (item) {
                                case 0:
                                    to.setText(menu[0]);
                                    to_ert = 1;
                                    break;
                                case 1:
                                    to.setText(menu[1]);
                                    to_ert = 2;
                                    break;
                                case 2:
                                    to.setText(menu[2]);
                                    to_ert = 3;
                                    break;
                                case 3:
                                    to.setText(menu[3]);
                                    to_ert = 4;
                                    break;
                            }
                        }
                    });
                }
                if (funkcio==11){
                    final String menu[]=menuList.toArray(new String[menuList.size()]);
                    menuAleart.setItems(menu,new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int item) {
                            switch (item) {
                                case 0:
                                    to.setText(menu[0]);
                                    to_ert = 1;
                                    break;
                                case 1:
                                    to.setText(menu[1]);
                                    to_ert = 2;
                                    break;
                            }
                        }
                    });
                }
                if (funkcio==12){
                    final String menu[]=menuList.toArray(new String[menuList.size()]);
                    menuAleart.setItems(menu,new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int item) {
                            switch (item) {
                                case 0:
                                    to.setText(menu[0]);
                                    to_ert = 1;
                                    break;
                                case 1:
                                    to.setText(menu[1]);
                                    to_ert = 2;
                                    break;
                                case 2:
                                    to.setText(menu[2]);
                                    to_ert = 3;
                                    break;
                            }
                        }
                    });
                }
                AlertDialog menuDrop = menuAleart.create();
                menuDrop.show();
            }
        });

        szamol.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String test1 = from_in.getText().toString();
                String test2 = to_in.getText().toString();
                String test3 = mereta_in.getText().toString();

            if (funkcioal==1) {
                if (from_ert != 0 & to_ert != 0 & !test1.matches("")) {
                    double in = Double.parseDouble(from_in.getText().toString());
                    to_in.setText(atvalt(funkcio,from_ert,to_ert,in));
                }else {
                    Toast.makeText(getApplicationContext(),
                            "Nem adtál meg mértékegységet", Toast.LENGTH_SHORT).show();
                }
            }
            if (funkcio==11)
            {
                double terkepi = 0;
                double terepi = 0;
                double mereta;

                if (!test1.matches("") & !test2.matches("") & to_ert!=0 & from_ert!=0)
                    //Terep- Térképi
                {
                    double in = Double.parseDouble(from_in.getText().toString());
                    double out = Double.parseDouble(to_in.getText().toString());

                    if (from_ert==1 ) {
                        terkepi = in/1000;
                    }
                    else if (from_ert==2) {
                        terkepi = in / 100;
                    }
                    else if (from_ert==3) {
                        terkepi = in / 10;
                    }
                    if (to_ert==1 ) {
                        terepi = out;
                    }
                    else if (to_ert==2) {
                        terepi = out*1000;
                    }
                    mereta = terepi / terkepi;
                    mereta_in.setText(df.format(mereta));
                }
                else if (!test1.matches("") & !test3.matches("") & from_ert!=0)
                //Térképi-Méretarány
                {
                    double in = Double.parseDouble(from_in.getText().toString());
                    float mert = Float.parseFloat(mereta_in.getText().toString());

                    if (from_ert==1 ) {
                        terkepi = in/1000;
                    }
                    else if (from_ert==2) {
                        terkepi = in / 100;
                    }
                    else if (from_ert==3) {
                        terkepi = in / 10;
                    }
                    terepi = terkepi * mert;

                    if (terepi < 1000) {
                        to.setText("m");
                        to_ert = 1;
                        to_in.setText(df2.format(terepi));
                    }
                    else {
                        to.setText("km");
                        to_ert = 2;
                        terepi = terepi /1000;
                        to_in.setText(df2.format(terepi));
                    }
                }
                else if (!test2.matches("") & !test3.matches("") & to_ert!=0)
                //Terepi-Méretarány
                {
                    double out = Double.parseDouble(to_in.getText().toString());
                    float mert = Float.parseFloat(mereta_in.getText().toString());

                    if (to_ert == 1) {
                        terepi = out;
                    } else if (to_ert == 2) {
                        terepi = out * 1000;
                    }
                    terkepi = terepi / mert;

                    if (terkepi >= 0.1) {
                        terkepi=terkepi*10;
                        from.setText("dm");
                        from_ert = 1;
                        from_in.setText(df2.format(terkepi));
                    }  else if (terkepi >= 0.01) {
                        terkepi=terkepi*100;
                        from.setText("cm");
                        from_ert = 2;
                        from_in.setText(df2.format(terkepi));
                    } else {
                        terkepi=terkepi*1000;
                        from.setText("mm");
                        from_ert = 3;
                        from_in.setText(df2.format(terkepi));

                    }
                } else {
                    Toast.makeText(getApplicationContext(),
                            "Túl kevés adat !", Toast.LENGTH_SHORT).show();
                }
            }
            if (funkcio==12)
                {
                    double terkepi = 0;
                    double terepi = 0;
                    double mereta;


                    if (!test1.matches("") & !test2.matches("") & to_ert!=0 & from_ert!=0)
                    //Terep- Térképi
                    {
                        double in = Double.parseDouble(from_in.getText().toString());
                        double out = Double.parseDouble(to_in.getText().toString());

                        if (from_ert==1 ) {
                            terkepi = in/1000000;
                        } else if (from_ert==2) {
                            terkepi = in / 10000;
                        } else if (from_ert==3) {
                            terkepi = in / 100;
                        }
                        if (to_ert==1 ) {
                            terepi = out;
                        } else if (to_ert==2) {
                            terepi = out*10000;
                        }  else if (to_ert==3) {
                            terepi = out*1000000;
                        }
                        double mereta_e = terepi / terkepi;
                        mereta= Math.sqrt(mereta_e);

                        mereta_in.setText(df.format(mereta));
                    }
                    else if (!test1.matches("") & !test3.matches("") & from_ert!=0)
                    //Térképi-Méretarány
                    {
                        double in = Double.parseDouble(from_in.getText().toString());
                        float mert = Float.parseFloat(mereta_in.getText().toString());

                        if (from_ert==1 ) {
                            terkepi = in/1000000;
                        } else if (from_ert==2) {
                            terkepi = in / 10000;
                        } else if (from_ert==3) {
                            terkepi = in / 100;
                        }
                        terepi = terkepi * Math.pow(mert,2);

                        if (terepi >= 1000000) {
                            terepi = terepi /1000000;
                            to.setText("km2");
                            to_ert = 1;
                            to_in.setText(df2.format(terepi));
                        } else if (terepi >= 10000) {
                        terepi = terepi /10000;
                        to.setText("ha");
                        to_ert = 2;
                        to_in.setText(df2.format(terepi));
                        } else {
                            to.setText("m2");
                            to_ert = 2;
                            to_in.setText(df2.format(terepi));
                        }
                    }
                    else if (!test2.matches("") & !test3.matches("") & to_ert!=0)
                    //Terepi-Méretarány
                    {
                        double out = Double.parseDouble(to_in.getText().toString());
                        float mert = Float.parseFloat(mereta_in.getText().toString());

                        if (to_ert == 1) {
                            terepi = out;
                        } else if (to_ert == 2) {
                            terepi = out * 10000;
                        } else if (to_ert == 3) {
                            terepi = out * 1000000;
                        }
                        terkepi = terepi / Math.pow(mert,2);

                        if (terkepi >= 0.01) {
                            terkepi = terkepi *100;
                            from.setText("dm2");
                            from_ert = 1;
                            from_in.setText(df2.format(terkepi));
                        } else if (terkepi >= 0.0001) {
                            terkepi = terkepi *10000;
                            from.setText("cm2");
                            from_ert = 2;
                            from_in.setText(df2.format(terkepi));
                        } else {
                            from.setText("mm2");
                            terkepi = terkepi *1000000;
                            from_ert = 2;
                            from_in.setText(df2.format(terkepi));

                        }
                    } else {
                        Toast.makeText(getApplicationContext(),
                                "Túl kevés adat !", Toast.LENGTH_SHORT).show();
                    }

                }
            }
        });


    }

    public String atvalt (byte funkcio ,byte from_ert ,byte to_ert , double in)
    {
        String vissza = "";
        //Távolság
        if (funkcio==1)
        {
            //Méter
            if (from_ert ==1 & to_ert==1) {
                vissza= (Double.toString(in*0.5272916));
            } else if (from_ert ==1 & to_ert==2) {
                vissza=(Double.toString(in*3.2808399));
            } else if (from_ert ==1 & to_ert==3) {
                vissza=(Double.toString(in*1.0936133));
            } else if (from_ert ==1 & to_ert==4) {
                vissza=(Double.toString(in*0.0006214));
            }
            //Öl
            else if (from_ert ==2 & to_ert==1) {
                vissza=(Double.toString(in*1.8964838));
            } else if (from_ert ==2 & to_ert==2) {
                vissza=(Double.toString(in*6.2220598));
            } else if (from_ert ==2 & to_ert==3) {
                vissza=(Double.toString(in*2.0740199));
            } else if (from_ert ==2 & to_ert==4) {
                vissza=(Double.toString(in*0.0011784));
            }
            //Foot
            else if (from_ert ==3 & to_ert==1) {
                vissza=(Double.toString(in*0.3048000));
            } else if (from_ert ==3 & to_ert==2) {
                vissza=(Double.toString(in*0.1607185));
            } else if (from_ert ==3 & to_ert==3) {
                vissza=(Double.toString(in*0.3333333));
            } else if (from_ert ==3 & to_ert==4) {
                vissza=(Double.toString(in*0.0001894));
            }
            //Yard
            else if (from_ert ==4 & to_ert==1) {
                vissza=(Double.toString(in*0.9144000));
            }else if (from_ert ==4 & to_ert==2) {
                vissza=(Double.toString(in*0.4821554));
            }else if (from_ert ==4 & to_ert==3) {
                vissza=(Double.toString(in*3.0000000));
            }else if (from_ert ==4 & to_ert==4) {
                vissza=(Double.toString(in*0.0005682));
            }
            //Mérföld
            else if (from_ert ==5 & to_ert==1) {
                vissza=(Double.toString(in*1609.3440000));
            }else if (from_ert ==5 & to_ert==2) {
                vissza=(Double.toString(in*848.5935741));
            } else if (from_ert ==5 & to_ert==3) {
                vissza=(Double.toString(in*5280.0000000));
            } else if (from_ert ==5 & to_ert==4) {
                vissza=(Double.toString(in*1760.0000000));
            }
        }
        //Terület
        else if (funkcio==2)
        {
            //Méter2
            if (from_ert ==1 & to_ert==1) {
                vissza=(Double.toString(in*0.0001000));
            } else if (from_ert ==1 & to_ert==2) {
                vissza=(Double.toString(in*0.2780364));
            } else if (from_ert ==1 & to_ert==3) {
                vissza=(Double.toString(in*(0.2780364/1600)));
            }
            //Hekter
            else if (from_ert ==2 & to_ert==1) {
                vissza=(Double.toString(in*10000));
            } else if (from_ert ==2 & to_ert==2) {
                vissza=(Double.toString(in*2780.3643234));
            } else if (from_ert ==2 & to_ert==3) {
                vissza=(Double.toString(in*1.73772756));
            }
            //Negyszog öl
            else if (from_ert ==3 & to_ert==1) {
                vissza=(Double.toString(in*3.5966510));
            } else if (from_ert ==3 & to_ert==2) {
                vissza=(Double.toString(in*0.0003597));
            } else if (from_ert ==3 & to_ert==3) {
                vissza=(Double.toString(in*0.000625));
            }
            //Hold
            else if (from_ert ==5 & to_ert==1) {
                vissza=(Double.toString(in*5754.6415286));
            } else if (from_ert ==5 & to_ert==2) {
                vissza=(Double.toString(in*0.5754642));
            } else if (from_ert ==5 & to_ert==3) {
                vissza=(Double.toString(in*1600));
            }
        }
        //Szög
        else if (funkcio==3)
        {
            //Fok
            if (from_ert ==1 & to_ert==1) {
                vissza=(Double.toString(in*1.1111111));
            } else if (from_ert ==1 & to_ert==2) {
                vissza=(Double.toString(in*17.7777778));
            } else  if (from_ert ==1 & to_ert==3) {
                vissza=(Double.toString(in*16.6666667));
            } else if (from_ert ==1 & to_ert==4) {
                vissza=(Double.toString(in*0.0174533));
            }
            //Grád
            else if (from_ert ==2 & to_ert==1) {
                vissza=(Double.toString(in*0.9000000));
            } else if (from_ert ==2 & to_ert==2) {
                vissza=(Double.toString(in*16.0000));
            } else if (from_ert ==2 & to_ert==3) {
                vissza=(Double.toString(in*15.0000));
            } else if (from_ert ==2 & to_ert==4) {
                vissza=(Double.toString(in*0.0157080));
            }
            //Vonás (6400)
            else if (from_ert ==3 & to_ert==1) {
                vissza=(Double.toString(in*0.0562500));
            } else if (from_ert ==3 & to_ert==2) {
                vissza=(Double.toString(in*0.0625000));
            } else if (from_ert ==3 & to_ert==3) {
                vissza=(Double.toString(in*0.9375000));
            } else if (from_ert ==3 & to_ert==4) {
                vissza=(Double.toString(in*0.0009817));
            }
            //Vonás (6k)
            else if (from_ert ==4 & to_ert==1) {
                vissza=(Double.toString(in*0.0600000));
            } else if (from_ert ==4 & to_ert==2) {
                vissza=(Double.toString(in*0.0666667));
            } else if (from_ert ==4 & to_ert==3) {
                vissza=(Double.toString(in*1.0666667));
            } else if (from_ert ==4 & to_ert==4) {
                vissza=(Double.toString(in*0.0010472));
            }
            //Radián
            else if (from_ert ==5 & to_ert==1) {
                vissza=(Double.toString(in*57.2957795));
            } else if (from_ert ==5 & to_ert==2) {
                vissza=(Double.toString(in*63.6619772));
            } else if (from_ert ==5 & to_ert==3) {
                vissza=(Double.toString(in*1018.5916358));
            } else if (from_ert ==5 & to_ert==4) {
                vissza=(Double.toString(in*954.9296586));
            }
        }
        return (vissza);
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