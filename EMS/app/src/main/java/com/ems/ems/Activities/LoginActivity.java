package com.ems.ems.Activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.ems.ems.API.APIClient;
import com.ems.ems.API.BusinessPojo.Business;
import com.ems.ems.R;


import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A login screen that offers login via email/password.
 */
public class LoginActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    /*
    Private API Client
     */
    private APIClient apiClient = new APIClient();

    //Spinner & Business Array to Populate
    Spinner businessSpinner;
    ArrayList<String> businessesArray = new ArrayList<>();



    // UI references.
    private AutoCompleteTextView mEmailView;
    private EditText mBusinessTagView;
    private EditText mUsernameView;
    private EditText mPasswordView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        PreferenceManager.getDefaultSharedPreferences(this).getAll().clear();

        // Spinner element
        businessSpinner = findViewById(R.id.business_spinner);
        businessSpinner.setOnItemSelectedListener(this);

        getBusinesses();

        Button mEmailSignInButton = (Button) findViewById(R.id.sign_in_button);
        mEmailSignInButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                //attemptLogin();
                mUsernameView = findViewById(R.id.edit_text_username);
                mPasswordView = findViewById(R.id.edit_text_password);

                login();
            }
        });


    }

    private void populateSpinner() {
        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<String> spinnerArrayAdapter = new ArrayAdapter<>(this,
                android.R.layout.simple_spinner_item, businessesArray);

        // Specify the layout to use when the list of choices appears
        spinnerArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // Apply the adapter to the spinner
        businessSpinner.setAdapter(spinnerArrayAdapter);
    }

    private void getBusinesses() {
        apiClient.getApiService().getBusiness().enqueue(new Callback<List<Business>>() {
            @Override
            public void onResponse(Call<List<Business>> call, Response<List<Business>> response) {
                List<Business> businesses = response.body();
                if (businesses.size() != 0) {
                    for (Business item : businesses) {
                        businessesArray.add(item.getTag());
                    }
                    populateSpinner();
                }
            }

            @Override
            public void onFailure(Call<List<Business>> call, Throwable t) {
                Log.d("aldmar: ", t.getMessage());
            }
        });
    }

    private void login() {
        Context context = getApplicationContext();
        // Store values at the time of the login attempt.
        String businessTag = "cibusinesstag";//mBusinessTagView.getText().toString();
        String username = "ciusername";//mUsernameView.getText().toString();
        String password = "cipassword";//mPasswordView.getText().toString();


        apiClient.getApiService().checkCredentials(businessTag, username, password).enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {


                if (response.isSuccessful()) {
                    Log.d("Successful Response: ", String.format("Success User Token = %s", response.body()));

                    PreferenceManager.getDefaultSharedPreferences(context).edit().putString("BUSINESS_TAG", businessTag).apply();
                    PreferenceManager.getDefaultSharedPreferences(context).edit().putString("TOKEN", response.body()).apply();
                    PreferenceManager.getDefaultSharedPreferences(context).edit().putString("USERNAME", mUsernameView.getText().toString() ).apply();


                    Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                    startActivity(intent);
                } else if (response.code() == 401) {
                    Log.d("401 response: ", String.format("Sorry Pal"));
                } else {

                    CharSequence text = "Incorrect password or username.";
                    int duration = Toast.LENGTH_SHORT;

                    Toast toast = Toast.makeText(context, text, duration);
                    toast.show();

                }

            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {

            }


        });
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    @Override
    public void onBackPressed() {
        //do nothing
    }
}

