package com.newway.newlib.Services.Notification;

import android.app.Notification;

/**
 * Created by goldm on 09/04/2017.
 */

public abstract class AbsNotificationContainer {

    protected Notification mNotification;
    protected int mId;

    public AbsNotificationContainer(Notification notification) {
        mNotification = notification;
        mId = -1;
    }

    public AbsNotificationContainer(Notification notification, int id) {
        mNotification = notification;
        mId = id;
    }

    public Notification getNotification() {
        return mNotification;
    }

    protected void setId(int id) {
        mId = id;
    }

    public int getId() {
        return mId;
    }
}
