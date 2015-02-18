package com.example.tfboe.trouve_toutquebec.Services;

import android.content.Context;
import android.content.res.AssetManager;
import android.location.Address;
import android.location.Geocoder;
import android.os.Environment;

import com.example.tfboe.trouve_toutquebec.Parser.ToiletteParser;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Thomas on 2015-02-12.
 */
public class GetLocation {

    public GetLocation(Context context)
    {
        AssetManager assetManager = context.getAssets();
        BufferedReader br = null;

        String line = "";
        String cvsSplitBy = "\\|";
        int id = 0;

        try {
            InputStream is = assetManager.open("files/TOILETTES.CSV");
            InputStreamReader isr = new InputStreamReader(is);
            br = new BufferedReader(isr);
            br.readLine();


            File file = new File(Environment.getExternalStorageDirectory() + "/newToilettes.csv");
            FileOutputStream fos = new FileOutputStream(file);

            fos.write(br.readLine().getBytes());

            br = new BufferedReader(isr);
            while ((line = br.readLine()) != null) {

               fos.write(getNewLineWithLatLng(line, context).getBytes());

            }

            fos.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (br != null) {
                try {
                    br.close();

                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

    }

    private String getNewLineWithLatLng(String address, Context context)
    {
        String[] service = address.split("\\|");

        Geocoder coder = new Geocoder(ServicesManager.getInstance().getContext());

        List<Address> adresses;
        try
        {
            adresses = coder.getFromLocationName(service[2] + " " + service[3] + " " + service[4] + " ,QC", 1);

            if(adresses == null)
            {
                return address + "|1|1";
            }

            Address location = adresses.get(0);

            return address + "|" + location.getLatitude() + "|" + location.getLongitude() + "\n";


        } catch (Exception e) {
             e.printStackTrace();
        }
        return address + "|0|0\n";

    }
}
