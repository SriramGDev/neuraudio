package com.goperez.neuraudio;

import android.app.ActivityManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import java.util.Iterator;
import java.util.List;

import static android.content.Context.ACTIVITY_SERVICE;

/**
 * Created by sggan on 2/9/2017.
 */

public class ResponseReceiver extends BroadcastReceiver {
    public static final String ACTION_RESP = "com.goperez.neuraudio.MESSAGE_PROCESSED";
    public Context context;

    public ResponseReceiver(Context context) {
        this.context = context;
    }

    @Override
    public void onReceive(Context context, Intent intent) {

        ActivityManager am = (ActivityManager) context.getSystemService(ACTIVITY_SERVICE);
        List<ActivityManager.RunningAppProcessInfo> runningAppProcesses = am.getRunningAppProcesses();

        Iterator<ActivityManager.RunningAppProcessInfo> iter = runningAppProcesses.iterator();

        while(iter.hasNext()){
            ActivityManager.RunningAppProcessInfo next = iter.next();

            String processName = context.getPackageName() + ":service";

            if(next.processName.equals(processName)){
                android.os.Process.killProcess(next.pid);
                break;
            }
        }

        Intent startPlayService = new Intent(context, PlayService.class);
        context.startService(startPlayService);
    }
}