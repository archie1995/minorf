package com.example.online;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class displaylistview2 extends AppCompatActivity {

    String json_string;
    JSONObject jsonObject;
    JSONArray jsonArray;
    contactadapter2 contactadapter2;
    ListView listView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_displaylistview2);
        listView=(ListView)findViewById(R.id.listview);

        contactadapter2=new contactadapter2(this,R.layout.row_layout2);
        listView.setAdapter(contactadapter2);
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
                contact2 contact2=new contact2(name,email,mobile);
                contactadapter2.add(contact2);
                count++;
            }

        }
        catch (JSONException e)
        {
            e.printStackTrace();
        }

    }
}
