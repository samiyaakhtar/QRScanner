package com.example.qrscanner;
import com.google.gdata.client.authn.oauth.*;
import com.google.gdata.client.docs.*;
import com.google.gdata.client.spreadsheet.SpreadsheetService;
import com.google.gdata.data.*;
import com.google.gdata.data.batch.*;
import com.google.gdata.data.docs.*;
import com.google.gdata.data.spreadsheet.ListEntry;
import com.google.gdata.data.spreadsheet.ListFeed;
import com.google.gdata.util.*;

import java.io.IOException;
import java.net.*;
import java.util.*;


public class Spreadsheet {
	private String GOOGLE_ACCOUNT_USERNAME = "<gmail account>"; 
	private String GOOGLE_ACCOUNT_PASSWORD = "<password>"; 

	private String SPREADSHEET_URL = "<Google spreadsheet url>";

	public boolean verifyScan(String scannedContent) {
		try
		{
			String status="";
			
	        SpreadsheetService service = new SpreadsheetService("Print Google Spreadsheet Demo");
			
	     // Login and prompt the user to pick a sheet to use.
	        service.setUserCredentials(GOOGLE_ACCOUNT_USERNAME,
	                GOOGLE_ACCOUNT_PASSWORD);
	
	        // Load sheet
	        URL metafeedUrl = new URL(SPREADSHEET_URL);
	        SpreadsheetEntry spreadsheet = service.getEntry(metafeedUrl,SpreadsheetEntry.class);
	        URL listFeedUrl = spreadsheet.getWorksheets().get(0).getListFeedUrl();
	        
	     // Print entries
	        ListFeed feed = service.getFeed(listFeedUrl, ListFeed.class);
	        
	        for (ListEntry entry : feed.getEntries()) {
	            System.out.println("new row");
	            for (String tag : entry.getCustomElements().getTags()) 
	            {
	                System.out.println("     " + tag + ": "
	                        + entry.getCustomElements().getValue(tag));
	                status=entry.getCustomElements().getValue(tag);

	            }
	        }
		}
		catch(Exception e){
	        System.out.println(e);
	    }
        return true;
	}
}
