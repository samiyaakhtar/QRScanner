package com.example.qrscanner;
import android.app.Activity;
import android.os.AsyncTask;

import com.google.gdata.client.authn.oauth.*;
import com.google.gdata.client.spreadsheet.SpreadsheetService;
import com.google.gdata.data.*;
import com.google.gdata.data.batch.*;
import com.google.gdata.data.spreadsheet.ListEntry;
import com.google.gdata.data.spreadsheet.ListFeed;
import com.google.gdata.data.spreadsheet.SpreadsheetEntry;
import com.google.gdata.data.spreadsheet.SpreadsheetFeed;
import com.google.gdata.util.*;

import java.io.IOException;
import java.net.*;
import java.util.*;


public class MySpreadsheet {
	
	private String GOOGLE_ACCOUNT_USERNAME = "samiyaakhtar7@gmail.com"; 
	private String GOOGLE_ACCOUNT_PASSWORD = "<censored>"; 
	
	private MainActivity m_activity;

	private String SPREADSHEET_URL = "https://docs.google.com/spreadsheets/d/1yd-R2YhoznZBWlPeEY-ZU9tcrENYpxIA3-VeYsFmIEk/edit#gid=479264020";
	
	private class checkSpreadsheet extends AsyncTask<String, Void, Boolean>{

		@Override
		protected Boolean doInBackground(String... params) {
			// TODO Auto-generated method stub
			try
			{
		        System.out.println("Hi1");
		        
				SpreadsheetService client = new SpreadsheetService("Print Google Spreadsheet Demo");
				if( client != null ){
					client.setUserCredentials(GOOGLE_ACCOUNT_USERNAME, GOOGLE_ACCOUNT_PASSWORD);
			        
					URL metafeedUrl = new URL(SPREADSHEET_URL);
			        SpreadsheetFeed feed = client.getFeed(metafeedUrl, SpreadsheetFeed.class);
			        
			        List<SpreadsheetEntry> spreadsheets = feed.getEntries();
			        for (SpreadsheetEntry service : spreadsheets) {             
			            System.out.println(service.getTitle().getPlainText());
			       }
			        
			        System.out.println("YOOOO");
				}

			}
			catch(Exception e){
		        System.out.println(e);
		    }
			return null;
		}
		
		@Override
	    protected void onPostExecute(Boolean result) {
			if (result) {
				
			}
	    }
	}
	
	public MySpreadsheet(MainActivity activity)
	{
		m_activity = activity;
		
	}
	public boolean verifyScan(String scannedContent) {
		
		new checkSpreadsheet().execute(scannedContent);
        return true;
	}
	
	
}
