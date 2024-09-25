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
public class UploadServiceS3Impl implements UploadService {
    @Value("${aws.bucket.name}")
    private String bucketName;
    @Override
    public String uploadImage(MultipartFile multipartFile) {
        String filename = UUID.randomUUID() + "-" + multipartFile.getOriginalFilename();

        try {
            File file = EventSecUtils.convertMultipartToFile(multipartFile);
            return AWSUtils.sendFileBucketS3(bucketName, filename, file);
        }catch(Exception err){
            System.out.println("Erro ao subir arquivo");
            return null;
        }
    }
}
