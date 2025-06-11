package com.ousltg.travelguide10.User;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.ousltg.travelguide10.R;

public class SignUpActivity extends AppCompatActivity {

    private FirebaseAuth auth; // instance of FirebaseAuth class( instance of FirebaseAuth class)
    private EditText signupEmail, signupPassword; //variable that references the email input field for the sign-up process.//variable that references the password input field for the sign-up process.
    private Button signupButton; //variable that references the sign-up button
    private TextView loginRedirectText; //variable that references the text view used for redirecting to the login screen.

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        getSupportActionBar().hide(); //hide the action bar

        auth = FirebaseAuth.getInstance(); //assign it to the auth variable.
        signupEmail = findViewById(R.id.signup_email);
        signupPassword = findViewById(R.id.signup_password);
        signupButton = findViewById(R.id.signup_button);
        loginRedirectText = findViewById(R.id.loginRedirectText);

        signupButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // EditText fields and stores them in the user and pass variables as trimmed strings.
                String user = signupEmail.getText().toString().trim();
                String pass = signupPassword.getText().toString().trim();

                if (user.isEmpty()){
                    //If the email field is empty sets an error message
                    signupEmail.setError("Email cannot be empty");
                }
                if (pass.isEmpty()){
                    // //If the password field is empty sets an error message
                    signupPassword.setError("Password cannot be empty");
                } else{
                    //both the email and password fields are not empty calls the createUserWithEmailAndPassword method on the auth Firebase authentication object.
                    //account creation task
                    auth.createUserWithEmailAndPassword(user, pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                //displays a toast message the sign-up was successful and starts the LoginScreenActivity
                                Toast.makeText(SignUpActivity.this, "SignUp Successful", Toast.LENGTH_SHORT).show();
                                startActivity(new Intent(SignUpActivity.this, LoginScreenActivity.class));
                            } else {
                                Toast.makeText(SignUpActivity.this, "SignUp Failed" + task.getException().getMessage(), Toast.LENGTH_SHORT).show(); //display sign-up failed and the exception message
                            }
                        }
                    });
                }

            }
        });

        //navigate the user to the login screen when the loginRedirectText is clicked
        loginRedirectText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(SignUpActivity.this, LoginScreenActivity.class));
            }
        });

    }
}