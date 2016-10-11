package com.example.online;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;

public class Addinfo extends Activity {
    EditText Name,Email,Mobile;
    String  name,email,mobile;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_info_layout);
        Name=(EditText)findViewById(R.id.et_name);
        Email=(EditText)findViewById(R.id.et_email);
        Mobile=(EditText)findViewById(R.id.et_mob);
    }

    public void saveInfo(View view)
    {
        name=Name.getText().toString();
        email=Email.getText().toString();
        mobile=Mobile.getText().toString();
        BackgroundTask backgroundTask=new BackgroundTask();
        backgroundTask.execute(name,email,mobile);
        finish();
    }












    class BackgroundTask extends AsyncTask<String,Void,String>
    {

        String add_info_url;
        @Override
        protected void onPreExecute()
        {
            add_info_url="http://lapla.net23.net/add_info.php";

        }

        @Override
        protected String doInBackground(String... args)
        {
            String name,email,mobile;
            name=args[0];
            email=args[1];
            mobile=args[2];

            //network operations


            try {
                URL url = new URL(add_info_url);
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoOutput(true);

                //httpURLConnection.setDoInput(true);
                OutputStream OS = httpURLConnection.getOutputStream();
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(OS, "UTF-8"));
                String data_string = URLEncoder.encode("name", "UTF-8") + "=" + URLEncoder.encode(name, "UTF-8") + "&" +
                        URLEncoder.encode("email", "UTF-8") + "=" + URLEncoder.encode(email, "UTF-8") + "&" +
                        URLEncoder.encode("mobile", "UTF-8") + "=" + URLEncoder.encode(mobile, "UTF-8");
                bufferedWriter.write(data_string);
                bufferedWriter.flush();
                bufferedWriter.close();
                OS.close();
                InputStream IS = httpURLConnection.getInputStream();
                IS.close();
                //httpURLConnection.connect();
                httpURLConnection.disconnect();

                return "one row of data inserted....";
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

            return null;
        }




        @Override
        protected void onProgressUpdate(Void... values) {
            super.onProgressUpdate(values);
        }

        @Override
        protected void onPostExecute(String result) {
            Toast.makeText(getApplicationContext(),result,Toast.LENGTH_LONG).show();
        }

    }
}
