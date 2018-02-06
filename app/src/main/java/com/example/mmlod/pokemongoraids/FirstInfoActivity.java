package com.example.mmlod.pokemongoraids;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class FirstInfoActivity extends AppCompatActivity implements View.OnClickListener{

    private static final String TAG = "FirstInfoActivity";
    
    private EditText pokemonGoUsername, pokemonGoLevel;
    private Button saveAndVerify;
    private RadioButton teamM, teamI, teamV;
    private String team;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first_info);

        pokemonGoUsername = (EditText) findViewById(R.id.pokemon_go_username);
        pokemonGoLevel = (EditText) findViewById(R.id.level);
        saveAndVerify = (Button) findViewById(R.id.save_button);
        teamM = (RadioButton) findViewById(R.id.radio_mystic);
        teamI = (RadioButton) findViewById(R.id.radio_instinct);
        teamV = (RadioButton) findViewById(R.id.radio_valor);

        teamM.setOnClickListener(this);
        teamI.setOnClickListener(this);
        teamV.setOnClickListener(this);

        saveAndVerify.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.radio_mystic:
                team = "Mystic";
                break;
            case R.id.radio_instinct:
                team = "Instinct";
                break;
            case R.id.radio_valor:
                team = "Valor";
                break;
            case R.id.save_button:
                saveAndVerifyMethod();
                finish();
                startActivity(new Intent(this, ChooseGymActivity.class));
                break;
        }
    }

    private void saveAndVerifyMethod(){
        if(!pokemonGoUsername.equals("")) {
            save();
            verify();
        } else Toast.makeText(this, "Username is empty", Toast.LENGTH_LONG).show();
    }

    private void save(){

    }

    private void verify(){
        final FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        user.sendEmailVerification().addOnCompleteListener(this, new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if(task.isSuccessful()){
                    Toast.makeText(FirstInfoActivity.this, "Verification email sent to " + user.getEmail(), Toast.LENGTH_SHORT).show();
                } else {
                    Log.e(TAG, "sendEmailVerification: ", task.getException());
                    Toast.makeText(FirstInfoActivity.this, "Failed to send verification email", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
