package ch.lu.mygym.loginservice;

import ch.lu.mygym.dtos.entities.UserEntity;
import ch.lu.mygym.dtos.plain.UserDTO;

public class UserConverter {

    public UserEntity convertUserDTOToEntity(UserDTO userDTO) {

        UserEntity userEntity = new UserEntity();
        userEntity.setEmail(userDTO.getEmail());
        userEntity.setFirstname(userDTO.getName());
        userEntity.setLastname(userDTO.getName());
        userEntity.setPhotourl(userDTO.getPictureUrl());
        userEntity.setRemoteid(userDTO.getUid());

        return userEntity;
    }

    public UserDTO convertUserEntityToDTO(UserEntity userEntity) {
        UserDTO userDTO = new UserDTO();
        userDTO.setId(userEntity.getUserid());
        userDTO.setEmail(userEntity.getEmail());
        userDTO.setName(userEntity.getFirstname());

        return userDTO;
    }
}
