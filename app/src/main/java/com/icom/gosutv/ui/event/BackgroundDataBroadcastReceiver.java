package com.icom.gosutv.ui.event;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import org.json.JSONObject;

/**
 * Created by Trung on 9/22/2015.
 */
public class BackgroundDataBroadcastReceiver extends BroadcastReceiver
{

    // This onReceive will be call when a OneSignal Background Data Notification is received(before clicking) by the device.
    // You can read the additionalData and do anything you need here with it.
    // You may consider adding a wake lock here if you need to make sure the devices doesn't go to sleep while processing.
    // The following must also be in your AndroidManifest.xml for this to fire:
    /*
	 <receiver
	    android:name="com.onesignal.example.BackgroundDataBroadcastReceiver"
	    android:exported="false">
		<intent-filter>
	    	<action android:name="com.onesignal.BackgroundBroadcast.RECEIVE" />
	 	</intent-filter>
	 </receiver>

	 Make sure to keep android:exported="false" so other apps can't call can this.
	*/
    @Override
    public void onReceive(Context context, Intent intent)
    {
        Bundle dataBundle = intent.getBundleExtra("data");

        try
        {
            Log.i("OneSignalExample", "Notification content: " + dataBundle.getString("alert"));
            Log.i("OneSignalExample", "Notification title: " + dataBundle.getString("title"));
            Log.i("OneSignalExample", "Is Your App Active: " + dataBundle.getBoolean("isActive"));

            JSONObject customJSON = new JSONObject(dataBundle.getString("custom"));
            if (customJSON.has("a"))
            {
                Log.i("OneSignalExample", "additionalData: " + customJSON.getJSONObject("a").toString());
            }
        }
        catch (Throwable t)
        {
            t.printStackTrace();
        }
    }
}