package com.example.agrisite;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;
import com.google.android.material.textfield.TextInputEditText;
import java.sql.Connection;
import java.sql.Statement;
import io.github.muddz.styleabletoast.StyleableToast;

public class RegisterFO extends AppCompatActivity {
   DatabaseHelper databaseHelper;
    private Connection connection;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_fo);

        databaseHelper = new DatabaseHelper();

        //DEFINE THE ARRAY OF PROVINCES
        String[] provinceitems = {"Central Province", "North Central Province", "Eastern Province", "Northern Province", "Western Province", "North Western Province", "Sabaragamuwa Province", "Southern Province", "Uva Province"};

        //DEFINE THE ARRAY OF DIVISIONS
        String[] centralDivisions = {"Kandy" ,"Matale", "Nuwara Eliya"};
        String[] easternDivisions = {"Ampara", "Batticaloa", "Trincomale"};
        String[] northernDivisions = {"Jaffna", "Mannar", "Mulativu", "Vavuniya", "Kilinochchi"};
        String[] northwesternDivisions = {"Puttalam", "Kurunegala"};
        String[] sabaragamuwaDivisions = {"Kegalle", "Rathnapura"};
        String[] westernDivisions = {"Colombo", "Gampaha", "Kalutara"};
        String[] southernDivisions = {"Galle", "Matara", "Hambantota"};
        String[] uvaDivisions = {"Badulla", "Monaragala"};

        //REFERENCES FOR EDIT TEXTS
        TextInputEditText fullName = findViewById(R.id.editTxtFullName);
        TextInputEditText username = findViewById(R.id.editTxtUsername);
        TextInputEditText password = findViewById(R.id.editTxtPassword);
        TextInputEditText confirmP = findViewById(R.id.editTxtCnfPassword);

        //REFERENCES FOR EDIT TEXTS
        Button btnRegister = findViewById(R.id.btnRegister);

        //REFERENCES FOR ALL THREE ARRAYS
        Spinner province = findViewById(R.id.ProvincePicker);
        Spinner division = findViewById(R.id.DivisionPicker);
        Spinner VSDomain = findViewById(R.id.VillageServiceDomainPicker);

        // Create the ArrayAdapter for the province Spinner
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, provinceitems);
        province.setAdapter(adapter);

        // Create an empty ArrayAdapter for the division Spinner
        ArrayAdapter<String> divisionAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item);
        divisionAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        division.setAdapter(divisionAdapter);

        // Set the OnItemSelectedListener for the province Spinner
        province.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                //Get the selected item from the picker and assign it to selectedProvince variable
                String selectedProvince = parent.getItemAtPosition(position).toString();

                // Update the division Picker based on the selected province
                if (selectedProvince.equals("Central Province")) {

                    // Update the divisionAdapter with centralDivisions array
                    divisionAdapter.clear();
                    divisionAdapter.addAll(centralDivisions);

                }else if(selectedProvince.equals("Eastern Province")){

                    //Update the divisionAdapter with easternDivisions array

                    divisionAdapter.clear();
                    divisionAdapter.addAll(easternDivisions);

                }else if(selectedProvince.equals("Northern Province")){

                    // Update the divisionAdapter with northernDivisions array

                    divisionAdapter.clear();
                    divisionAdapter.addAll(northernDivisions);

                }else if(selectedProvince.equals("North Western Province")){

                    // Update the divisionAdapter with northwesternDivisions array

                    divisionAdapter.clear();
                    divisionAdapter.addAll(northwesternDivisions);

                }else if(selectedProvince.equals("Sabaragamuwa Province")){

                    // Update the divisionAdapter with sabaragamuwaDivisions array

                    divisionAdapter.clear();
                    divisionAdapter.addAll(sabaragamuwaDivisions);

                } else if (selectedProvince.equals("Western Province")) {

                    // Update the divisionAdapter with westernDivisions array

                    divisionAdapter.clear();
                    divisionAdapter.addAll(westernDivisions);

                } else if (selectedProvince.equals("Southern Province")) {

                    // Update the divisionAdapter with southernDivisions array

                    divisionAdapter.clear();
                    divisionAdapter.addAll(southernDivisions);
                }

                else if(selectedProvince.equals("Uva Province")){
                    // Update the divisionAdapter with uvaDivisions array
                    divisionAdapter.clear();
                    divisionAdapter.addAll(uvaDivisions);
                }

                // Notify the divisionAdapter that the data has changed
                divisionAdapter.notifyDataSetChanged();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        btnRegister.setOnClickListener(v -> {
            String SfullName = fullName.getText().toString();
            String Susername = username.getText().toString();
            String Spassword = password.getText().toString();
            String Sprovince = province.getSelectedItem().toString();
            String Sdivision = division.getSelectedItem().toString();
            
            InsertData(SfullName,Susername,Spassword,Sprovince,Sdivision);

            validateUsername(String.valueOf(username));
            validateFullName(String.valueOf(fullName));
            validatePassword(String.valueOf(password));
            validateCnfPassword(String.valueOf(confirmP));
            CheckIfSamePassword(String.valueOf(password), String.valueOf(confirmP));
        });
    }

   public void InsertData(String fullName, String username, String password, String province, String division) {

        connection = databaseHelper.connectionclass();

        try {
            if (connection != null) {
                Log.e("Error", "Con is null");

                // Construct the SQL INSERT statement with parameterized query
                //String SQLINSERT = "INSERT INTO USERINFO (UserID, FullName, Username, Password, Province, Divisional_Secretariat) VALUES ('1' ,'jOHN', 'Christ' , '1234ABC', 'Western Province', 'Colombo')";
                String SQLINSERT = "INSERT INTO USERINFO (FullName, Username, Password, Province, Divisional_Secretariat) VALUES ('" + fullName + "', '" + username+ "','" + password + "', '" + province+ "', '" + division+ "')";

                Statement statement = connection.createStatement();
                statement.executeUpdate(SQLINSERT);
                StyleableToast.makeText(this, "Successfully Registered the Field Officer!", Toast.LENGTH_LONG, R.style.InsertToast).show();
                // Additional code if needed after successful insertion
            }
        } catch (Exception exception) {
            Log.e("Error", exception.getMessage());
        }
    }

    private boolean validateUsername(String username) {
        String pattern = "^[a-zA-Z0-9._-]{4,20}$";

        if (TextUtils.isEmpty(username)) {
            TextInputEditText editText = findViewById(R.id.editTxtUsername);
            editText.setError("Username is required.");
            return false;

        } else if (!username.matches(pattern)) {
            TextInputEditText editText = findViewById(R.id.editTxtUsername);
            editText.setError("Invalid username format. Only letters, numbers, '.', '_', and '-' are allowed.");
            return false;

        } else {
            return true;
        }
    }
    private boolean validateFullName(String fullName){
        String noWhiteSpace = "\\A\\w{4,20}\\z";

        if(TextUtils.isEmpty(fullName)){
            TextInputEditText editText = findViewById(R.id.editTxtFullName);
            editText.setError("Full Name is required.");
            return false;

        } else if ( fullName.length() >= 15) {
            TextInputEditText editText = findViewById(R.id.editTxtFullName);
            editText.setError("Full Name can't be too long.");
            return false;
        } else {
            return true;
        }
    }

    private boolean validatePassword(String password) {
        String passwordVal = "^" +
                //"(?=.*[0-9])" +         //at least 1 digit
                //"(?=.*[a-z])" +         //at least 1 lower case letter
                //"=.*[A-Z])" +         //at least 1 upper case letter
                "(?=.*[a-zA-Z])" +      //any letter
                "(?=.*[@#$%^&+=])" +    //at least 1 special character
                "(?=\\S+$)" +           //no white spaces
                ".{4,}" +               //at least 4 characters
                "$";

        if (TextUtils.isEmpty(password)) {
            TextInputEditText editText = findViewById(R.id.editTxtPassword);
            editText.setError("Password is required.");
            return false;

        } else if (!password.matches(passwordVal)) {
            TextInputEditText editText = findViewById(R.id.editTxtPassword);
            editText.setError("Invalid Password Format.");
            return false;

        } else {
            return true;
        }
    }
    private boolean validateCnfPassword(String confirmP){

        String passwordVal = "^" +
                //"(?=.*[0-9])" +         //at least 1 digit
                //"(?=.*[a-z])" +         //at least 1 lower case letter
                //"(?=.*[A-Z])" +         //at least 1 upper case letter
                "(?=.*[a-zA-Z])" +      //any letter
                "(?=.*[@#$%^&+=])" +    //at least 1 special character
                "(?=\\S+$)" +           //no white spaces
                ".{4,}" +               //at least 4 characters
                "$";

        if(TextUtils.isEmpty(confirmP)){
            TextInputEditText editText = findViewById(R.id.editTxtCnfPassword);
            editText.setError("This field can't be empty.");
            return false;

        } else if (!confirmP.matches(passwordVal)) {
            TextInputEditText editText = findViewById(R.id.editTxtCnfPassword);
            editText.setError("Invalid Password Format..");
            return false;

        } else {
            return true;
        }
    }

    private boolean CheckIfSamePassword(String password, String confirmP){
        if(password!=confirmP){
            TextInputEditText editText = findViewById(R.id.editTxtCnfPassword);
            editText.setError("Both passwords should matches");
            return false;
        }
        return true;
    }
}