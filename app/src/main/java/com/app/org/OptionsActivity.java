package com.app.org;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.CompoundButton;
import android.widget.Switch;

public class OptionsActivity extends AppCompatActivity implements CompoundButton.OnCheckedChangeListener {
    private Switch soundSwitch, musicSwitch, onlineSwitch;

    private SharedPreferences preferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_options);

        soundSwitch = (Switch) findViewById(R.id.switchSound);
        musicSwitch = (Switch) findViewById(R.id.switchMusic);
        onlineSwitch = (Switch) findViewById(R.id.switchOnline);

        preferences = PreferenceManager.getDefaultSharedPreferences(this);
        musicSwitch.setChecked(preferences.getBoolean("music", false));
        soundSwitch.setChecked(preferences.getBoolean("sound", false));
        onlineSwitch.setChecked(preferences.getBoolean("online", false));

        soundSwitch.setOnCheckedChangeListener(this);
        musicSwitch.setOnCheckedChangeListener(this);
        onlineSwitch.setOnCheckedChangeListener(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        getMenuInflater().inflate(R.menu.options, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.ok)
            finish();
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {

        if (compoundButton == musicSwitch) {
            Log.d("Switch", "music " + musicSwitch.isChecked());
            if (isChecked) {
                preferences.edit().putBoolean("music", true).apply();
            } else {
                preferences.edit().putBoolean("music", false).apply();
            }
        }

        else if  (compoundButton == soundSwitch) {
            Log.d("Switch", "sound " + soundSwitch.isChecked());
            if (isChecked)
                preferences.edit().putBoolean("sound", true).apply();
            else
                preferences.edit().putBoolean("sound", false).apply();
        }

        else if  (compoundButton == onlineSwitch) {
            Log.d("Switch", "online " + onlineSwitch.isChecked());
            if (isChecked)
                preferences.edit().putBoolean("online", true).apply();
            else
                preferences.edit().putBoolean("online", false).apply();
        }
    }
}
