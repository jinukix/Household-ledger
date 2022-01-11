package com.household.service;

import com.household.exception.DuplicatedException;
import com.household.mapper.UserMapper;
import com.household.model.dto.UserRequestDto;
import com.household.utils.PasswordEncrypt;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserMapper userMapper;

    @Transactional(readOnly = true)
    public boolean isExistsEmail(String email) {
        return userMapper.isExistsEmail(email);
    }

    public void signUp(UserRequestDto userRequestDto) {
        if (isExistsEmail(userRequestDto.getEmail())) {
            throw new DuplicatedException("This email already exists.");
        }

        String encryptPassword = PasswordEncrypt.encrypt(userRequestDto.getPassword());
        userRequestDto.setPassword(encryptPassword);
        userMapper.insertUser(userRequestDto.toEntity());
    }
}
