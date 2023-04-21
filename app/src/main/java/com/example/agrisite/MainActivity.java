
package com.example.agrisite;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.StrictMode;
import android.util.Log;
import java.sql.Connection;
import java.sql.DriverManager;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //REFERENCES FOR XML

        //METHODS
    }
    //CODE FOR DATABASE CONNECTION

   /*@SuppressLint("NewApi")
        public Connection connectionclass(){


            Connection con = null;
            String ip="172.1.1.0", port="1433", username="sa", password="root123", databasename="AgriSite";
        StrictMode.ThreadPolicy tp = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(tp);

        try{
            Class.forName("net.sourceforge.jtds.jdbc.Driver");
            String connectionUrl="jdbc:jtds:sqlserver://"+ip+":"+port+";databasename="+databasename+";User="+username+";password="+password+";";
            con = DriverManager.getConnection(connectionUrl);

        }catch(Exception exception){
            Log.e("Error!", exception.getMessage());
        }
    return con;
    }*/
}
