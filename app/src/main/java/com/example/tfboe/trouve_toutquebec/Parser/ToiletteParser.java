package com.example.tfboe.trouve_toutquebec.Parser;

import android.content.Context;
import android.content.res.AssetManager;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import com.example.tfboe.trouve_toutquebec.Services.Service;
import com.example.tfboe.trouve_toutquebec.Services.Toilette;

public class ToiletteParser extends Parser{

	
	private List<Service> services;
	
	public List<Service> parseFile(Context context) {

        AssetManager assetManager = context.getAssets();
        BufferedReader br = null;

        String line = "";
        String cvsSplitBy = "\\|";
        int id = 0;

        this.services = new ArrayList<Service>();
        try {
            InputStream is = assetManager.open("files/newToilettes.csv");
            InputStreamReader isr = new InputStreamReader(is);
            br = new BufferedReader(isr);
            while ((line = br.readLine()) != null) {
                String[] service = line.split(cvsSplitBy);

                Toilette t = new Toilette();
                t.setId(id);
                t.setType("toilette");
                t.setName(service[0]);
                t.setAdresse(service[2] + " " + service[3]);
                t.setQuartier(service[4]);
                t.setLatitude(Float.parseFloat(service[6]));
                t.setLongitude(Float.parseFloat(service[7]));
                this.services.add(t);
            }
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

        return this.services;
    }
}
