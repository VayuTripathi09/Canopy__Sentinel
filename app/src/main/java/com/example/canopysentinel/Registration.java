package com.example.canopysentinel;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.lifecycle.DefaultLifecycleObserver;
import androidx.lifecycle.LifecycleOwner;

import com.example.canopysentinel.databinding.ActivityRegistrationBinding;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Registration extends AppCompatActivity implements DefaultLifecycleObserver {
    private ActivityRegistrationBinding binding;
    private FirebaseAuth auth;
    private FirebaseDatabase database;
    private ProgressDialog progressDialog;
    private final String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        
        binding = ActivityRegistrationBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        
        ViewCompat.setOnApplyWindowInsetsListener(binding.main, (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        initializeFirebase();
        initializeViews();
        setupClickListeners();
    }

    @Override
    public void onStart(@NonNull LifecycleOwner owner) {
        if (!isNetworkAvailable()) {
            Toast.makeText(this, "No internet connection available", Toast.LENGTH_LONG).show();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (progressDialog != null && progressDialog.isShowing()) {
            progressDialog.dismiss();
        }
        binding = null;
    }

    private void initializeFirebase() {
        try {
            database = FirebaseDatabase.getInstance();
            auth = FirebaseAuth.getInstance();
        } catch (Exception e) {
            Toast.makeText(this, "Error connecting to Firebase: " + e.getMessage(), Toast.LENGTH_LONG).show();
            finish();
        }
    }

    private void initializeViews() {
        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Please Wait...");
        progressDialog.setCancelable(false);
    }

    private void setupClickListeners() {
        binding.Logbutton.setOnClickListener(v -> {
            Intent intent = new Intent(Registration.this, Login.class);
            startActivity(intent);
            finish();
        });

        binding.button.setOnClickListener(v -> handleSignUp());
    }

    private boolean isNetworkAvailable() {
        ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(CONNECTIVITY_SERVICE);
        if (connectivityManager != null) {
            NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
            return activeNetworkInfo != null && activeNetworkInfo.isConnected();
        }
        return false;
    }

    private void handleSignUp() {
        if (!isNetworkAvailable()) {
            Toast.makeText(this, "No internet connection available", Toast.LENGTH_LONG).show();
            return;
        }

        try {
            String name = binding.reusername.getText().toString().trim();
            String email = binding.remail.getText().toString().trim();
            String password = binding.editTextPassword2.getText().toString();
            String confirmPassword = binding.repassword.getText().toString();

            if (!validateInputs(name, email, password, confirmPassword)) {
                return;
            }

            progressDialog.show();
            registerUser(name, email, password);

        } catch (Exception e) {
            progressDialog.dismiss();
            Toast.makeText(this, "Error during sign up: " + e.getMessage(), Toast.LENGTH_LONG).show();
        }
    }

    private boolean validateInputs(String name, String email, String password, String confirmPassword) {
        if (TextUtils.isEmpty(name) || TextUtils.isEmpty(email) || 
            TextUtils.isEmpty(password) || TextUtils.isEmpty(confirmPassword)) {
            Toast.makeText(this, "Please Enter Valid Information", Toast.LENGTH_SHORT).show();
            return false;
        }

        if (!email.matches(emailPattern)) {
            binding.remail.setError("Type A Valid Email Here");
            return false;
        }

        if (password.length() < 6) {
            binding.editTextPassword2.setError("Password Must Be 6 Characters Or More");
            return false;
        }

        if (!password.equals(confirmPassword)) {
            binding.repassword.setError("The Password Doesn't Match");
            return false;
        }

        return true;
    }

    private void registerUser(String name, String email, String password) {
        auth.createUserWithEmailAndPassword(email, password)
            .addOnSuccessListener(authResult -> {
                if (authResult.getUser() != null) {
                    createUserProfile(authResult.getUser().getUid(), name, email, password);
                } else {
                    progressDialog.dismiss();
                    Toast.makeText(Registration.this, "Failed to create account", Toast.LENGTH_SHORT).show();
                }
            })
            .addOnFailureListener(e -> {
                progressDialog.dismiss();
                Toast.makeText(Registration.this, "Error: " + e.getMessage(), Toast.LENGTH_SHORT).show();
            });
    }

    private void createUserProfile(String userId, String name, String email, String password) {
        try {
            DatabaseReference reference = database.getReference("user").child(userId);
            String defaultImageUri = "https://firebasestorage.googleapis.com/v0/b/av-messenger-dc8f3.appspot.com/o/man.png?alt=media&token=880f431d-9344-45e7-afe4-c2cafe8a5257";
            String status = "Hey I'm Using This Application";
            
            Users user = new Users(userId, name, email, password, defaultImageUri, status);
            
            reference.setValue(user)
                .addOnSuccessListener(unused -> {
                    progressDialog.dismiss();
                    Intent intent = new Intent(Registration.this, MainActivity.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    startActivity(intent);
                    finish();
                })
                .addOnFailureListener(e -> {
                    progressDialog.dismiss();
                    Toast.makeText(Registration.this, "Error creating profile: " + e.getMessage(), Toast.LENGTH_SHORT).show();
                });
        } catch (Exception e) {
            progressDialog.dismiss();
            Toast.makeText(this, "Error creating user profile: " + e.getMessage(), Toast.LENGTH_LONG).show();
        }
    }
}