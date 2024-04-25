package org.example.mae_back.dto_convertor;

import lombok.AllArgsConstructor;
import lombok.ToString;
import org.example.mae_back.data.RoleRepository;
import org.example.mae_back.dto.UserDto;
import org.example.mae_back.models.Role;
import org.example.mae_back.models.User;
import org.springframework.stereotype.Component;
@ToString
@AllArgsConstructor
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
    userDto.setStatus(user.getStatus());
    userDto.setRole(user.getRole().getLabel());
    return userDto;
  }



}
