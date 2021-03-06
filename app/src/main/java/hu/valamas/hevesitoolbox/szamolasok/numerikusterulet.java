package hu.valamas.hevesitoolbox.szamolasok;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;

import com.example.valamas.hevesitoolbox.R;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;

public class numerikusterulet extends Activity {

    ListView mylistview;
    ListAdapter simpleAdapter;
    final ArrayList<HashMap<String, String>> bevitt= new ArrayList<HashMap<String, String>>();
    int num_count = 0 ;
    double[] Y_koord = new double[15];
    double[] X_koord = new double[15] ;

    public void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_numerikusterulet);
        mylistview = (ListView) findViewById(R.id.listView1);

        AdView mAdView = (AdView) findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);

        final EditText Y_in = (EditText) findViewById(R.id.Y_in);
        final EditText X_in = (EditText) findViewById(R.id.X_in);
        final TextView eredmeny = (TextView) findViewById(R.id.terulet);

        final HashMap<String, String> map = new HashMap<String, String>();
        final SimpleAdapter simpleAdapter = new SimpleAdapter(this, bevitt, R.layout.list_layout, new String[]{"n" ,"Y", "X"}, new int[]{R.id.id_listout,R.id.Y_listout, R.id.X_listout});
        mylistview.setAdapter(simpleAdapter);

        Bundle extras = getIntent().getExtras();
        Byte orientation = extras.getByte("orientation");
        if (orientation == 0)   {
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        }   else {
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        }

        Button add = (Button) findViewById(R.id.add);
        add.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                //Ellenörzés
                float Y , X ;
                try {
                    String Y_in_s = Y_in.getText().toString();
                    String X_in_s = X_in.getText().toString();
                    Y = Float.parseFloat(Y_in_s);
                    X = Float.parseFloat(X_in_s);

                } catch (Exception e) {
                    Toast.makeText(getApplicationContext(),
                            getString(R.string.numerikus_ures), Toast.LENGTH_LONG).show();
                    return;
                }

                //Hozzáadás
                HashMap<String, String> map = new HashMap<String, String>();
                Y_koord[num_count]=Y;
                X_koord[num_count]=X;
                num_count=num_count +1;
                map.put("n",Integer.toString(num_count));
                map.put("Y",Float.toString(Y));
                map.put("X",Float.toString(X) );
                bevitt.add(map);
                simpleAdapter.notifyDataSetChanged();
                Y_in.setText("");
                X_in.setText("");

                //Visszazárás
                if (Y==Y_koord[0] && X==X_koord[0] && num_count>3 ) {
                    eredmeny.setText("Terület :"+ Double.toString(szamol())   );
                    eredmeny.setVisibility(View.VISIBLE);
                }
            }
        });
        Button szamol = (Button) findViewById(R.id.calc);
        szamol.setOnClickListener(new View.OnClickListener() {
           public void onClick(View v) {
               if (num_count<3)
               {
                   Toast.makeText(getApplicationContext(),
                           "Túl kevés bevitt adat!", Toast.LENGTH_SHORT).show();
                   return;
               } else {
                   float Y = (float) Y_koord[0];
                   float X = (float) X_koord[0];
                   Y_koord[num_count]=Y;
                   X_koord[num_count]=X;
                   num_count=num_count +1;
                   map.put("n",Integer.toString(num_count));
                   map.put("Y",Float.toString(Y));
                   map.put("X",Float.toString(X) );
                   bevitt.add(map);
                   simpleAdapter.notifyDataSetChanged();
                   Y_in.setText("");
                   X_in.setText("");
                   eredmeny.setText("Terület :"+ Double.toString(szamol())   );
                   eredmeny.setVisibility(View.VISIBLE);
               }
           }
           }
        ) ;
    }

    public double szamol(){
        double M =0;
        int i=0;
        while ( i!=num_count) {
            M = M + ((X_koord[i] * Y_koord[i + 1]) - (Y_koord[i] * X_koord[i + 1]));
            i++;
        }
        M=Math.abs(M/2);
        return( M );
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_numerikusterulet, menu);
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
        if (id == R.id.action_info)
        {
            AlertDialog alertDialog = new AlertDialog.Builder(this).create();
            alertDialog.setTitle(getString(R.string.menu_sugo));
            alertDialog.setMessage(getString(R.string.numerikus_sugo));
            alertDialog.setButton("OK", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int which) {
                }
            });
            alertDialog.show();
        }


        /* if (id ==R.id.action_rajz)
        {
            Intent intent = new Intent(numerikusterulet.this,numerikusteruletrajz.class);

            intent.putExtra("X_koord",X_koord );
            intent.putExtra("Y_koord",Y_koord );
            intent.putExtra("count",num_count );
            startActivity(intent);
        } */
        return super.onOptionsItemSelected(item);
    }
}




