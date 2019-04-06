package com.halam.midtronicsapphalam;

import android.app.ListActivity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;


//this activity creates the list of countries that will be displayed
public class countriesActivity extends ListActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_countries);

        //list view is created and populated with the given ultimate list of countries
        ListView countryList = getListView();

        setListAdapter(new ArrayAdapter<String>(this, R.layout.activity_countries, R.id.textView,
                getResources().getStringArray(R.array.countries_array)));

        countryList.setTextFilterEnabled(true);






    }


   //When a user clicks/taps on an item from the list, they are sent to the countryInfo activity
    public void onListItemClick(ListView parent, View view, int position, long id)
    {

        Intent countryInfo = new Intent(countriesActivity.this, com.halam.midtronicsapphalam.countryInfo.class);

        //the name of the country chosen is passed onto the countryInfo activity
        TextView countryName = (TextView) view.findViewById(R.id.textView);
        String text = countryName.getText().toString();
        Log.i("MainActivity", text);
        countryInfo.putExtra("name", text);

        startActivity(countryInfo);



    }
}
