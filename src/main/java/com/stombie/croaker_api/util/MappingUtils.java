package com.stombie.croaker_api.util;

import com.stombie.croaker_api.entity.Image;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

public class MappingUtils {
    private static final String RESOURCE_IMAGES_RELATIVE_PATH = "/images";

    public static String getResourceImageRelativePath(String filename) {
        if (filename == null) {
            return null;
        }
        return RESOURCE_IMAGES_RELATIVE_PATH + '/' + filename;
    }

    public static String getImageLink(String filename) {
        if (filename == null) {
            return null;
        }
        return ServletUriComponentsBuilder.fromCurrentContextPath().toUriString() + getResourceImageRelativePath(filename);
    }

    public static String getLink(Image image) {
        if (image == null) {
            return null;
        }
        return getImageLink(image.getFilename());
    }
}
