package com.eventstec.api.services;

import org.springframework.web.multipart.MultipartFile;

public interface UploadService {
    /**
     * Upload image.
     *
     * @param multipartFile the multipart file
     * @return the path string
     */
    String uploadImage(MultipartFile multipartFile);
}
