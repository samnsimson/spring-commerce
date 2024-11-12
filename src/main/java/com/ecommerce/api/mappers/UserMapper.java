package com.ecommerce.api.mappers;

import com.ecommerce.api.user.dto.UserInputDto;
import com.ecommerce.api.user.UserModel;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class UserMapper implements Mapper<UserModel, UserInputDto>{
    private final ModelMapper modelMapper;

    public UserMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    @Override
    public UserInputDto mapTo(UserModel userModel) {
        return this.modelMapper.map(userModel, UserInputDto.class);
    }

    @Override
    public UserModel mapFrom(UserInputDto userInputDto) {
        return this.modelMapper.map(userInputDto, UserModel.class);
    }
}
