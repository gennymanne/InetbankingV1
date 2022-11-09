package com.intebanking.utilities;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class ReadConfig {

    Properties pr ;

    public ReadConfig(){
    File f= new File("./src/main/resources/configuration/config.properties");

    FileInputStream fi;


        try {
            fi = new FileInputStream(f);
            pr =new Properties();
            pr.load(fi);
        }  catch (Exception e) {
            System.out.println("Exception is : "+e.getMessage());
        }
    }
     public String getApplicatonUrl(){
        String url = pr.getProperty("baseUrl");
        return url;
     }

    public String getUserName(){
        String username = pr.getProperty("uname");
        return username;
    }

    public String getPassword(){
        String password = pr.getProperty("pwd");
        return password;
    }

    public String getChromepath(){
        String browserpath = pr.getProperty("chromepath");
        return browserpath;
    }



}
