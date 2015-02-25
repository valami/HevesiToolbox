package hu.valamas.hevesitoolbox.szamolasok;

import android.app.Activity;
import android.os.Bundle;
import android.text.Editable;
import android.text.Html;
import android.text.InputFilter;
import android.text.TextWatcher;
import android.text.method.DigitsKeyListener;
import android.view.Menu;
import android.view.MenuItem;

import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.example.valamas.hevesitoolbox.R;

public class szelveny extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_szelveny);

        EditText x_in = (EditText) findViewById(R.id.x_in);
        EditText y_in = (EditText) findViewById(R.id.y_in);
        final EditText szelv_also = (EditText) findViewById(R.id.szelv_also);
        final EditText szelv_bal = (EditText) findViewById(R.id.szelv_bal);
        final EditText szelv_jobb = (EditText) findViewById(R.id.szelv_jobb);
        final EditText szelv_fel = (EditText) findViewById(R.id.szelv_fel);
        final EditText szelv_kozep = (EditText) findViewById(R.id.szelv_kozep);
        final Button szamol = (Button) findViewById(R.id.szamol);

        Spinner spinner = (Spinner) findViewById(R.id.spinner);
        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.meretarany_array, android.R.layout.simple_spinner_item);
        // Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner
        spinner.setAdapter(adapter);



      /*  szelv_kozep.setOnFocusChangeListener(new View.OnFocusChangeListener() {

            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus) {
                }
            }
        }); */

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
                            if (kozep_c[1] == '-')
                            {
                            String kozep_s_out = Character.toString(kozep_c[0]);
                            szelv_kozep.setText(kozep_s_out);
                            szelv_kozep.setSelection(szelv_kozep.getText().length());
                            }
                            if (kozep_c.length>3)
                            {
                                if (kozep_c[3]!='-' && kozep_c[2]!='-')
                                {
                                    String kozep_s_out =Character.toString(kozep_c[0])+ Character.toString(kozep_c[1]) + Character.toString(kozep_c[2]) + "-" + Character.toString(kozep_c[3])  ;
                                    szelv_kozep.setText(kozep_s_out);
                                    szelv_kozep.setSelection(szelv_kozep.getText().length());
                                }
                            }
                            if (kozep_c.length ==7)
                            {
                                if (kozep_c[2]== '-' && kozep_c[6] != '-' )
                                {
                                    String kozep_s_out =Character.toString(kozep_c[0])+ Character.toString(kozep_c[1])+ Character.toString(kozep_c[2])+ Character.toString(kozep_c[3]) + Character.toString(kozep_c[4])+ Character.toString(kozep_c[5])+ "-" + Character.toString(kozep_c[6]);
                                    szelv_kozep.setText(kozep_s_out) ;
                                    szelv_kozep.setSelection(szelv_kozep.getText().length());
                                }
                            }
                            if (kozep_c.length ==8)
                            {
                                if (kozep_c[3]== '-' && kozep_c[7] != '-' )
                                {
                                    String kozep_s_out =Character.toString(kozep_c[0])+ Character.toString(kozep_c[1])+ Character.toString(kozep_c[2])+ Character.toString(kozep_c[3]) + Character.toString(kozep_c[4])+ Character.toString(kozep_c[5])+ Character.toString(kozep_c[6])+ "-" + Character.toString(kozep_c[7]);
                                    szelv_kozep.setText(kozep_s_out) ;
                                    szelv_kozep.setSelection(szelv_kozep.getText().length());
                                }
                            }
                            if (kozep_c.length ==11)
                            {
                                if (kozep_c[2]== '-' && kozep_c[10] != '-' )
                                {
                                    String kozep_s_out =Character.toString(kozep_c[0])+ Character.toString(kozep_c[1])+ Character.toString(kozep_c[2])+ Character.toString(kozep_c[3]) + Character.toString(kozep_c[4])+ Character.toString(kozep_c[5])+ Character.toString(kozep_c[6]) + Character.toString(kozep_c[7])+ Character.toString(kozep_c[8])+ Character.toString(kozep_c[9])+"-"+ Character.toString(kozep_c[10]);
                                    szelv_kozep.setFilters(new InputFilter[] {new InputFilter.LengthFilter(12)});
                                    szelv_kozep.setText(kozep_s_out) ;
                                    szelv_kozep.setSelection(szelv_kozep.getText().length());
                                }
                            }
                            if (kozep_c.length ==12)
                            {
                                if (kozep_c[3]== '-' && kozep_c[11] != '-' )
                                {
                                    String kozep_s_out =Character.toString(kozep_c[0])+ Character.toString(kozep_c[1])+ Character.toString(kozep_c[2])+ Character.toString(kozep_c[3]) + Character.toString(kozep_c[4])+ Character.toString(kozep_c[5])+ Character.toString(kozep_c[6]) + Character.toString(kozep_c[7])+ Character.toString(kozep_c[8])+ Character.toString(kozep_c[9])+Character.toString(kozep_c[10])+"-"+ Character.toString(kozep_c[11]);
                                    szelv_kozep.setFilters(new InputFilter[] {new InputFilter.LengthFilter(13)});
                                    szelv_kozep.setText(kozep_s_out) ;
                                    szelv_kozep.setSelection(szelv_kozep.getText().length());
                                }
                            }

                        }
                    }
                }
            }

            @Override
            public void afterTextChanged(Editable s) {
                String kozep_s = szelv_kozep.getText().toString();
                if (kozep_s.length()>2) {
                    if (letezik(szelvenyszam(kozep_s))==true ) {
                        String also_sz ,felso_sz, bal_sz,jobb_sz;
                        int szelveny = szelvenyszam(kozep_s);
                        char[] kozep_c = Integer.toString(szelvenyszam(kozep_s)).toCharArray();
                        int szelv_kozep_eleje ,szelv_kozep_vege;

                        if (Integer.toString(szelveny).length()==3 && kozep_c[0]=='1'  )    {
                            szelv_kozep_eleje = 10;
                            szelv_kozep_vege = Integer.parseInt(Character.toString(kozep_c[2]));
                        }
                        else if (Integer.toString(szelveny).length()==3 && !(kozep_c[0] =='1'))     {
                            szelv_kozep_eleje =Integer.parseInt(Character.toString(kozep_c[1]));
                            szelv_kozep_vege = Integer.parseInt(Character.toString(kozep_c[2]) +Character.toString(kozep_c[3]));
                        } else {
                            szelv_kozep_eleje =Integer.parseInt(Character.toString(kozep_c[0]));
                            szelv_kozep_vege = Integer.parseInt(Character.toString(kozep_c[1]));
                        }

                    szelv_fel.setText((Integer.toString(szelv_kozep_eleje)+Integer.toString(szelv_kozep_vege)));
                    szelv_also.setText(Integer.toString(szelvenyszamutan(kozep_s)));














                    } else  {
                        szelv_kozep.setError(Html.fromHtml("<font color='black'>Nem létező szelvény!</font>"));
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
        String elso ="";
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
        else if (kozep_c[3]=='-'&& kozep.length()> 4)
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



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_szelveny, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}