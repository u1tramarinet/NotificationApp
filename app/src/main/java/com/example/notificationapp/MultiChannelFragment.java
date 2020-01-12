package com.example.notificationapp;


import android.app.Notification;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;

public class MultiChannelFragment extends BaseFragment {

    private String channelId01 = "notificationTest02_01";
    private String channelId02 = "notificationTest02_02";
    private String channelTitle01 = "MultiChannelNotification 01";
    private String channelTitle02 = "MultiChannelNotification 02";
    private String channelDescription01 = "This is a multi channel notification test(01).";
    private String channelDescription02 = "This is a multi channel notification test(02).";

    private String channelGroupId = "MultiChannelNotification";
    private String channelGroupName = "MultiChannelNotification";

    private int notificationId01 = 20001;
    private int notificationId02 = 20002;

    private String notificationTitle = "Test";
    private String notificationTitleUpdated = "Test(updated)";
    private String notificationContentText01 = "This is test.(from channel 01)";
    private String notificationContentText02 = "This is test.(from channel 02)";

    public MultiChannelFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_multi_channel, container, false);
        View containerC1 = root.findViewById(R.id.controller1);
        initialize(containerC1,
                channelId01, channelTitle01, channelDescription01,
                notificationId01,
                notificationTitle, notificationTitleUpdated, notificationContentText01);
        View containerC2 = root.findViewById(R.id.controller2);
        initialize(containerC2,
                channelId02, channelTitle02, channelDescription02,
                notificationId02,
                notificationTitle, notificationTitleUpdated, notificationContentText02);
        return root;
    }

    private void initialize(@NonNull View root,
                            @NonNull final String channelId, @NonNull final String channelTitle, @NonNull final String channelDescription,
                            final int notificationId,
                            @NonNull final String notificationTitle, @NonNull final String notificationTitleUpdated, @NonNull final String notificationContentText) {
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
