package com.newway.newlib.Services.Notification;

import android.app.NotificationManager;
import android.content.Context;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by goldm on 09/04/2017.
 */

public abstract class AbsNotificationService {

    protected Context mContext;
    protected List<AbsNotificationContainer> mNotifications;


    protected AbsNotificationService(Context context) {
        mNotifications = new ArrayList<>();
        mContext = context.getApplicationContext();
    }


    public void notify(AbsNotificationContainer notificationContainer) {
        if (notificationContainer.getId() == -1)
            notificationContainer.setId(generateNewId());
        mNotifications.add(notificationContainer);
        NotificationManager mNotificationManager = (NotificationManager) mContext.getApplicationContext().getSystemService(Context.NOTIFICATION_SERVICE);
        mNotificationManager.notify(notificationContainer.getId(), notificationContainer.getNotification());
    }


    protected int generateNewId() {
        return mNotifications.size() + 1;
    }
}
