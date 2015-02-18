package com.example.tfboe.trouve_toutquebec;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.location.LocationProvider;
import android.provider.ContactsContract;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.text.style.SubscriptSpan;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.tfboe.trouve_toutquebec.Parser.CulteParser;
import com.example.tfboe.trouve_toutquebec.Parser.Parser;
import com.example.tfboe.trouve_toutquebec.Parser.ToiletteParser;
import com.example.tfboe.trouve_toutquebec.Services.GetLocation;
import com.example.tfboe.trouve_toutquebec.Services.LieuCulte;
import com.example.tfboe.trouve_toutquebec.Services.Service;
import com.example.tfboe.trouve_toutquebec.Services.ServicesManager;
import com.example.tfboe.trouve_toutquebec.Services.Toilette;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends ActionBarActivity implements LocationListener {

    private DatabaseTable db = new DatabaseTable(this);
    LocationManager locationManager;

    private double latitude;
    private double longitude;

    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button send = (Button)findViewById(R.id.button);

        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                searchClick();

                }
        });

        locationManager = (LocationManager)getSystemService(Context.LOCATION_SERVICE);
        if(locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER))
        {
            locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 10000, 0, this);
        }
        locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 10000, 0, this);


    }

    public void searchClick() {
        EditText text = (EditText) findViewById(R.id.editText);

        String []query = text.getText().toString().split(" ");
        UserRequest request = new UserRequest();

        for(String word: query)
        {
            Cursor c = db.getWordMatches(word, null);
            if (c != null) {
                System.out.println(c.getString(c.getColumnIndex("name")));

                String type = c.getString(c.getColumnIndex("type"));
                String name = c.getString(c.getColumnIndex("name"));

                switch (type) {
                    case "Proximite":
                        if (name.equals("Loin"))
                            request.setNear(false);
                        else
                            request.setNear(true);
                        break;
                    case "Service":
                        request.setService(name);
                    default:
                        break;
                }

            }
        }
        System.out.println(request.toString());

        execute(request);
    }

    private void execute(UserRequest request)
    {
        if(request != null && request.isRequestComplete()) {
            Parser parser = null;
            switch (request.getService()) {
                case "Toilette":
                    parser = new ToiletteParser();
                    break;

                case "Lieu de culte":
                    parser = new CulteParser();
                    break;
            }

            ServicesManager.getInstance().executeRequest(request, latitude, longitude, this.getBaseContext());

            Intent i = new Intent(getApplicationContext(), MapActivity.class);
            startActivity(i);
        }
        else
        {
            String msg = String.format("Aucun résultat n'a été trouvé.");
            Toast.makeText(this, msg, Toast.LENGTH_LONG).show();
        }
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

    @Override
    protected void onPause()
    {
        super.onPause();
        locationManager.removeUpdates(this);
    }

    @Override
    public void onLocationChanged(Location location) {
        latitude = location.getLatitude();
        longitude = location.getLongitude();
    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {
        String newStatus = "";
        switch (status) {
            case LocationProvider.OUT_OF_SERVICE:
                newStatus = "OUT_OF_SERVICE";
                break;
            case LocationProvider.TEMPORARILY_UNAVAILABLE:
                newStatus = "TEMPORARILY_UNAVAILABLE";
                break;
            case LocationProvider.AVAILABLE:
                newStatus = "AVAILABLE";
                break;
        }
        String msg = String.format("%1$s disabled status:%2$s", provider, newStatus);
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onProviderEnabled(String provider) {

        String message = String.format("%s enabled", provider);
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onProviderDisabled(String provider) {
        String message = String.format("%s disabled", provider);
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }
}
