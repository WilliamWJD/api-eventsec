package com.eventstec.api.utils;

import com.amazonaws.services.s3.AmazonS3;
import org.springframework.stereotype.Component;

import java.io.File;

public class AWSUtils {
    private static AmazonS3 s3Client;
    public AWSUtils(AmazonS3 s3Client){
        AWSUtils.s3Client = s3Client;
    }

    /**
     * Send file bucket s3.
     *
     * @param bucketName the bucket name
     * @param filename   the filename
     * @param file       the file
     * @return the string with path
     */
    public static String sendFileBucketS3(String bucketName, String filename, File file){
        s3Client.putObject(bucketName, filename, file);
        file.delete();
        return s3Client.getUrl(bucketName, filename).toString();
    }
}
