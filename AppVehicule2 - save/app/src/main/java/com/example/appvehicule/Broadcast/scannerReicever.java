package com.example.appvehicule.Broadcast;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.telecom.ConnectionService;
import android.telephony.SmsMessage;
import android.widget.Toast;

public class scannerReicever extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        if (Intent.ACTION_BOOT_COMPLETED.equals(intent.getAction())){
            Toast.makeText(context, "ouille", Toast.LENGTH_LONG).show();
        }

        if (ConnectionService.CONNECTIVITY_SERVICE.equals(intent.getAction())){
            Toast.makeText(context, "Change", Toast.LENGTH_SHORT).show();
        }
    }
}
