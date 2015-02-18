package com.example.tfboe.trouve_toutquebec.Services;

import android.content.Context;

import com.example.tfboe.trouve_toutquebec.Parser.CulteParser;
import com.example.tfboe.trouve_toutquebec.Parser.Parser;
import com.example.tfboe.trouve_toutquebec.Parser.ToiletteParser;
import com.example.tfboe.trouve_toutquebec.UserRequest;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Thomas on 2015-02-10.
 */
public class ServicesManager {
    private static ServicesManager instance = null;
    private Context context;
    List<Service> requested = new ArrayList<Service>();

    protected ServicesManager() {
        // Exists only to defeat instantiation.
    }
    public static ServicesManager getInstance() {
        if(instance == null) {
            instance = new ServicesManager();
        }
        return instance;
    }

    public List<Service> getList()
    {
        return this.requested;
    }

    public Context getContext()
    {
        return  this.context;
    }
    public void executeRequest(UserRequest request, double latitude, double longitude, Context context)
    {
        int count = 0;
        this.context = context;
        requested = new ArrayList<Service>();
        Parser parser = null;
        switch (request.getService())
        {
            case "Toilette":
                parser = new ToiletteParser();
                break;

            case "Lieu de culte":
                parser = new CulteParser();
                break;
        }

        List<Service> services = parser.parseFile(context);

        for(Service s : services)
        {
            if(request.isNear)
            {
                if(s.getDistance(latitude, longitude) < 10)
                {
                    requested.add(s);
                }
            }
            else
            {
                if(s.getDistance(latitude, longitude) > 10)
                {
                    requested.add(s);
                }
            }
            count ++;
        }

        System.out.println("Total count " + count);

        //new GetLocation(this.context);

    }
}
