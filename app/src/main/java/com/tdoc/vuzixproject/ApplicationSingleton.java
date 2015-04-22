package com.tdoc.vuzixproject;

import android.app.Application;

import com.parse.Parse;

/**
 * Created by KET on 22-04-2015.
 */
public class ApplicationSingleton extends Application {
    private static ApplicationSingleton ourInstance = new ApplicationSingleton();

    public static ApplicationSingleton getInstance() {
        return ourInstance;
    }

    private ApplicationSingleton() {

        // Enable Local Datastore. Currently not used and creates issues due to running before .initialize for some reason.
        //Parse.enableLocalDatastore(this);

        // Initialize Parse.com access, probably with user and project hashes.
        Parse.initialize(this, "sbnDtByNJrzgQXik8HRac2HyUVhqkigKUOcbQ52g", "oTAXhgq4M8qHcvAfAxJKRQ07DyP2zJz1phdeut8r");

    }
}