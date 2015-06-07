package com.engineeringx.alfred;

import android.app.Activity;
import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends ListActivity {

    /*
     * Class variables go here
     */

    private List<Patient> patients;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_INDETERMINATE_PROGRESS);

        setContentView(R.layout.activity_main);

        fetchDataFromParse();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
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

    private void fetchDataFromParse() {

        patients = new ArrayList<Patient>();
        ArrayAdapter<Patient> patientListAdapter = new ArrayAdapter<Patient>(this,
                R.layout.patient_list_item, R.id.patientName, patients);
        setListAdapter(patientListAdapter);

        refreshPatientList();
    }

    private void refreshPatientList() {
        ParseQuery<ParseObject> query = ParseQuery.getQuery("PatientObject");

        setProgressBarIndeterminateVisibility(true);
        query.findInBackground(new FindCallback<ParseObject>() {

            @Override
            public void done(List<ParseObject> patientList, ParseException e) {
                setProgressBarIndeterminateVisibility(false);
                if (e == null) {
                    // If there are results, update the list of posts
                    // and notify the adapter
                    patients.clear();
                    for (ParseObject patient : patientList) {
                        Patient p = new Patient(patient.getString("objectId"), patient.getString("firstName"), patient.getString("lastName"),
                                patient.getInt("age"), patient.getString("gender"), patient.getInt("heartRate"),
                                patient.getInt("bodyTemperature"));
                        System.out.println("PATIENT: " + p.getFirstName());
                        patients.add(p);
                    }
                    ((ArrayAdapter<Patient>) getListAdapter()).notifyDataSetChanged();
                } else {
                    Log.d(getClass().getSimpleName(), "Error: " + e.getMessage());
                }
            }
        });
    }

    @Override
    protected void onListItemClick(ListView l, View v, int position, long id) {

        Patient p = patients.get(position);
        Intent intent = new Intent(this, PatientDetailActivity.class);
        intent.putExtra("patientId", p.getPatientID());
        startActivity(intent);
    }
}
