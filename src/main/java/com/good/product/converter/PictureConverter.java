package com.good.product.converter;

import com.good.product.dto.PictureDto;
import com.good.product.entity.Picture;
import org.springframework.stereotype.Component;

@Component
public class PictureConverter {

    public PictureDto convertToDto(Picture picture) {
        PictureDto pictureDto = new PictureDto();
        pictureDto.setPriority(picture.getPriority());
        pictureDto.setUrl(picture.getUrl());
        return pictureDto;
    }

    public Picture convertToEntity(PictureDto pictureDto) {
        Picture picture = new Picture();
        picture.setPriority(pictureDto.getPriority());
        picture.setUrl(pictureDto.getUrl());
        return picture;
    }
}

