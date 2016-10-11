package com.example.online;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class contact1
{
    private String name,email,mobile;



    public contact1(String name,String email,String mobile)
    {
        this.setName(name);
        this.setEmail(email);
        this.setMobile(mobile);
    }
    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
