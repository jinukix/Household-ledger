package com.household.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.household.exception.DuplicatedException;
import com.household.mapper.UserMapper;
import com.household.model.dto.UserRequestDto;
import com.household.model.entity.User;
import com.household.utils.PasswordEncrypt;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {

    @Mock
    UserMapper userMapper;

    @InjectMocks
    UserService userService;

    UserRequestDto userRequestDto;

    @BeforeEach
    public void makeUser() {
        userRequestDto = UserRequestDto.builder()
            .email("email@email.com")
            .password(PasswordEncrypt.encrypt("password"))
            .build();
    }

    @Test
    @DisplayName("회원가입에 성공합니다.")
    public void signUpTestWhenSuccess() {
        when(userMapper.isExistsEmail(userRequestDto.getEmail())).thenReturn(false);
        userService.signUp(userRequestDto);
        verify(userMapper).isExistsEmail(userRequestDto.getEmail());
        verify(userMapper).insertUser(any(User.class));
    }

    @Test
    @DisplayName("회원가입에 실패합니다. :중복된 이메일")
    public void signUpTestWhenFail() {
        when(userMapper.isExistsEmail(userRequestDto.getEmail())).thenReturn(true);
        assertThrows(DuplicatedException.class, () -> userService.signUp(userRequestDto));
        verify(userMapper).isExistsEmail(userRequestDto.getEmail());
    }
}