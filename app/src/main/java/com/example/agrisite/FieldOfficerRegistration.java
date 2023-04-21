package com.example.agrisite;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
//import android.widget.ArrayAdapter;
//import android.widget.AdapterView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.Toast;
import java.util.Calendar;

public class FieldOfficerRegistration extends AppCompatActivity {

    EditText EditTxtFName, EditTxtLName, EditTxtEmail, EditTxtPassword, EditTxtCnfPassword;
    RadioButton radio_male, radio_female;
    //CheckBox checkbox_correct;
    Spinner spinner;
    DatePickerDialog datePickerDialog;
    Button dateButton, btnAgree;

    @SuppressWarnings("UnnecessarilyQualifiedInnerClassAccess")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_field_officer_registration);

        //Creating References for Views

        EditTxtFName = findViewById(R.id.EditTxtFName);
        EditTxtLName = findViewById(R.id.EditTxtLName);
        EditTxtEmail = findViewById(R.id.EditTxtEmail);
        EditTxtPassword = findViewById(R.id.EditTxtPassword);
        EditTxtCnfPassword = findViewById(R.id.EditTxtCnfPassword);

        radio_male = findViewById(R.id.radio_male);
        radio_female = findViewById(R.id.radio_female);

        //checkbox_correct = findViewById(R.id.checkbox_correct);

        spinner = findViewById(R.id.spinner1);
        btnAgree = findViewById(R.id.btnAgree);

        initDatePicker();
        dateButton = findViewById(R.id.BtnDatePicker);
        dateButton.setText(getTodayDate());

        /*ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,R.array.WorkingDistrict, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(this);*/

        btnAgree.setOnClickListener(view -> {

            //What happens after clicking the button comes here

            String firstName = EditTxtFName.getText().toString().trim();
            String lastName = EditTxtLName.getText().toString().trim();
            String email = EditTxtEmail.getText().toString().trim();
            String password = EditTxtPassword.getText().toString().trim();
            String confirm = EditTxtCnfPassword.getText().toString().trim();

            // Calling Methods
            CheckEmptyValues(firstName, lastName, email, password, confirm);
            validateEmail(email);
            validatePassword(password, confirm);

        });

    }

    private boolean CheckEmptyValues(String firstName, String lastName, String email, String password, String confirm) {

        if (TextUtils.isEmpty(firstName)) {
            EditTxtFName.setError("This field is required.");
            return false;
        }

        if (TextUtils.isEmpty(lastName)) {
            EditTxtLName.setError("This field is required.");
            return false;
        }

        if (TextUtils.isEmpty(email)) {
            EditTxtEmail.setError("This field is required.");
            return false;
        }

        if (TextUtils.isEmpty(password)) {
            EditTxtPassword.setError("This field is required.");
            return false;
        }

        if (TextUtils.isEmpty(confirm)) {
            EditTxtCnfPassword.setError("This field is required.");
            return false;
        }
        return true;
    }

    /*private void CheckEmptyValues(String firstName, String lastName, String email, String password, String confirm) {
        if(firstName.isEmpty()){
            Toast.makeText(FieldOfficerRegistration.this, "Please Enter the First Name.", Toast.LENGTH_SHORT).show();

        } else if (lastName.isEmpty()) {
            Toast.makeText(FieldOfficerRegistration.this, "Please Enter the Last Name", Toast.LENGTH_SHORT).show();

        } else if (email.isEmpty()) {
            Toast.makeText(FieldOfficerRegistration.this, "This field can't be empty.", Toast.LENGTH_SHORT).show();

        } else if (password.isEmpty()) {
            Toast.makeText(FieldOfficerRegistration.this, "This field can't be empty.", Toast.LENGTH_SHORT).show();

        } else if (confirm.isEmpty()) {
            Toast.makeText(FieldOfficerRegistration.this, "This field can't be empty.", Toast.LENGTH_SHORT).show();
        }
        else{
            Toast.makeText(this, "Created Account Successfully!", Toast.LENGTH_SHORT).show();
        }
    }*/

    //Email Validation
    private boolean validateEmail(String email) {

        if (TextUtils.isEmpty(email)) {
            EditTxtEmail.setError("This field is required.");
            return false;

        } else if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            EditTxtEmail.setError("Please enter a valid email address");
            return false;
        }
        return true;
    }

    private void validatePassword(String password, String confirm) {
        if(password.equals(confirm)){
            Toast.makeText(this, "Both Password Matches!", Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(this, "Password doesn't match!", Toast.LENGTH_SHORT).show();
        }
    }

    private String getTodayDate() {
        Calendar cal = Calendar.getInstance();
        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH);
        month = month+1;
        int day = cal.get(Calendar.DAY_OF_MONTH);
        return makeDateString(day, month, year);

    }

    private void initDatePicker() {
        DatePickerDialog.OnDateSetListener dateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                month = month+1;
                String date = makeDateString(day,month,year);
                dateButton.setText(date);
            }
        };

        Calendar cal = Calendar.getInstance();
        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH);
        int day = cal.get(Calendar.DAY_OF_MONTH);

        int style = AlertDialog.THEME_HOLO_LIGHT;
        datePickerDialog = new DatePickerDialog(this, style,dateSetListener,year, month,day);
    }

    private String makeDateString(int day, int month, int year) {
        return getMonthFormat(month) + " " + day + " " + year;
    }

    private String getMonthFormat(int month) {
        if(month == 1)
            return "JAN";
        if(month == 2)
            return "FEB";
        if(month == 3)
            return "MAR";
        if(month == 4)
            return "APR";
        if(month == 5)
            return "MAY";
        if(month == 6)
            return "JUN";
        if(month == 7)
            return "JUL";
        if(month == 8)
            return "AUG";
        if(month == 9)
            return "SEP";
        if(month == 10)
            return "OCT";
        if(month == 11)
            return "NOV";
        if(month == 12)
            return "DEC";

        //default should never happen
        return "JAN";
    }

    public void openDatePicker(View view) {
        datePickerDialog.show();
    }

   /* @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        String text = parent.getItemAtPosition(position).toString();
        Toast.makeText(parent.getContext(),text, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }*/
}