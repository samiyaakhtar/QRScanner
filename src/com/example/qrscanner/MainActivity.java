package com.example.qrscanner;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.support.v7.app.ActionBarActivity;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;
import android.support.v7.app.ActionBar;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.os.Build;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity implements OnClickListener
{
	private Button scanBtn;
	private TextView formatTxt, contentTxt;
	MySpreadsheet sheet;
	String url1 ="https://spreadsheets.google.com/tq?tqx=out:tq?tqx=out:json&tq=select+B+where+(+G+%3D+";
	
	String url2 = ")&key=1ueaft1tUCYssK_ucANSQbhmF7At09lv1MqSapH6T_Gc&gid=2";	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		scanBtn = (Button)findViewById(R.id.scan_button);
		formatTxt = (TextView)findViewById(R.id.scan_format);
		contentTxt = (TextView)findViewById(R.id.scan_content);
		
		sheet = new MySpreadsheet(this);
		
		scanBtn.setOnClickListener(this);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	/**
	 * A placeholder fragment containing a simple view.
	 */
	public static class PlaceholderFragment extends Fragment {

		public PlaceholderFragment() {
		}

		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container,
				Bundle savedInstanceState) {
			View rootView = inflater.inflate(R.layout.fragment_main, container,
					false);
			return rootView;
		}
	}

	public void onClick(View v){
		if(v.getId()==R.id.scan_button){
			//scan
			IntentIntegrator scanIntegrator = new IntentIntegrator(this);
			scanIntegrator.initiateScan();
			}
		}
	public void onActivityResult(int requestCode, int resultCode, Intent intent) {
		//retrieve scan result
		try
		{
		IntentResult scanningResult = IntentIntegrator.parseActivityResult(requestCode, resultCode, intent);
		if (scanningResult != null) {
			//we have a result
			String scanContent = scanningResult.getContents();
			String scanFormat = scanningResult.getFormatName();
			
			formatTxt.setText("FORMAT: " + scanFormat);
			contentTxt.setText("CONTENT: " + scanContent);
			
			sheet.verifyScan(scanContent);
			
			/* Json stuff */
			/*
			String finalurl = url1 + scanContent + url2;
			Json getJsonFromUrl = new Json();
			getJsonFromUrl.execute(finalurl);
			
			JSONObject jsonparse = getJsonFromUrl.get();
			JSONArray rows = null;
			try {
				 rows = jsonparse.getJSONObject("table").getJSONArray("rows");
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			if (rows.length() == 0){
				Toast toast = Toast.makeText(getApplicationContext(), 
				        "Invalid QR! Cannot Enter!", Toast.LENGTH_SHORT);
				    toast.show();
			}
			else {
				Toast toast = Toast.makeText(getApplicationContext(), 
				        "Entry is legal!", Toast.LENGTH_SHORT);
				    toast.show();
			}
			*/
		//	sheet.verifyScan(scanContent);
			/* ArraySolution 
			if( ArraySolution.isPersonAuthorizedToEnter(scanContent)) {
				Toast toast = Toast.makeText(getApplicationContext(), 
				        "Entry is legal!", Toast.LENGTH_SHORT);
				    toast.show();
			}
			else
			{
				Toast toast = Toast.makeText(getApplicationContext(), 
				        "Duplicate QR! Cannot Enter!", Toast.LENGTH_SHORT);
				    toast.show();
			}
			*/
		}
		else{
		    Toast toast = Toast.makeText(getApplicationContext(), 
		        "No scan data received!", Toast.LENGTH_SHORT);
		    toast.show();
		}
	}
		catch(Exception e){
			
			
		}
	}

}
