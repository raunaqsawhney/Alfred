package com.engineeringx.alfred;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.Window;

import com.parse.GetCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;

/**
 * Created by raunaqsawhney on 6/7/15.
 */
public class PatientDetailActivity extends Activity {

    private Patient patient;
    private String patientObjectID;
    private String patientFirstName;
    private String patientLastName;
    private int patientAge;
    private String patientGender;
    private int patientHeartRate;
    private int patientBodyTemperature;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_INDETERMINATE_PROGRESS);
        setContentView(R.layout.activity_main);

        // Catch the intent from MainActivity to show patient detail information
        Intent intent = this.getIntent();

        parseIntent(intent);
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

    private void parseIntent(Intent intent) {

        if (intent.getExtras() != null) {
            patientObjectID = intent.getStringExtra("patientID");

            populatePatientDetail(patientObjectID);
        }
    }

    private void populatePatientDetail(String patientObjectID) {
        ParseQuery<ParseObject> query = ParseQuery.getQuery("PatientObject");
        query.getInBackground(patientObjectID, new GetCallback<ParseObject>() {
            public void done(ParseObject p, ParseException e) {
                if (e == null) {
                    System.out.println(p.getString("objectId") + p.getString("firstName") +
                            p.getString("lastName") + p.getInt("age") +
                            p.getString("gender") + p.getInt("heartRate") +
                            p.getInt("bodyTemperature"));
                } else {
                    Log.d(getClass().getSimpleName(), "Error: " + e.getMessage());
                }
            }
        });
    }
}

