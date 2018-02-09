package com.example.mmlod.pokemongoraids;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class ChangeGymActionActivity extends AppCompatActivity {

    private static final String TAG = "ChangeGymActionActivity";
    private EditText name;
    private TextView startDate, endDate;
    private Button changeSave;
    private DatabaseReference databaseGymActions;
    private GymAction gymActionToChange, gymActionChanged;
    private DatePickerDialog.OnDateSetListener sDateSetListener, eDateSetListener;
    private int sDay, sMonth, sYear, eDay, eMonth, eYear;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_gym_action);

        Intent incomingIntent = getIntent();
        initializeFieldsAndButtons();



        databaseGymActions = FirebaseDatabase.getInstance().getReference("GymActions");
        gymActionToChange = new GymAction(incomingIntent.getStringExtra("id"), incomingIntent.getStringExtra("name"), incomingIntent.getStringExtra("startDate"), incomingIntent.getStringExtra("endDate"), incomingIntent.getStringArrayListExtra("raidsId"));
        gymActionChanged = gymActionToChange;

        setDates();


        name.setText(gymActionToChange.getName());
        startDate.setText(gymActionToChange.getStartDate());
        endDate.setText(gymActionToChange.getEndDate());


        startDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                DatePickerDialog dialog = new DatePickerDialog(ChangeGymActionActivity.this, android.R.style.Theme_Holo_Light_Dialog_MinWidth, sDateSetListener, sDay, sMonth, sDay);
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog.show();

            }
        });
        sDateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                sDay = day;
                sMonth = month;
                sYear = year;
                eDay = sDay + 6;
                eMonth = sMonth;
                eYear = sYear;
                if(eDay < 7){
                    eMonth = sMonth + 1;
                    if(eMonth < 1) {
                        eYear = sYear +1;
                    }
                }
                if(sMonth<9){
                    startDate.setText(sDay + "-0" + (sMonth + 1) + "-" + sYear );
                } else {
                    startDate.setText(sDay + "-" + (sMonth + 1) + "-" + sYear);
                }
            }
        };

        endDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                DatePickerDialog dialog = new DatePickerDialog(ChangeGymActionActivity.this, android.R.style.Theme_Holo_Light_Dialog_MinWidth, eDateSetListener, eYear, eMonth, eDay);
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog.show();

            }
        });
        eDateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                eDay = day;
                eMonth = month;
                eYear = year;
                if(eMonth < 9){
                    endDate.setText(eDay+"-0"+(eMonth+1)+"-"+eYear);
                } else {
                    endDate.setText(eDay + "-" + (eMonth + 1) + "-" + eYear);
                }
            }
        };



        changeSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                gymActionChanged.setName(name.getText().toString());
                gymActionChanged.setStartDate(startDate.getText().toString());
                gymActionChanged.setEndDate(endDate.getText().toString());
                databaseGymActions.child(gymActionChanged.getId()).setValue(gymActionToChange);
                startActivity(new Intent(ChangeGymActionActivity.this,ChooseGymActivity.class));
            }
        });

    }

    private void setDates(){
        sDay = Integer.parseInt(gymActionChanged.getStartDate().split("-")[0]);
        sMonth = Integer.parseInt(gymActionChanged.getStartDate().split("-")[1]) - 1;
        sYear = Integer.parseInt(gymActionChanged.getStartDate().split("-")[2]);

        eDay = Integer.parseInt(gymActionChanged.getEndDate().split("-")[0]);
        eMonth = Integer.parseInt(gymActionChanged.getEndDate().split("-")[1]) - 1;
        eYear = Integer.parseInt(gymActionChanged.getEndDate().split("-")[2]);
    }

    private void initializeFieldsAndButtons(){
        name = (EditText) findViewById(R.id.gym_name_change);
        startDate = findViewById(R.id.start_date_change);
        endDate = findViewById(R.id.end_date_change);
        changeSave = findViewById(R.id.save_change_gym_action_button);
    }
}
