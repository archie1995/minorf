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

public class Addinfo_history extends Activity {

    EditText Idd,Dated,Hnamed,Hidd,Dnamed,Dised,Sred,Medid;
    String  idd,dated,hnamed,hidd,dnamed,dised,sred,medid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_info_history_layout);


        Idd=(EditText)findViewById(R.id.et_id);
        Dated=(EditText)findViewById(R.id.et_date);
        Hnamed=(EditText)findViewById(R.id.et_hname);
        Hidd=(EditText)findViewById(R.id.et_hid);
        Dnamed=(EditText)findViewById(R.id.et_dname);
        Dised=(EditText)findViewById(R.id.et_dise);
        Sred=(EditText)findViewById(R.id.et_sre);
        Medid=(EditText)findViewById(R.id.et_medi);


    }

    public void saveInfo(View view)
    {
        idd=Idd.getText().toString();
        dated=Dated.getText().toString();
        hnamed=Hnamed.getText().toString();
        hidd=Hidd.getText().toString();
        dnamed=Dnamed.getText().toString();
        dised=Dised.getText().toString();
        sred=Sred.getText().toString();
        medid=Medid.getText().toString();

        BackgroundTask backgroundTask=new BackgroundTask();
        backgroundTask.execute(idd,dated,hnamed,hidd,dnamed,dised,sred,medid);
        finish();
    }












    class BackgroundTask extends AsyncTask<String,Void,String>
    {

        String add_info_url;
        @Override
        protected void onPreExecute()
        {
            add_info_url="http://lapla.net23.net/add_info_prescriptions.php";

        }

        @Override
        protected String doInBackground(String... args)
        {
            String  idd,dated,hnamed,hidd,dnamed,dised,sred,medid;
            idd=args[0];
            dated=args[1];
            hnamed=args[2];
            hidd=args[3];
            dnamed=args[4];
            dised=args[5];
            sred=args[6];
            medid=args[7];

            //network operations


            try {
                URL url = new URL(add_info_url);
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoOutput(true);

                //httpURLConnection.setDoInput(true);
                OutputStream OS = httpURLConnection.getOutputStream();
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(OS, "UTF-8"));
                String data_string = URLEncoder.encode("adhar_id", "UTF-8") + "=" + URLEncoder.encode(idd, "UTF-8") + "&" +
                                     URLEncoder.encode("date", "UTF-8") + "=" + URLEncoder.encode(dated, "UTF-8")+"&"+
                                     URLEncoder.encode("hospital_name", "UTF-8") + "=" + URLEncoder.encode(hnamed, "UTF-8") +"&"+
                                     URLEncoder.encode("hospital_id", "UTF-8") + "=" + URLEncoder.encode(hidd, "UTF-8") +"&"+
                                     URLEncoder.encode("doctor_name", "UTF-8") + "=" + URLEncoder.encode(dnamed, "UTF-8") +"&"+
                                     URLEncoder.encode("diseases", "UTF-8") + "=" + URLEncoder.encode(dised, "UTF-8") +"&" +
                                     URLEncoder.encode("specail_remarks", "UTF-8") + "=" + URLEncoder.encode(sred, "UTF-8")+"&" +
                                     URLEncoder.encode("medication", "UTF-8") + "=" + URLEncoder.encode(medid, "UTF-8") ;
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

//bieber


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
