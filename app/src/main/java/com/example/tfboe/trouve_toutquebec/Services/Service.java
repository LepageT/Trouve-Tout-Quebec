package com.example.tfboe.trouve_toutquebec.Services;

import android.os.Parcelable;

public abstract class Service{

	protected int id;
	protected String type;
	protected String name;
    protected double longitude;
    protected double latitude;


    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(float longitude) {
        this.longitude = longitude;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(float latitude) {
        this.latitude = latitude;
    }

	public String getName()
	{
		return this.name;
	}
	
	public void setName(String name)
	{
		this.name = name;
	}
	
	public String getType()
	{
		return this.type;
	}
	
	public void setType(String type)
	{
		this.type = type;
	}
	
	public int getId()
	{
		return this.id;
	}
	
	public void setId(int id)
	{
		this.id = id;
	}
	
	public String getInfo()
	{
		return null;
	}

    public double getDistance(double latitude, double longitude) {return -1; }
}
