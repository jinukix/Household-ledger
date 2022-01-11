package com.household.model.entity;

import java.sql.Timestamp;
import lombok.Builder;

@Builder
public class User {

    private Long id;
    private String email;
    private String password;
    private Timestamp createdAt;
    private Timestamp updatedAt;
}
