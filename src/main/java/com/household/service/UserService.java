package com.household.service;

import com.household.exception.DuplicatedException;
import com.household.exception.NotFoundException;
import com.household.mapper.UserMapper;
import com.household.model.dto.LoginRequestDto;
import com.household.model.dto.UserRequestDto;
import com.household.model.entity.User;
import com.household.utils.PasswordEncrypt;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserMapper userMapper;
    private final JWTService jwtService;

    @Transactional(readOnly = true)
    public boolean isExistsEmail(String email) {
        return userMapper.isExistsEmail(email);
    }

    @Transactional(readOnly = true)
    public User selectUserByEmail(String email) {
        return userMapper.selectUserByEmail(email)
            .orElseThrow(() -> new NotFoundException("Select Not Found User."));
    }

    public void signUp(UserRequestDto userRequestDto) {
        if (isExistsEmail(userRequestDto.getEmail())) {
            throw new DuplicatedException("This email already exists.");
        }

        String encryptPassword = PasswordEncrypt.encrypt(userRequestDto.getPassword());
        userRequestDto.setPassword(encryptPassword);
        userMapper.insertUser(userRequestDto.toEntity());
    }

    public String login(LoginRequestDto loginRequestDto) {
        User user = selectUserByEmail(loginRequestDto.getEmail());
        if (PasswordEncrypt.isMatch(loginRequestDto.getPassword(), user.getPassword())) {
            return jwtService.createToken(user.getId());
        } else {
            throw new IllegalArgumentException("Your password is invalid.");
        }
    }
}
