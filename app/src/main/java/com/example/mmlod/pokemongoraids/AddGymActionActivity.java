package com.example.mmlod.pokemongoraids;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.Calendar;

public class AddGymActionActivity extends AppCompatActivity {

    private static final String TAG = "AddGymActionActivity";

    private EditText gymName;
    private TextView startDate, endDate;
    private Button save;
    private DatePickerDialog.OnDateSetListener sDateSetListener, eDateSetListener;
    private int sDay, sMonth, sYear, eDay, eMonth, eYear;
    private DatabaseReference database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_gym_action);
        gymName = (EditText) findViewById(R.id.gym);
        startDate = (TextView) findViewById(R.id.start_date);
        endDate = (TextView) findViewById(R.id.end_date);
        save = (Button) findViewById(R.id.save_gym_action_button);
        Calendar cal = Calendar.getInstance();
        sYear = cal.get(Calendar.YEAR);
        sMonth = cal.get(Calendar.MONTH);
        sDay = cal.get(Calendar.DAY_OF_MONTH);
        database = FirebaseDatabase.getInstance().getReference("GymActions");

        startDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                DatePickerDialog dialog = new DatePickerDialog(AddGymActionActivity.this, android.R.style.Theme_Holo_Light_Dialog_MinWidth, sDateSetListener, sYear, sMonth, sDay);
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
                startDate.setText(sDay+"-"+(sMonth+1)+"-"+sYear);
            }
        };

        endDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                DatePickerDialog dialog = new DatePickerDialog(AddGymActionActivity.this, android.R.style.Theme_Holo_Light_Dialog_MinWidth, eDateSetListener, eYear, eMonth, eDay);
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
                endDate.setText(eDay+"-"+(eMonth+1)+"-"+eYear);
            }
        };

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(saveGymAction()) {
                    startActivity(new Intent(AddGymActionActivity.this, ChooseGymActivity.class));
                } else Toast.makeText(AddGymActionActivity.this, "Gym name, start date and end date cannot be empty", Toast.LENGTH_LONG).show();
            }
        });
    }

    private boolean saveGymAction(){
        if(gymName.getText().toString().equals("") || startDate.getText().equals("") || endDate.getText().equals("")) return false;
        else {
            String id = database.push().getKey();
            GymAction ga = new GymAction(id, gymName.getText().toString(), sDay + "-" + (sMonth + 1) + "-" + sYear, eDay + "-" + (eMonth + 1) + "-" + eYear, new ArrayList<String>());
            database.child(id).setValue(ga);
            return true;
        }
    }
}
