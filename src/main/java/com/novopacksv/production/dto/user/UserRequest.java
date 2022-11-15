package com.novopacksv.production.dto.user;

import com.novopacksv.production.validator.EnumValue;
import lombok.Getter;
import lombok.Setter;
import com.novopacksv.production.model.userModel.Role;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.List;

@Getter
@Setter
public class UserRequest {

    @NotBlank(message = "username is a required field!")
    @Size(max = 30, message = "username must be less then 30 symbols long!")
    private String username;

    @NotBlank(message = "first name is a required field!")
    @Size(max = 30, message = "first name must be less then 30 symbols long!")
    private String firstName;

    @NotBlank(message = "last name is a required field!")
    @Size(max = 30, message = "last name must be less then 30 symbols long!")
    private String lastName;

    @NotEmpty(message = "roles is a required field!")
    private List<@EnumValue(value = Role.class, message = "role is not found!") String> roles;

}