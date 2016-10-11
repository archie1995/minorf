package com.example.online;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class displaylistview1 extends AppCompatActivity {

    String json_string;
    JSONObject jsonObject;
    JSONArray jsonArray;
    contactadapter1 contactadapter1;
    ListView listView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_displaylistview1);
        listView=(ListView)findViewById(R.id.listview);

        contactadapter1=new contactadapter1(this,R.layout.row_layout1);
        listView.setAdapter(contactadapter1);
        json_string=getIntent().getExtras().getString("json_data");

        //listview with custom adapter and custom listview
        try
        {
            jsonObject = new JSONObject(json_string);
            jsonArray=jsonObject.getJSONArray("server_response");
            int count=0;
            String name,email,mobile;

            while(count<jsonArray.length())
            {
                JSONObject JO=jsonArray.getJSONObject(count);
                name=JO.getString("name");
                email=JO.getString("email");
                mobile=JO.getString("mobile");
                contact1 contact1=new contact1(name,email,mobile);
                contactadapter1.add(contact1);
                count++;
            }

        }
        catch (JSONException e)
        {
            e.printStackTrace();
        }

    }
}
