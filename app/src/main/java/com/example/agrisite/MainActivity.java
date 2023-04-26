
package com.example.agrisite;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.StrictMode;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class MainActivity extends AppCompatActivity {

    private TextView TextFetch;

    private static String ip = "192.168.1.39";
    private static String port = "1433";
    private static String ClassName ="net.sourceforge.jtds.jdbc.Driver";
    private static String database = "FOR_TEST2";
    private static String username="root";
    private static String password="root123";
    private static String url = "jdbc:jtds:sqlserver://"+ip+":"+port+"/"+database;

    private Connection connection=null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextFetch = findViewById(R.id.TextFetch);

        //ENSURES THAT OUR CONNECTION IS A SET PATTERN

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        try {
            Class.forName(ClassName);
            connection = DriverManager.getConnection(url,username,password);
            TextFetch.setText("CONNECTION SUCCESS");

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            //throw new RuntimeException(e);
            TextFetch.setText("CONNECTION ERROR");

        } catch (SQLException e) {
            //throw new RuntimeException(e);
            e.printStackTrace();
            TextFetch.setText("FAILURE");
        }
    }

    public void SQLBUTTON(View view){

        if(connection!=null) {
            Statement statement;
            try {
                statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery("SELECT*FROM TEST_TABLE2");

                while (resultSet.next()) {
                    TextFetch.setText(resultSet.getString(1));
                }
            } catch (SQLException e) {
                //throw new RuntimeException(e);
                e.printStackTrace();
            }
        }
         else{
             TextFetch.setText("Connection is null");
            }
    }
}
