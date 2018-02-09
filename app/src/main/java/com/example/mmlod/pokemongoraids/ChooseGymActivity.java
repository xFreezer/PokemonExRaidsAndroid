package com.example.mmlod.pokemongoraids;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
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
    private DatabaseReference databaseGymActions, databasePlayers;
    private ListView allGyms;
    private GymActionsAdapter adapter;
    private FirebaseUser user;
    private boolean hasPermissionToChangeData = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_gym);
        Log.d(TAG, "onCreate: Started");
        allGyms = (ListView) findViewById(R.id.listView);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        filter = (EditText) findViewById(R.id.searchFilter);
        setSupportActionBar(toolbar);
        registerForContextMenu(allGyms);

        databaseGymActions = FirebaseDatabase.getInstance().getReference("GymActions");
        databasePlayers = FirebaseDatabase.getInstance().getReference("Players");
        gymActions = new ArrayList();
        adapter = new GymActionsAdapter(ChooseGymActivity.this, R.layout.adapter_view_layout, gymActions);
        user = FirebaseAuth.getInstance().getCurrentUser();

        databaseGymActions.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                gymActions.clear();
                for(DataSnapshot gymActionsDataSnapshot : dataSnapshot.getChildren()){
                    GymAction ga = gymActionsDataSnapshot.getValue(GymAction.class);
                    gymActions.add(ga);
                }
                adapter = new GymActionsAdapter(ChooseGymActivity.this, R.layout.adapter_view_layout, gymActions);
                allGyms.setAdapter(adapter);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });


        checkPermissions();


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
        checkPermissions();

    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        if(v.getId() == R.id.listView){
            getMenuInflater().inflate(R.menu.context_menu_list_view, menu);
            if(!hasPermissionToChangeData){
                for(int  i = 0; i < menu.size(); i++){
                    menu.getItem(i).setVisible(false);
                }
            }
        }
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        switch(item.getItemId()) {
            case R.id.change_context_menu:
                Intent intent = new Intent(this, ChangeGymActionActivity.class);
                intent.putExtra("id", adapter.getFilteredGyms().get(info.position).getId());
                intent.putExtra("name", adapter.getFilteredGyms().get(info.position).getName());
                intent.putExtra("startDate", adapter.getFilteredGyms().get(info.position).getStartDate());
                intent.putExtra("endDate", adapter.getFilteredGyms().get(info.position).getEndDate());
                intent.putExtra("raidsId", adapter.getFilteredGyms().get(info.position).getRaidsId());
                startActivity(intent);
                Toast.makeText(this, "Trying to change gym activity", Toast.LENGTH_LONG).show();
                return true;
            case R.id.delete_context_menu:

                databaseGymActions.child(adapter.getFilteredGyms().get(info.position).getId()).removeValue();
                Log.d(TAG, "onContextItemSelected: deleted object from db");
                Toast.makeText(this, "Trying to delete gym activity " + adapter.getFilteredGyms().get(info.position).getName() , Toast.LENGTH_LONG).show();
                return true;
            default:
                return super.onContextItemSelected(item);
        }
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        if(!hasPermissionToChangeData){
            menu.getItem(1).setVisible(false);
        }
        return super.onPrepareOptionsMenu(menu);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

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

    private void checkPermissions(){

        databasePlayers.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for(DataSnapshot playersSnapshot : dataSnapshot.getChildren()){
                    if (playersSnapshot.getValue(Player.class).getUserId().equals(user.getUid()) && playersSnapshot.getValue(Player.class).getPermission().equals("user")){
                        hasPermissionToChangeData = false;
                        Toast.makeText(ChooseGymActivity.this, "Changed permission to: " + hasPermissionToChangeData, Toast.LENGTH_SHORT).show();
                    }
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        invalidateOptionsMenu();
    }

}
