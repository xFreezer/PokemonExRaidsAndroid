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

import java.util.ArrayList;

public class ChooseGymActivity extends AppCompatActivity {
    private static final String TAG = "ChooseGymActivity";
    private EditText filter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_gym);
        Log.d(TAG, "onCreate: Started");
        ListView allGyms = (ListView) findViewById(R.id.listView);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        filter = (EditText) findViewById(R.id.searchFilter);
        setSupportActionBar(toolbar);

        ArrayList<GymAction> gymActions = new ArrayList();

        gymActions.add(new GymAction("Górka Widzewska1","17-01-2018", "23-01-2018" ));
        gymActions.add(new GymAction("Górka Widzewska2", "28-01-2018", "02-02-2018"));
        gymActions.add(new GymAction("Górka Widzewska3", "28-01-2018", "02-02-2018"));
        gymActions.add(new GymAction("Górka Widzewska4", "28-01-2018", "02-02-2018"));
        gymActions.add(new GymAction("Górka Widzewska5", "28-01-2018", "02-02-2018"));
        gymActions.add(new GymAction("Poniatowski", "28-01-2018", "02-02-2018"));
        gymActions.add(new GymAction("Górka Widzewska7", "28-01-2018", "02-02-2018"));
        gymActions.add(new GymAction("Górka Widzewska8", "28-01-2018", "02-02-2018"));
        gymActions.add(new GymAction("Park Struga", "28-01-2018", "02-02-2018"));
        gymActions.add(new GymAction("Górka Widzewska10", "28-01-2018", "02-02-2018"));
        gymActions.add(new GymAction("Górka Widzewska11", "28-01-2018", "02-02-2018"));
        gymActions.add(new GymAction("Górka Widzewska12", "28-01-2018", "02-02-2018"));
        gymActions.add(new GymAction("Górka Widzewska13", "28-01-2018", "02-02-2018"));

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
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        /*switch (item.getItemId()){
            case  R.id.action_search:
             //   if(filter.getVisibility() == View.VISIBLE)   filter.setVisibility(View.GONE);
              //  else filter.setVisibility(View.VISIBLE);
                break;

            case R.id.action_log_out:
                FirebaseAuth.getInstance().signOut();
                startActivity(new Intent(this, LoginActivity.class));
                break;

            case R.id.action_add:
                break;
        }*/
        if(item.getItemId() == R.id.action_log_out){
            FirebaseAuth.getInstance().signOut();
            startActivity(new Intent(this, LoginActivity.class));
        }
        return true;
    }
}
