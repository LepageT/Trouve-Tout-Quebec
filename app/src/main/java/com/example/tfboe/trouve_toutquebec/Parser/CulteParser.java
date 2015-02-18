package com.example.tfboe.trouve_toutquebec.Parser;

import android.content.Context;
import android.content.res.AssetManager;
import android.graphics.Typeface;
import android.os.Environment;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import com.example.tfboe.trouve_toutquebec.Services.LieuCulte;
import com.example.tfboe.trouve_toutquebec.Services.Service;

public class CulteParser extends Parser{

	public List<Service> parseFile(Context context)
	{
		BufferedReader br = null;
		String line = "";
		String cvsSplitBy = "\\|";
		int id = 0;
        AssetManager assetManager = context.getAssets();

        List<Service> cultes = new ArrayList<Service>();
		try {
            InputStream is = assetManager.open("files/LIEU_CULTE.CSV");
            InputStreamReader isr = new InputStreamReader(is);
			br = new BufferedReader(isr);
			br.readLine();

			while((line = br.readLine()) != null)
			{
				String[] service = line.split(cvsSplitBy);
				
				LieuCulte c = new LieuCulte();
				c.setId(id);
				c.setType("culte");
				c.setName(service[0]);
				c.setQuartier(service[7]);
				c.setReligion(service[5]);
				c.setLongitude(Float.parseFloat(service[8]));
				c.setLatitude(Float.parseFloat(service[9]));
				
				cultes.add(c);
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
		
		return cultes;
	}
}
