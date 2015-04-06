package hu.valamas.hevesitoolbox;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.text.InputFilter;
import android.text.method.DigitsKeyListener;
import android.text.method.PasswordTransformationMethod;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Spinner;

import com.example.valamas.hevesitoolbox.R;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;


public class settings extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        final Spinner tizedes_s = (Spinner) findViewById(R.id.tizedes_s);
        final Spinner szogkiiras_s = (Spinner) findViewById(R.id.szogkiiras_s);
        Button save = (Button) findViewById(R.id.SAVE);
        final CheckBox debug_c = (CheckBox) findViewById(R.id.debug);
        Boolean debug= false;

        debug_c.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final boolean[] pass = {false};

                AlertDialog.Builder alert =  new AlertDialog.Builder(settings.this)
                        .setTitle("PIN");
                final EditText input = new EditText(settings.this);
                input.setFilters(new InputFilter[] {new InputFilter.LengthFilter(4)});
                input.setKeyListener(DigitsKeyListener.getInstance("0123456789"));
                input.setTransformationMethod(PasswordTransformationMethod.getInstance());
                alert.setView(input)
                        .setCancelable(false);

                AlertDialog show = alert.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        String pin = input.getEditableText().toString();
                        if (pin.equals("2501")) {
                            pass[0] = true;
                            debug_c.setChecked(true);
                        } else {
                            debug_c.setChecked(false);
                        }
                    }
                })
                .setNegativeButton("Mégsem", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        debug_c.setChecked(false);
                        dialog.cancel();
                    }
                })
                .show();
            }
        });




        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String tizedes = tizedes_s.getSelectedItem().toString();
                String szogkiiras = szogkiiras_s.getSelectedItem().toString();
                Boolean debug = debug_c.isChecked();
                try {
                    FileOutputStream settings = openFileOutput("settings.txt",MODE_WORLD_WRITEABLE);
                    OutputStreamWriter write = new OutputStreamWriter(settings);
                    write.write(tizedes);
                    write.write(szogkiiras);
                    write.write(Boolean.toString(debug));
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        return super.onOptionsItemSelected(item);
    }
}
