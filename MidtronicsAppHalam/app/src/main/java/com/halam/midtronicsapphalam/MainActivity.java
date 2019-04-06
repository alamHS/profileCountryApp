package com.halam.midtronicsapphalam;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
//Hello, this is the main activity from which the user will select to see my profile or the list of countries
public class MainActivity extends AppCompatActivity {


    protected Button profile;
    protected Button countrylist;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        profile = findViewById(R.id.button);
        countrylist = findViewById(R.id.button2);


        //will send user to profileActivity
        profile.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View v)
            {
                Intent sendToProfile = new Intent(MainActivity.this, profileActivity.class);

                startActivity(sendToProfile);
            }
        });


        //will send user to countriesActvity
        countrylist.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View v)
            {
                Intent sendToCountries = new Intent(MainActivity.this, countriesActivity.class);

                startActivity(sendToCountries);
            }
        });





    }
}
