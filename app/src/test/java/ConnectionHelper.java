/*
import android.annotation.SuppressLint;
import android.os.StrictMode;
import android.util.Log;

import java.sql.Connection;
import java.sql.DriverManager;

public class ConnectionHelper {
    Connection con;
    String username, password, ip,port, database;

    @SuppressLint("NewApi")
    public Connection connectionClass(){
        username="sa";
        password="root123";
        ip = "172.1.1.0";
        port = "1433";
        database = "AgriSite";

        //Detect everything that's potentially suspect.
        StrictMode.ThreadPolicy tp = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(tp);

        Connection connection=null;
        String connectionURL=null;

        try{
            Class.forName("net.sourceforge.jtds.jdbc.Driver");
            con = DriverManager.getConnection(connectionURL);
            String connectionUrl="jdbc:jtds:sqlserver://"+ip+":"+port+";databasename="+database+";User="+username+";password="+password+";";
            con = DriverManager.getConnection(connectionUrl);

        }catch (Exception exception){
            Log.e("Error!", exception.getMessage());
        }
        return connection;
    }
}
*/
