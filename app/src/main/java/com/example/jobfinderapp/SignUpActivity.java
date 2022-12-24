package com.example.jobfinderapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
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

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

public class SignUpActivity extends AppCompatActivity {
    private TextView tvSignUpTitle;
    private Button btnSignUp;
    private TextView tvSignUp;

    private FirebaseAuth firebaseAuth;
    private FirebaseFirestore firebaseFirestore;

    private TextInputLayout tilSignUpUsername, tilSignUpPassword, tilSignUpEmail, tilSignUpConfirmPassword;
    private CustomProgressDialog customProgressDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        tvSignUpTitle = findViewById(R.id.tvSignUpTitle);
        btnSignUp = findViewById(R.id.btnSignUp);
        tvSignUp = findViewById(R.id.tvSignUp);

        tilSignUpUsername = findViewById(R.id.tilSignUpUsername);
        tilSignUpEmail = findViewById(R.id.tilSignUpEmail);
        tilSignUpPassword = findViewById(R.id.tilSignUpPassword);
        tilSignUpConfirmPassword = findViewById(R.id.tilSignUpConfirmPassword);

        firebaseAuth = FirebaseAuth.getInstance();
        firebaseFirestore = FirebaseFirestore.getInstance();

        customProgressDialog = new CustomProgressDialog(SignUpActivity.this);

        btnSignUp.setOnClickListener(v -> registerUser());
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
        String val = tilSignUpUsername.getEditText().getText().toString();
        String noWhiteSpace = "\\A\\w{4,20}\\z";
        if (val.isEmpty()) {
            tilSignUpUsername.setError("Username can't be empty!");
            return false;
        } else if (val.length() >= 15) {
            tilSignUpUsername.setError("Username too long!");
            return false;
        } else if (val.length() < 8) {
            tilSignUpUsername.setError("Username must have at least 8 characters!");
            return false;
        } else if (!val.matches(noWhiteSpace)) {
            tilSignUpUsername.setError("White Spaces are not allowed");
            return false;
        } else {
            tilSignUpUsername.setError(null);
            tilSignUpUsername.setErrorEnabled(false);
            return true;
        }
    }
    private Boolean validateEmail() {
        String val = tilSignUpEmail.getEditText().getText().toString();
        String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
        if (val.isEmpty()) {
            tilSignUpEmail.setError("Email cannot be empty!");
            return false;
        } else if (!val.matches(emailPattern)) {
            tilSignUpEmail.setError("Invalid email address!");
            return false;
        } else {
            tilSignUpEmail.setError(null);
            tilSignUpEmail.setErrorEnabled(false);
            return true;
        }
    }
    private Boolean validatePassword() {
        String val = tilSignUpPassword.getEditText().getText().toString();
        String passwordVal = "^" +
                "(?=.*[a-zA-Z])" +      //any letter
                "(?=\\S+$)" +           //no white spaces
                ".{8,}" +               //at least 4 characters
                "$";
        if (val.isEmpty()) {
            tilSignUpPassword.setError("Password can't be empty!");
            return false;
        } else if (!val.matches(passwordVal)) {
            tilSignUpPassword.setError("Password is too weak!");
            return false;
        } else {
            tilSignUpPassword.setError(null);
            tilSignUpPassword.setErrorEnabled(false);
            return true;
        }
    }
    private Boolean validateConfirmPassword() {
        String val1 = tilSignUpPassword.getEditText().getText().toString();
        String val2 = tilSignUpConfirmPassword.getEditText().getText().toString();
        if (val2.isEmpty()) {
            tilSignUpConfirmPassword.setError("Confirm Password can't be empty!");
            return false;
        } else if (!val1.matches(val2)) {
            tilSignUpConfirmPassword.setError("Please make sure your passwords match!");
            return false;
        } else {
            tilSignUpConfirmPassword.setError(null);
            tilSignUpConfirmPassword.setErrorEnabled(false);
            return true;
        }
    }
    private void registerUser() {
        if(!validateUsername() |!validatePassword() | !validateEmail() | !validateUsername() | !validateConfirmPassword())
        {
            return;
        }
        String email= tilSignUpEmail.getEditText().getText().toString();
        String userName= tilSignUpUsername.getEditText().getText().toString();
        String password = tilSignUpPassword.getEditText().getText().toString();
        List<String> emptyList = new ArrayList<>();
        customProgressDialog.show();
        // Tao user moi FireStore
        firebaseAuth.createUserWithEmailAndPassword(email,password)
                .addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                    @Override
                    public void onSuccess(AuthResult authResult) {
                        startActivity(new Intent(getApplicationContext(),SignInActivity.class));
                        Toast.makeText(getApplicationContext(),"Sign Up Successful",Toast.LENGTH_SHORT).show();
                        firebaseFirestore.collection("User")
                                .document(Objects.requireNonNull(FirebaseAuth.getInstance().getUid()))
                                .set(new User(email, userName, "", "", "", "", "","", "", "","", "", emptyList, 0));
                        customProgressDialog.cancel();
                        finish();
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(getApplicationContext(),e.getMessage(),Toast.LENGTH_SHORT).show();
                        customProgressDialog.cancel();
                    }
                });
    }

}
