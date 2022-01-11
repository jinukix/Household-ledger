package com.household.mapper;

import com.household.model.entity.User;
import java.util.Optional;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper {

    void insertUser(User user);

    boolean isExistsEmail(String email);

    Optional<User> selectUserByEmail(String email);
}
