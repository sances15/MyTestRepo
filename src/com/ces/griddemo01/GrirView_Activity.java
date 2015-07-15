package com.ces.griddemo01;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.protocol.ResponseDate;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

public class GrirView_Activity extends Activity {

	Context context;
	JSONObject responseJSON = null;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_grir_view_);

		context = GrirView_Activity.this;
	}

	@Override
	public void onBackPressed() {
		finish();
		overridePendingTransition(R.anim.backanimleft, R.anim.backanimright);
	}

	public void showToast(View sender){
		switch(sender.getId()){
		case R.id.buttonOne:
			makeServerRequest();
			break;
		case R.id.buttonTwo:
			Intent loginIntent = new Intent(GrirView_Activity.this,
					LoginView_Activity.class);
			startActivity(loginIntent);
			break;
		case R.id.buttonBack:
			Intent intent = new Intent(GrirView_Activity.this,
					DashBoard_Activity.class);
			startActivity(intent);
			break;
		default:
			break;
		}
	}
	
	public void makeServerRequest(){
		
		Toast.makeText(this, "Calling Async", Toast.LENGTH_SHORT).show();
		new SampleAsyncTask().execute();
	}
	
	class SampleAsyncTask extends AsyncTask<String, String, String>{
		
		ProgressDialog pdLoading = new ProgressDialog(GrirView_Activity.this);

	    @Override
	    protected void onPreExecute() {
	        super.onPreExecute();

	        //this method will be running on UI thread
	        pdLoading.setMessage("Loading...");
	        pdLoading.show();
	    }
		
		@Override
		protected String doInBackground(String... params) {
	        String response = null;
	        
//	        //POST Request
//			try {
//				HttpClient httpClient = new DefaultHttpClient();
//				HttpPost httpPost = new HttpPost("http://jsonplaceholder.typicode.com/comments");
//				HttpResponse httpRespones = httpClient.execute(httpPost);
//				ResponseHandler<String> responseHandler=new BasicResponseHandler();
//		        response = httpClient.execute(httpPost, responseHandler);
//		        
//	        } catch (Exception e) {
//	
//	            e.printStackTrace();
//	        }
	        
	        //GET Request
			try {
				HttpClient httpClient = new DefaultHttpClient();
				HttpGet httpGet = new HttpGet();
				URI website = new URI("http://jsonplaceholder.typicode.com/users");
				httpGet.setURI(website);
				HttpResponse httpRespones = httpClient.execute(httpGet);

		        HttpEntity entity = httpRespones.getEntity();
		        response = EntityUtils.toString(entity);  

	        } catch (Exception e) {
	
	            e.printStackTrace();
	        }
			return response;
		}
		
		protected void onPostExecute(String result) {
					
				try {
			        JSONArray jsonarray = new JSONArray(result);
			        List<String> responseData = new ArrayList<String>();
			        
			        for(int i=0; i<jsonarray.length(); i++){			        	
			            JSONObject jsnObj = jsonarray.getJSONObject(i);
			            String name = jsnObj.getString("name");
			            responseData.add(name);
			        } 
			        
					Intent intent = new Intent(GrirView_Activity.this,
							ListItem_Activity.class);
					intent.putStringArrayListExtra("Response", (ArrayList<String>) responseData);
					startActivity(intent);
					
				} catch (Exception e) {
					e.printStackTrace();
				}
				
		pdLoading.dismiss();
		}
	}
}
