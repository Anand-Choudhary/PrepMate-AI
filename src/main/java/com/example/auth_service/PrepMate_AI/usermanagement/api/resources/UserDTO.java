package com.example.auth_service.PrepMate_AI.usermanagement.api.resources;

import com.example.auth_service.PrepMate_AI.usermanagement.utility.ValidEmail;
import com.example.auth_service.PrepMate_AI.usermanagement.utility.ValidPassword;
import com.example.auth_service.PrepMate_AI.utility.Status;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import jakarta.persistence.Column;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserDTO implements Serializable
{
    private Long id;

    @NotNull(message = "Email cannot be null")
    @ValidEmail(message = "Invalid email format")
    private String email;

    private String name;

    @NotNull(message = "Password cannot be null")
    @ValidPassword(message = "Password must contain at least 8 characters, one uppercase letter, one lowercase letter, one number, and one special character")
    private String password;

    private Date dateOfBirth;

    private String phoneNumber;

    private String activationToken;

    private Integer experience;

    private Status status;

    private String token;

}
