package com.example.tfboe.trouve_toutquebec;

public class UserRequest {

	public boolean isNear;
	public String typeService;

	public UserRequest()
	{
        isNear = true;
	}
	
	public String getService() {
		return typeService;
	}
	
	public void setService(String service) {
		this.typeService = service;
	}
	
	public boolean isNear() {
		return isNear;
	}
	
	public void setNear(boolean isNear) {
		this.isNear = isNear;
	}
	
	public boolean isRequestComplete()
	{
        if(typeService != null)
            return true;
		return false;
	}

    public String toString()
    {
        return this.typeService + ": " + this.isNear;
    }
}

