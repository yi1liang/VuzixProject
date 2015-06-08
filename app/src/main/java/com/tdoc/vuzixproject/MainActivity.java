package com.tdoc.vuzixproject;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.WindowManager;


public class MainActivity extends Activity {
    public static boolean scannerIntentRunning = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.i("onCreate", "onCreate run");
        setContentView(R.layout.activity_main);


        //gestSensor = new GestureController(this);


        // Add first fragment
        if (savedInstanceState == null) {
            LoginFragment fragment = new LoginFragment();
            getFragmentManager().beginTransaction()
                    .add(R.id.fragmentcontainer, fragment, "FRAG_LOGIN")
                    .commit();
            if (ApplicationSingleton.isThereVoice) ApplicationSingleton.getVoiceCtrl().setCallingFragment(fragment);
        }

        // Parse.com test data push
        //ParseObject testObject = new ParseObject("LoginCreds");
        //testObject.put("name", "Ketil Kirchhof");
        //testObject.put("data2", 002);
        //testObject.saveInBackground();

        // Keep screen on (requires WAKE_LOCK permission in manifest)
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
    }

    @Override // Currently not used, might never be, due to restricted controls
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override // Currently not used, might never be, due to restricted controls
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


    /***********************
     * Ensure that activity doesn't keep listening when not in focus, and clean up once destroyed.
     **********************/
    @Override
    protected void onResume(){
        super.onResume();
        if (ApplicationSingleton.isThereVoice) ApplicationSingleton.getVoiceCtrl().on();
        //gestSensor.register();
    }

    @Override
    protected void onPause(){
        super.onPause();
        if (ApplicationSingleton.isThereVoice && !scannerIntentRunning) ApplicationSingleton.getVoiceCtrl().off();
        //gestSensor.unregister();
    }

    @Override
    protected void onDestroy(){
        super.onDestroy();
        if (ApplicationSingleton.getVoiceCtrl() != null) ApplicationSingleton.getVoiceCtrl().destroy();
        //if (gestSensor != null) gestSensor = null;
    }
}
