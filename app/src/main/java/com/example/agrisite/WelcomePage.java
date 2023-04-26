package com.example.agrisite;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import java.sql.Connection;
import io.github.muddz.styleabletoast.StyleableToast;

public class WelcomePage extends AppCompatActivity {

    EditText username, password;
    TextView signupText;
    Button btnLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome_page);

        username = findViewById(R.id.username);
        password = findViewById(R.id.password);
        btnLogin = findViewById(R.id.btnLogin);
        signupText = findViewById(R.id.signupText);

        signupText.setOnClickListener(view -> {
            Intent i = new Intent(this, FieldOfficerRegistration.class);
            startActivity(i);
        });

        btnLogin.setOnClickListener(view -> {
            if(username.getText().toString().equals("user") && password.getText().toString().equals("1234")){
                //Toast.makeText(WelcomePage.this, "Login Success !", Toast.LENGTH_LONG).show();
                StyleableToast.makeText(this, "Login Success!", Toast.LENGTH_LONG, R.style.SuccessToast).show();

                Intent i = new Intent(this, AdminDashboard.class);
                startActivity(i);

            }else{
                //Toast.makeText(WelcomePage.this, "Login Failed !", Toast.LENGTH_LONG).show();
                StyleableToast.makeText(this, "Login Failed!", Toast.LENGTH_LONG, R.style.FailToast).show();

            }
        });
    }

}