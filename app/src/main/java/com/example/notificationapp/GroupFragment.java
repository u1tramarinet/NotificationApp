package com.example.notificationapp;

import android.app.Notification;
import android.app.NotificationManager;
import android.os.Bundle;
import android.service.notification.StatusBarNotification;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

public class GroupFragment extends BaseFragment {

    private String groupId = "notificationGroup";

    private String channelId = "notificationTest03";
    private String channelTitle = "GroupNotification";
    private String channelDescription = "This is a group notification test.";

    private String channelGroupId = "GroupNotification";
    private String channelGroupName = "GroupNotification";

    private int notificationId = 30000;
    private int idCount = 1;

    private String notificationTitle = "Test";
    private String notificationContentText = "This is test.";

    public GroupFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_group, container, false);
        Button add = root.findViewById(R.id.add);
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                add();
            }
        });
        Button summarize = root.findViewById(R.id.summarize);
        summarize.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                summarize();
            }
        });
        Button reset = root.findViewById(R.id.reset);
        reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                reset();
            }
        });
        initializeNotification(channelId, channelTitle, channelDescription, channelGroupId, channelGroupName);
        return root;
    }

    private void add() {
        int notificationId = this.notificationId + idCount;
        Notification.Builder builder = buildNotification(
                channelId, notificationTitle, notificationContentText + "(" + notificationId + ")", groupId);
        notifyNotification(notificationId, builder);
        idCount++;
    }

    private void summarize() {
        if (notificationExists(notificationId)) {
            cancelNotification(notificationId);
        } else {
            Notification.Builder builder = buildNotification(
                    channelId, "Summary", "It is a summary.", groupId)
                    .setGroupSummary(true);
            notifyNotification(notificationId, builder);
        }
    }

    private void reset() {
        for (int i = 0; i < idCount; i++) {
            int notificationId = this.notificationId + i;
            if (notificationExists(notificationId)) {
                cancelNotification(notificationId);
            }
        }
    }

    private boolean notificationExists(int notificationId) {
        NotificationManager manager = getNotificationManager();
        StatusBarNotification[] notifications = manager.getActiveNotifications();
        for (StatusBarNotification notification : notifications) {
            if (notification.getId() == notificationId) {
                return true;
            }
        }
        return false;
    }
}
