package com.example.mmlod.pokemongoraids;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class ChooseGymActivity extends AppCompatActivity {
    private static final String TAG = "ChooseGymActivity";
    private EditText filter;
    private ArrayList<GymAction> gymActions;
    DatabaseReference databaseGymActions;
    ListView allGyms;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_gym);
        Log.d(TAG, "onCreate: Started");
        allGyms = (ListView) findViewById(R.id.listView);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        filter = (EditText) findViewById(R.id.searchFilter);
        setSupportActionBar(toolbar);

        databaseGymActions = FirebaseDatabase.getInstance().getReference("GymActions");
        gymActions = new ArrayList();

        final GymActionsAdapter adapter = new GymActionsAdapter(this, R.layout.adapter_view_layout, gymActions);
        allGyms.setAdapter(adapter);

        filter.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                adapter.filter(charSequence.toString());
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        databaseGymActions.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                gymActions.clear();
                for(DataSnapshot gymActionsDataSnapshot : dataSnapshot.getChildren()){
                    GymAction ga = gymActionsDataSnapshot.getValue(GymAction.class);
                    gymActions.add(ga);
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case  R.id.action_search:
                if(filter.getVisibility() == View.VISIBLE)   {
                    filter.setVisibility(View.GONE);
                    filter.setText("");
                }
                else filter.setVisibility(View.VISIBLE);
                break;

            case R.id.action_log_out:
                FirebaseAuth.getInstance().signOut();
                startActivity(new Intent(this, LoginActivity.class));
                break;

            case R.id.action_add:
                startActivity(new Intent(this, AddGymActionActivity.class));
                break;
        }

        return true;
    }
}
