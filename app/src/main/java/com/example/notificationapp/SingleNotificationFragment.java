package com.example.notificationapp;

import android.app.Notification;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;

public class SingleNotificationFragment extends BaseFragment {
    private String channelId = "notificationTest01";
    private String channelTitle = "SingleNotification";
    private String channelDescription = "This is a single notification test.";

    private String channelGroupId = "SingleNotification";
    private String channelGroupName = "SingleNotification";

    private int notificationId = 10001;

    private String notificationTitle = "Test";
    private String notificationTitleUpdated = "Test(updated)";
    private String notificationContentText = "This is test.";

    public SingleNotificationFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_single_notification, container, false);
        initialize(root);
        return root;
    }

    private void initialize(@NonNull View root) {
        Button notifyButton = root.findViewById(R.id.notify);
        notifyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Notification.Builder builder = buildNotification(channelId, notificationTitle, notificationContentText, null);
                notifyNotification(notificationId, builder);
            }
        });
        Button updateButton = root.findViewById(R.id.update);
        updateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Notification.Builder builder = buildNotification(channelId, notificationTitleUpdated, notificationContentText, null);
                notifyNotification(notificationId, builder);
            }
        });
        Button cancelButton = root.findViewById(R.id.cancel);
        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cancelNotification(notificationId);
            }
        });
        Button resetButton = root.findViewById(R.id.reset);
        resetButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cancelNotification(notificationId);
            }
        });

        initializeNotification(channelId, channelTitle, channelDescription, channelGroupId, channelGroupName);
    }
}
