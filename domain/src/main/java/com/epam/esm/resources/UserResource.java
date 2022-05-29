package com.epam.esm.resources;


import com.epam.esm.dto.reponse.UserGetResponse;
import lombok.Getter;
import org.springframework.hateoas.RepresentationModel;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;

@Getter
public class UserResource extends RepresentationModel<UserResource> {
    private final UserGetResponse user;
    public UserResource(final UserGetResponse user) {
        this.user = user;
    }

}
