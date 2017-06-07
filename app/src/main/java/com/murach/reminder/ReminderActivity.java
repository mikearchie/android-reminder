package com.murach.reminder;

import android.app.Service;
import android.content.Intent;
import android.os.Bundle;
import android.app.Activity;
import android.os.IBinder;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

public class ReminderActivity extends Activity implements OnClickListener {

    private Button startServiceButton;
    private Button stopServiceButton;

    private Intent serviceIntent;
    
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_reminder);
		
        startServiceButton = (Button) findViewById(R.id.startServiceButton);
        stopServiceButton = (Button) findViewById(R.id.stopServiceButton);
        
        startServiceButton.setOnClickListener(this);
        stopServiceButton.setOnClickListener(this);

        serviceIntent = new Intent(this, ReminderService.class);
	}

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
        	case R.id.startServiceButton:
                startService(serviceIntent);
        	    Toast.makeText(this, "Service started.", Toast.LENGTH_SHORT)
                        .show();
        		break;
        	case R.id.stopServiceButton:
        		// put code to stop service and display toast here
                stopService(serviceIntent);
                Toast.makeText(this, "Service stopped.", Toast.LENGTH_SHORT)
                        .show();
                break;
        }
    }
}