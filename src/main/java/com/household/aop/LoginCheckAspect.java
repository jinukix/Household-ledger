package com.household.aop;

import com.household.service.JWTService;
import lombok.RequiredArgsConstructor;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Aspect
@Component
@RequiredArgsConstructor
public class LoginCheckAspect {

    private final JWTService jwtService;

    @Before("@annotation(com.household.annotation.LoginCheck)")
    public Long loginCheck() {
        return jwtService.loginAuth();
    }
}
