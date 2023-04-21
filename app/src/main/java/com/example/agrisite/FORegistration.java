package com.example.agrisite;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.Toast;

public class FORegistration extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    EditText EditTxtFName, EditTxtLName, EditTxtBDate, EditTxtEmail, EditTxtPassword, EditTxtCnfPassword;
    RadioButton radio_male, radio_female;
    //CheckBox checkbox_correct;
    Spinner spinner;
    Button BtnAgree;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_foregistration);

        //Creating References for Views

        EditTxtFName = findViewById(R.id.EditTxtFName);
        EditTxtLName = findViewById(R.id.EditTxtLName);
        EditTxtBDate = findViewById(R.id.EditTxtBDate);
        EditTxtEmail = findViewById(R.id.EditTxtEmail);
        EditTxtPassword = findViewById(R.id.EditTxtPassword);
        EditTxtCnfPassword = findViewById(R.id.EditTxtCnfPassword);

        radio_male = findViewById(R.id.radio_male);
        radio_female = findViewById(R.id.radio_female);

        //checkbox_correct = findViewById(R.id.checkbox_correct);

        spinner = findViewById(R.id.spinner1);
        BtnAgree = findViewById(R.id.BtnAgree);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,R.array.WorkingDistrict, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(this);

        BtnAgree.setOnClickListener(view -> {

            //What happens after clicking the button comes here

            String firstName = EditTxtFName.getText().toString();
            String lastName = EditTxtLName.getText().toString();
            String birthday = EditTxtBDate.getText().toString();
            String email = EditTxtEmail.getText().toString();
            String password = EditTxtPassword.getText().toString();
            String confirm = EditTxtCnfPassword.getText().toString();

            // Calling Methods
            CheckEmptyValues(firstName, lastName, birthday, email,password, confirm);
            validateEmail(email);
            validatePassword(password, confirm);

        });
    }

    private void CheckEmptyValues(String firstName, String lastName, String birthday, String email, String password, String confirm){
        if(firstName.isEmpty()){
            Toast.makeText(FORegistration.this, "Please Enter the First Name.", Toast.LENGTH_SHORT).show();

        } else if (lastName.isEmpty()) {
            Toast.makeText(FORegistration.this, "Please Enter the Last Name", Toast.LENGTH_SHORT).show();

        }else if (birthday.isEmpty()) {
            Toast.makeText(FORegistration.this, "This field can't be empty.", Toast.LENGTH_SHORT).show();

        } else if (email.isEmpty()) {
            Toast.makeText(FORegistration.this, "This field can't be empty.", Toast.LENGTH_SHORT).show();

        } else if (password.isEmpty()) {
            Toast.makeText(FORegistration.this, "This field can't be empty.", Toast.LENGTH_SHORT).show();

        } else if (confirm.isEmpty()) {
            Toast.makeText(FORegistration.this, "This field can't be empty.", Toast.LENGTH_SHORT).show();
        }
        else{
            Toast.makeText(this, "Created Account Successfully!", Toast.LENGTH_SHORT).show();
        }
    }
    private void validateEmail(String email){

        if(!email.isEmpty() && Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            Toast.makeText(this, "Email Validated Successfully!", Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(this, "Invalid Email Address!", Toast.LENGTH_SHORT).show();
        }
    }

    private void validatePassword(String password, String confirm){
        if(password.equals(confirm)){
            Toast.makeText(this, "Both Password Matches!", Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(this, "Password doesn't match!", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        String text = parent.getItemAtPosition(position).toString();
        Toast.makeText(parent.getContext(),text, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}