package io.javabrains.springsecurityjwt.repository;

import io.javabrains.springsecurityjwt.models.MyUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<MyUser, Long> {
    Optional<MyUser> findMyUserByName(String name);
}
