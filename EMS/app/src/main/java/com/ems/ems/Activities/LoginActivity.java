package com.ems.ems.Activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.ems.ems.API.APIClient;
import com.ems.ems.API.Business;
import com.ems.ems.R;


import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A login screen that offers login via email/password.
 */
public class LoginActivity extends AppCompatActivity  {

    /*
    Private API Client
     */
    private APIClient apiClient = new APIClient();


    // UI references.
    private AutoCompleteTextView mEmailView;
    private EditText mBusinessTagView;
    private EditText mUsernameView;
    private EditText mPasswordView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        Button mEmailSignInButton = (Button) findViewById(R.id.sign_in_button);
        mEmailSignInButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                //attemptLogin();
                mBusinessTagView = findViewById(
                        R.id.edit_text_business_tag);
                mUsernameView = findViewById(R.id.edit_text_username);
                mPasswordView = findViewById(R.id.edit_text_password);

                login();
            }
        });




    }

    private void getApi() {
        apiClient.getApiService().getBusiness().enqueue(new Callback<List<Business>>() {
            @Override
            public void onResponse(Call<List<Business>> call, Response<List<Business>> response) {
                Business business = response.body().get(0);
                Log.d("aldmar: ", String.format("businessName = %s, businessTag = %s, ", business.getName(), business.getTag()));
            }

            @Override
            public void onFailure(Call<List<Business>> call, Throwable t) {
                Log.d("aldmar: ", t.getMessage());
            }
        });
    }

    private void login() {

        // Store values at the time of the login attempt.
        String businessTag = mBusinessTagView.getText().toString(); //"cibusinesstag";
        String username = mUsernameView.getText().toString(); //"ciusername";
        String password = mPasswordView.getText().toString(); //"cipassword";


        apiClient.getApiService().checkCredentials(businessTag, username, password).enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {


                if (response.isSuccessful()) {
                    Log.d("Successful Response: ", String.format("Success User Token = %s", response.body()));

                    Context context = getApplicationContext();
                    CharSequence text = response.body();
                    int duration = Toast.LENGTH_LONG;

                    Toast toast = Toast.makeText(context, text, duration);
                    toast.show();

                    PreferenceManager.getDefaultSharedPreferences(context).edit().putString("BUSINESS_TAG", businessTag).apply();
                    PreferenceManager.getDefaultSharedPreferences(context).edit().putString("TOKEN", response.body()).apply();

                    Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                    startActivity(intent);
                } else if (response.code() == 401) {
                    Log.d("401 response: ", String.format("Sorry Pal"));
                } else {
                    Context context = getApplicationContext();
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
}

