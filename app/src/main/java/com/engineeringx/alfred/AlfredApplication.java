package com.engineeringx.alfred;

import android.app.Application;

import com.parse.Parse;
import com.parse.ParseACL;
import com.parse.ParseCrashReporting;
import com.parse.ParseObject;
import com.parse.ParseUser;

/**
 * Created by raunaqsawhney on 6/7/15.
 */
public class AlfredApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        // Initialize Crash Reporting.
        ParseCrashReporting.enable(this);

        // Enable Local Datastore.
        Parse.enableLocalDatastore(this);

        // Initialize parse connection
        Parse.initialize(this, "kKW7oJS0nwEG4V6f3LvYooU5BQxFnH6eZ9aS31A3", "9OYwyRgiH7g9rPAPYD7qq5wAYlBdLIvkz9c4UzwG");
    }
}
