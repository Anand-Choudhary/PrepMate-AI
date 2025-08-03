package com.example.auth_service.PrepMate_AI.usermanagement.db.models;

import com.example.auth_service.PrepMate_AI.db.models.BaseModel;
import com.example.auth_service.PrepMate_AI.utility.Status;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;


@Entity
@Table(name = "pm_user")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class User extends BaseModel {

    @NotNull
    @Column(name = "email", unique = true, nullable = false)
    private String email;

    @Column(name = "name")
    private String name;

    @Column(name ="password")
    private String password;

    @Column(name = "date_of_birth")
    private Date dateOfBirth;

    @Column(name="phone_number")
    private String phoneNumber;

    @Column(name="activation_token")
    private String activationToken;

    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    private Status accountStatus;

    @Column(name ="years_of_experience")
    private Integer experience;

    @Column(name = "login_count")
    private Integer loginCount;

    @Column(name = "login_status")
    private Status loginStatus;

    @Column(name = "auth_token")
    private String token;
}
