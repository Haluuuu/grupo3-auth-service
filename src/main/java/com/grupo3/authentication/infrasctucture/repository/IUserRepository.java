package com.grupo3.authentication.infrasctucture.repository;

import com.grupo3.authentication.infrasctucture.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


public interface IUserRepository extends JpaRepository<UserEntity, Integer> {

    Optional<UserEntity> findByUsername(String username);
}
