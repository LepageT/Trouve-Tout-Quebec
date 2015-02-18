package com.example.tfboe.trouve_toutquebec.Services;


import android.content.ClipData;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;

import com.example.tfboe.trouve_toutquebec.MainActivity;

public class LieuCulte extends Service {


	private String religion;
	private String quartier;

    public LieuCulte() {
    }
	
	public String getReligion() {
		return religion;
	}
	
	public void setReligion(String religion) {
		this.religion = religion;
	}
	
	public String getQuartier() {
		return quartier;
	}
	
	public void setQuartier(String quartier) {
		this.quartier = quartier;
	}
	
	public String getInfo()
	{
		return this.religion + " " + this.quartier + " " + this.longitude + " " + this.latitude;
	}

    public double getDistance(double latitude, double longitude)
    {
        System.out.println(String.format("Current location: %1$f, %2$f", this.latitude, this.longitude));
        System.out.println(String.format("Passed location: %1$f, %2$f", latitude, longitude));


        double dlon = Math.toRadians(longitude - this.longitude);
        double dlat = Math.toRadians(latitude - this.latitude);
        double a = Math.sin(dlat / 2) * Math.sin(dlat / 2) +
                Math.cos(Math.toRadians(latitude)) * Math.cos(Math.toRadians(this.latitude)) *
                        Math.sin(dlon / 2) * Math.sin(dlon / 2);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
        double d = 6373 * c;

        System.out.println(d);

        return d;
    }

}
