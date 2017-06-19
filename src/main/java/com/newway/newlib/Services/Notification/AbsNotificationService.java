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

    public void dismiss(int id) {
        // This ForEach Statement produces runtime eventbus exception
        for (int i = 0; i < mNotifications.size(); i++) {
            if (mNotifications.get(i).getId() == id) {
                NotificationManager notificationManager = (NotificationManager) mContext.getApplicationContext().getSystemService(Context.NOTIFICATION_SERVICE);
                notificationManager.cancel(id);
                mNotifications.remove(mNotifications.get(i));
            }
        }
    }


    protected int generateNewId() {
        return mNotifications.size() + 1;
    }
}
