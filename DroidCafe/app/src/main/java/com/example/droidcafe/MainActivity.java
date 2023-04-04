package com.example.droidcafe;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.preference.PreferenceManager;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;


import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

public class MainActivity extends AppCompatActivity {

    //The order message, displayed in the Toast and sent to the new activity
    private String mOrderMessage;

    //Tag for intent string
    public static final String EXTRA_MESSAGE = "com.example.android.droidcafe.extra.MESSAGE";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view){
                Intent intent = new Intent(MainActivity.this, OrderActivity.class);
                intent.putExtra(EXTRA_MESSAGE, mOrderMessage);
                startActivity(intent);
            }

        });

        PreferenceManager.setDefaultValues(this,
                R.xml.header_preferences, false);
        PreferenceManager.setDefaultValues(this,
                R.xml.messages_preferences, false);
        PreferenceManager.setDefaultValues(this,
                R.xml.pref_account, false);

        SharedPreferences sharedPref = PreferenceManager
                .getDefaultSharedPreferences(this);
        String marketPref = sharedPref
                .getString("market", "-1");
        displayToast(marketPref);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        //Inflate the menu; this adds items to the action bar if it is present
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        //Handle action bar item clicks here. The action bar will
        //automatically handle clicks on the Home/up button, so long as
        //you specify a parent activity in AndroidManifest.xml

        int id = item.getItemId();

        //noninspection simplifiableIfStatement
        switch (item.getItemId()) {
            case R.id.action_order:
                Intent intent = new Intent(MainActivity.this, OrderActivity.class);
                intent.putExtra(EXTRA_MESSAGE, mOrderMessage);
                startActivity(intent);
                return true;
            // ... code for other cases
            case R.id.action_status:
                displayToast(getString(R.string.action_status));
                return true;
            case R.id.action_favorites:
                displayToast(getString(R.string.action_favorites));
                return true;
            case R.id.action_contact:
                displayToast(getString(R.string.action_contact));
                return true;

            case R.id.action_settings:
                Intent settingsIntent = new Intent(this,SettingsActivity.class);
                startActivity(settingsIntent);
                return true;



            default:
                // Do nothing
        }
        return super.onOptionsItemSelected(item);

    }


    public void displayToast(String message){
        Toast.makeText(getApplicationContext(), message,
                Toast.LENGTH_SHORT).show();
    }

    //shows message that donut was clicked
    public void showDonutOrder(View view){
        mOrderMessage = getString(R.string.donut_order_message);
        displayToast(mOrderMessage);
    }

    //shows method that the ice cream sandwich image was clicked
    public void showIceCreamOrder(View view){
        mOrderMessage = getString(R.string.ice_cream_order_message);
        displayToast(mOrderMessage);
    }

    //shows method that the froyo image was clicked
    public void showFroyoOrder(View view){
        mOrderMessage = getString(R.string.froyo_order_message);
        displayToast(mOrderMessage);
    }
}

