package com.ousltg.travelguide10.User;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import android.app.Activity;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import androidx.annotation.NonNull;
import android.content.Intent;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.developer.gbuttons.GoogleSignInButton;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.ousltg.travelguide10.R;


public class LoginScreenActivity extends AppCompatActivity {

    private EditText loginEmail, loginPassword; // Input fields for email and password
    private TextView signupRedirectText;  // Text view to redirect to sign up screen
    private Button loginButton; // Button for login
    private FirebaseAuth auth; // Firebase authentication instance
    TextView forgotPassword; // Text view for forgot password
    GoogleSignInButton googleBtn;  // Button for Google sign-in
    GoogleSignInOptions gOptions; // Google sign-in options
    GoogleSignInClient gClient; // Google sign-in client


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_screen);
        getSupportActionBar().hide(); // Hide the action bar

        loginEmail = findViewById(R.id.login_email); // Initialize email input field
        loginPassword = findViewById(R.id.login_password); // Initialize password input field
        loginButton = findViewById(R.id.login_button);  // Initialize login button
        signupRedirectText = findViewById(R.id.signUpRedirectText); // Initialize sign up redirect text view
        forgotPassword = findViewById(R.id.forgot_password); //Initialize forgot password text view
        googleBtn = findViewById(R.id.googleBtn); // Initialize Google sign-in button

        auth = FirebaseAuth.getInstance(); // Get Firebase authentication instance

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String email = loginEmail.getText().toString();
                String pass = loginPassword.getText().toString();

                // Validate email and password
                if (!email.isEmpty() && Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                    if (!pass.isEmpty()) {
                        // Sign in with email and password using Firebase authentication
                        auth.signInWithEmailAndPassword(email, pass)
                                .addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                                    @Override
                                    public void onSuccess(AuthResult authResult) {
                                        // Login successful, start the main activity
                                        Toast.makeText(LoginScreenActivity.this, "Login Successful", Toast.LENGTH_SHORT).show();
                                        startActivity(new Intent(LoginScreenActivity.this, StartActivity.class));
                                        finish();
                                    }
                                }).addOnFailureListener(new OnFailureListener() {
                                    @Override
                                    public void onFailure(@NonNull Exception e) {
                                        // Login failed, display an error message
                                        Toast.makeText(LoginScreenActivity.this, "Login Failed", Toast.LENGTH_SHORT).show();
                                    }
                                });
                    } else {
                        loginPassword.setError("Empty fields are not allowed");
                    }
                } else if (email.isEmpty()) {
                    loginEmail.setError("Empty fields are not allowed");
                } else {
                    loginEmail.setError("Please enter correct email");
                }
            }
        });

        signupRedirectText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Redirect to the sign up activity
                startActivity(new Intent(LoginScreenActivity.this, SignUpActivity.class));
            }
        });

        forgotPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Handle forgot password functionality
                // Display a dialog to reset the password
                AlertDialog.Builder builder = new AlertDialog.Builder(LoginScreenActivity.this);
                View dialogView = getLayoutInflater().inflate(R.layout.activity_forgotpassword, null);
                EditText emailBox = dialogView.findViewById(R.id.emailBox);

                //Create the dialog
                builder.setView(dialogView);
                AlertDialog dialog = builder.create();

                dialogView.findViewById(R.id.btnReset).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        String userEmail = emailBox.getText().toString();

                        //If the email is empty or does not match the pattern of a valid email address, display a toast message
                        if (TextUtils.isEmpty(userEmail) && !Patterns.EMAIL_ADDRESS.matcher(userEmail).matches()){
                            Toast.makeText(LoginScreenActivity.this, "Enter your registered email id", Toast.LENGTH_SHORT).show();
                            return;
                        }
                        //send a password reset email to the provided email address.
                        auth.sendPasswordResetEmail(userEmail).addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                if (task.isSuccessful()){
                                    // task is successful, display a toast message
                                    Toast.makeText(LoginScreenActivity.this, "Check your email", Toast.LENGTH_SHORT).show();
                                    dialog.dismiss();
                                } else {
                                    // the task fails, display a toast message
                                    Toast.makeText(LoginScreenActivity.this, "Unable to send, failed", Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
                    }
                });

                //Set an OnClickListener on the "Cancel" button within the dialog to dismiss the dialog when clicked
                dialogView.findViewById(R.id.btnCancel).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        dialog.dismiss();
                    }
                });
                //Set a transparent background for the dialog window
                if (dialog.getWindow() != null){
                    dialog.getWindow().setBackgroundDrawable(new ColorDrawable(0));
                }
                //Show the dialog
                dialog.show();
            }
        });

       //region LOGIN WITH GOOGLE ACCOUNT

        gOptions = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN).requestEmail().build();
        gClient = GoogleSignIn.getClient(this, gOptions);

        // Check if a Google account is already signed in
        GoogleSignInAccount gAccount = GoogleSignIn.getLastSignedInAccount(this);
        if (gAccount != null){
            // If a Google account is already signed in, start the main activity
            finish();
            Intent intent = new Intent(LoginScreenActivity.this, StartActivity.class);
            startActivity(intent);
        }
        ActivityResultLauncher<Intent> activityResultLauncher = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(),
                new ActivityResultCallback<ActivityResult>() {
                    @Override
                    public void onActivityResult(ActivityResult result) {
                        if (result.getResultCode() == Activity.RESULT_OK){
                            Intent data = result.getData();
                            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);//method retrieves the signed-in Google account from the intent data.
                            try {
                                task.getResult(ApiException.class); // If the sign-in was successful, it finishes the current activity and starts the LoginScreenActivity again.
                                finish();
                                Intent intent = new Intent(LoginScreenActivity.this, LoginScreenActivity.class);
                                startActivity(intent);
                            } catch (ApiException e){
                                Toast.makeText(LoginScreenActivity.this, "Something went wrong", Toast.LENGTH_SHORT).show();
                            }
                        }
                    }
                });

        //This sets an OnClickListener for the Google sign-in button
        googleBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent signInIntent = gClient.getSignInIntent();
                activityResultLauncher.launch(signInIntent);
            }
        });

    }
    //endregion
}