package com.novopacksv.production.converter.user;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import com.novopacksv.production.dto.user.UserResponse;
import com.novopacksv.production.model.userModel.User;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class UserToUserResponseConverter implements Converter<User, UserResponse> {

    @Override
    public UserResponse convert(User source) {
        List<String> roles = source.getRoles()
                .stream()
                .map(Enum::name)
                .collect(Collectors.toList());
        UserResponse result = new UserResponse();
        result.setId(source.getId());
        result.setUsername(source.getUsername());
        result.setFirstName(source.getFirstName());
        result.setLastName(source.getLastName());
        result.setRoles(roles);
        return result;
    }

}