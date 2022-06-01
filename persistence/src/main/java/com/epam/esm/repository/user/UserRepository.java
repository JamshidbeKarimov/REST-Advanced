package com.epam.esm.repository.user;

import com.epam.esm.entity.UserEntity;
import com.epam.esm.repository.CrudRepository;

import java.util.Optional;

public interface UserRepository extends CrudRepository<UserEntity, Long>, UserQueries{

    Optional<UserEntity> findByName(String name);
}
