package com.capstone.while1.beaconandroidstudio;

import android.content.Intent;
import android.location.Location;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;

public class MainActivity extends AppCompatActivity {

    static boolean isLoggedIn = false;
    //ImageButton mybutton = (ImageButton) findViewById(R.id.imageButton1);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //setContentView(R.layout.activity_login);
        if (!isLoggedIn) {
            //setContentView(R.layout.activity_login);

            Intent intent = new Intent(this, LoginActivity.class);
            startActivity(intent);

        }

        setContentView(R.layout.activity_main);

//this causes the app to crash so I commented out when I merged homerLogin
		//setContentView(R.xml.pref_notification);
        addListenerOnButton();
    }

    public void addListenerOnButton() {
        ImageButton myButton = (ImageButton) findViewById(R.id.imageButton1);
        myButton.setOnClickListener(new OnClickListener() {

            public void onClick(View arg0) {

                Intent intent = new Intent
                        (getApplicationContext(), SettingsActivity.class);
                startActivity(intent);
            }
        });
    }

}
