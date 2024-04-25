package org.example.mae_back.requests;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@Getter
@AllArgsConstructor
@EqualsAndHashCode
@ToString
public class RegistrationRequest {
private final String name;
private final String last_name;
private final String gender;
private final String phone_number;
private final String email;
private final String region;
private final String password;
private final String token;
}
