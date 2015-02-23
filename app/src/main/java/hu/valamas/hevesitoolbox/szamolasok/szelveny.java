package hu.valamas.hevesitoolbox.szamolasok;

import android.app.Activity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.text.method.DigitsKeyListener;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
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
        EditText szelv_ball = (EditText) findViewById(R.id.szelv_ball);
        EditText szelv_jobb = (EditText) findViewById(R.id.szelv_jobb);
        EditText szelv_fel = (EditText) findViewById(R.id.szelv_fel);
        final EditText szelv_kozep = (EditText) findViewById(R.id.szelv_kozep);
        Button szamol = (Button) findViewById(R.id.szamol);

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
                                    String kozep_s_out = Character.toString(kozep_c[0]) + Character.toString(kozep_c[1]) + Character.toString(kozep_c[2]) + "-" + Character.toString(kozep_c[3])  ;
                                    szelv_kozep.setText(kozep_s_out);
                                    szelv_kozep.setSelection(szelv_kozep.getText().length());
                                }
                            }
                        }
                    }
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
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
