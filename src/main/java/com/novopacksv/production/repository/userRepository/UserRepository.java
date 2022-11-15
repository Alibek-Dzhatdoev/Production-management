package com.novopacksv.production.repository.userRepository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.novopacksv.production.model.userModel.User;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository <User, Long> {

    Optional<User> findByUsername(String username);
}
