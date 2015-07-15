package com.ces.griddemo01;

import org.json.JSONObject;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LoginView_Activity extends Activity implements OnClickListener {

	EditText edtUserName,edtPassWord,edtAccount;
	Button btnLogin;
	
	Context context;
	
	JSONObject userParam = new JSONObject();
	JSONObject authReq = new JSONObject();
	
	public static final String WEB_SERVICE_URL = "https://my.adlware.com/adlwareiphoneservice/adlwareiphone_new.svc/Authenticate";
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login_view_);
		
		context = LoginView_Activity.this;
		
		edtUserName = (EditText) findViewById(R.id.username);
		edtPassWord = (EditText) findViewById(R.id.password);
		edtAccount  = (EditText) findViewById(R.id.accountId);
		
		btnLogin = (Button) findViewById(R.id.login);
		btnLogin.setOnClickListener(this);
	}
	
	public void onClick(View actionSender){
		switch(actionSender.getId()){
		case R.id.login:
			getUserData();
			break;
		}
	}
	
	public void getUserData(){
		
		try {	
			userParam.put("UserId","Admin15066");
			userParam.put("Password","Offth3w@ll");
			userParam.put("AccountAccessId","15066");
			
			userParam.put("DeviceId","APA91bGlWgLKz_tM3YjPyQoDkGd4JZ7rQGBu9NAzFvv83B7wU-J-QVrZOHP3PW9vNx9hHLN3lA6JynWzl9yKMg2lmhQWqE9eX9rGYvNp9_JlNldeDiqHiJd9RWF_mW6mf4FphsfEvU7N_-NQ8imzmPALZZtjFycsWA");
			userParam.put("DeviceOS","ANDROID");

			authReq.put("authReq", userParam);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		System.out.println("authReq========" + authReq.toString());
		
		new MakeLoginRequest().execute();
	}
	
	private class MakeLoginRequest extends AsyncTask<String, String, String>{
		
		ProgressDialog loginLoading = new ProgressDialog(LoginView_Activity.this);

	    @Override
	    protected void onPreExecute() {
	        super.onPreExecute();

	        loginLoading.setMessage("Loading...");
	        loginLoading.show();
	    }
		@Override
		protected String doInBackground(String... params) {
			
			String response = null;

			return response;
		}
		
		protected void onPostExecute(String result) {
			System.out.print("RESPONSE RESULT ======> "+result);
			Toast.makeText(context, result, Toast.LENGTH_SHORT).show();
			loginLoading.dismiss();
		}
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.login_view_, menu);
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
}
