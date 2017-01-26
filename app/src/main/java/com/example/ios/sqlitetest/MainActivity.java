package com.example.ios.sqlitetest;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Date;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //final DBHelper dbHelper = new DBHelper(getApplicationContext(), "MoneyBook.db", null, 1);
        final DBHelper dbHelper = DBHelper.getInstance(getApplication());


        final TextView result = (TextView) findViewById(R.id.result);
        final EditText etDate = (EditText) findViewById(R.id.date);
        final EditText etItem = (EditText) findViewById(R.id.item);
        final EditText etPrice = (EditText) findViewById(R.id.price);

        long now = System.currentTimeMillis();
        Date date = new Date(now);

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy MM dd");
        etDate.setText(simpleDateFormat.format(date));

        Button insert = (Button) findViewById(R.id.insert);
        insert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)  {
                String date = etDate.getText().toString();
                String item = etItem.getText().toString();
                int price = Integer.parseInt(etPrice.getText().toString());
                dbHelper.insert(date, item, price);
                result.setText(dbHelper.getResult());
            }
        });

        Button update = (Button)findViewById(R.id.update);
        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)  {
                String item = etItem.getText().toString();
                int price = Integer.parseInt(etPrice.getText().toString());
                dbHelper.update(item, price);
                result.setText(dbHelper.getResult());
            }
        });

        Button delete = (Button)findViewById(R.id.delete);
        delete.setOnClickListener(new View.OnClickListener()  {
            @Override
            public void onClick(View v)  {
                String item = etItem.getText().toString();
                dbHelper.delete(item);
                result.setText(dbHelper.getResult());
            }
        });

        Button select = (Button)findViewById(R.id.select);
        select.setOnClickListener(new View.OnClickListener()  {
            @Override
            public void onClick(View v)  {
                result.setText(dbHelper.getResult());
            }
        });





        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
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
