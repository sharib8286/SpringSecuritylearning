package com.example.RegistrationLoginDemo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User,Integer> {
    User findByUserName(String userName);

    void deleteByName(String userName);

    Optional<User> findByEmail(String email);
}
