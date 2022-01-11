package com.household.service;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.household.exception.DuplicatedException;
import com.household.exception.NotFoundException;
import com.household.mapper.UserMapper;
import com.household.model.dto.LoginRequestDto;
import com.household.model.dto.UserRequestDto;
import com.household.model.entity.User;
import com.household.utils.PasswordEncrypt;
import java.util.Optional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {

    @InjectMocks
    UserService userService;

    @Mock
    UserMapper userMapper;

    @Mock
    JWTService jwtService;

    UserRequestDto userRequestDto;
    LoginRequestDto loginRequestDto;
    User user;

    @BeforeEach
    public void makeUser() {
        userRequestDto = UserRequestDto.builder()
            .email("email@email.com")
            .password("password")
            .build();

        loginRequestDto = LoginRequestDto.builder()
            .email("email@email.com")
            .password("password")
            .build();

        user = User.builder()
            .id(1L)
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

    @Test
    @DisplayName("로그인에 성공합니다.")
    public void loginTestWhenSuccess() {
        when(userMapper.selectUserByEmail(loginRequestDto.getEmail())).thenReturn(
            Optional.of(user));
        when(jwtService.createToken(user.getId())).thenReturn("token");

        userService.login(loginRequestDto);
        verify(userMapper).selectUserByEmail(loginRequestDto.getEmail());
        verify(jwtService).createToken(user.getId());
    }

    @Test
    @DisplayName("로그인에 실패합니다 :존재하지 않는 이메일.")
    public void loginTestWhenFail() {
        when(userMapper.selectUserByEmail(loginRequestDto.getEmail())).thenReturn(
            Optional.empty());
        assertThrows(NotFoundException.class, () -> userService.login(loginRequestDto));
        verify(userMapper).selectUserByEmail(loginRequestDto.getEmail());
    }
}