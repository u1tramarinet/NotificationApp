package com.example.notificationapp;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationChannelGroup;
import android.app.NotificationManager;
import android.os.Build;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.core.util.Preconditions;
import androidx.fragment.app.Fragment;

import java.util.Objects;

/**
 * NotificationChannel:Category of notifications. setting by {@link NotificationChannel}'s constructor.
 * NotificationChannelGroup:Category of Channels. setting by {@link NotificationChannel#setGroup(String)}
 * NotificationGroup:Group of notifications. setting by {@link Notification.Builder#setGroup(String)}
 */
public abstract class BaseFragment extends Fragment {

    private static final String CHANNEL_ID = "channel id";
    private static final String CHANNEL_TITLE = "channel title";
    private static final String CHANNEL_DESCRIPTION = "channel description";
    private static final String GROUP_ID = "com.example.notificationapp";
    private static final String GROUP_NAME = "Notification App";

    public BaseFragment() {
        // Required empty public constructor
    }

    protected Notification.Builder buildNotification(@NonNull String channelId,
                                                   @NonNull String title,
                                                   @NonNull String contentText,
                                                   @Nullable String groupKey) {
        Notification.Builder builder;
        if (isSdkVersionLaterThan(Build.VERSION_CODES.O)) {
            builder = new Notification.Builder(getContext(), channelId);
        } else {
            builder = new Notification.Builder(getContext());
        }

        builder.setSmallIcon(android.R.drawable.ic_menu_today)
                .setContentTitle(title)
                .setContentText(contentText)
                .setPriority(Notification.PRIORITY_DEFAULT);

        if (groupKey != null) {
            builder.setGroup(groupKey);
        }

        return builder;
    }

    protected void notifyNotification(int notificationId, @NonNull Notification.Builder builder) {
        notifyNotification(notificationId, builder.build());
    }

    protected void notifyNotification(int notificationId, @NonNull Notification notification) {
        NotificationManager manager = getNotificationManager();
        manager.notify(notificationId, notification);
    }

    protected void cancelNotification(int notificationId) {
        NotificationManager manager = getNotificationManager();
        manager.cancel(notificationId);
    }

    protected void cancelAllNotification() {
        NotificationManager manager = getNotificationManager();
        manager.cancelAll();
    }

    protected void initializeNotification() {
        initializeNotification(GROUP_ID, GROUP_NAME, CHANNEL_ID, CHANNEL_TITLE, CHANNEL_DESCRIPTION);
    }

    protected void initializeNotification(@NonNull String channelId,
                                          @NonNull String channelTitle,
                                          @NonNull String channelDescription,
                                          @Nullable String groupId,
                                          @Nullable String groupName) {

        if (isSdkVersionLaterThan(Build.VERSION_CODES.O)) {
            NotificationChannelGroup group = createNotificationChannelGroup(groupId, groupName);
            createNotificationChannel(channelId, channelTitle, channelDescription, group);
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    protected NotificationChannelGroup createNotificationChannelGroup(@Nullable String groupId,
                                                  @Nullable String groupName) {
        if ((groupId == null) || (groupName == null)) {
            return null;
        }

        NotificationChannelGroup group = new NotificationChannelGroup(groupId, groupName);
        NotificationManager manager = getNotificationManager();
        manager.createNotificationChannelGroup(group);
        return group;
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    protected void createNotificationChannel(@NonNull String channelId,
                                             @NonNull String channelTitle,
                                             @NonNull String channelDescription,
                                             @Nullable NotificationChannelGroup group) {
        NotificationChannel channel = new NotificationChannel(channelId, channelTitle, NotificationManager.IMPORTANCE_DEFAULT);
        channel.setDescription(channelDescription);
        if (group != null) {
            channel.setGroup(group.getId());
        }
        NotificationManager manager = getNotificationManager();
        manager.createNotificationChannel(channel);
    }

    @NonNull
    protected NotificationManager getNotificationManager() {
        NotificationManager manager = Objects.requireNonNull(getContext()).getSystemService(NotificationManager.class);
        Preconditions.checkNotNull(manager);
        return manager;
    }

    protected boolean isSdkVersionLaterThan(int version) {
        return Build.VERSION.SDK_INT >= version;
    }
}
