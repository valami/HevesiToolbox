package hu.valamas.hevesitoolbox;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.example.valamas.hevesitoolbox.R;


public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final Button iranyszog = (Button) findViewById(R.id.iranyszog);
        iranyszog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1 = new Intent(MainActivity.this, hu.valamas.hevesitoolbox.iranyszog.class);
                startActivity(intent1);
            }
        });
        final Button polaris = (Button) findViewById(R.id.polaris);
        polaris.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                Intent intent2 = new Intent(MainActivity.this, polarispont.class);
                startActivity(intent2);
            }
        });
        final Button numerikus = (Button) findViewById(R.id.numerikus);
        numerikus.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                Intent intent2 = new Intent(MainActivity.this, numerikusterulet.class);
                startActivity(intent2);
            }
        });
        final Button haromszog = (Button) findViewById(R.id.haromszog);
        haromszog.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                Intent intent2 = new Intent(MainActivity.this,  hu.valamas.hevesitoolbox.haromszog.class);
                startActivity(intent2);
            }
        });

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
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
