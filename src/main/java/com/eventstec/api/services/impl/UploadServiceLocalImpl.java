package com.eventstec.api.services.impl;

import com.eventstec.api.services.UploadService;
import com.eventstec.api.utils.AWSUtils;
import com.eventstec.api.utils.EventSecUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.UUID;

@Service
public class UploadServiceLocalImpl implements UploadService {
    @Value("${image.storage.path}")
    private String storagePath;
    @Override
    public String uploadImage(MultipartFile multipartFile) {
        String filename = UUID.randomUUID() + "-" + multipartFile.getOriginalFilename();

        try {
            String pathDestinationFile = storagePath + "/" + filename;
            File file = EventSecUtils.convertMultipartToFile(multipartFile);
            return EventSecUtils.copyFile(file, pathDestinationFile);
        }catch(Exception err){
            System.out.println("Erro ao subir arquivo");
            return null;
        }
    }
}
