package com.newway.newlib.Services.Notification;

import android.app.AlarmManager;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.util.Log;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.List;

import static android.content.Context.ALARM_SERVICE;

/**
 * Created by goldm on 09/04/2017.
 */

public abstract class AbsNotificationService {

    protected NotificationManager mNotificationManager;
    protected Context mContext;
    protected List<AbsNotificationContainer> mNotifications;


    protected AbsNotificationService(Context context) {
        mNotifications = new ArrayList<>();
        mContext = context.getApplicationContext();
        mNotificationManager = (NotificationManager) mContext.getSystemService(Context.NOTIFICATION_SERVICE);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O)
            createChannels();
    }

    protected abstract void createChannels();


    protected void setAlarm(long millis, Intent intent) {

        PendingIntent pendingIntent = PendingIntent.getBroadcast(mContext, 120, intent, 0);

        AlarmManager alarmManager = (AlarmManager) mContext.getSystemService(ALARM_SERVICE);
        // alarmManager.set(AlarmManager.RTC_WAKEUP, System.currentTimeMillis() + millis, pendingIntent);
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.KITKAT)
            alarmManager.set(AlarmManager.RTC_WAKEUP, millis, pendingIntent);
        else
            alarmManager.setExact(AlarmManager.RTC_WAKEUP, millis, pendingIntent);
        System.out.println("Time Total ----- " + (System.currentTimeMillis() + millis));

    }

    protected void cancelAlarm(Intent intent) {
        AlarmManager alarmManager = (AlarmManager) mContext.getSystemService(ALARM_SERVICE);

        PendingIntent pendingIntent = PendingIntent.getBroadcast(mContext, 120, intent, 0);
        alarmManager.cancel(pendingIntent);

    }


    protected long getNearestHour(Calendar... executeTimes) {
        //must use current system time
        List<Long> timeList = new ArrayList<>();
        for (Calendar cal : executeTimes) {
            timeList.add(cal.getTimeInMillis());
        }
        Collections.sort(timeList);

        Integer nearestHourIndex = null;
        long currentTime = System.currentTimeMillis();
        for (int i = 0; i < timeList.size(); i++)
            if (timeList.get(i) - currentTime > 0) {
                nearestHourIndex = i;
                Log.d("Hour" + i, " TRUE");
                break;
            }
        if (nearestHourIndex != null)
            return timeList.get(nearestHourIndex);
        else {
            Log.d("Not Found", "TRUE");
            executeTimes[0].add(Calendar.DAY_OF_MONTH, 1);
            return executeTimes[0].getTimeInMillis();
        }
    }

    public void notify(AbsNotificationContainer notificationContainer) {
        if (notificationContainer.getId() == -1)
            notificationContainer.setId(generateNewId());
        mNotifications.add(notificationContainer);
        mNotificationManager.notify(notificationContainer.getId(), notificationContainer.getNotification());
    }

    public void dismissAll() {
        mNotifications.clear();
        mNotificationManager.cancelAll();
    }

    public void dismiss(int id) {
        // This ForEach Statement produces runtime eventbus exception
        for (int i = 0; i < mNotifications.size(); i++) {
            if (mNotifications.get(i).getId() == id) {
                mNotifications.remove(mNotifications.get(i));
            }
        }
        mNotificationManager.cancel(id);
    }


    protected int generateNewId() {
        return mNotifications.size() + 1;
    }
}
