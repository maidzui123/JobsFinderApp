package com.example.jobfinderapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;

import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import com.google.firebase.firestore.FirebaseFirestore;

public class SignUpActivity extends AppCompatActivity {
    private TextView tvSignUpTitle;
    private Button btnSignUp;
    private TextView tvSignUp;

    private FirebaseAuth mAuth;
    private ProgressBar progressBar;

    private FirebaseFirestore firebaseFirestore;

    private EditText edtSignUpUsername, edtSignUpEmail, edtSignUpPassword, edtSignUpConfirmPassword;
//    private TextInputLayout tilSignUpUsername, tilSignupPassword, tilSignUpEmail, tilSignUpConfirmPassword;
//    private TextInputEditText edtSignUpUsername, edtSignUpEmail, edtSignUpPassword, edtSignUpConfirmPassword;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        tvSignUpTitle = findViewById(R.id.tvSignUpTitle);
        btnSignUp = findViewById(R.id.btnSignUp);
        tvSignUp = findViewById(R.id.tvSignUp);

        edtSignUpEmail = findViewById(R.id.edtSignUpEmail);
        edtSignUpUsername = findViewById(R.id.edtSignUpUsername);
        edtSignUpPassword = findViewById(R.id.edtSignUpPassword);
        edtSignUpConfirmPassword = findViewById(R.id.edtSignUpConfirmPassword);

        edtSignUpUsername= findViewById(R.id.edtSignUpUsername);
        edtSignUpEmail = findViewById(R.id.edtSignUpEmail);
        edtSignUpPassword = findViewById(R.id.edtSignUpPassword);
        edtSignUpConfirmPassword = findViewById(R.id.edtSignUpConfirmPassword);

        mAuth = FirebaseAuth.getInstance();
        firebaseFirestore = FirebaseFirestore.getInstance();
        progressBar = findViewById(R.id.progressBar);

        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email= edtSignUpEmail.getText().toString();
                String userName= edtSignUpUsername.getText().toString();
                String password = edtSignUpPassword.getText().toString();
                if(!validatePassword() | !validateEmail() | !validateUsername() | !validateConfirmPassword())
                {
                    return;
                }
                progressBar.setVisibility(View.VISIBLE);
                mAuth.createUserWithEmailAndPassword(email,password)
                        .addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                            @Override
                            public void onSuccess(AuthResult authResult) {
                                startActivity(new Intent(getApplicationContext(),SignInActivity.class));
                                progressBar.setVisibility(View.GONE);
                                firebaseFirestore.collection("User")
                                        .document(FirebaseAuth.getInstance().getUid())
                                        .set(new User(email,userName));
                            }
                        })
                        .addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                progressBar.setVisibility(View.GONE);
                                Toast.makeText(getApplicationContext(),e.getMessage(),Toast.LENGTH_SHORT).show();
                            }
                        });
            }
        });
        tvSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), SignInActivity.class));
            }
        });
        String title = "Sign Up";
        SpannableString s = new SpannableString(title);
        s.setSpan(new ForegroundColorSpan(Color.parseColor("#F15A07")), 5, 7,0);
        tvSignUpTitle.setText(s);
    }
    private Boolean validateUsername() {
        String val = edtSignUpUsername.getText().toString();
        String noWhiteSpace = "\\A\\w{4,20}\\z";
        if (val.isEmpty()) {
            edtSignUpUsername.setError("Username can't be empty!");
            return false;
        } else if (val.length() >= 15) {
            edtSignUpUsername.setError("Username too long!");
            return false;
        } else if (val.length() < 8) {
            edtSignUpUsername.setError("Username must have at least 8 characters!");
            return false;
        } else if (!val.matches(noWhiteSpace)) {
            edtSignUpUsername.setError("White Spaces are not allowed");
            return false;
        } else {
            edtSignUpUsername.setError(null);
            return true;
        }
    }
    private Boolean validateEmail() {
        String val = edtSignUpEmail.getText().toString();
        String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
        if (val.isEmpty()) {
            edtSignUpEmail.setError("Email cannot be empty!");
            return false;
        } else if (!val.matches(emailPattern)) {
            edtSignUpEmail.setError("Invalid email address!");
            return false;
        } else {
            edtSignUpEmail.setError(null);
            return true;
        }
    }
    private Boolean validatePassword() {
        String val = edtSignUpPassword.getText().toString();
        String passwordVal = "^" +
                //"(?=.*[0-9])" +         //at least 1 digit
                //"(?=.*[a-z])" +         //at least 1 lower case letter
                //"(?=.*[A-Z])" +         //at least 1 upper case letter
                "(?=.*[a-zA-Z])" +      //any letter
                //"(?=.*[@#$%^&+=])" +    //at least 1 special character
                "(?=\\S+$)" +           //no white spaces
                ".{8,}" +               //at least 4 characters
                "$";
        if (val.isEmpty()) {
            edtSignUpPassword.setError("Password can't be empty!");
            return false;
        } else if (!val.matches(passwordVal)) {
            edtSignUpPassword.setError("Password is too weak!");
            return false;
        } else {
            edtSignUpPassword.setError(null);
            return true;
        }
    }
    private Boolean validateConfirmPassword() {
        String val1 = edtSignUpConfirmPassword.getText().toString();
        String val2 = edtSignUpConfirmPassword.getText().toString();
        if (val2.isEmpty()) {
            edtSignUpConfirmPassword.setError("Confirm Password can't be empty!");
            return false;
        } else if (!val1.matches(val2)) {
            edtSignUpConfirmPassword.setError("Please make sure your passwords match!");
            return false;
        } else {
            edtSignUpConfirmPassword.setError(null);
            return true;
        }
    }



}