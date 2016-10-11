package com.example.online;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;

public class jason_appointment extends Activity
{
    String JSON_STRING;
    String json_string;
    String json_la;

    @Override
    protected void onCreate(Bundle savedInstanceState)

        {

            super.onCreate(savedInstanceState);
            setContentView(R.layout.jason_appointment_layout);


        }

    public void getJSON(View view)

    {
        json_la = getIntent().getExtras().getString("json_born");
        new BTask().execute(json_la);
    }

    class BTask extends AsyncTask<String,Void,String>
    {
       String json_url;

        @Override
        protected void onPreExecute()
        {
                json_url = "http://lapla.net23.net/json_get_data_appointments.php";
        }




        @Override
        protected String doInBackground(String... args)
        {
            String check;
            check=args[0];
            try
            {
                URL url = new URL(json_url);
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();



                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoOutput(true);
                //httpURLConnection.setDoInput(true);
                OutputStream OS = httpURLConnection.getOutputStream();
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(OS, "UTF-8"));
                String data_string = URLEncoder.encode("check", "UTF-8") + "=" + URLEncoder.encode(check, "UTF-8");
                bufferedWriter.write(data_string);
                bufferedWriter.flush();
                bufferedWriter.close();
                OS.close();




                InputStream inputStream = httpURLConnection.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
                StringBuilder stringBuilder = new StringBuilder();
                while ((JSON_STRING = bufferedReader.readLine()) != null)
                {
                    stringBuilder.append(JSON_STRING + "\n");
                }
                bufferedReader.close();
                inputStream.close();
                httpURLConnection.disconnect();
                return stringBuilder.toString().trim();

            }
            catch (MalformedURLException e)
            {
                e.printStackTrace();
            }
            catch (IOException e)
            {
                e.printStackTrace();
            }
            return null;
        }





       @Override
        protected void onProgressUpdate(Void... values) {
            try {
                Toast.makeText(jason_appointment.this, "start onprogress", Toast.LENGTH_LONG).show();

                super.onProgressUpdate(values);
            } catch (Exception e) {
                Toast.makeText(jason_appointment.this, e.getMessage(), Toast.LENGTH_LONG).show();

            }
        }

        @Override
        protected void onPostExecute(String result) {
            try {
                Toast.makeText(jason_appointment.this, "start on_post_execute", Toast.LENGTH_LONG).show();

                TextView textView = (TextView) findViewById(R.id.tammy1);
                textView.setText(result);
                json_string=result;
            }
             catch (Exception e) {
                Toast.makeText(jason_appointment.this, e.getMessage(), Toast.LENGTH_LONG).show();

            }

        }
        public void parseJSON(View view)
        {
            if(json_string==null)
            {
                Toast.makeText(getApplicationContext(),"First Get JSON", Toast.LENGTH_SHORT).show();

            }
            else
            {
                Intent intent=new Intent(this,displaylistview1.class);
                intent.putExtra("json data",json_string);
                startActivity(intent);
            }

        }

    }
























































    public void parseJSON(View view) {
        if (json_string == null)
        {
            Toast.makeText(getApplicationContext(), "first get json", Toast.LENGTH_LONG).show();
        }
        else
        {
            Intent intent = new Intent(this, DisplayListView.class);
            intent.putExtra("json_data",json_string);
            startActivity(intent);

        }
    }
}
