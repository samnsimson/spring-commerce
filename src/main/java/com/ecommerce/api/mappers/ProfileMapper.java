package com.ecommerce.api.mappers;

import com.ecommerce.api.profile.ProfileInputDto;
import com.ecommerce.api.profile.ProfileModel;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class ProfileMapper implements Mapper<ProfileModel, ProfileInputDto>{
    private final ModelMapper modelMapper;

    public ProfileMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    @Override
    public ProfileInputDto mapTo(ProfileModel profileModel) {
        return this.modelMapper.map(profileModel, ProfileInputDto.class);
    }

    @Override
    public ProfileModel mapFrom(ProfileInputDto profileInputDto) {
        return this.modelMapper.map(profileInputDto, ProfileModel.class);
    }
}
