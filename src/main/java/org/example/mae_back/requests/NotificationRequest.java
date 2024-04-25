package org.example.mae_back.requests;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class NotificationRequest {

    private String token;
    private String title;
    private String body;


}
