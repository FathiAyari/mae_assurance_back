package org.example.mae_back.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {
private Integer id ;
private String name;
private String last_name;
private String gender;
private String phone_number;
private String email;
private String region;
private String token;
}
