package com.ak.api.services;

import java.util.List;

import com.ak.dto.UserDto;
import com.ak.entities.User;

public interface IUserService {

    public List<UserDto> getAllUsersDto();

    UserDto addUser(UserDto userDto);

    User getUserById(long userId);

    UserDto getUserDtoById(long userId);

    String getCurrentUserName();

    User getCurrentUser();

    UserDto getUserDtoByUsername(String username);

    UserDto getUserDtoByEmail(String email);

    void updateUser(UserDto userDto);

    void delete();

    void setEnabled(long userId, boolean enabled);

}
