package com.example.tfboe.trouve_toutquebec;

import android.app.Activity;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.location.LocationProvider;
import android.provider.SyncStateContract;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.tfboe.trouve_toutquebec.Services.LieuCulte;
import com.example.tfboe.trouve_toutquebec.Services.Service;
import com.example.tfboe.trouve_toutquebec.Services.ServicesManager;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import org.apache.http.impl.conn.SingleClientConnManager;

import java.util.ArrayList;
import java.util.List;


public class MapActivity extends Activity{


    GoogleMap googleMap;

    private void createMapView(){

        /**
         * Catch the null pointer exception that
         * may be thrown when initialising the map
         */
        try {
            if(null == googleMap){
                googleMap = ((MapFragment) getFragmentManager().findFragmentById(
                        R.id.mapView)).getMap();



                /**
                 * If the map is still null after attempted initialisation,
                 * show an error to the user
                 */
                if(null == googleMap) {
                    Toast.makeText(getApplicationContext(),
                            "Error creating map", Toast.LENGTH_SHORT).show();
                }
            }
        } catch (NullPointerException exception){
            Log.e("mapApp", exception.toString());
        }
    }

    private void addMarker(){

        List<Service> myList = ServicesManager.getInstance().getList();

        for(Service s: myList) {

            /** Make sure that the map has been initialised **/
            if (null != googleMap) {
                if(s.getLongitude() != 0 && s.getLatitude() != 0) {
                    googleMap.addMarker(new MarkerOptions()
                                    .position(new LatLng(s.getLatitude(), s.getLongitude()))
                                    .title("My Location")
                                    .draggable(true)
                                    .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_BLUE))

                    );
                }
            }
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);
        createMapView();
        addMarker();

        googleMap.setMyLocationEnabled(true);

        Location loc = googleMap.getMyLocation();

        if(loc != null)
        {
            LatLng myLocation = new LatLng(loc.getLatitude(), loc.getLongitude());
            CameraUpdate zoom = CameraUpdateFactory.zoomTo(15);
            CameraUpdate center = CameraUpdateFactory.newLatLng(myLocation);

            googleMap.moveCamera(center);
            googleMap.animateCamera(zoom);
        }


    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_map, menu);
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
