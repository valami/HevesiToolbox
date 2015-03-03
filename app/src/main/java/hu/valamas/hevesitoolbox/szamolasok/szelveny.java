package hu.valamas.hevesitoolbox.szamolasok;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.text.Editable;
import android.text.Html;
import android.text.InputFilter;
import android.text.TextWatcher;
import android.text.method.DigitsKeyListener;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.valamas.hevesitoolbox.R;

public class szelveny extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_szelveny);

        final EditText x_in = (EditText) findViewById(R.id.x_in);
        final EditText y_in = (EditText) findViewById(R.id.y_in);
        final EditText szelv_also = (EditText) findViewById(R.id.szelv_also);
        final EditText szelv_bal = (EditText) findViewById(R.id.szelv_bal);
        final EditText szelv_jobb = (EditText) findViewById(R.id.szelv_jobb);
        final EditText szelv_fel = (EditText) findViewById(R.id.szelv_fel);
        final EditText szelv_kozep = (EditText) findViewById(R.id.szelv_kozep);
        final Button szamol = (Button) findViewById(R.id.szamol);

        final Spinner spinner = (Spinner) findViewById(R.id.spinner);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.meretarany_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        //Forgatás
        Bundle extras = getIntent().getExtras();
        Byte orientation = extras.getByte("orientation");
        if (orientation == 0)   {
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        }   else {
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        }

        //Szelvély keresét
        szamol.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String x_in_s = x_in.getText().toString();
                String y_in_s = y_in.getText().toString();

                if (!x_in_s.matches("") & !y_in_s.matches("") )
                {
                    double x = Double.parseDouble(x_in_s);
                    double y = Double.parseDouble(y_in_s);

                    if (x < 32000 || x > 384000 || y < 384000 || y > 960000)
                    {
                        Toast.makeText(getApplicationContext(),
                            getString(R.string.szelveny_ervenytelen),Toast.LENGTH_SHORT).show();
                    } else {

                        int meretarany = meretarany(String.valueOf(spinner.getSelectedItem()));
                        szelv_kozep.setText(koord(meretarany,y,x));
                    }
                } else {
                    Toast.makeText(getApplicationContext(),
                        getString(R.string.szelveny_ures), Toast.LENGTH_SHORT).show();
                }
            }
        });

        //Középsö tagolás
        szelv_kozep.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                //Beírás
                String kozep_s = szelv_kozep.getText().toString();
                if (!kozep_s.matches("")) {
                    char kozep_c[] = kozep_s.toCharArray();
                    if (kozep_c.length <= 3) {
                        szelv_kozep.setKeyListener(DigitsKeyListener.getInstance("0123456789-"));
                    }
                    if (kozep_c.length == 3) {
                        szelv_kozep.setKeyListener(DigitsKeyListener.getInstance("1234"));
                    }

                    if (kozep_c[0] == '-') {
                        szelv_kozep.setText("");
                    } else {
                        if (kozep_c.length>1)
                        {
                            kotojel(kozep_c);
                        }
                    }
                }
            }

            @Override
            public void afterTextChanged(Editable s) {
                String kozep_s = szelv_kozep.getText().toString();
                if (kozep_s.length()>2) {
                    if (letezik(szelvenyszam(kozep_s))) {
                        int szelveny = szelvenyszam(kozep_s);
                        char[] kozep_c = Integer.toString(szelvenyszam(kozep_s)).toCharArray();
                        String also_sz ,felso_sz, bal_sz,jobb_sz;

                        if (szelvenyszamutan(kozep_s)==0)
                        {
                            if (szelveny < 110){
                                also_sz = Integer.toString(szelveny-10);
                                felso_sz = Integer.toString(szelveny+10);
                            } else {
                                also_sz = Integer.toString(szelveny-100);
                                felso_sz = Integer.toString(szelveny+100);
                            }
                            if (Integer.toString(szelveny).length() == 2     )
                            {
                                bal_sz = Character.toString(kozep_c[0]) +  Integer.toString(Integer.parseInt(Character.toString(kozep_c[1])) - 1 );
                                jobb_sz = Character.toString(kozep_c[0]) +  Integer.toString(Integer.parseInt(Character.toString(kozep_c[1])) + 1 );
                            }
                            else
                            {
                                bal_sz = Character.toString(kozep_c[0])+ Character.toString(kozep_c[2]) +  Integer.toString(Integer.parseInt(Character.toString(kozep_c[2])) - 1 );
                                jobb_sz = Character.toString(kozep_c[0])+ Character.toString(kozep_c[2]) +  Integer.toString(Integer.parseInt(Character.toString(kozep_c[2])) + 1 );
                            }

                            if (letezik(Integer.parseInt(also_sz)))
                            {
                                szelv_also.setText(also_sz);
                            } else {
                                szelv_also.setText("");
                            }
                            if (letezik(Integer.parseInt(felso_sz)))
                            {
                                szelv_fel.setText(felso_sz);
                            } else {
                                szelv_fel.setText("");
                            }
                            if (letezik(Integer.parseInt(jobb_sz)))
                            {
                                szelv_jobb.setText(jobb_sz);
                            } else {
                                szelv_jobb.setText("");
                            }
                            if (letezik(Integer.parseInt(bal_sz)))
                            {
                                szelv_bal.setText(bal_sz);
                            } else {
                                szelv_bal.setText("");
                            }
                        } else {
                            szelv_bal.setText(bal(szelvenyszam(kozep_s),szelvenyszamutan(kozep_s)));
                            szelv_jobb.setText(jobb(szelvenyszam(kozep_s),szelvenyszamutan(kozep_s)));
                            szelv_fel.setText(fel(szelvenyszam(kozep_s),szelvenyszamutan(kozep_s)));
                            szelv_also.setText(le(szelvenyszam(kozep_s),szelvenyszamutan(kozep_s)));
                            }
                    } else  {
                        szelv_kozep.setError(Html.fromHtml("<font color='black'>getString(R.string.szeveny_nemletezo)</font>"));
                        szelv_also.setText("");
                        szelv_fel.setText("");
                        szelv_bal.setText("");
                        szelv_jobb.setText("");
                        }
                    } else {
                        szelv_also.setText("");
                        szelv_fel.setText("");
                        szelv_bal.setText("");
                        szelv_jobb.setText("");
                }
            }
        });
    }

    public void kotojel (char[] bemeno)
    {
        EditText szelv_kozep = (EditText) findViewById(R.id.szelv_kozep);
        if (bemeno[1] == '-')
        {
            String kozep_s_out = Character.toString(bemeno[0]);
            szelv_kozep.setText(kozep_s_out);
            szelv_kozep.setSelection(szelv_kozep.getText().length());
        }
        if (bemeno.length>3)
        {
            if (bemeno[3]!='-' && bemeno[2]!='-')
            {
                String kozep_s_out =Character.toString(bemeno[0])+ Character.toString(bemeno[1]) + Character.toString(bemeno[2]) + "-" + Character.toString(bemeno[3])  ;
                szelv_kozep.setText(kozep_s_out);
                szelv_kozep.setSelection(szelv_kozep.getText().length());
            }
        }
        if (bemeno.length ==7)
        {
            if (bemeno[2]== '-' && bemeno[6] != '-' )
            {
                String kozep_s_out =Character.toString(bemeno[0])+ Character.toString(bemeno[1])+ Character.toString(bemeno[2])+ Character.toString(bemeno[3]) + Character.toString(bemeno[4])+ Character.toString(bemeno[5])+ "-" + Character.toString(bemeno[6]);
                szelv_kozep.setText(kozep_s_out) ;
                szelv_kozep.setSelection(szelv_kozep.getText().length());
            }
            if (bemeno[2]== '-' && bemeno[6] == '-' )
            {
                String kozep_s_out =Character.toString(bemeno[0])+ Character.toString(bemeno[1])+ Character.toString(bemeno[2])+ Character.toString(bemeno[3]) + Character.toString(bemeno[4])+ Character.toString(bemeno[5]);
                szelv_kozep.setText(kozep_s_out) ;
                szelv_kozep.setSelection(szelv_kozep.getText().length());
            }
        }
        if (bemeno.length ==8)
        {
            if (bemeno[3]== '-' && bemeno[7] != '-' )
            {
                String kozep_s_out =Character.toString(bemeno[0])+ Character.toString(bemeno[1])+ Character.toString(bemeno[2])+ Character.toString(bemeno[3]) + Character.toString(bemeno[4])+ Character.toString(bemeno[5])+ Character.toString(bemeno[6])+ "-" + Character.toString(bemeno[7]);
                szelv_kozep.setText(kozep_s_out) ;
                szelv_kozep.setSelection(szelv_kozep.getText().length());
            }
            if (bemeno[3]== '-' && bemeno[7] == '-' )
            {
                String kozep_s_out =Character.toString(bemeno[0])+ Character.toString(bemeno[1])+ Character.toString(bemeno[2])+ Character.toString(bemeno[3]) + Character.toString(bemeno[4])+ Character.toString(bemeno[5])+ Character.toString(bemeno[6]);
                szelv_kozep.setText(kozep_s_out) ;
                szelv_kozep.setSelection(szelv_kozep.getText().length());
            }
        }
        if (bemeno.length ==11)
        {
            if (bemeno[2]== '-' && bemeno[10] != '-' )
            {
                String kozep_s_out =Character.toString(bemeno[0])+ Character.toString(bemeno[1])+ Character.toString(bemeno[2])+ Character.toString(bemeno[3]) + Character.toString(bemeno[4])+ Character.toString(bemeno[5])+ Character.toString(bemeno[6]) + Character.toString(bemeno[7])+ Character.toString(bemeno[8])+ Character.toString(bemeno[9])+"-"+ Character.toString(bemeno[10]);
                szelv_kozep.setFilters(new InputFilter[] {new InputFilter.LengthFilter(12)});
                szelv_kozep.setText(kozep_s_out) ;
                szelv_kozep.setSelection(szelv_kozep.getText().length());
            }
            if (bemeno[2]== '-' && bemeno[10] == '-' )
            {
                String kozep_s_out =Character.toString(bemeno[0])+ Character.toString(bemeno[1])+ Character.toString(bemeno[2])+ Character.toString(bemeno[3]) + Character.toString(bemeno[4])+ Character.toString(bemeno[5])+ Character.toString(bemeno[6]) + Character.toString(bemeno[7])+ Character.toString(bemeno[8])+ Character.toString(bemeno[9]);
                szelv_kozep.setFilters(new InputFilter[] {new InputFilter.LengthFilter(12)});
                szelv_kozep.setText(kozep_s_out) ;
                szelv_kozep.setSelection(szelv_kozep.getText().length());
            }
        }
        if (bemeno.length ==12)
        {
            if (bemeno[3]== '-' && bemeno[11] != '-' )
            {
                String kozep_s_out =Character.toString(bemeno[0])+ Character.toString(bemeno[1])+ Character.toString(bemeno[2])+ Character.toString(bemeno[3]) + Character.toString(bemeno[4])+ Character.toString(bemeno[5])+ Character.toString(bemeno[6]) + Character.toString(bemeno[7])+ Character.toString(bemeno[8])+ Character.toString(bemeno[9])+Character.toString(bemeno[10])+"-"+ Character.toString(bemeno[11]);
                szelv_kozep.setFilters(new InputFilter[] {new InputFilter.LengthFilter(13)});
                szelv_kozep.setText(kozep_s_out) ;
                szelv_kozep.setSelection(szelv_kozep.getText().length());
            }
            if (bemeno[3]== '-' && bemeno[11] == '-' )
            {
                String kozep_s_out =Character.toString(bemeno[0])+ Character.toString(bemeno[1])+ Character.toString(bemeno[2])+ Character.toString(bemeno[3]) + Character.toString(bemeno[4])+ Character.toString(bemeno[5])+ Character.toString(bemeno[6]) + Character.toString(bemeno[7])+ Character.toString(bemeno[8])+ Character.toString(bemeno[9])+Character.toString(bemeno[10]);
                szelv_kozep.setFilters(new InputFilter[] {new InputFilter.LengthFilter(13)});
                szelv_kozep.setText(kozep_s_out) ;
                szelv_kozep.setSelection(szelv_kozep.getText().length());
            }
        }
    }
    public String koord (int mereta , double y , double x)
    {
        String r = "";
        int szelvenyszam ,szelveny_eleje , szelveny_vege;
        szelveny_eleje = (int) ((x-32000)/32000) ;
        szelveny_vege = (int) ((y-384000)/48000) ;
        szelvenyszam =   Integer.parseInt(Integer.toString(szelveny_eleje) + Integer.toString(szelveny_vege)) ;

        double a1 = ((x-32000)/32000)-szelveny_eleje  ;
        double a2 = ((y-384000)/48000)-szelveny_vege  ;

        for (byte i = 1 ; i<=mereta ; i++)
        {
            double a3 = 1/Math.pow(2,i);
            if ( a1  >=  a3 && a2 >=  a3  ) {
                r = r + "2";
                a1 = a1 - a3;
                a2 = a2 - a3;
            }   else if ( a1  <=  a3 && a2 >=  a3  )    {
                r = r + "4";
                a2 = a2 - a3;
            }   else if ( a1  >=  a3 && a2 <=  a3  )    {
                r = r + "1";
                a1 = a1 -a3;
            } else  {
                r = r + "3";
            }
        }

        if (String.valueOf(szelvenyszam).length()==2)
        {
            return szelvenyszam + "-" + tagol(r);
        } else {
            return szelvenyszam + tagol(r);
        }
    }
    public int meretarany (String in)
    {
        switch (in) {
            case "1:100000":
                return 0;
            case "1:50000":
                return 1;
            case "1:25000":
                return 2;
            case "1:10000":
                return 3;
            case "1:4000":
                return 4;
            case "1:2000":
                return 5;
            case "1:1000":
                return 6;
            default:
                return 7;
        }
    }
    public String tagol (String bemeno )
    {
        char[] bemeno_c = bemeno.toCharArray();
        if (bemeno_c.length>6)  {
            return (bemeno.substring(0,3)+"-"+bemeno.substring(3,6) + "-" + bemeno.substring(6) );
        } else if (bemeno_c.length> 3)   {
            return (bemeno.substring(0,3)+ "-" +bemeno.substring(3)  );
        } else {
            return bemeno.substring(0);
        }
    }
    public String intarraytostring (int[] intarray)
    {
        String eredmeny = "";
        for (int o=0 ; o<intarray.length ; o++)
        {
            eredmeny = eredmeny + Integer.toString(intarray[o]);
        }
        return eredmeny;
    }
    public int szelvenyszam (String kozep)
    {
        char kozep_c[] = kozep.toCharArray();
        int szam;
        if (kozep_c[2]=='-')
        {
            szam =Integer.parseInt(Character.toString(kozep_c[0]) + Character.toString(kozep_c[1]));
        }
        else
        {
            szam =Integer.parseInt(Character.toString(kozep_c[0]) +Character.toString(kozep_c[1])+Character.toString(kozep_c[2]));
        }
        return (szam);
    }
    public int szelvenyszamutan (String kozep)
    {
        char kozep_c[] = kozep.toCharArray();
        String elso="";
        String masodik ="";
        String harmadik="";
        int szam;
        if (kozep_c[2]=='-'&& kozep.length()> 3)
        {
            if (kozep.length()<7 )
            {
                elso = kozep.substring(3);
            } else if (kozep.length()>7 && kozep.length()<11) {
                elso = kozep.substring(3,6);
                masodik = kozep.substring(7);
            } else {
                elso = kozep.substring(3,6);
                masodik = kozep.substring(7,10);
                harmadik = kozep.substring(11);
            }

            szam= Integer.parseInt(elso+masodik+harmadik);
            return (szam);
        }
        else if (kozep.length()> 4)
        {
            if (kozep.length()<8 )
            {
                elso = kozep.substring(4);
            } else if (kozep.length()>8 && kozep.length()<12) {
                elso = kozep.substring(4,7);
                masodik = kozep.substring(8);
            } else {
                elso = kozep.substring(4,7);
                masodik = kozep.substring(8,11);
                harmadik = kozep.substring(12);
            }
            szam= Integer.parseInt(elso+masodik+harmadik);
            return (szam);
        }
        else {
            return (0);
        }
    }
    public boolean letezik (int szelveny)
    {
        if (szelveny == 40 || szelveny == 41|| szelveny == 42 || szelveny == 43 || szelveny == 44 || szelveny == 45 || szelveny == 46 || szelveny == 47 || szelveny == 48 || szelveny == 49)
        {
            return (true);
        } else if (szelveny == 51 || szelveny == 52|| szelveny == 53|| szelveny == 54|| szelveny == 55|| szelveny == 56|| szelveny == 57|| szelveny == 58|| szelveny == 59)
        {
            return (true);
        }else if (szelveny== 61 || szelveny == 62 || szelveny == 63 || szelveny == 64|| szelveny == 65|| szelveny == 66|| szelveny == 67|| szelveny == 68|| szelveny == 69|| szelveny == 610)
        {
            return (true);
        }else if (szelveny== 71 || szelveny == 72 || szelveny == 73 || szelveny == 74|| szelveny == 75|| szelveny == 76|| szelveny == 77|| szelveny == 78|| szelveny == 79|| szelveny == 710|| szelveny == 711)
        {
            return (true);
        }else if (szelveny== 82 || szelveny == 85 || szelveny == 86 || szelveny == 87|| szelveny == 88|| szelveny == 89|| szelveny == 810|| szelveny == 811)
        {
            return (true);
        }else if (szelveny == 96|| szelveny == 97|| szelveny == 98|| szelveny == 99|| szelveny == 910)
        {
            return (true);
        }else if (szelveny == 107|| szelveny == 108|| szelveny == 109)
        {
            return (true);
        }else if (szelveny == 31 || szelveny == 32|| szelveny == 33|| szelveny == 34|| szelveny == 35|| szelveny == 36|| szelveny == 37|| szelveny == 38|| szelveny == 39)
        {
            return (true);
        }else if (szelveny == 21 || szelveny == 22|| szelveny == 23|| szelveny == 24|| szelveny == 25|| szelveny == 26|| szelveny == 27|| szelveny == 28|| szelveny == 29)
        {
            return (true);
        }else if (szelveny == 12 || szelveny == 13|| szelveny == 14|| szelveny == 15|| szelveny == 16|| szelveny == 17|| szelveny == 18)
        {
            return (true);
        }else if (szelveny == 2 || szelveny == 3|| szelveny == 4|| szelveny == 5)
        {
            return (true);
        } else {
            return (false);
        }
    }
    public String bal (int alap, int utani)
    {
        String raw =  Integer.toString(utani);
        String r = "";
        int[] num = new int[raw.length()];
        for (int o = 0; o < raw.length(); o++){
            num[o] = raw.charAt(o) - '0';
        }

        for  (int i = 0;i<num.length; i++) {
            if (num[(num.length - 1)-i] == 2 || num[(num.length - 1)-i] == 4) {
                num[(num.length - 1)-i] = num[(num.length - 1)-i] -1 ;
                r = (Integer.toString(alap) + "-" + tagol(intarraytostring(num)) );
                i = i + 10;
            } else {
                if (num.length == i + 1) {
                    if (letezik(alap - 1)) {
                        num[num.length - 1 - i] = num[num.length - 1 - i] + 1;
                        return (Integer.toString(alap - 1) + "-" + tagol(intarraytostring(num)));
                    } else {
                        return "";
                    }
                } else {
                    num[num.length - 1 - i] = num[num.length - 1 - i] + 1;
                }
            }
        }
        return (r);
    }
    public String jobb (int alap, int utani)
    {
        String raw =  Integer.toString(utani);
        String r = "";
        int[] num = new int[raw.length()];
        for (int o = 0; o < raw.length(); o++){
            num[o] = raw.charAt(o) - '0';
        }

        for  (int i = 0;i<num.length; i++) {
            if (num[(num.length - 1)-i] == 1 || num[(num.length - 1)-i] == 3) {
                num[(num.length - 1)-i] = num[(num.length - 1)-i] +1 ;
                r = (Integer.toString(alap) + "-" + tagol(intarraytostring(num)) );
                i = i + 10;
            } else {
                if (num.length == i + 1) {
                    if (letezik(alap + 1)) {
                        num[num.length - 1 - i] = num[num.length - 1 - i] - 1;
                        return (Integer.toString(alap + 1) + "-" + tagol(intarraytostring(num)));
                    } else {
                        return "";
                    }
                } else {
                    num[num.length - 1 - i] = num[num.length - 1 - i] - 1;
                }
            }
        }
        return (r);
    }
    public String fel (int alap, int utani)
    {
        String raw =  Integer.toString(utani);
        String r = "";
        int[] num = new int[raw.length()];
        for (int o = 0; o < raw.length(); o++){
            num[o] = raw.charAt(o) - '0';
        }

        for  (int i = 0;i<num.length; i++) {
            if (num[(num.length - 1)-i] == 3 || num[(num.length - 1)-i] == 4) {
                num[(num.length - 1)-i] = num[(num.length - 1)-i] -2 ;
                r = (Integer.toString(alap) + "-" + tagol(intarraytostring(num)) );
                i = i + 10;
            } else {
                if (num.length == i + 1) {
                    if (letezik(alap + 10)) {
                        num[num.length - 1 - i] = num[num.length - 1 - i] + 2;
                        return (Integer.toString(alap + 10) + "-" + tagol(intarraytostring(num)));
                    } else {
                        return "";
                    }
                } else {
                    num[num.length - 1 - i] = num[num.length - 1 - i] + 2;
                }
            }
        }
        return (r);
    }
    public String le (int alap, int utani)
    {
        String raw =  Integer.toString(utani);
        String r = "";
        int[] num = new int[raw.length()];
        for (int o = 0; o < raw.length(); o++){
            num[o] = raw.charAt(o) - '0';
        }

        for  (int i = 0;i<num.length; i++) {
            if (num[(num.length - 1)-i] == 1 || num[(num.length - 1)-i] == 2) {
                num[(num.length - 1)-i] = num[(num.length - 1)-i] +2 ;
                r = (Integer.toString(alap) + "-" + tagol(intarraytostring(num)) );
                i = i + 10;
            } else {
                if (num.length == i + 1) {
                    if (letezik(alap - 10)) {
                        num[num.length - 1 - i] = num[num.length - 1 - i] - 2;
                        return (Integer.toString(alap - 10) + "-" + tagol(intarraytostring(num)));
                    } else {
                        return "";
                    }
                } else {
                    num[num.length - 1 - i] = num[num.length - 1 - i] - 2;
                }
            }
        }
        return (r);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_szelveny, menu);
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
        if (id == R.id.action_info)
        {
            AlertDialog alertDialog = new AlertDialog.Builder(this).create();
            alertDialog.setTitle(getString(R.string.menu_sugo));
            alertDialog.setMessage(getString(R.string.szelveny_sugo));
            alertDialog.setButton("OK", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int which) {
                }
            });
            alertDialog.show();
        }
        return super.onOptionsItemSelected(item);
    }
}