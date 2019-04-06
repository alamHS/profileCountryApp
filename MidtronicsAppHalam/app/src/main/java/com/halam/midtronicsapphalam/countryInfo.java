package com.halam.midtronicsapphalam;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextClock;
import android.widget.TextView;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;



//This class gets the info of the country from the provided website and displays it within the app
public class countryInfo  extends AppCompatActivity {

    //String that holds the contents of the webpage
    public String returnString;

    //private class that will connect to the url through an httpsUrlConnection object
    private class httpGET extends AsyncTask {



        @Override
        protected String doInBackground(Object... urlName) {


           //the connection is opened through this try catch statement
            try {
                URL url = new URL((String)urlName[0]);
                HttpsURLConnection httpConnect = (HttpsURLConnection) url.openConnection();

                try {

                    httpConnect.setRequestMethod("GET");
                    httpConnect.setDoInput(true);


                    InputStream in = new BufferedInputStream(httpConnect.getInputStream());
                    BufferedReader buff = new BufferedReader(new InputStreamReader(in));

                    String allInfo;
                    StringBuffer connectResponse = new StringBuffer();


                    while ((allInfo = buff.readLine()) != null) {
                        connectResponse.append(allInfo);

                    }


                    in.close();

                    returnString = connectResponse.toString();
                    return connectResponse.toString();

                } finally {
                    httpConnect.disconnect();
                }


            } catch (Exception e) {
                //should there be an error in the thread, an exception will be thrown
                e.printStackTrace();
            }

            return null;

        }

        //once all the info has been retrieved, it is parsed into the layout of the app
        @Override
        protected void onPostExecute(Object result)
        {

            //each value retrieves the xml variable it corresponds to
            TextView capital = findViewById(R.id.capital);
            TextView region  = findViewById(R.id.Region);
            TextView subregion = findViewById(R.id.Subregion);
            TextView area = findViewById(R.id.Area);
            TextView population = findViewById(R.id.Population);
            TextView demonym = findViewById(R.id.Demonym);

            //each value is parsed from the returned info and set as the text for it's spot in the layout
            String cap = returnString.split("capital")[1];
            String cap2[] = cap.split(":");
            capital.setText(cap2[1].split(",")[0].replaceAll("\"", " "));

            String reg = returnString.split("region")[1];
            String reg2[] = reg.split(":");
            region.setText(reg2[1].split(",")[0].replaceAll("\"", " "));

            String sub = returnString.split("subregion")[1];
            String sub2[] = sub.split(":");
            subregion.setText(sub2[1].split(",")[0].replaceAll("\"", " "));

            String ar = returnString.split("area")[1];
            String ar2[] = ar.split(":");
            area.setText(ar2[1].split(",")[0].replaceAll("\"", " "));

            String pop = returnString.split("population")[1];
            String pop2[] = pop.split(":");
            population.setText(pop2[1].split(",")[0].replaceAll("\"", " "));

            String den = returnString.split("demonym")[1];
            String den2[] = den.split(":");
            demonym.setText(den2[1].split(",")[0].replaceAll("\"", " "));





        }


    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_country_info);

        //the chosen country name is taken from the main activity
        Intent i = getIntent();
        String name = i.getStringExtra("name");

        //it is sent to it's spot in the layout
        TextView nameView = findViewById(R.id.countryName);
        nameView.setText(name);

        //the name of the country is added to the url of the site the data is coming from
        String urlName = "https://restcountries.eu/rest/v1/name/" + name;

        //request is made to get the info from the site and populate the layout
        new httpGET().execute(urlName);





    }
}
