package com.novopacksv.production.service.user;

import com.novopacksv.production.model.userModel.User;
import com.novopacksv.production.service.BaseEntityService;

public interface UserService extends BaseEntityService<User> {

     User findByUsername(String username);

}
