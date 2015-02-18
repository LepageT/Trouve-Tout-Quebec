package com.example.tfboe.trouve_toutquebec.Services;

import android.location.Address;
import android.location.Geocoder;
import android.os.Parcel;

import com.google.ads.AdRequest;

import java.io.IOException;
import java.util.List;

public class Toilette extends Service{

	private String adresse;
	private String quartier;
	
	public Toilette()
	{
		
	}

    public String getAdresse() {
		return adresse;
	}
	
	public void setAdresse(String adresse) {
		this.adresse = adresse;
	}
	
	public String getQuartier() {
		return quartier;
	}
	
	public void setQuartier(String quartier) {
		this.quartier = quartier;
	}
	
	public String getInfo()
	{
		return this.adresse + " " + this.quartier + " ";
	}

    public double getDistance(double latitude, double longitude)
    {

       /* Geocoder coder = new Geocoder(ServicesManager.getInstance().getContext());

        List<Address> adresses;
        try
        {
            adresses = coder.getFromLocationName(this.adresse + " " + this.getQuartier() + " ,QC", 1);

            if(adresses == null)
            {
                return -1;
            }

            Address location = adresses.get(0);

            //System.out.println(String.format("Current location: %1$f, %2$f", location.getLatitude(), location.getLongitude()));
            //System.out.println(String.format("Passed location: %1$f, %2$f", latitude, longitude));
*/

            double dlon = Math.toRadians(longitude - this.longitude);
            double dlat = Math.toRadians(latitude - this.latitude);
            double a = Math.sin(dlat / 2) * Math.sin(dlat / 2) +
                    Math.cos(Math.toRadians(latitude)) * Math.cos(Math.toRadians(this.latitude)) *
                            Math.sin(dlon / 2) * Math.sin(dlon / 2);
            double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
            double d = 6373 * c;

            //System.out.println(d);

            return d;
       /* } catch (Exception e) {
           // e.printStackTrace();
        }

        return -1;*/
    }
}
