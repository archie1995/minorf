package com.example.online;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {

    EditText e;
    String text;
    Button b1,b2;
    TextView textView;
    @Override
    protected void onCreate(Bundle savedInstanceState)

    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        b1=(Button)findViewById(R.id.b1);
        b2=(Button)findViewById(R.id.b2);
        textView=(TextView)findViewById(R.id.textView);
        ConnectivityManager connectivityManager=(ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo=connectivityManager.getActiveNetworkInfo();
        if(networkInfo!=null && networkInfo.isConnected())
        {
            textView.setVisibility(View.INVISIBLE);
        }
        else
        {
            b1.setEnabled(false);
            b2.setEnabled(false);
        }

        e=(EditText)findViewById(R.id.uiop);

    }

    public void addContact(View view)
    {
        startActivity(new Intent(this,Addinfo.class));
    }



    public void viewRegistration(View view)

    {
        try{
        Intent intent = new Intent(this, jason.class);
            text=e.getText().toString();

            intent.putExtra("json_born",text);
        startActivity(intent);
        Toast.makeText(MainActivity.this,"YOYYOYOYO",Toast.LENGTH_LONG).show();
    }
        catch (Exception e)
        {
            Toast.makeText(MainActivity.this,e.getMessage(),Toast.LENGTH_LONG).show();
        }
    }







    public void viewAppointment(View view)

    {
        try{
            Intent intent = new Intent(this, jason_appointment.class);
            text=e.getText().toString();

            intent.putExtra("json_born",text);
            startActivity(intent);
            Toast.makeText(MainActivity.this,"YOYYOYOYO",Toast.LENGTH_LONG).show();
        }
        catch (Exception e)
        {
            Toast.makeText(MainActivity.this,e.getMessage(),Toast.LENGTH_LONG).show();
        }
    }


    public void viewFullHistory(View view)

    {
        try{
            Intent intent = new Intent(this, jason_fullHistory.class);
            text=e.getText().toString();

            intent.putExtra("json_born",text);
            startActivity(intent);
            Toast.makeText(MainActivity.this,"YOYYOYOYO",Toast.LENGTH_LONG).show();
        }
        catch (Exception e)
        {
            Toast.makeText(MainActivity.this,e.getMessage(),Toast.LENGTH_LONG).show();
        }
    }


}
