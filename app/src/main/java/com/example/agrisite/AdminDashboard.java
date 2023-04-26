package com.example.agrisite;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

public class AdminDashboard extends AppCompatActivity {

    ImageView ImgFO, ImgServiceTask, ImageLocation, Image_Calendar, ImgDataAnalytics, ImgAgriProducts;
    CardView RegisterCard, ServiceCard, LocationCard, SchedulerCard, AnalyticsCard,ProductsCard;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_dashboard);

        //References to the Views in XML

        ImgFO = findViewById(R.id.ImgFO);
        ImgServiceTask = findViewById(R.id.ImgServiceTask);
        ImageLocation = findViewById(R.id.ImageLocation);
        Image_Calendar = findViewById(R.id.Image_Calendar);
        ImgDataAnalytics = findViewById(R.id.ImgDataAnalytics);
        ImgAgriProducts = findViewById(R.id.ImgAgriProducts);

        //setOnClickListener on Image
        ImgServiceTask.setOnClickListener(view -> {
            Intent i = new Intent(this, TaskDetails.class);
            startActivity(i);
        });

        //setOnClickListener on Image
        ImgFO.setOnClickListener(view -> {
            Intent i = new Intent(this, FieldOfficerRegistration.class);
            startActivity(i);
        });

    }
}