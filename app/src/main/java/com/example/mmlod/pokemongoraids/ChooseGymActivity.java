package com.example.mmlod.pokemongoraids;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Date;

public class ChooseGymActivity extends AppCompatActivity {

    private static final String TAG = "ChooseGymActivity";
    private GymActionsAdapter adapter;

    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_gym);
        Log.d(TAG, "onCreate: Started");
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        setContentView(R.layout.activity_choose_gym);
        ListView mListView = (ListView) findViewById(R.id.listView);
        final EditText myfilter = (EditText) findViewById(R.id.searchFilter);

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

        adapter = new GymActionsAdapter(this, R.layout.adapter_view_layout, gymActions);
        mListView.setAdapter(adapter);
        myfilter.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                String textFilter = myfilter.getText().toString().toLowerCase();
                adapter.filter(textFilter);
            }
        });

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Chanhged", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_choose_gym, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
