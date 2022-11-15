package com.novopacksv.production.dto.user;

import com.novopacksv.production.dto.BaseEntityResponse;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class UserResponse extends BaseEntityResponse {

    private String username;

    private String firstName;

    private String lastName;

    private List<String> roles;

}
