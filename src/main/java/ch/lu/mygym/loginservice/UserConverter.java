package ch.lu.mygym.loginservice;

import ch.lu.mygym.dtos.entities.UserEntity;
import ch.lu.mygym.dtos.plain.ExerciseDTO;
import ch.lu.mygym.dtos.plain.ExerciseGroupDTO;
import ch.lu.mygym.dtos.plain.UserDTO;

import java.util.List;
import java.util.stream.Collectors;

public class UserConverter {

    public static UserEntity convertUserDTOToEntity(UserDTO userDTO) {

        UserEntity userEntity = new UserEntity();
        userEntity.setEmail(userDTO.getEmail());
        userEntity.setFirstname(userDTO.getName());
        userEntity.setLastname(userDTO.getName());
        userEntity.setPhotourl(userDTO.getPictureUrl());
        userEntity.setRemoteid(userDTO.getUid());

        return userEntity;
    }

    public static UserDTO convertUserEntityToDTO(UserEntity userEntity) {

        return null;
    }


}
