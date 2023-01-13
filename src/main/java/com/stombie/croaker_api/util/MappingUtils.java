package com.stombie.croaker_api.util;

import com.stombie.croaker_api.entity.Croak;
import com.stombie.croaker_api.entity.Image;
import com.stombie.croaker_api.models.CroakGetDto;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

public class MappingUtils {
    public static String getLink(String filename) {
        return ServletUriComponentsBuilder.fromCurrentContextPath().toUriString() + '/' + filename;
    }

    public static String getLink(Image image) {
        if (image == null) {
            return null;
        }
        return getLink(image.getFilename());
    }

    public static CroakGetDto mapToDto(Croak croak) {
        return new CroakGetDto(croak);
    }
}
