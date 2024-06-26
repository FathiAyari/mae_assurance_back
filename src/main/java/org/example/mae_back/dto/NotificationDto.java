package org.example.mae_back.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class NotificationDto {
  Integer id;
  String notification;
  String date;
  UserDto sender;
  UserDto receiver;
  String type;
}
