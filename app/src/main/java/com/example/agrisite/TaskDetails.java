package com.example.agrisite;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

public class TaskDetails extends AppCompatActivity {

    EditText descriptionBox, title;
    TextView txtDescription, txtLocation;
    Button btnComplete, btnRemove;
    Spinner spinner;
    DatePickerDialog datePickerDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task_details);

        //References to the XML

        title = findViewById(R.id.taskTitle);
        descriptionBox = findViewById(R.id.txtdescriptionBox);

        //Task Description Box
        descriptionBox.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                v.getParent().requestDisallowInterceptTouchEvent(true);
                switch (event.getAction() & MotionEvent.ACTION_MASK){
                    case MotionEvent.ACTION_UP:
                        v.getParent().requestDisallowInterceptTouchEvent(false);
                        break;
                }
                return false;
            }
        });

        //METHODS
        btnRemove.setOnClickListener(view -> {
            descriptionBox.setText("");
            title.setText("");
        });
    }
}

