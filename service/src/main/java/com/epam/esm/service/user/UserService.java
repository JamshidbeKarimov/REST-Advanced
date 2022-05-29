package com.epam.esm.service.user;

import com.epam.esm.dto.BaseResponse;
import com.epam.esm.dto.reponse.UserGetResponse;
import com.epam.esm.dto.request.UserPostRequest;
import com.epam.esm.exception.user.InvalidUserException;
import com.epam.esm.service.base.BaseService;

import java.util.List;

public interface UserService extends BaseService<UserPostRequest, UserGetResponse> {
    @Override
    default void validator(UserPostRequest userPostRequest){
        if(userPostRequest.getUsername() == null)
            throw new InvalidUserException("username cannot be empty or null");
        if(userPostRequest.getPassword() == null)
            throw new InvalidUserException("username cannot be empty or null");
        if(userPostRequest.getAge() == null)
            throw new InvalidUserException("age cannot be 0 or negative");
    }

    List<UserGetResponse> getAll(int limit, int offset);
}
