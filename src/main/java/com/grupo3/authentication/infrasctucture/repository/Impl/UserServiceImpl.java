package com.grupo3.authentication.infrasctucture.repository.Impl;

import com.grupo3.authentication.application.ports.out.IUserServiceOutPort;
import com.grupo3.authentication.domain.models.User;
import com.grupo3.authentication.domain.schemas.RegisterForm;
import com.grupo3.authentication.infrasctucture.entity.UserEntity;
import com.grupo3.authentication.infrasctucture.repository.IUserRepository;

import java.util.Optional;

public class UserServiceImpl implements IUserServiceOutPort {
    private final IUserRepository userRepository;

    public UserServiceImpl(IUserRepository userRepository) {
        this.userRepository = userRepository;
    }


    @Override
    public User registerUser(RegisterForm form) {

        UserEntity userEntity =  new UserEntity();
        userEntity.setUsername(form.getUsername());
        userEntity.setPassword(form.getPassword());
        userEntity.setEmail(form.getEmail());
        userEntity.setName(form.getName());
        userEntity.setLastName(form.getLastName());
        userRepository.save(userEntity);

        User user = new User();
        user.setUsername(userEntity.getUsername());
        user.setPassword(userEntity.getPassword());
        user.setEmail(userEntity.getEmail());
        user.setName(userEntity.getName());
        user.setLastName(userEntity.getLastName());

        return user;
    }

    @Override
    public Optional<User> getUser(String username) {

        User user=new User();
        Optional<UserEntity> userEntity = userRepository.findByUsername(username);
        if(userEntity.isEmpty()){
            return Optional.empty();
        }
        user.setId(userEntity.get().getId());
        user.setUsername(userEntity.get().getUsername());
        user.setEmail(userEntity.get().getEmail());
        user.setName(userEntity.get().getName());
        user.setLastName(userEntity.get().getLastName());
        user.setPassword(userEntity.get().getPassword());

        return Optional.of(user);
    }
}
