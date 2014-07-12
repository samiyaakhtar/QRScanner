package com.example.qrscanner;
import android.app.Activity;
import android.os.AsyncTask;

import com.google.gdata.client.authn.oauth.*;
import com.google.gdata.client.authn.oauth.OAuthParameters.OAuthType;
import com.google.gdata.client.spreadsheet.SpreadsheetService;
import com.google.gdata.data.*;
import com.google.gdata.data.batch.*;
import com.google.gdata.data.spreadsheet.CustomElementCollection;
import com.google.gdata.data.spreadsheet.ListEntry;
import com.google.gdata.data.spreadsheet.ListFeed;
import com.google.gdata.data.spreadsheet.SpreadsheetEntry;
import com.google.gdata.data.spreadsheet.SpreadsheetFeed;
import com.google.gdata.util.*;
import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeFlow;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.jackson.JacksonFactory;
import com.google.gdata.client.authn.oauth.GoogleOAuthParameters; 
//import com.google.gdata.client.docs.*;
import com.google.gdata.client.authn.oauth.*;

import java.io.IOException;
import java.net.*;
import java.util.*;


public class MySpreadsheet {
	
	
	private MainActivity m_activity;

	public static String REDIRECT_URI = "urn:ietf:wg:oauth:2.0:oob";
	
	private class checkSpreadsheet extends AsyncTask<String, Void, Boolean>{
		
		@Override
		protected Boolean doInBackground(String... params) {
			// TODO Auto-generated method stub
			/*
			try
			{	        

		        GoogleOAuthParameters oauthParameters = new GoogleOAuthParameters();
		        oauthParameters.setOAuthConsumerKey (Config.CLIENT_ID);
		        oauthParameters.setOAuthConsumerSecret (Config.CLIENT_SECRET);
		        oauthParameters.setOAuthType (OAuthType.TWO_LEGGED_OAUTH);
		        oauthParameters.setScope ("https://spreadsheets.google.com/feeds/");
	
				SpreadsheetService client = new SpreadsheetService("QRScanner");
				client.useSsl();
				client.setOAuthCredentials (oauthParameters, new OAuthHmacSha1Signer ());


				if( client != null ){
					client.setUserCredentials(Config.GOOGLE_ACCOUNT_USERNAME, Config.GOOGLE_ACCOUNT_PASSWORD);
			        
					URL metafeedUrl = new URL(Config.SPREADSHEET_URL);
					
					SpreadsheetEntry spreadsheet = client.getEntry(metafeedUrl,SpreadsheetEntry.class);
			        URL listFeedUrl = spreadsheet.getWorksheets().get(0).getListFeedUrl();

			        // Print entries
			        ListFeed feed = client.getFeed(listFeedUrl, ListFeed.class);

			        for (ListEntry entry : feed.getEntries()) {
			            System.out.println("new row");
			            for (String tag : entry.getCustomElements().getTags()) {
			                System.out.println("     " + tag + ": "
			                        + entry.getCustomElements().getValue(tag));

			            }
			        }
			        
			        System.out.println("YOOOO");
				}

			}
			catch(Exception e){
		        System.out.println(e);
		    }
			return null;
			*/
		    try {
		        GoogleOAuthParameters oauthParameters = new GoogleOAuthParameters();
		        oauthParameters.setOAuthConsumerKey (Config.CLIENT_ID);
		        oauthParameters.setOAuthConsumerSecret (Config.CLIENT_SECRET);
		        oauthParameters.setOAuthType (OAuthType.TWO_LEGGED_OAUTH);
		        oauthParameters.setScope ("https://spreadsheets.google.com/feeds/");
		        
				SpreadsheetService service = new SpreadsheetService("QRScanner");
				service.useSsl();
				service.setOAuthCredentials (oauthParameters, new OAuthHmacSha1Signer ());
			
		      // Notice that the url ends
		      // with default/public/values.
		      // That wasn't obvious (at least to me)
		      // from the documentation.
		      String urlString = Config.SPREADSHEET_PUBLIC_URL;

		      // turn the string into a URL
		      URL url = new URL(urlString);

		      // You could substitute a cell feed here in place of
		      // the list feed
		      ListFeed feed = service.getFeed(url, ListFeed.class);

		      for (ListEntry entry : feed.getEntries()) {
		        CustomElementCollection elements = entry.getCustomElements();
		        String name = elements.getValue("fullname");
		        System.out.println(name);
		        String number = elements.getValue("emailaddress");
		        System.out.println(number);
		      }
		    } catch (Exception e) {
		      e.printStackTrace();
		    }
		    
		    return true;
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
