package com.job.tracking.controller.assembler;

import com.job.tracking.controller.UserController;
import com.job.tracking.controller.dto.UserDto;
import com.job.tracking.model.UserModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class UserAssembler extends RepresentationModelAssemblerSupport<UserDto, UserModel> {
    private static final String GET_REL = "get_user";
    private static final String CREATE_REL = "create_user";
    private static final String UPDATE_USER = "update_user";
    private static final String DELETE_USER = "delete_user";


    public UserAssembler() {
        super(UserController.class, UserModel.class);
    }

    @Override
    public UserModel toModel(UserDto entity) {
        UserModel userModel = new UserModel(entity);

        Link get = linkTo(methodOn(UserController.class).getUser(entity.getEmail())).withRel(GET_REL);
        Link create = linkTo(methodOn(UserController.class).createUser(entity)).withRel(CREATE_REL);
        Link update = linkTo(methodOn(UserController.class).updateUser(entity)).
                withRel(UPDATE_USER);
        Link delete = linkTo(methodOn(UserController.class).deleteUser(entity.getEmail())).withRel(DELETE_USER);
        userModel.add(get, update, create, delete);
        return userModel;
    }
}
