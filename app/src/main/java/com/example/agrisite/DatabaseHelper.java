package com.example.agrisite;

import android.annotation.SuppressLint;
import android.os.StrictMode;
import android.util.Log;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseHelper {
    @SuppressLint("NewApi")
    /*@SuppressLint("NewApi") used to suppress warnings related to the use of API features that are not available in the minimum supported API level of
     the project.*/

    public Connection connectionclass(){

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        String ip = "192.168.1.41";
        String port = "1433";
        String Classes = "net.sourceforge.jtds.jdbc.Driver";
        String database = "AgriSite";
        String username = "root";
        String password = "root123";
        String url= "jdbc:jtds:sqlserver://"+ip+":"+port+"/"+database;
        Connection connection = null;

        try {
            Class.forName(Classes);
            connection = DriverManager.getConnection(url,username,password);
            Log.e("POSSIBLE","CONNECTION SUCCESS");

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            Log.e("IMPOSSIBLE","CONNECTION ERROR");
        } catch (SQLException e) {
            e.printStackTrace();
            Log.e("CAN'T","CONNECTION ERROR");
        }
        return connection;
    }
}
