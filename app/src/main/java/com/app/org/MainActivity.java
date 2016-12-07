package com.app.org;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private Button mapButton, quitButton, camButton;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mapButton = (Button) findViewById(R.id.mapButton);
        camButton = (Button) findViewById(R.id.camButton);
        quitButton = (Button) findViewById(R.id.quitButton);

        mapButton.setOnClickListener(this);
        camButton.setOnClickListener(this);
        quitButton.setOnClickListener(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.options) {
            Intent start = new Intent(this, OptionsActivity.class);
            startActivity(start);
        } else if (item.getItemId() == R.id.quit)
            System.exit(0);
        else
            return super.onOptionsItemSelected(item);
        return true;
    }

    @Override
    public void onClick(View view) {
        if (view == mapButton) {
           Intent start = new Intent(this, MapActivity.class);
           startActivity(start);
        } else if (view == camButton) {
            Intent start = new Intent(this, CamActivity.class);
            startActivity(start);
        } else if (view == quitButton)
            finish();
    }

    @Override
    public void onPanelClosed(int featureId, Menu menu) {
        super.onPanelClosed(featureId, menu);
    }
}
