package com.stombie.croaker_api.controller;

import com.stombie.croaker_api.models.Message;
import com.stombie.croaker_api.util.MappingUtils;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.InputStream;

@RestController
@RequestMapping("/images")
public class ImagesController {
    @GetMapping(
        value = "/{filename}",
        produces = MediaType.APPLICATION_OCTET_STREAM_VALUE
    )
    public ResponseEntity<?> index(@PathVariable String filename) {
        InputStream imageStream = getClass().getResourceAsStream(MappingUtils.getResourceImageRelativePath(filename));
        if (imageStream == null) {
            return ResponseEntity.notFound().build();
        }
        InputStreamResource imageStreamResource = new InputStreamResource(imageStream);
        if (filename.endsWith("png")) {
            return ResponseEntity.ok()
                    .contentType(MediaType.IMAGE_PNG)
                    .body(imageStreamResource);
        } else if (filename.endsWith("jpg") || filename.endsWith("jpeg")) {
            return ResponseEntity.ok()
                    .contentType(MediaType.IMAGE_JPEG)
                    .body(imageStreamResource);
        }
        return ResponseEntity.status(HttpStatus.UNSUPPORTED_MEDIA_TYPE)
                .body(Message.of("Image can have only such extensions as 'png', 'jpg' or 'jpeg'"));
    }
}
