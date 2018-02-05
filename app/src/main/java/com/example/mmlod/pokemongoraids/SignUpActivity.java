package com.example.mmlod.pokemongoraids;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class SignUpActivity extends AppCompatActivity implements View.OnClickListener{
    private FirebaseAuth mAuth;
    private static final String TAG = "SignUpActivity";

    private EditText mEmail, mPassword, mReEmail;
    private Button btnSignUp;
    private TextView signInText;
    private Toast toast;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        mAuth = FirebaseAuth.getInstance();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        mEmail = (EditText) findViewById(R.id.email);
        mReEmail = (EditText) findViewById(R.id.re_email);
        mPassword = (EditText) findViewById(R.id.password);
        btnSignUp = (Button) findViewById(R.id.email_sign_up_button);
        signInText = (TextView) findViewById(R.id.sign_in_text);

        btnSignUp.setOnClickListener(this);

        signInText.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.sign_in_text:
                finish();
                startActivity(new Intent(this, LoginActivity.class));
                break;
            case R.id.email_sign_up_button:
                String email = mEmail.getText().toString();
                String password = mPassword.getText().toString();
                String reEmail = mReEmail.getText().toString();
                if(!email.equals("") && !password.equals("")) {
                    if (reEmail.equals(email)) createAccount(email, password);
                    else Toast.makeText(this, "Emails are not the same", Toast.LENGTH_LONG).show();
                } else Toast.makeText(this, "Email or Password is empty", Toast.LENGTH_LONG).show();
                break;
        }

    }

    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser();
        updateUI(currentUser);
    }

    private void updateUI(FirebaseUser user){
        ///
    }

    private void createAccount(String email, String password){
        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d(TAG, "createUserWithEmail:success");
                            FirebaseUser user = mAuth.getCurrentUser();
                            updateUI(user);
                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w(TAG, "createUserWithEmail:failure", task.getException());
                            Toast.makeText(SignUpActivity.this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();
                            updateUI(null);
                        }

                        // ...
                    }
                });
    }

}
