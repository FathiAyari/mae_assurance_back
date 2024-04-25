package org.example.mae_back.dto_convertor;

import org.example.mae_back.dto.UserDto;
import org.example.mae_back.models.User;
import org.springframework.stereotype.Component;

@Component
public class UserConvertor {

  public static UserDto userToDto(User user) {
    UserDto userDto = new UserDto();
    userDto.setId(user.getId());
    userDto.setName(user.getName());
    userDto.setLast_name(user.getLast_name());
    userDto.setEmail(user.getEmail());
    userDto.setRegion(user.getRegion());
    userDto.setGender(user.getGender());
    userDto.setPhone_number(user.getPhone_number());
    userDto.setToken(user.getToken());
    return userDto;
  }



}
